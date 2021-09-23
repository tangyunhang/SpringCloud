package com.abc.service;

import com.abc.bean.Depart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("fallback/consumer/depart")
public class DepartFallback implements DepartService{
    @Override
    public boolean saveDepart(Depart depart) {
        System.out.println("开始执行saveDepart()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean removeDepartById(Integer id) {
        System.out.println("开始执行removeDepartById()的服务降级处理方法");
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        System.out.println("开始执行modifyDepart()的服务降级处理方法");
        return false;
    }

    @Override
    public Depart getDepartById(Integer id) {
        System.out.println("开始执行getDepartById()的服务降级处理方法");
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no depart method class!");
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        System.out.println("开始执行listAllDeparts()的服务降级处理方法");
        return null;
    }
}
