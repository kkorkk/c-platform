package com.cp.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.admin.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.admin.vo.PageVO;
import com.cp.admin.vo.ResultVO;

import java.util.List;

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

    /**
     * 保存
     * @author songzk
     * @date 2020/4/12 13:38
     * @param
     * @return
     **/
    ResultVO saveOrUpdateDict(SysDict sysDict);

    /**
     * 删除
     * @author songzk
     * @date 2020/4/12 13:51
     * @param
     * @return
     **/
    ResultVO deleteDict(Integer id);

    /**
     * 批量删除
     * @author songzk
     * @date 2020/4/12 14:06
     * @param idList id列表
     * @return
     **/
    ResultVO deleteDictBatch(List<Integer> idList);

    /**
     * 获取
     * @author songzk
     * @date 2020/4/12 14:14
     * @param
     * @return
     **/
    ResultVO getDict(Integer id);

}
