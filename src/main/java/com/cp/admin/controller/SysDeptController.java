package com.cp.admin.controller;


import com.cp.admin.dto.TreeNodeDTO;
import com.cp.admin.entity.SysDept;
import com.cp.admin.service.ISysDeptService;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
@Controller
@RequestMapping("/sys-dept")
public class SysDeptController {

    @Autowired
    ISysDeptService sysDeptService;

    @RequestMapping("index")
    public String index(){
        return "basic/sys_dept";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultVO save(@RequestBody SysDept sysDept){
        try {
            boolean saveDept = sysDeptService.saveDept(sysDept);
        } catch (Exception e) {
            return ResultVO.error(e.getMessage());
        }
        return ResultVO.success();
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResultVO delete(@PathVariable("id")Long id){
        try {
            sysDeptService.deleteDept(id);
        } catch (Exception e) {
            return ResultVO.error(e.getMessage());
        }
        return ResultVO.success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultVO update(@RequestBody SysDept sysDept){
        boolean b = sysDeptService.updateDept(sysDept);
        return ResultVO.success();
    }

    @GetMapping("get/{id}")
    @ResponseBody
    public ResultVO get(@PathVariable("id")Long id){
        SysDept dept = sysDeptService.getDept(id);
        return ResultVO.success(dept);
    }

    @PostMapping("page")
    @ResponseBody
    public PageVO page(Integer offset, Integer limit){
        PageVO<SysDept> page = sysDeptService.page(offset, limit);
        return page;
    }

    @GetMapping("list")
    @ResponseBody
    public List<SysDept> list(){
        List<SysDept> list = sysDeptService.list(null);
        return list;
    }

    @GetMapping("tree")
    @ResponseBody
    public ResultVO tree(){
        List<TreeNodeDTO> tree = sysDeptService.tree();
        return ResultVO.success(tree);
    }

}

