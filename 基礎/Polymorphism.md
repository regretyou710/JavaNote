# 多態性的理解:
可以理解為一個事物的多種形態。

# 何為多態性:
對象的多態性:父類的引用指向子類的對象(或子類的對象賦給父類的引用)  
舉例:
```
Person p = new Man();
Object obj = new Date();
```

# 多態性的使用:虛擬方法調用
* 有了對象的多態性以後，我們在編譯期，只能調用父類中聲明的方法，但在運行期，我們實際執行的是子類重寫父類的方法。
* 總結:編譯，看左邊；運行，看右邊。

# 多態性的使用前提:
1. 類的繼承關系
2. 方法的重寫

# 多態性的應用舉例:
舉例一:
```
	public void func(Animal animal){//Animal animal = new Dog();
		animal.eat();
		animal.shout();
	}
```
舉例二:
```
public void method(Object obj){
		
	}
```
舉例三:
```
class Driver{	
	public void doData(Connection conn){//conn = new MySQlConnection(); / conn = new OracleConnection();
		//規範的步驟去操作數據
//		conn.method1();
//		conn.method2();
//		conn.method3();
		
	}	
}
```

# 多態性使用的注意點:
對象的多態性，只適用於方法，不適用於屬性(編譯和運行都看左邊)

# 關於向上轉型與向下轉型:
* 向上轉型:多態
* 向下轉型:
  1. 為什麽使用向下轉型:  
	  有了對象的多態性以後，內存中實際上是加載了子類特有的屬性和方法的，但是由於變量聲明為父類類型，導致編譯時，只能調用父類中聲明的屬性和方法。子類特有的屬性和方法不能調用。如何才能調用子類特的屬性和方法?使用向下轉型。
  2. 如何實現向下轉型:  
	 使用強制類型轉換符:()
  3. 使用時的注意點:
     * 使用強轉時，可能出現ClassCastException的異常。
     * 為了避免在向下轉型時出現ClassCastException的異常，我們在向下轉型之前，先進行instanceof的判斷，一旦返回true，就進行向下轉型。如果返回false，不進行向下轉型。
  4. instanceof的使用:
     * a instanceof A:判斷對象a是否是類A的實例。如果是，返回true；如果不是，返回false。
     * 如果 a instanceof A返回true,則 a instanceof B也返回true.其中，類B是類A的父類。
     * 要求a所屬的類與類A必須是子類和父類的關系，否則編譯錯誤。

```
		//問題一：編譯時通過，運行時不通過
		//舉例一：
//		Person p3 = new Woman();
//		Man m3 = (Man)p3;
		//舉例二：
//		Person p4 = new Person();
//		Man m4 = (Man)p4;

		
		//問題二：編譯通過，運行時也通過
//		Object obj = new Woman();
//		Person p = (Person)obj;
		
		//問題三：編譯不通過
//		Man m5 = new Woman();
		
//		String str = new Date();
		
//		Object o = new Date();
//		String str1 = (String)o;
		
```
	 
# 談談你對多態性的理解?
1. 實現代碼的通用性。
2. Object類中定義的public boolean equals(Object obj){  }  
   JDBC:使用java程序操作(獲取數據庫連接、CRUD)數據庫(MySQL、Oracle、DB2、SQL Server)
3. 抽象類、接口的使用肯定體現了多態性。(抽象類、接口不能實例化)

# 多態是編譯時行為還是運行時行為?
運行時行為

# 多態練習
```
package com.atguigu.exer;

//考查多態的筆試題目:
public class InterviewTest1 {

	public static void main(String[] args) {
		Base1 base = new Sub1();
		base.add(1, 2, 3);

		Sub1 s = (Sub1)base;
		s.add(1,2,3);//確定調用的(形參確定)的優先調用
	}
}

class Base1 {
	public void add(int a, int... arr) {
		System.out.println("base1");
	}
}

class Sub1 extends Base1 {

	public void add(int a, int[] arr) {
		System.out.println("sub_1");
	}

	public void add(int a, int b, int c) {
		System.out.println("sub_2");
	}

}
```
	
```
package com.atguigu.exer;
/*
 * 練習：
 * 1.若子類重寫了父類方法，就意味著子類里定義的方法徹底覆蓋了父類里的同名方法，
 * 系統將不可能把父類里的方法轉移到子類中：編譯看左邊，運行看右邊
 * 
 * 2.對於實例變量則不存在這樣的現象，即使子類里定義了與父類完全相同的實例變量，
 * 這個實例變量依然不可能覆蓋父類中定義的實例變量：編譯運行都看左邊
 * 
 * 
 * 
 * 
 */
class Base {
	int count = 10;

	public void display() {
		System.out.println(this.count);
	}
}

class Sub extends Base {
	int count = 20;

	public void display() {
		System.out.println(this.count);
	}
}

public class FieldMethodTest {
	public static void main(String[] args) {
		Sub s = new Sub();
		System.out.println(s.count);//20
		s.display();//20
		
		Base b = s;//多態性
		//==：對於引用數據類型來講，比較的是兩個引用數據類型變量的地址值是否相同
		System.out.println(b == s);//true
		System.out.println(b.count);//10
		b.display();//20
	}
}
```