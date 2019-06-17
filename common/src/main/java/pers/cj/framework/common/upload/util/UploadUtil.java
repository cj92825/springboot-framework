package pers.cj.framework.common.upload.util;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import pers.cj.framework.common.constant.ExceptionCode;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.upload.config.AliOos;
import pers.cj.framework.common.upload.config.UploadProperties;
import pers.cj.framework.common.util.SpringContextHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description 文件上传工具类
 * @Author chenj
 * @Date 2019/6/14 15:42
 **/
@Slf4j
public class UploadUtil {



    private static UploadProperties uploadProperties= SpringContextHolder.getBean(UploadProperties.class);

    public static Map<String,Object> uploadFile(MultipartFile file) throws CustomException {
        Map<String,Object> result=new HashMap<>(5);
        result.put("fileName",file.getOriginalFilename());
        try {
            if(uploadProperties.getAliOos().isEnable()){
                uploadToAliOss(file);
            }else {
                file.transferTo(new File(uploadProperties.getFilePath() + File.separator + file.getOriginalFilename()));
            }
            result.put("result",true);
        } catch (IOException e) {
            log.error("文件上传异常",e);
            throw new CustomException(ExceptionCode.UPLOAD_ERROR);
        }
        return result;
    }

    private static void uploadToAliOss(MultipartFile file) throws IOException {
        AliOos aliOos=uploadProperties.getAliOos();
        OSSClient ossClient = new OSSClient(aliOos.getEndpoint(),
                aliOos.getAccessKeyId(),
                aliOos.getAccessKeySecret());
        String fileName=aliOos.getPath()+"/"+file.getOriginalFilename();
        ossClient.putObject(aliOos.getBucketName(),fileName,file.getInputStream());
        ossClient.shutdown();
    }


//    public static List<Map<String,Object>> multiUpload(MultipartFile[] files) {
//        List<Map<String,Object>> results=new ArrayList<>();
//        for (MultipartFile file: files) {
//            Map<String,Object>map=null;
//            try {
//                map=uploadFile(file);
//            } catch (CustomException e) {
//            }
//            results.add(map);
//
//        }
//        return results;
//    }
}
