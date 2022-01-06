package com.example.patent.config.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.patent.core.error.BusinessException;
import com.example.patent.core.error.EmBusinessError;
import com.example.patent.pojo.Admin;
import com.example.patent.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zero
 * @date 2022/1/4 21:41
 * @description
 * @since 1.8
 **/
@Component
public class CustomuserDetailService implements UserDetailsService {

    @Autowired
    private IAdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null|| username == "") {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"用户不能为空");
        }
        final Admin name = adminService.getOne(new QueryWrapper<Admin>().eq(username != null, "name", username));
        if(null == name) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"用户不存在");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("user");
        return new User(name.getName(), new BCryptPasswordEncoder().encode(name.getPassword()) , auths);
    }
}
