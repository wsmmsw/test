package com.baicheng.fork.web.util.aop.log;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author SongPengpeng
 * @date 2017/5/11.
 */
public class LogAdvice {

	private static final Logger LOGGER = Logger.getLogger(LogAdvice.class.getName());

	public void before(JoinPoint jp) {
		// TODO: do some biz.
	}

	/**
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		long startTime = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long endTime = System.currentTimeMillis();
		System.out.println("Timer: " + className + "#" + methodName + ": " + (endTime - startTime) + " ms");
		return obj;
	}

	public void afterReturning(Object retVal) throws IOException {
		// TODO: do some biz.
	}

	public void after(JoinPoint jp) {
		// TODO: do some biz.
	}

}
