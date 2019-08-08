package com.weixin.publicnation.controller;

import com.weixin.publicnation.compose.FileComposeService;
import com.weixin.publicnation.response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/wx/file")
public class FileController {

    @Autowired
    private FileComposeService fileCompose;

    @PostMapping(value = "/upload")
    public SimpleResponse<String> fileUpload(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
        try {
            String mediaId = fileCompose.uploadFile(file);
            return SimpleResponse.success(mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SimpleResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"上传文件失败");
    }
}
