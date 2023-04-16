package com.techway.testCreateAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.*;

@Component
@Aspect
public class CurrentUserAspect {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Around("@annotation(CurrentUser)")
    public Object currentUser(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(null, null)
        );
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i].getClass().isAnnotationPresent(CurrentUser.class)) {
                args[i] = authentication.getPrincipal();
            }
        }
        return joinPoint.proceed(args);
    }
}
