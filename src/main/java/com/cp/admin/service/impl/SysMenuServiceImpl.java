package com.cp.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cp.admin.entity.SysMenu;
import com.cp.admin.mapper.SysMenuMapper;
import com.cp.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> listByUserId(Long userId) {
        List<SysMenu> sysMenus = sysMenuMapper.listByUserId(userId);
        return sysMenus;
    }

    @Override
    public Set<String> listPermsByUserId(Long userId) {
        List<String> strings = sysMenuMapper.listPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for(String perms : strings){
            if(StringUtils.isNotBlank(perms)){
                permsSet.addAll(Arrays.asList(perms.split(",")));
            }
        }
        return permsSet;
    }


}
