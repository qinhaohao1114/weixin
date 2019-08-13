package com.weixin.publicnation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.publicnation.bean.entity.SysUser;
import com.weixin.publicnation.dao.SysUserMapper;
import com.weixin.publicnation.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
