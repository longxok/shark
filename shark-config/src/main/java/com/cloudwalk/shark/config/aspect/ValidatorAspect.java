package com.cloudwalk.shark.config.aspect;

import com.cloudwalk.shark.common.constant.BusinessCode;
import com.cloudwalk.shark.config.exception.SharkException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.Iterator;
import java.util.Set;

/**
 * controller 层入参校验切面
 *
 * @Author zetting
 * @Date 2017/11/15 21:50
 **/
@Order(2)
@Component
@Aspect
public class ValidatorAspect {
    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    public ValidatorAspect() {
    }

    /**
     * 方式1：切入点(
     */
    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
                    "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
//    @Pointcut("execution(* com.*..controller.*.*(..))")//方式2
    private void parameterPointCut() {
    }

    /**
     * 处理
     *
     * @param joinPoint
     * @param request
     */
    @Before("parameterPointCut() && args(request,..)")
    public void validateParameter(JoinPoint joinPoint, Object request) {
        Set<ConstraintViolation<Object>> validErrors = this.localValidatorFactoryBean.validate(request, new Class[]{Default.class});
        Iterator iterator = validErrors.iterator();
        StringBuilder errorMsg = new StringBuilder();

        while (iterator.hasNext()) {
            ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();
            String error = constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage();
            errorMsg.append(iterator.hasNext() ? error + "; " : error);
        }
        if (!validErrors.isEmpty()) {
            throw new SharkException(BusinessCode.PARAM_ILLEGAL, errorMsg.toString());
        }
    }
}
