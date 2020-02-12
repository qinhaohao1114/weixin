package com.weixin.publicnation.day01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qinhaohao
 * @Date 2020-02-12 18:15
 * @description 部门 对应部门表
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private Long id;

    private String groupName;

    private Long parentId;
}
