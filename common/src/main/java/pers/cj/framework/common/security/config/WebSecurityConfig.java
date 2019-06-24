package pers.cj.framework.common.security.config;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.util.PathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description Security配置类
 * @Author chenj
 * @Date 2019/6/1 16:13
 * 不用加@Configuration  了，EnableWebSecurity里面包含了
 **/
@EnableWebSecurity
@EnableRedisHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    CustomExpiredSessionStrategy customExpiredSessionStrategy;
    @Autowired
    CustomInvalidSessionStrategy customInvalidSessionStrategy;
    @Autowired
    DataSource dataSource;

//    这个是更改cookie name的
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("framecookie");
//        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        serializer.setSameSite(null);
        serializer.setUseHttpOnlyCookie(false);
        return serializer;
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setMaxAge(3600L);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    /**
     * 默认只有WebExpressionVoter，添加需要的RoleVoter,并将角色名前缀去掉
     * 匹配permitAll就在WebExpressionVoter里
     * @return
     */
    @Bean
    public AccessDecisionManager accessDecisionManager(){
        RoleVoter voter=new RoleVoter();
        voter.setRolePrefix("");
        List<AccessDecisionVoter<? extends Object>> voters= Arrays.asList(new WebExpressionVoter(),voter,new AuthenticatedVoter());
        return new AffirmativeBased(voters);
    }

    /**
     * 自定义资源获取方法，当自己获匹配的结果为空时，返回原来的，否则permitAll会失效
     * @param metadataSource
     * @return
     */
    @Bean
    public FilterInvocationSecurityMetadataSource filterSecuritySource(FilterInvocationSecurityMetadataSource metadataSource){
        return new CustomSecuritySource(metadataSource);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return rawPassword.equals(encodedPassword);
//            }
//        });
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                // 如果有允许匿名的url，填在下面.antMatchers().permitAll()
                .antMatchers("/session/invalid").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login").permitAll()
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
                .and().logout().logoutSuccessHandler(customLogoutSuccessHandler).permitAll()
                // 自动登录
                .and().rememberMe().tokenRepository(persistentTokenRepository()).userDetailsService(userDetailsService)
                .and()
                .sessionManagement().invalidSessionStrategy(customInvalidSessionStrategy).maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(customExpiredSessionStrategy);

          http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
               @Override
               public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                   fsi.setAccessDecisionManager(accessDecisionManager());
                   fsi.setSecurityMetadataSource(filterSecuritySource(fsi.getSecurityMetadataSource()));
                   return fsi;
               }
          });

//        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {
//            @Override
//            public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O object) {
//                return object;
//            }
//        });
//        http.addFilterAt()

        http.csrf().disable();
    }

}
