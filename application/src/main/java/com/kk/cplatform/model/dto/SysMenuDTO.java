package com.kk.cplatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuDTO {

    private Integer id;

    private String menuName;

    private String url;

    private String perms;

    private Integer menuType;

    private String icon;

    private Integer parentId;

    private Integer orderNum;

    private List<SysMenuDTO> subMenuList;

}
