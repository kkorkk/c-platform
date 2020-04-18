package com.cp.admin.config;

import com.cp.admin.constant.SysConstant;
import com.cp.admin.entity.SysUser;
import com.cp.admin.service.ISysMenuService;
import com.cp.admin.service.ISysUserService;
import com.cp.admin.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    ISysMenuService sysMenuService;

    @Autowired
    ISysUserService sysUserService;

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = ShiroUtils.getUserId();
        Set<String> perms = sysMenuService.listPermsByUserId(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();

        //判断用户是否存在
        SysUser sysUser = sysUserService.getByUserName(userName);
        if(Objects.isNull(sysUser)){
            throw new UnknownAccountException("用户不存在");
        }
        //判断账号是否被锁定
        if(Objects.isNull(sysUser.getUserStatus()) || sysUser.getUserStatus() == -1){
            throw new LockedAccountException("账户已经被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        return simpleAuthenticationInfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(SysConstant.SHIRO_HASH_ALGORITHM_NAME);
        shaCredentialsMatcher.setHashIterations(SysConstant.SHIRO_HASH_ITERATIONS);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
