package com.example.patent.controller;

import com.example.patent.core.response.CommonReturnType;
import com.example.patent.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zero
 * @date 2022/1/4 21:31
 * @description
 * @since 1.8
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {
    @Autowired
    private IAdminService adminService;

}
