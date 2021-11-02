package com.abc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;

public class GrayFilter2 extends ZuulFilter {
    private AtomicBoolean flag =new AtomicBoolean();
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (flag.get()) {
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "runnting-mark");
            flag.set(false);
        }else {
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "gray-mark");
            flag.set(true);

        }
        return null;
    }
}
