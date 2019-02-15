package cn.itcast.entity;

public class MyMath {
	// 演示一下除法运算
	public Integer divide(Integer a, Integer b) {
		return a / b;
	}
	
	// 随便写一个方法，演示一下没有返回值时 @AfterReturning 方法会不会执行
	public void hello() {
		System.out.println("hello");
	};
}
