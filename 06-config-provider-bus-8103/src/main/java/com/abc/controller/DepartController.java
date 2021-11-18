package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService departService;
    //声明服务发现客户端
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${suffix}")
    private String suffix;

    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart){
        depart.setName(depart.getName()+suffix);
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean delHandler(@PathVariable("id") Integer id){
        return departService.removeDepartById(id);
    }

    @PutMapping("update")
    public boolean updateHandler(@RequestBody Depart depart){
        return departService.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Depart getHandler(@PathVariable("id") Integer id){
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandler(){
        return departService.listAllDeparts();
    }

    @GetMapping("/discovery")
    public List<String> discoveryHandler(){
        List<String> services = discoveryClient.getServices();
        services.forEach(name->{
            //获取当前遍历微服务名称的所有提供者
            List<ServiceInstance> instances = discoveryClient.getInstances(name);
            instances.forEach(instance->{
                //当前提供者唯一标识
                String instanceId = instance.getInstanceId();
                String serviceId = instance.getServiceId();
                String host = instance.getHost();
                System.out.println("serviceId="+serviceId);
                System.out.println("instanceId="+instanceId);
                System.out.println("host="+host);
            });
        });
        return services;
    }
}
