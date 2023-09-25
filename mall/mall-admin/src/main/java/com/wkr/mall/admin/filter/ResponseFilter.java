package com.wkr.mall.admin.filter;


import com.wkr.admin.common.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**

 * 返回值输出过滤器
 */
@Slf4j
@Component
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) response);
        // 这里只拦截返回，直接让请求过去，如果在请求前有处理，可以在这里处理
        filterChain.doFilter(request, wrapperResponse);
        byte[] content = wrapperResponse.getContent();
        //判断是否有值
        if (content.length > 0) {
            String str = new String(content, StandardCharsets.UTF_8);
            try {
                HttpServletRequest req = (HttpServletRequest) request;
                str = new ResponseRouter().filter(str, RequestUtil.getUri(req));
                log.info("path={}, request={},response={}", ((HttpServletRequest) request).getRequestURI(), request.getParameterMap(), str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //把返回值输出到客户端
            ServletOutputStream outputStream = response.getOutputStream();
            if (str.length() > 0) {
                outputStream.write(str.getBytes());
                outputStream.flush();
                outputStream.close();
                //最后添加这一句，输出到客户端
                response.flushBuffer();
            }
        }
    }
}
