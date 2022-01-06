package com.example.patent.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Zero
 * @date 2022/1/4 21:27
 * @description
 * @since 1.8
 **/
@Data
@AllArgsConstructor
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;

    private String isDelete;

    private LocalDateTime gmt_create;

    private LocalDateTime gmt_modified;
}
