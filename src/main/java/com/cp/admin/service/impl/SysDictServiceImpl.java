package com.cp.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.admin.entity.SysDict;
import com.cp.admin.mapper.SysDictMapper;
import com.cp.admin.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.admin.vo.PageVO;
import org.springframework.stereotype.Service;

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
        Page pageResult = this.page(page);
        PageVO<SysDict> pageVO = new PageVO<>();
        pageVO.setTotal(pageResult.getTotal());
        pageVO.setRows(pageResult.getRecords());
        return pageVO;
    }


}
