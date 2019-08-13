//package com.weixin.publicnation.service.security;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.weixin.publicnation.bean.entity.SysRole;
//import com.weixin.publicnation.bean.entity.SysUser;
//import com.weixin.publicnation.bean.entity.SysUserRole;
//import com.weixin.publicnation.service.SysRoleService;
//import com.weixin.publicnation.service.SysUserRoleService;
//import com.weixin.publicnation.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service("userDetailsService")
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private SysUserService userService;
//
//    @Autowired
//    private SysRoleService roleService;
//
//    @Autowired
//    private SysUserRoleService userRoleService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        // 从数据库中取出用户信息
//        SysUser user = userService.getOne(new QueryWrapper<>(SysUser.builder().name(username).build()));
//
//        // 判断用户是否存在
//        if(user == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//
//        // 添加权限
//        List<SysUserRole> userRoles = userRoleService.list(new QueryWrapper<>(SysUserRole.builder().userId(user.getId()).build()));
//        for (SysUserRole userRole : userRoles) {
//            SysRole role = roleService.getById(userRole.getRoleId());
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        // 返回UserDetails实现类
//        return new User(user.getName(), user.getPassword(), authorities);
//    }
//}
