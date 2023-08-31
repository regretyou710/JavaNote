```
public class SubClassTest {
	
	public static void main(String[] args) {
		SubClass s = new SubClass();
		
//		s.method1();
//		SubClass.method1();
		//知識點1：接口中定義的靜態方法，只能通過接口來調用。
		CompareA.method1();
		//知識點2：通過實現類的對象，可以調用接口中的默認方法。
		//如果實現類重寫了接口中的默認方法，調用時，仍然調用的是重寫以後的方法
		s.method2();
		//知識點3：如果子類(或實現類)繼承的父類和實現的接口中聲明了同名同參數的默認方法，
		//那麽子類在沒有重寫此方法的情況下，默認調用的是父類中的同名同參數的方法。-->類優先原則
		//知識點4：如果實現類實現了多個接口，而這多個接口中定義了同名同參數的默認方法，
		//那麽在實現類沒有重寫此方法的情況下，報錯。-->接口沖突。
		//這就需要我們必須在實現類中重寫此方法
		s.method3();
		
	}
	
}

class SubClass extends SuperClass implements CompareA,CompareB{
	
	public void method2(){
		System.out.println("SubClass：上海");
	}
	
	public void method3(){
		System.out.println("SubClass：深圳");
	}
	
	//知識點5：如何在子類(或實現類)的方法中調用父類、接口中被重寫的方法
	public void myMethod(){
		method3();//調用自己定義的重寫的方法
		super.method3();//調用的是父類中聲明的
		//調用接口中的默認方法
		CompareA.super.method3();
		CompareB.super.method3();
	}
}
```

```
/*
 * 
 * JDK8：除了定義全局常量和抽象方法之外，還可以定義靜態方法、默認方法
 * 
 */
public interface CompareA {
	
	//靜態方法
	public static void method1(){
		System.out.println("CompareA:北京");
	}
	//默認方法
	public default void method2(){
		System.out.println("CompareA：上海");
	}
	
	default void method3(){
		System.out.println("CompareA：上海");
	}
}
```

```
public interface CompareB {
	
	default void method3(){
		System.out.println("CompareB：上海");
	}
	
}
```

```
public class SuperClass {
	
	public void method3(){
		System.out.println("SuperClass:北京");
	}
	
}

```
