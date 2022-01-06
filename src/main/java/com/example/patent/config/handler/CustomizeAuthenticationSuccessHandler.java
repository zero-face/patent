package com.example.patent.config.handler;

import com.alibaba.fastjson.JSON;
import com.example.patent.core.response.CommonReturnType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zero
 * @Description: 登录成功处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private int expiredTime = 60 * 60 * 24; //默认令牌有效期是24小时

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
//        Map<String,Object> map = new HashMap<>();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        System.out.println(authentication.getDetails());
//        map.put("username", ((User)authentication.getPrincipal()).getUsername());
//        String token = jwtTokenUtil.generateToken(map, ((User)authentication.getPrincipal()).getUsername(), expiredTime * 1000);
        Map<String,Object> returnToken = new HashMap<>();
        returnToken.put("token", "success");
        CommonReturnType result = CommonReturnType.success(returnToken,"登录成功");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}