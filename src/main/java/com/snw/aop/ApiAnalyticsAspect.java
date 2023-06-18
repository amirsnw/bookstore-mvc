package com.snw.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ApiAnalyticsAspect {

	@Before("com.snw.aop.AopExpressions.forDaoPackageModifyOperation()")
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing DAO 'modify' analytics");
	}

}
