package com.example.patent.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 专利表
 * </p>
 *
 * @author zyf
 * @since 2022-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class Patent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private String form;

    private Date publishTime;

    private String type;

    @TableLogic
    private Integer isDelete;


    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
