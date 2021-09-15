package com.abc.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity//使用自动建表
public class Depart {
    @Id//表示当前属性为自动建表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示主键自增
    private Integer id;
    private String name;
}
