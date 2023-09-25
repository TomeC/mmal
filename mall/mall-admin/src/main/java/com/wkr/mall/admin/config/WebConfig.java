package com.wkr.mall.admin.config;

import com.wkr.mall.admin.filter.ResponseFilter;
import com.wkr.admin.common.config.MallConfig;
import com.wkr.admin.common.constants.Constants;
import com.wkr.admin.common.interceptor.SwaggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.annotation.Resource;

/**
 * token验证拦截器

 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private MallConfig mallConfig;
    @Resource
    private ResponseFilter responseFilter;

    @Value("${swagger.basic.username}")
    private String swaggerUsername;
    @Value("${swagger.basic.password}")
    private String swaggerPassword;
    @Value("${swagger.basic.check}")
    private Boolean swaggerCheck;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加token拦截器
        //addPathPatterns添加需要拦截的命名空间；
        //excludePathPatterns添加排除拦截命名空间


//        //后台token拦截
//        registry.addInterceptor(adminTokenInterceptor()).
//                addPathPatterns("/api/admin/**").
//                excludePathPatterns("/api/admin/validate/**").
//                excludePathPatterns("/api/admin/login").
//                excludePathPatterns("/api/admin/logout").
//                excludePathPatterns("/api/admin/getLoginPic").
//                excludePathPatterns("/api/admin/wechat/config").
//                excludePathPatterns("/api/admin/authorize/login").
//                excludePathPatterns("/api/admin/payment/callback/**").
////                excludePathPatterns("/api/admin/system/role/menu").
//                excludePathPatterns("/api/admin/system/role/info").
//                excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//
//        //后台权限规则
//        registry.addInterceptor(adminAuthInterceptor()).
//                addPathPatterns("/api/admin/**").
//                excludePathPatterns("/api/admin/validate/**").
//                excludePathPatterns("/api/admin/login").
//                excludePathPatterns("/api/admin/logout").
//                excludePathPatterns("/api/admin/getLoginPic").
//                excludePathPatterns("/api/admin/payment/callback/**").
//                excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.UPLOAD_TYPE_IMAGE + "/**")
                .addResourceLocations("file:" + mallConfig.getImagePath() + "/" + Constants.UPLOAD_TYPE_IMAGE + "/");
    }

    @Bean
    public FilterRegistrationBean filterRegister() {
        //注册过滤器
        FilterRegistrationBean registration = new FilterRegistrationBean(responseFilter);
        // 仅仅api前缀的请求才会拦截
        registration.addUrlPatterns("/api/admin/*");
        registration.addUrlPatterns("/api/front/*");
        return registration;
    }

    /* 必须在此处配置拦截器,要不然拦不到swagger的静态资源 */
    @Bean
    @ConditionalOnProperty(name = "swagger.basic.enable", havingValue = "true")
    public MappedInterceptor getMappedInterceptor() {
        return new MappedInterceptor(new String[]{"/doc.html", "/webjars/**"}, new SwaggerInterceptor(swaggerUsername, swaggerPassword, swaggerCheck));
    }
}
