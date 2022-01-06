package com.example.patent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.patent.mapper.AdminMapper;
import com.example.patent.mapper.PatentMapper;
import com.example.patent.pojo.Admin;
import com.example.patent.pojo.Patent;
import com.example.patent.service.IAdminService;
import org.springframework.stereotype.Service;

/**
 * @author Zero
 * @date 2022/1/4 21:27
 * @description
 * @since 1.8
 **/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService{
}
