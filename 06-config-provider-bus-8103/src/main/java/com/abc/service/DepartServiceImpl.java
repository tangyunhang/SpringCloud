package com.abc.service;

import com.abc.bean.Depart;
import com.abc.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class DepartServiceImpl implements DepartService{
    @Autowired
    DepartRepository departRepository;

    @Value("${server.port}")
    private int port;
    @Override
    public boolean saveDepart(Depart depart) {
        //对于save（）参数，根据id的不同，分为三种情况
        //depart的id为null，save()执行插入操作
        //depart的id不为null，且DB中存在id，save()执行的是修改操作
        //depart的id不为null，但DB中不存在id，save()执行插入操作，但其插入后记录的id值并不是这里指定的id，是根据指定id生成策略所生成的id
        Depart obj = departRepository.save(depart);
        return obj!=null?true:false;
    }

    @Override
    public boolean removeDepartById(Integer id) {
        if(departRepository.existsById(id)){
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        Depart obj = departRepository.save(depart);
        return obj!=null?true:false;
    }

    @Override
    public Depart getDepartById(int id) {
//        对于提供者的调用设置超时时限配置验证
//        try {
//            TimeUnit.SECONDS.sleep(id);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Depart depart = new Depart();
        if(departRepository.existsById(id)){
            Depart dep = departRepository.getOne(id);
            depart.setId(dep.getId());
            depart.setName(dep.getName()+port);
        }
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        List<Depart> departList = departRepository.findAll();
            //名称添加端口号
         departList.forEach(depart -> {depart.setName(depart.getName()+port);});
        return departList;
    }
}
