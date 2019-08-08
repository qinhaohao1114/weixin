package com.weixin.publicnation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.publicnation.bean.entity.FileInfo;
import com.weixin.publicnation.dao.FileInfoMapper;
import com.weixin.publicnation.service.FileInfoService;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
}
