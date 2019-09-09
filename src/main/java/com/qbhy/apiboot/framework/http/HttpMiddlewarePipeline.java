package com.qbhy.apiboot.framework.http;

import com.qbhy.apiboot.app.exceptions.Handler;
import com.qbhy.apiboot.framework.kernel.pipeline.BasePipeline;
import com.qbhy.apiboot.framework.util.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;

public class HttpMiddlewarePipeline extends BasePipeline {
    @Override
    protected Object handleException(Object passable, Throwable e) throws Throwable {
        return SpringContextUtil.getBean(Handler.class).handle((HttpServletRequest) passable, e);
    }
}
