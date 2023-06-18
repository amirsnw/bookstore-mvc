package com.snw.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

	// create pointcut for dao package
	@Pointcut("execution(* com.snw.dao.*.*(..))")
	public void forDaoPackage() {}
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.snw.dao.*.get*(..))")
	public void getter() {}

	@Pointcut("execution(* com.snw.dao.*.save(..))")
	public void saver() {}

	@Pointcut("execution(* com.snw.dao.*.delete(..))")
	public void deleter() {}

	// create pointcut: include package ... exclude getter ... include save/delete
	@Pointcut("forDaoPackage() && !getter() && (saver() || deleter())")
	public void forDaoPackageModifyOperation() {}

}
