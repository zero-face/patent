package com.example.patent.mapper;

import com.example.patent.pojo.Patent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 专利表 Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2022-01-04
 */
@Mapper
public interface PatentMapper extends BaseMapper<Patent> {

}
