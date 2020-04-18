package com.cp.admin.mapper;

import com.cp.admin.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查找用户的菜单
     * @author kkorkk
     * @date 2020/4/18 9:33
     * @param
     * @return
     **/
    List<SysMenu> listByUserId(Long userId);

    /**
     * 查找用户的权限
     * @author kkorkk
     * @date 2020/4/18 9:39
     * @param
     * @return
     **/
    List<String> listPermsByUserId(Long userId);
}
