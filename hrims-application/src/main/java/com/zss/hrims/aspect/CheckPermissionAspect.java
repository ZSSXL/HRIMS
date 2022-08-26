package com.zss.hrims.aspect;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.response.ResponseCode;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZSS
 * @date 2022/7/28 17:36
 * @desc 权限身份拦截校验
 */
@Slf4j
@Aspect
@Component
public class CheckPermissionAspect {

    /**
     * within 用于匹配，持有指定注解的，类型内的“所有方法”
     */
    @Around("@within(checkPermission)")
    public Object doAroundForClass(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        return doAround(joinPoint, checkPermission);
    }

    /**
     * annotation于匹配，持有指定注解的，方法
     */
    @Around("@annotation(checkPermission)")
    public Object doAroundForMethod(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        return doAround(joinPoint, checkPermission);
    }

    private Object doAround(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        HttpServletRequest request = currentRequest();
        if (request != null) {
            String token = request.getHeader(HrimsConstants.HRIMS_TOKEN);
            if (TokenUtil.isValid(token)) {
                int roleNum;
                if (checkPermission.admin()) {
                    roleNum = HrimsConstants.Role.ADMIN;
                } else {
                    roleNum = HrimsConstants.Role.ORDINARY;
                }
                log.info("CurrentRole: [{}]", roleNum == 1 ? "admin" : "ordinary");
                String role = TokenUtil.getClaim(token, "role").asString();
                if (Integer.valueOf(role).equals(roleNum)) {
                    return joinPoint.proceed();
                } else {
                    return ServerResponse.error(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
                }
            } else {
                return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(), "Token已过期，请重新登录");
            }
        } else {
            log.error("权限校验拦截失败");
            return ServerResponse.error("权限校验拦截失败，请重新发送请求");
        }
    }

    /**
     * 获取当前请求
     *
     * @return request
     */
    private HttpServletRequest currentRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            return sra.getRequest();
        } else {
            return null;
        }
    }
}
