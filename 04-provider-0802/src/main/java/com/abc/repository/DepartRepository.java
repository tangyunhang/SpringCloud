package com.abc.repository;

import com.abc.bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
//第一个范型，当前Repository所操作对象的类型
//第二个范型，当前Repository所操作对象的id类型
public interface DepartRepository extends JpaRepository<Depart,Integer> {

}
