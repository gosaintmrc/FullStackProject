package com.gosaint.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ApiMonitorAspect {

    @Around(
        "@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
        "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
        "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
        "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
        "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
        "@annotation(org.springframework.web.bind.annotation.PatchMapping)"      // 可选：PATCH 请求
    )
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String method = request.getMethod(); // 获取 HTTP 方法：GET, POST, PUT, DELETE 等
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;
        if(duration>=50L){
            log.info("***********************API 调用情况*******************: {} {} | IP: {} | 耗时: {}ms", method, uri, ip, duration);
        }

        return result;
    }
}