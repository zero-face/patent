package com.example.patent.service.impl;

import com.example.patent.pojo.Patent;
import com.example.patent.mapper.PatentMapper;
import com.example.patent.service.IPatentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专利表 服务实现类
 * </p>
 *
 * @author zyf
 * @since 2022-01-04
 */
@Service
public class PatentServiceImpl extends ServiceImpl<PatentMapper, Patent> implements IPatentService {

}
