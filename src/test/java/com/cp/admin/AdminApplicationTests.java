package com.cp.admin;

import com.cp.admin.entity.SysDict;
import com.cp.admin.service.ISysDictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class AdminApplicationTests {

    @Autowired
    ISysDictService sysDictService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSysDictService(){
        SysDict sysDict = sysDictService.getById(14);
        sysDict.setName(null);
        sysDict.setType(null);
        sysDict.setOrderNum(10000);
        boolean b = sysDictService.updateById(sysDict);
        System.out.println(b);
    }

}
