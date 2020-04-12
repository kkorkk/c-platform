package com.cp.admin.controller;


import com.cp.admin.entity.SysDict;
import com.cp.admin.param.IdList;
import com.cp.admin.service.ISysDictService;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.ResultVO;
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

    @GetMapping("get/{id}")
    @ResponseBody
    public ResultVO get(@PathVariable("id")Integer id){
        ResultVO dict = sysDictService.getDict(id);
        return dict;
    }

    @PostMapping("save")
    @ResponseBody
    public ResultVO save(@RequestBody SysDict sysDict){
        ResultVO resultVO = sysDictService.saveOrUpdateDict(sysDict);
        return resultVO;
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResultVO delete(@PathVariable("id")Integer id){
        ResultVO resultVO = sysDictService.deleteDict(id);
        return resultVO;
    }

    @PostMapping("deleteBatch")
    @ResponseBody
    public ResultVO deleteBatch(@RequestBody IdList idList){
        ResultVO resultVO = sysDictService.deleteDictBatch(idList.getIdList());
        return resultVO;
    }

}

