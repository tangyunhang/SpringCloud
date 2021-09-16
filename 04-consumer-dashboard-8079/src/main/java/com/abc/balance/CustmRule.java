package com.abc.balance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 从所有可用的 provider 中排除掉指定端口号的 provider，剩
 * 余 provider 进行随机选择。
 */
public class CustmRule implements IRule {
    private ILoadBalancer lb;
    //要排除的端口号
    private List<Integer> excludePorts;

    public CustmRule(){

    }
    public CustmRule(List<Integer> excludePorts){
            this.excludePorts=excludePorts;
    }

    @Override
    public Server choose(Object o) {
        //获取所有可用(UP)的server
        List<Server> servers = lb.getReachableServers();
        //排除指定port的server
        List<Server> availableServers = getAvailableServers(servers);
        //从剩余server中随机server
        return getAvailableRandomServers(availableServers);
    }

    //从剩余server中随机server
    private Server getAvailableRandomServers(List<Server> servers) {
        //获取[0,server.size()]随机数
        int index = new Random().nextInt(servers.size());
        return servers.get(index);
    }

    //从所有server中排除指定Port的server
    private List<Server> getAvailableServers(List<Server> servers) {
        //若没有指定excludePorts则返回全部server
        if(excludePorts == null || excludePorts.size()==0){
            return servers;
        }
//        List<Server> aservers = new ArrayList<>();
//        servers.forEach(server -> {
//            if (!excludePorts.contains(server.getPort())){
//                aservers.add(server);
//            }
//        });
        //filter过滤
        //noneMatch用于判断stream中元素是否全部不满足，只要一个满足就false
        List<Server> aservers = servers.stream().filter(server -> excludePorts.stream().noneMatch(port ->
                server.getPort() == port)).collect(Collectors.toList());
        return aservers;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb=iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
