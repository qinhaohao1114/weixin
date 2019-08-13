package com.weixin.publicnation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.publicnation.bean.entity.SysRole;
import com.weixin.publicnation.dao.SysRoleMapper;
import com.weixin.publicnation.service.SysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
