package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import com.netflix.client.http.HttpRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("consumer/depart")
public class SomeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private ForkJoinPool pool = new ForkJoinPool(5);
    @Autowired
    private DepartService service;
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
//        向管理员发警报
        String ip = request.getLocalAddr();
        String key = ip + "getHystrixHandler";
        alarm(key);
        Depart depart = new Depart();
        depart.setId(3);
        depart.setName("no depart method!");
        return depart;
    }
//    降级发生后的警报
    private void alarm(String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        String value = ops.get();
        if (value == null){
            synchronized (this){
                value = ops.get();
                if (value == null){
                    //像管理员发送报警发送
                    sendMsg(key);
                    ops.set("已发生服务降级",10, TimeUnit.SECONDS);
                }
            }
        }

    }
//    发送短信
    private void sendMsg(String key) {
        pool.submit(()->{
            System.out.println("发送服务降级警报"+key);
        });
    }

    @GetMapping("/list")
    public List<Depart> listHandler(){
        return service.listAllDeparts();
    }
}
