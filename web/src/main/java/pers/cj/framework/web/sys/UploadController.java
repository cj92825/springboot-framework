package pers.cj.framework.web.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.cj.framework.common.ResponseUtil;
import pers.cj.framework.common.exception.CustomException;
import pers.cj.framework.common.upload.util.UploadUtil;


/**
 * @Description 文件上传控制器
 * @Author chenj
 * @Date 2019/6/14 16:03
 **/
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Object upload(MultipartFile file) throws CustomException {
        UploadUtil.uploadFile(file);
        return ResponseUtil.success();
    }
//    @PostMapping("/multiUpload")
//    public Object multiUpload(MultipartFile []file) throws CustomException {
//        UploadUtil.multiUpload(file);
//        return "test";
//    }
}
