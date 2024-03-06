package com.hubs.ing.assignment.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class RestControllerAspect {
	private final ObjectMapper mapper = new ObjectMapper();

	@Pointcut("within(com.hubs.ing.assignment.rest.api.controller.*)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void logBeforeMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mappingOfController = signature.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class);
		RequestMapping mappingOfMethod = signature.getMethod().getAnnotation(RequestMapping.class);

		String controllerName = signature.getMethod().getDeclaringClass().getName();
		String methodName = signature.getMethod().getName();

		var parameters = getParameters(joinPoint);

		try {
			log.info("Before => rootPath: {}, methodPath: {}, method: {}, methodType: {}, class: {}, arguments: {} ",
					mappingOfController.value(), mappingOfMethod.path(), methodName, mappingOfMethod.method(), controllerName, mapper.writeValueAsString(parameters)
			);
		} catch (JsonProcessingException e) {
			log.warn("Error while converting", e);
		}
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		var signature = (MethodSignature) joinPoint.getSignature();
		var parameterNames = signature.getParameterNames();
		var parameters = new HashMap<String, Object>();
		for (int i = 0; i < parameterNames.length; i++) {
			parameters.put(parameterNames[i], joinPoint.getArgs()[i]);
		}
		return parameters;
	}

	@AfterReturning(pointcut = "pointcut()", returning = "entity")
	public void logAfterReturningMethod(JoinPoint joinPoint, ResponseEntity<?> entity) {
		var signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mappingOfController = signature.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class);
		RequestMapping mappingOfMethod = signature.getMethod().getAnnotation(RequestMapping.class);

		String controllerName = signature.getMethod().getDeclaringClass().getName();
		String methodName = signature.getMethod().getName();

		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
			log.info("After returning => rootPath: {}, methodPath: {}, method: {}, methodType: {}, class: {}, arguments: {} ",
					mappingOfController.value(), mappingOfMethod.value(), methodName, mappingOfMethod.method(), controllerName, json
			);
		} catch (JsonProcessingException e) {
			log.warn("Error while converting", e);
		}
	}
}
