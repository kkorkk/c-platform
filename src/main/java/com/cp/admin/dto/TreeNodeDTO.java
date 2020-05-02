package com.cp.admin.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * LayUI树形节点
 * */
@Data
@Builder
public class TreeNodeDTO {

    private Long id;
    private String text;
    private List<TreeNodeDTO> children;


}
