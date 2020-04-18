package com.cp.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cp.admin.constant.SysConstant;
import com.cp.admin.entity.SysUser;
import com.cp.admin.mapper.SysUserMapper;
import com.cp.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.admin.util.ShiroUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean saveUser(SysUser sysUser) throws Exception {
        SysUser byUserName = this.getByUserName(sysUser.getUserName());
        if(!Objects.isNull(byUserName)){
            throw new Exception("用户名已存在");
        }
        String salt = RandomUtil.randomString(5);
        sysUser.setSalt(salt);
        String pwd = new SimpleHash(SysConstant.SHIRO_HASH_ALGORITHM_NAME, sysUser.getPassword(), salt, SysConstant.SHIRO_HASH_ITERATIONS).toString();
        sysUser.setPassword(pwd);
        sysUser.setCreateTime(LocalDateTime.now());
        boolean save = this.save(sysUser);
        return save;
    }

    @Override
    public boolean updateUser(SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());
        boolean b = this.updateById(sysUser);
        return b;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        boolean b = this.removeById(userId);
        return b;
    }

    @Override
    public SysUser login(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>()
                .eq("user_name", sysUser.getUserName())
                .eq("password", sysUser.getPassword());
        SysUser one = this.getOne(queryWrapper);
        return one;
    }

    @Override
    public SysUser getByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>()
                .eq("user_name", userName);
        SysUser sysUser = this.getOne(queryWrapper);
        return sysUser;
    }
}
