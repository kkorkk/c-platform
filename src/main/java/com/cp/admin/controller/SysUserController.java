package com.cp.admin.controller;


import com.cp.admin.entity.SysUser;
import com.cp.admin.service.ISysUserService;
import com.cp.admin.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-17
 */
@Controller
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @PostMapping("save")
    @ResponseBody
    public ResultVO save(@RequestBody SysUser sysUser){
        try {
            sysUserService.saveUser(sysUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResultVO.success();
    }

}

