package com.abc.balance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ZuulRule implements IRule {
    private ILoadBalancer lb;
    //要排除的端口号
    private List<Integer> excludePorts;

    public ZuulRule(){

    }
    public ZuulRule(List<Integer> excludePorts){
        this.excludePorts = excludePorts;
    }

    @Override
    public Server choose(Object o) {
        //获取所有可用(UP)的server
        List<Server> reachableServers = lb.getReachableServers();
        //排除指定port的server
        List<Server> availableServers = getAvailableServers(reachableServers);
        //从剩余server中随机server
        return getAvailableRandomServers(availableServers);
    }

    private Server getAvailableRandomServers(List<Server> availableServers) {
        //获取[0,server.size()]随机数
        int index = new Random().nextInt(availableServers.size());
        return availableServers.get(index);
    }

    private List<Server> getAvailableServers(List<Server> reachableServers) {
        //若没有指定excludePorts则返回全部server
        if(excludePorts == null || excludePorts.size()==0){
            return reachableServers;
        }
        return reachableServers.stream().filter(server -> excludePorts.stream().noneMatch(port->
            server.getPort() == port)).collect(Collectors.toList());
    }

    @Override
    public void setLoadBalancer(ILoadBalancer loadBalancer) {
        this.lb = loadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
