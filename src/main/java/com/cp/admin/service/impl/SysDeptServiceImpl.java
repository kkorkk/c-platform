package com.cp.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.admin.constant.SysConstant;
import com.cp.admin.dto.TreeNodeDTO;
import com.cp.admin.entity.SysDept;
import com.cp.admin.mapper.SysDeptMapper;
import com.cp.admin.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.admin.util.ShiroUtils;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.SysDeptVO;
import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public boolean saveDept(SysDept sysDept) throws Exception {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("name", sysDept.getName());
        List<SysDept> depts = this.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(depts)){
            throw new Exception("部门名称已存在");
        }
        sysDept.setDelFlag(SysConstant.DELETE_FLAG_FALSE);
        sysDept.setCreateUser(ShiroUtils.getUserName());
        sysDept.setCreateTime(LocalDateTime.now());
        boolean save = this.save(sysDept);
        return save;
    }

    @Override
    public boolean deleteDept(Long deptId) throws Exception {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("dept_id", deptId);
        SysDept sysDept = this.getOne(queryWrapper);
        if(Objects.isNull(sysDept)){
           throw new Exception("指定的记录不存在");
        }
        sysDept.setDelFlag(SysConstant.DELETE_FLAG_TRUE);
        sysDept.setUpdateUser(ShiroUtils.getUserName());
        sysDept.setUpdateTime(LocalDateTime.now());
        boolean b = this.updateById(sysDept);
        return b;
    }

    @Override
    public boolean updateDept(SysDept sysDept) throws Exception {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("name", sysDept.getName());
        List<SysDept> depts = this.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(depts)){
            throw new Exception("部门名称已存在");
        }
        sysDept.setUpdateUser(ShiroUtils.getUserName());
        sysDept.setUpdateTime(LocalDateTime.now());
        return this.updateById(sysDept);
    }

    @Override
    public SysDeptVO getDept(Long deptId) {
        SysDept sysDept = this.getById(deptId);
        SysDept parentDept = this.getById(sysDept.getParentId());
        SysDeptVO vo = new SysDeptVO();
        BeanUtils.copyProperties(sysDept, vo);
        vo.setParentName(parentDept==null?"顶级节点":parentDept.getName());
        return vo;
    }

    @Override
    public PageVO<SysDept> page(Integer offset, Integer limit) {
        Page queryPage = new Page(offset/limit+1, limit);
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("del_flag", 0);
        Page page = this.page(queryPage, queryWrapper);

        PageVO<SysDept> pageVO = new PageVO<>();
        pageVO.setTotal(page.getTotal());
        pageVO.setRows(page.getRecords());
        return pageVO;
    }

    @Override
    public List<Long> getDeptSubIdList(Long parId) {
        List<SysDept> deptSubList = getDeptSubList(parId);
        List<Long> idList = deptSubList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        return idList;
    }

    @Override
    public List<SysDept> getDeptSubList(Long parId) {
        List<SysDept> sysDeptList = listByParId(parId);
        List<SysDept> subDeptList = Lists.newArrayList();
        buildDeptList(sysDeptList, sysDeptList);
        return sysDeptList;
    }

    @Override
    public List<TreeNodeDTO> tree() {
        TreeNodeDTO treeNodeDTO = TreeNodeDTO.builder().id(0L)
                .text("顶级节点")
                .children(null)
                .build();
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("del_flag", 0);
        List<SysDept> deptList = this.list(queryWrapper);
        buildTree(treeNodeDTO, deptList);
        return Lists.newArrayList(treeNodeDTO);
    }

    private void buildTree(TreeNodeDTO treeNodeDTO, List<SysDept> deptList) {
        for(SysDept sysDept : deptList){
            if(treeNodeDTO.getId().equals(sysDept.getParentId())){
                List<TreeNodeDTO> children = treeNodeDTO.getChildren();
                if(null == children){
                    children = new ArrayList<>();
                    treeNodeDTO.setChildren(children);
                }
                TreeNodeDTO childrenNode = TreeNodeDTO.builder().id(sysDept.getDeptId())
                        .text(sysDept.getName())
                        .children(null)
                        .build();
                children.add(childrenNode);

                buildTree(childrenNode, deptList);
            }
        }
    }

    public void buildDeptList(List<SysDept> subDeptList, List<SysDept> resultDeptList){
        for(SysDept sysDept : subDeptList){
            List<SysDept> sysDepts = listByParId(sysDept.getDeptId());
            if(CollectionUtils.isNotEmpty(sysDepts)){
                buildDeptList(sysDepts, resultDeptList);
            }
            resultDeptList.addAll(sysDepts);
        }
    }

    public List<SysDept> listByParId(Long parId){
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .eq("parent_id", parId)
                .eq("del_flag", SysConstant.DELETE_FLAG_FALSE)
                .orderByAsc("order_num");
        return this.list(queryWrapper);
    }

}
