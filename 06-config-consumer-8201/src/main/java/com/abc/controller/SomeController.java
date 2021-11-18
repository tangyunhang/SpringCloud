package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("consumer/depart")
public class SomeController {
    @Autowired
    private DepartService service;

    @Value("${server.port}")
    private Integer port;
    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart){
        return service.saveDepart(depart);
    }
    @DeleteMapping("/del/{id}")
    public boolean delHandler(@PathVariable("id") Integer id){
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandler(@RequestBody Depart depart){
        return service.modifyDepart(depart);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandler")
    @GetMapping("/get/{id}")
    @ResponseBody
    public Depart getHandler(@PathVariable("id") Integer id, HttpServletRequest request){
        return service.getDepartById(id);
    }

    //定义服务降级方法，响应给客户端的备选方案
    public Depart getHystrixHandler(@PathVariable("id") Integer id, HttpServletRequest request){
        System.out.println("token="+request.getHeader("token"));
        System.out.println("Set-Cookie="+request.getHeader("Set-Cookie"));
        Depart depart = new Depart();
        depart.setId(3);
        depart.setName("no depart method " + port);
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> listHandler(){
        return service.listAllDeparts();
    }
}
