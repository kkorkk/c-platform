package com.cp.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.admin.constant.SysConstant;
import com.cp.admin.entity.SysDict;
import com.cp.admin.mapper.SysDictMapper;
import com.cp.admin.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-11
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Override
    public PageVO<SysDict> page(Integer offset, Integer limit) {
        Integer pageIndex = offset/limit+1;
        Page page = new Page(pageIndex, limit);
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<SysDict>()
                .eq("del_flag", 0);
        Page pageResult = this.page(page, queryWrapper);
        PageVO<SysDict> pageVO = new PageVO<>();
        pageVO.setTotal(pageResult.getTotal());
        pageVO.setRows(pageResult.getRecords());
        return pageVO;
    }

    @Override
    public ResultVO saveOrUpdateDict(SysDict sysDict) {
        boolean opResult = false;
        if(Objects.isNull(sysDict.getId()) || sysDict.getId()==0){
            sysDict.setCreateTime(new Date());
            //todo 设置操作用户
            //sysDict.setCreateUser();
            opResult = this.save(sysDict);
        }else{
            //todo 设置操作用户
            //sysDict.setUpdateUser();
            sysDict.setUpdateTime(new Date());
            opResult = this.updateById(sysDict);
        }
        if(opResult){
            return ResultVO.success();
        }else{
            return ResultVO.error();
        }
    }

    @Override
    public ResultVO deleteDict(Integer id) {
        SysDict sysDict = this.getById(id);
        sysDict.setDelFlag(SysConstant.DELETE_FLAG_TRUE);
        boolean b = this.updateById(sysDict);
        return b?ResultVO.success():ResultVO.error();
    }

    @Override
    public ResultVO deleteDictBatch(List<Integer> idList) {
        List<SysDict> sysDicts = this.listByIds(idList);
        sysDicts.stream().forEach(sysDict -> sysDict.setDelFlag(SysConstant.DELETE_FLAG_TRUE));
        boolean b = this.updateBatchById(sysDicts);
        return b?ResultVO.success():ResultVO.error();
    }

    @Override
    public ResultVO getDict(Integer id) {
        SysDict byId = this.getById(id);
        return ResultVO.success(byId);
    }


}
