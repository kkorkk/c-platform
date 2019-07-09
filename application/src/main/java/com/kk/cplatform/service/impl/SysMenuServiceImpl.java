package com.kk.cplatform.service.impl;

import com.kk.cplatform.mapper.SysMenuMapper;
import com.kk.cplatform.model.dto.SysMenuDTO;
import com.kk.cplatform.model.entity.SysMenu;
import com.kk.cplatform.service.SysMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SysMenuDTO> menuTree() {
        //获取所有一级菜单
        List<SysMenu> levelOneMenuList = sysMenuMapper.selectByParent(0);
        List<SysMenuDTO> dtoList = this.convert2DTOList(levelOneMenuList);
        //获取二级菜单
        dtoList.stream().forEach(sysMenuDTO -> {
            List<SysMenu> subMenuList = sysMenuMapper.selectByParent(sysMenuDTO.getId());
            List<SysMenuDTO> subDtoList = this.convert2DTOList(subMenuList);
            sysMenuDTO.setSubMenuList(subDtoList);
        });
        return dtoList;
    }

    public List<SysMenuDTO> convert2DTOList(List<SysMenu> sysMenuList){
        List<SysMenuDTO> dtoList = new ArrayList<>();
        sysMenuList.stream().forEach(sysMenu -> {
            SysMenuDTO sysMenuDTO = new SysMenuDTO();
            BeanUtils.copyProperties(sysMenu,sysMenuDTO);
            dtoList.add(sysMenuDTO);
        });
        return dtoList;
    }

}
