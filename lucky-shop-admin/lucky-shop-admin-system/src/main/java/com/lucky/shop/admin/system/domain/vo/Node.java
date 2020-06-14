package com.lucky.shop.admin.system.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/14 21:39
 */
@Data
public class Node {
    private Long id;
    private Long pid;
    private String name;
    private Boolean checked;
    private List<Node> children = new ArrayList<>(10);

}
