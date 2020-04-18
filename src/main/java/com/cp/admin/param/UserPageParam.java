package com.cp.admin.param;

import lombok.Data;

@Data
public class UserPageParam {

    private Integer offset;
    private Integer limit;
    private Long deptId;
    private String userName;

}
