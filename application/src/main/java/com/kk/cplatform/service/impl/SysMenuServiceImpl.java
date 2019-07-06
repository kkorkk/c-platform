package com.kk.cplatform.service.impl;

import com.kk.cplatform.mapper.SysMenuMapper;
import com.kk.cplatform.model.entity.SysMenu;
import com.kk.cplatform.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public boolean save(SysMenu sysMenu) {
        int i = sysMenuMapper.insertSelective(sysMenu);
        return i==1;
    }

    @Override
    public boolean delete(Integer id) {
        int i = sysMenuMapper.deleteByPrimaryKey(id);
        return i==1;
    }

    @Override
    public boolean update(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return i==1;
    }

    @Override
    public SysMenu getById(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }
}
