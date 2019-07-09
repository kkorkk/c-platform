package com.kk.cplatform.controller;

import com.kk.cplatform.model.dto.SysMenuDTO;
import com.kk.cplatform.model.vo.ResultVO;
import com.kk.cplatform.service.SysMenuService;
import com.kk.cplatform.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sys_menu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("menu_tree")
    public ResultVO menuTree(){
        List<SysMenuDTO> menuList = sysMenuService.menuTree();
        return ResultVOUtil.success(menuList);
    }

}
