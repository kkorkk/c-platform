package com.cp.admin.vo;

import com.cp.admin.entity.SysDept;
import lombok.Data;

@Data
public class SysDeptVO extends SysDept {

    private String parentName;

}
