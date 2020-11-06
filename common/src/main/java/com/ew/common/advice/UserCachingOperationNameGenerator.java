package com.ew.common.advice;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 过滤启动日志Generating unique operation named
 * @author Mr`Huang
 * @Date 2020-11-6 18:57:36
 */
@Component
@Aspect
public class UserCachingOperationNameGenerator {


  private Map<String, Integer> generated = newHashMap();


  @Pointcut("execution(* springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator.startingWith(String))")
  public void startingWith() {}


  @Around("startingWith()")
  public Object showLog(ProceedingJoinPoint point) {
    Object[] args = point.getArgs();
    String prefix = String.valueOf(args[0]);
    if (generated.containsKey(prefix)) {
        generated.put(prefix, generated.get(prefix) + 1);
        String nextUniqueOperationName = String.format("%s_%s", prefix, generated.get(prefix));
//        log.warn("组件中存在相同的方法名称，自动生成组件方法唯一名称进行替换: {}", nextUniqueOperationName);
        return nextUniqueOperationName;
      } else {
        generated.put(prefix, 0);
        return prefix;
      }
  }

}


