package com.weixin.publicnation.compose;

import com.weixin.publicnation.bean.entity.FileInfo;
import com.weixin.publicnation.service.FileInfoService;
import com.weixin.publicnation.service.TokenService;
import com.weixin.publicnation.service.UploadService;
import com.weixin.publicnation.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class FileComposeService {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private TokenService tokenService;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        log.info(multipartFile.getContentType());
        String mediaId = uploadService.upload(multipartFile,tokenService.getAccess_Token(), FileUtils.castFileType(multipartFile.getContentType()));
        if (StringUtils.isEmpty(mediaId)){
            throw new RuntimeException("文件上传失败");
        }
        FileInfo fileInfo=new FileInfo();
        fileInfo.setContentType(multipartFile.getContentType());
        fileInfo.setFileName(multipartFile.getOriginalFilename());
        fileInfo.setFileSize(multipartFile.getSize());
        fileInfo.setMediaId(mediaId);
        fileInfoService.save(fileInfo);
        return mediaId;
    }
}
