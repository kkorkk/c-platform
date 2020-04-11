package com.cp.admin.controller;


import com.cp.admin.entity.SysDict;
import com.cp.admin.service.ISysDictService;
import com.cp.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-11
 */
@Controller
@RequestMapping("/sys-dict")
public class SysDictController {

    @Autowired
    ISysDictService sysDictService;

    @GetMapping("index")
    public String index(){
        return "basic/sys_dict";
    }

    @PostMapping("page")
    @ResponseBody
    public PageVO page(Integer offset, Integer limit){
        PageVO<SysDict> page = sysDictService.page(offset, limit);
        return page;
    }

}

