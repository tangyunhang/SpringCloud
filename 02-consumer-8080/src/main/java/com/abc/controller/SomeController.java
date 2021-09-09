package com.abc.controller;

import com.abc.bean.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer/depart")
public class SomeController {
    @Autowired
    private RestTemplate restTemplate;
//    直连提供者
//    private static final String SERVICE_PROVIDER="http://localhost:8081";
//    通过微服务名称来从eureka server来查找提供者
private static final String SERVICE_PROVIDER="http://abcmsc-provider-depart";
    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart){
        String url = SERVICE_PROVIDER+"/provider/depart/save";
        return restTemplate.postForObject(url,depart,Boolean.class);
    }
    @DeleteMapping("/del/{id}")
    public void delHandler(@PathVariable("id") Integer id){
        String url = SERVICE_PROVIDER+"/provider/depart/del/"+id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void updateHandler(@RequestBody Depart depart){
        String url = SERVICE_PROVIDER+"/provider/depart/update";
        restTemplate.put(url,depart);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Depart getHandler(@PathVariable("id") Integer id){
        String url = SERVICE_PROVIDER+"/provider/depart/get/"+id;

        return restTemplate.getForObject(url,Depart.class);
    }

    @GetMapping("/list")
    public List<Depart> listHandler(){
        String url = SERVICE_PROVIDER+"/provider/depart/list";
        return restTemplate.getForObject(url,List.class);
    }
    @GetMapping("/discovery")
    public List<String> discoveryHandler(){
        String url = SERVICE_PROVIDER+"/provider/depart/discovery";
        return restTemplate.getForObject(url,List.class);
    }
}
