package com.qbhy.apiboot.framework.http.middlewares.cross;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;
import com.qbhy.apiboot.framework.http.middlewares.HttpMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class CrossMiddleware extends HttpMiddleware {

    private Crosses crosses;

    @Autowired
    public void setCrosses(CrossProvider provider) {
        this.crosses = provider.get();
    }

    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        Cross cross = crosses.get(request.getRemoteHost());
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        if (cross != null && Arrays.asList(cross.getMethods()).contains(method)) { // 判断是否允许跨域
            ResponseEntity response = (ResponseEntity) stack.next(request);
            List<String> withHeaders = Arrays.asList(cross.getWithHeaders());
            HttpHeaders headers = response.getHeaders();
            headers.setAccessControlAllowCredentials(cross.isWithCredentials());
            headers.setAccessControlAllowHeaders(withHeaders);
            headers.setAccessControlAllowOrigin(cross.getHost());
            headers.setAccessControlAllowMethods(Arrays.asList(cross.getMethods()));
            headers.setAccessControlExposeHeaders(withHeaders);
            return response;
        }

        return (ResponseEntity) stack.next(request);
    }
}
