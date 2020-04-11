package com.cp.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.admin.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-11
 */
public interface ISysDictService extends IService<SysDict> {


    /**
     * 分页
     * @author songzk
     * @date 2020/4/11 14:37
     * @param offset 开始下标
     * @param limit 分页大小
     * @return
     **/
    PageVO<SysDict> page(Integer offset, Integer limit);

}
