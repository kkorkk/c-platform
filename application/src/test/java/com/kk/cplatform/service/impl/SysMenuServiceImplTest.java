package com.kk.cplatform.service.impl;

import com.kk.cplatform.model.dto.SysMenuDTO;
import com.kk.cplatform.model.entity.SysMenu;
import com.kk.cplatform.service.SysMenuService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysMenuServiceImplTest {

    @Autowired
    SysMenuService sysMenuService;

    @Test
    public void save() {
        List<SysMenuDTO> sysMenuDTOS = sysMenuService.menuTree();
        System.out.println(sysMenuDTOS);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getById() {
    }
}