package cn.itcast.entity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// 【注意】 这里我们只是为了演示，把aspectj 定义的所有通知类型都给用到了，真实的开发中，我们可能只会用到其中的某些通知而已
@Aspect
public class MyAspect {
	// 所有的通知需要都一个切入点表达式来明确要切入哪个类的哪个方法
	// 如果我们所有方法的切入点表达式都一样的话，那么我们可以使用@Pointcut 注解抽取出来，
	// 然后这个方法名就是切入点表达式的引用或者说 id
	@Pointcut("execution(* cn.itcast.entity.MyMath.* (..))")
	public void pointCut() {};
	
	// 执行目标方法之前执行
	@Before(value="pointCut()")
	public void logStart(JoinPoint joinPoint) {
		System.out.println("@Before： 开始执行" + joinPoint.getSignature().getName() + "方法");
	}
	
	// 执行目标方法的前后都会执行
	@Around(value="pointCut()")
	public Object around(ProceedingJoinPoint joinPoint)throws Throwable {
		// 执行目标方法前可以执行某些操作
		System.out.println("@Around： 前");
		// 因为我们并不想搞什么事情，所以这里直接执行 proceed 方法放行就好了
		// 如果你想搞事情的话，那么这里可以选择不执行原来的方法, 直接执行你想要的某些操作
		System.out.println("@Around： 开始执行目标方法：");
		Object result = joinPoint.proceed(joinPoint.getArgs());
		// 执行目标方法后还可以再执行某些操作
		System.out.println("@Around： 后");
		// 返回目标方法的返回值
		return result;
	}
	
	// 如果目标方法有返回值的话，这个通知可以拿到返回值
	// 【注意】 如果目标方法在运行过程中出异常了，那么将不会执行这个方法，会执行另一个 @AfterThrowing 方法（如果有配置的话）
	// 【注意】 如果目标方法没有返回值？
	@AfterReturning(value="pointCut()", returning="returning")
	public void logReturning(JoinPoint joinPoint, Object returning) {
		System.out.println("@AfterReturning:" + joinPoint.getSignature().getName() + "方法正常执行。返回值是：" + returning);
	}
	
	// @AfterReturning 和 @AfterThrowing 只会执行其中一个
	@AfterThrowing(value="pointCut()", throwing="exception")
	public void logException(JoinPoint joinPoint, Throwable exception) {
		System.out.println("@AfterThrowing: " + joinPoint.getSignature().getName() + "方法发生异常了。异常信息是：" + exception);
	}
	
	// 不管目标方法有没有发生异常，都会执行此方法
	// 这个方法总是最后执行的
	@After(value="pointCut()")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("@After: " + joinPoint.getSignature().getName() + "方法结束，执行一些后续操作....");
	}
}
