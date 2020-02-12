package com.weixin.publicnation.day01;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author qinhaohao
 * @Date 2020-02-12 18:14
 **/
public class test01 {

    /**
     * @Author qinhaohao
     * @Description //1、将list转换成树结构
     * @Date 18:14 2020-02-12
     * @Param [args]
     * @return void
     **/

    public static void main(String[] args) {
        //模拟从数据库查询部门数据
        List<Group> list = listAllGroup();
        //将部门数据转换成dto，利用dto中的children属性构建树结构
        List<GroupDto> groupDtos = list.stream().map(GroupDto::valueOf).collect(Collectors.toList());
        //TODO step1将groupDtos变成树状结构

        //TODO step2尝试写一个通用方法来转换成树状结构

    }

    private static List<Group> listAllGroup(){
        Group group1 = Group.builder().id(1L).groupName("部门1").parentId(0L).build();
        Group group2 = Group.builder().id(2L).groupName("部门2").parentId(1L).build();
        Group group3 = Group.builder().id(3L).groupName("部门3").parentId(2L).build();
        Group group4 = Group.builder().id(4L).groupName("部门4").parentId(3L).build();
        Group group5 = Group.builder().id(5L).groupName("部门5").parentId(4L).build();
        Group group6 = Group.builder().id(6L).groupName("部门6").parentId(5L).build();
        Group group7 = Group.builder().id(7L).groupName("部门7").parentId(6L).build();
        Group group8 = Group.builder().id(8L).groupName("部门8").parentId(7L).build();
        List<Group> list= Lists.newArrayList(group1,group2,group3,group4,group5,group6,group7,group8);
        return list;
    };
}
