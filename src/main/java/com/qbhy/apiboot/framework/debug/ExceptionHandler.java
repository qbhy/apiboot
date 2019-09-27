package com.qbhy.apiboot.framework.debug;

import com.qbhy.apiboot.framework.contracts.debug.HttpExceptionHandler;
import com.qbhy.apiboot.framework.http.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class ExceptionHandler implements HttpExceptionHandler {

    protected List<Class<? extends Throwable>> dontReports = new ArrayList<>();

    protected boolean shouldReport(Throwable throwable) {
        return !dontReports.contains(throwable.getClass());
    }

    abstract public void report(Throwable throwable);


    /**
     * @param request   请求实例
     * @param throwable 异常实例
     * @return 响应实例
     */
    @Override
    public ResponseEntity<?> handle(HttpServletRequest request, Throwable throwable) {
        this.report(throwable);

        HttpStatus status = getStatus(request, throwable);
        Object data = reformat(throwable);
        if (throwable instanceof RenderableException) {
            RenderableException e = (RenderableException) throwable;
            e.setData(data);
            return e.render(request, status).responseEntity();
        }

        return Response.fail(status, throwable.getMessage(), data).responseEntity();
    }

    /**
     * @param request   请求实例
     * @param throwable 异常实例
     * @return 返回标准 http 状态码
     */
    protected HttpStatus getStatus(HttpServletRequest request, Throwable throwable) {
        if (throwable instanceof RenderableException) {
            return ((RenderableException) throwable).getHttpStatus();
        }
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    protected static HashMap<String, Object> reformat(Throwable throwable) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("class", throwable.getClass().getName());
        map.put("message", throwable.getMessage());
        map.put("trace", throwable.getStackTrace());
        return map;
    }
}
