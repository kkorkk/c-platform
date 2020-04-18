package com.cp.admin.service;

import com.cp.admin.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.admin.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkorkk
 * @since 2020-04-18
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 保存部门信息
     * @author kkorkk
     * @date 2020/4/18 13:46
     * @param sysDept 部门信息
     * @return
     **/
    boolean saveDept(SysDept sysDept) throws Exception;

    /**
     * 删除部门信息
     * @author kkorkk
     * @date 2020/4/18 14:04
     * @param
     * @return
     **/
    boolean deleteDept(Long deptId) throws Exception;

    /**
     * 修改部门信息
     * @author kkorkk
     * @date 2020/4/18 14:07
     * @param
     * @return
     **/
    boolean updateDept(SysDept sysDept);

    /**
     * 根据ID获取部门信息
     * @author kkorkk
     * @date 2020/4/18 14:08
     * @param
     * @return
     **/
    SysDept getDept(Long deptId);

    /**
     * 分页查询
     * @author kkorkk
     * @date 2020/4/18 14:16
     * @param
     * @return
     **/
    PageVO<SysDept> page(Integer offset, Integer limit);

    /**
     * 根据父部门ID查找子部门ID列表
     * @author kkorkk
     * @date 2020/4/18 13:47
     * @param
     * @return
     **/
    List<Long> getDeptSubIdList(Long parId);

    /**
     * 根据父部门ID查找子部门列表
     * @author kkorkk
     * @date 2020/4/18 13:58
     * @param
     * @return
     **/
    List<SysDept> getDeptSubList(Long parId);
}
