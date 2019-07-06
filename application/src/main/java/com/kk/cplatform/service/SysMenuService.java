package com.kk.cplatform.service;

import com.kk.cplatform.model.entity.SysMenu;

/**
 * 菜单信息业务处理类
 * */
public interface SysMenuService {

    /**
     * 保存菜单信息
     * @date 2019/7/6 21:59
     * @param sysMenu 菜单
     * @return true：成功  false：失败
     * */
    boolean save(SysMenu sysMenu);

    /**
     * 删除菜单信息
     * @date 2019/7/6 22:00
     * @param id 菜单id
     * @return true: 成功 false:失败
     * */
    boolean delete(Integer id);

    /**
     * 更新菜单信息
     * @date 2019/7/6 22:01
     * @param sysMenu 菜单
     * @return true:成功  false:失败
     * */
    boolean update(SysMenu sysMenu);

    /**
     * 根据菜单ID获取
     * @date 2019/7/6 22:01
     * @param id 菜单ID
     * @return 菜单信息
     * */
    SysMenu getById(Integer id);
    
}
