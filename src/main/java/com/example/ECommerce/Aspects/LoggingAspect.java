package com.example.ECommerce.Aspects;

import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.example.ECommerce.Controllers.*.*(..))")
    public void controllerCall(){}



    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        return userDetails.getId();
    }

    @Before("controllerCall()")
    public void logControllerCalls(JoinPoint joinPoint) {
        {
            System.out.println("Controller" + joinPoint.getSignature() + " called by a  user with id :" + getUserId().toString());
            System.out.println("The controller als has been called with such an arguments: " + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(pointcut = "controllerCall()", returning = "result")
    public void logControllerCalls(JoinPoint joinPoint, Object result) {
        System.out.println("Controller" + joinPoint.getSignature() + " returned " + result);
    }

    @AfterThrowing(pointcut = "controllerCall()", throwing = "exception")
    public void logControllerCalls(JoinPoint joinPoint, Exception exception) {
        System.out.println("Controller" + joinPoint.getSignature() + " threw " + exception);
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceCall(){}

    @AfterReturning(pointcut = "serviceCall()",returning = "result")
    public void logServiceCalls(JoinPoint joinPoint,Object result) {
        {
            System.out.println("Service" + joinPoint.getSignature() + " called by a  user with id :" +
                    getUserId().toString() + "The service als has been called with such an arguments: " + Arrays.toString(joinPoint.getArgs()));
            System.out.println("The result is: " + result);

        }
    }

}

