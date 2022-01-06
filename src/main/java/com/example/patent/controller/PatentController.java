package com.example.patent.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.patent.core.error.BusinessException;
import com.example.patent.core.error.EmBusinessError;
import com.example.patent.core.response.CommonReturnType;
import com.example.patent.pojo.Patent;
import com.example.patent.service.IPatentService;
import org.apache.ibatis.annotations.Delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 专利表 前端控制器
 * </p>
 *
 * @author zyf
 * @since 2022-01-04
 */
@RestController
@RequestMapping("/api/v1/patent")
public class PatentController extends BaseController{

    @Autowired
    private IPatentService patentService;

    @GetMapping("list")
    public CommonReturnType list(@RequestParam("page")Integer page,
                                 @RequestParam("size")Integer size) {
        Page<Patent> pages = new Page<Patent>(page,size);
        final Page<Patent> list = patentService.page(pages, new QueryWrapper<Patent>().eq(true, "is_delete", 0));
        final List<Patent> records = list.getRecords();
        System.out.println(records);
        return CommonReturnType.success(records);
    }

    @PostMapping
    public CommonReturnType add(@RequestParam("name")String name,
                                @RequestParam("author")String author,
                                @RequestParam(value = "form")String form,
                                @RequestParam("publish_time") Date publishTime,
                                @RequestParam("type")String type) {
        final Patent patent = new Patent() {{
            setName(name);
            setAuthor(author);
            setForm(form);
            setPublishTime(publishTime);
            setType(type);
        }};
        final boolean save = patentService.save(patent);
        if(!save) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"增加信息失败");
        }
        return CommonReturnType.success(patent,"增加成功");
    }

    @GetMapping
    public CommonReturnType get(@RequestParam(value = "name", required =true) String name) {
        if(name == null) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"请输入参数");
        }
//        Page<Patent> page = new Page<>()
        List<Patent> list = patentService.list(new QueryWrapper<Patent>().like(true, "name", name).or().like("author", name));
        if(list == null) {
            list = patentService.list(new QueryWrapper<Patent>().eq(true, "name", name));
        }
        if(list == null || list.isEmpty()) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"没有数据");
        }
        return CommonReturnType.success(list);
    }
    @DeleteMapping
    public CommonReturnType deleted(@RequestParam("id")Integer id) {
        final boolean b = patentService.remove(new QueryWrapper<Patent>().eq(id != null, "id", id));
        if(!b) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"删除失败");
        }
        return CommonReturnType.success("删除成功");
    }
    @PutMapping
    public CommonReturnType put(@RequestParam(value = "name",required = false)String name,
                                @RequestParam(value = "author",required = false)String author,
                                @RequestParam(value = "form",required = false)String form,
                                @RequestParam(value = "type",required = false)String type,
                                @RequestParam(value = "publishTime",required = false) Date publisTime,
                                @RequestParam("id")Integer id) {
        final Patent one = patentService.getOne(new QueryWrapper<Patent>().eq(id != null, "id", id));
        final Patent patent = new Patent() {{
            setAuthor(author);
            setName(name);
            setForm(form);
            setPublishTime(publisTime);
            setType(type);
        }};
        System.out.println("genggai de duixiang:" + patent);

        BeanUtil.copyProperties(patent, one,
                true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        System.out.println("最终对象" + one);
        final boolean update = patentService.update(patent, new UpdateWrapper<Patent>().eq("id", id));
        if(!update) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"修改失败");
        }
        return CommonReturnType.success(patent,"修改成功");
    }
}
