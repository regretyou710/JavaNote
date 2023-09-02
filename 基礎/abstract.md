# abstract: 抽象的
   * 可以用來修飾:類、方法
   * 具體的:
     * abstract修飾類:抽象類
       1. 此類不能實例化  
       2. 抽象類中一定有構造器，便於子類實例化時調用(涉及:子類對象實例化的全過程)  
       3. 開發中，都會提供抽象類的子類，讓子類對象實例化，完成相關的操作 --->抽象的使用前提:繼承性  
     * abstract修飾方法:抽象方法
       1. 抽象方法只方法的聲明，沒方法體  
       2. 包含抽象方法的類，一定是一個抽象類。反之，抽象類中可以沒有抽象方法的。  
       3. 若子類重寫了父類中的所的抽象方法後，此子類方可實例化  
       4. 若子類沒重寫父類中的所的抽象方法，則此子類也是一個抽象類，需要使用abstract修飾       
   * 注意點:
     1. abstract不能用來修飾:屬性、構造器等結構
     2. <mark>abstract不能用來修飾私方法、靜態方法、final的方法、final的類</mark>
   * abstract的應用舉例:
     * 舉例一:
     * 舉例二:
     	```
     	abstract class GeometricObject{
     		public abstract double findArea();
     	}

     	class Circle extends GeometricObject{
     		private double radius;

     		public double findArea(){
     				return 3.14 * radius * radius;
     		};
     	}
     	```
     * 舉例三:
         * IO流中設計到的抽象類:InputStream/OutputStream/Reader/Writer。在其內部定義了抽象的read()、write()方法。
   * 模板方法的設計模式
     * 解決的問題:
    	在軟件開發中實現一個算法時，整體步驟很固定、通用，這些步驟已經在父類中寫好了。但是某些部分易變，易變部分可以抽象出來，供不同子類實現。這就是一種模板模式。
     * 舉例
		```
		abstract class Template{
			
			//計算某段代碼執行所需要花費的時間
			public void spendTime(){
				
				long start = System.currentTimeMillis();
				
				this.code();//不確定的部分、易變的部分
				
				long end = System.currentTimeMillis();
				
				System.out.println("花費的時間為:" + (end - start));
				
			}
			
			public abstract void code();
			
		}

		class SubTemplate extends Template{

			@Override
			public void code() {
				
				for(int i = 2;i <= 1000;i++){
					boolean isFlag = true;
					for(int j = 2;j <= Math.sqrt(i);j++){
						
						if(i % j == 0){
							isFlag = false;
							break;
						}
					}
					if(isFlag){
						System.out.println(i);
					}
				}

			}	
		}
		```

# 創建抽象類的匿名子類對象
```
package com.atguigu.java;
/*
 * 抽象類的匿名子類
 * 
 */
public class PersonTest {
	
	public static void main(String[] args) {
		
		method(new Student());//匿名對象
		
		Worker worker = new Worker();
		method1(worker);//非匿名的類非匿名的對象
		
		method1(new Worker());//非匿名的類匿名的對象
		
		System.out.println("********************");
		
		//創建了一匿名子類的對象:p
		Person p = new Person(){

			@Override
			public void eat() {
				System.out.println("吃東西");
			}

			@Override
			public void breath() {
				System.out.println("好好呼吸");
			}
			
		};
		
		method1(p);
		System.out.println("********************");
		//創建匿名子類的匿名對象
		method1(new Person(){
			@Override
			public void eat() {
				System.out.println("吃好吃東西");
			}

			@Override
			public void breath() {
				System.out.println("好好呼吸新鮮空氣");
			}
		});
	}
	
	
	public static void method1(Person p){
		p.eat();
		p.breath();
	}
	
	public static void method(Student s){
		
	}
}

class Worker extends Person{

	@Override
	public void eat() {
	}

	@Override
	public void breath() {
	}
	
}

```
	
# abstract 能修飾哪些結構? 修飾以後，有什麽特點?
1. 類、方法。
2. 類不能實例化，提供子類抽象方法，只定義了一種功能的標準。具體的執行，需要子類去實現。

# 聲明抽象類，並包含抽象方法。測試類中創建一個繼承抽象類的匿名子類的對象
```
abstract AA{
	public abstract void m();
	}

	main(){
		AA a = new AA(){
			public void m(){}
		};
		
	a.m();
}

class Person{
	String name;

	public void eat(){}
}

main(){
	Person p = new Person(){
		public void eat(){}
	};

	p.eat();
}
```
	
