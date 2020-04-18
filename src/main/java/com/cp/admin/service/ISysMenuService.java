package com.cp.admin.service;

import com.cp.admin.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查找用户的菜单
     * @author kkorkk
     * @date 2020/4/18 9:41
     * @param userId 用户ID
     * @return
     **/
    List<SysMenu> listByUserId(Long userId);

    /**
     * 查找用户的权限
     * @author kkorkk
     * @date 2020/4/18 9:45
     * @param userId 用户ID
     * @return
     **/
    Set<String> listPermsByUserId(Long userId);

}
