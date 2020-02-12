package com.weixin.publicnation.day01;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Author qinhaohao
 * @Date 2020-02-12 18:16
 **/
@Data
public class GroupDto {

    private Long id;

    private String groupName;

    private Long parentId;

    private List<GroupDto> childrens;

    public static GroupDto valueOf(Group group){
        GroupDto groupDto = new GroupDto();
        BeanUtils.copyProperties(group,groupDto);
        return groupDto;
    }
}
