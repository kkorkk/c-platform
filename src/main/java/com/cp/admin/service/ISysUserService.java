package com.cp.admin.service;

import com.cp.admin.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.admin.param.UserPageParam;
import com.cp.admin.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-17
 */
public interface ISysUserService extends IService<SysUser> {

    boolean saveUser(SysUser sysUser) throws Exception;

    boolean updateUser(SysUser sysUser);

    boolean deleteUser(Integer userId);

    SysUser login(SysUser sysUser);

    SysUser getByUserName(String userName);

    PageVO page(UserPageParam userPageParam);

}
