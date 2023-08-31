# 創建接口匿名實現類的對象
```
package com.atguigu.java1;
/*
 * 接口的使用
 * 1.接口使用上也滿足多態性
 * 2.接口，實際上就是定義了一種規範
 * 3.開發中，體會面向接口編程！
 * 
 */
public class USBTest {
	public static void main(String[] args) {
		
		Computer com = new Computer();
		//1.創建了接口的非匿名實現類的非匿名對象
		Flash flash = new Flash();
		com.transferData(flash);
		
		//2. 創建了接口的非匿名實現類的匿名對象
		com.transferData(new Printer());
		
		//3. 創建了接口的匿名實現類的非匿名對象
		USB phone = new USB(){

			@Override
			public void start() {
				System.out.println("手機開始工作");
			}

			@Override
			public void stop() {
				System.out.println("手機結束工作");
			}
			
		};
		com.transferData(phone);
		
		
		//4. 創建了接口的匿名實現類的匿名對象
		
		com.transferData(new USB(){
			@Override
			public void start() {
				System.out.println("mp3開始工作");
			}

			@Override
			public void stop() {
				System.out.println("mp3結束工作");
			}
		});
	}
}

class Computer{
	
	public void transferData(USB usb){//USB usb = new Flash();
		usb.start();
		
		System.out.println("具體傳輸數據的細節");
		
		usb.stop();
	}
	
	
}

interface USB{
	//常量:定義了長、寬、最大最小的傳輸速度等
	
	void start();
	
	void stop();	
}

class Flash implements USB{

	@Override
	public void start() {
		System.out.println("U盤開啟工作");
	}

	@Override
	public void stop() {
		System.out.println("U盤結束工作");
	}
	
}

class Printer implements USB{
	@Override
	public void start() {
		System.out.println("打印機開啟工作");
	}

	@Override
	public void stop() {
		System.out.println("打印機結束工作");
	}
	
}
```

# interface:接口
   * 使用說明:
     * 接口使用interface來定義
     * Java中，接口和類是並列的兩個結構
     * 如何定義接口:定義接口中的成員
        1. JDK7及以前:只能定義全局常量和抽象方法
           * 全局常量:public static final的.但是書寫時，可以省略不寫
           * 抽象方法:public abstract的  			
        2. JDK8:除了定義全局常量和抽象方法之外，還可以定義靜態方法、默認方法(略  
     * 接口中不能定義構造器的！意味著接口不可以實例化 
     * Java開發中，接口通過讓類去實現(implements)的方式來使用.
        * 如果實現類覆蓋了接口中的所抽象方法，則此實現類就可以實例化
        * 如果實現類沒覆蓋接口中所的抽象方法，則此實現類仍為一個抽象類    
     * Java類可以實現多個接口 ---> 彌補了Java單繼承性的局限性
       * 格式:class AA extends BB implements CC,DD,EE
     * 接口與接口之間可以繼承，而且可以多繼承
     * 接口的具體使用:體現多態性
     * 接口，實際上可以看做是一種規範
   * 舉例:
		```
		class Computer{
			
			public void transferData(USB usb){//USB usb = new Flash();
				usb.start();
				
				System.out.println("具體傳輸數據的細節");
				
				usb.stop();
			}	
		}

		interface USB{
			//常量:定義了長、寬、最大最小的傳輸速度等
			
			void start();
			
			void stop();
			
		}

		class Flash implements USB{

			@Override
			public void start() {
				System.out.println("U盤開啟工作");
			}

			@Override
			public void stop() {
				System.out.println("U盤結束工作");
			}
			
		}

		class Printer implements USB{
			@Override
			public void start() {
				System.out.println("打印機開啟工作");
			}

			@Override
			public void stop() {
				System.out.println("打印機結束工作");
			}
			
		}
		```   
   * 代理模式
     * 解決的問題  
		代理模式是Java開發中使用較多的一種設計模式。代理設計就是為其他對象提供一種代理以控制對這個對象的訪問。 
     * 舉例
		```
		interface NetWork{
			
			public void browse();
			
		}

		//被代理類
		class Server implements NetWork{

			@Override
			public void browse() {
				System.out.println("真實的服務器訪問網絡");
			}

		}
		//代理類
		class ProxyServer implements NetWork{
			
			private NetWork work;
			
			public ProxyServer(NetWork work){
				this.work = work;
			}
			

			public void check(){
				System.out.println("聯網之前的檢查工作");
			}
			
			@Override
			public void browse() {
				check();
				
				work.browse();				
			}
			
		}
		```
   * 工廠模式
     * 解決的問題  
		實現了創建者與調用者的分離，即將創建對象的具體過程屏蔽隔離起來，達到提高靈活性的目的。
     * 具體模式
       * 簡單工廠模式:用來生產同一等級結構中的任意產品。(對於增加新的產品，需要修改已有代碼)
       * 工廠方法模式:用來生產同一等級結構中的固定產品。(支持增加任意產品)
       * 抽象工廠模式:用來生產不同產品族的全部產品。(對於增加新的產品，無能為力；支持增加產品族)
	   
# 面試題一
```
package com.atguigu.java1;

interface A {
	int x = 0;
}

class B {
	int x = 1;
}

class C extends B implements A {
	public void pX() {
		//編譯不通過。因為x是不明確的
		// System.out.println(x);
		System.out.println(super.x);//1
		System.out.println(A.x);//0
		
	}

	public static void main(String[] args) {
		new C().pX();
	}
}
```
	
# 面試題二
```
interface Playable {
	void play();
}

interface Bounceable {
	void play();
}

interface Rollable extends Playable, Bounceable {
	Ball ball = new Ball("PingPang");
}

class Ball implements Rollable {
	private String name;

	public String getName() {
		return name;
	}

	public Ball(String name) {
		this.name = name;
	}

	public void play() {
		ball = new Ball("Football");//interface屬性是final
		System.out.println(ball.getName());
	}
}
```

# 接口是否能繼承接口? 抽象類是否能實現(implements)接口? 抽象類是否能繼承非抽象的類? 
能，能，能
	
# 抽象類和接口有哪些共同點和區別? 
相同點:不能實例化，都可以被繼承  
不同點:抽象類:有構造器。 接口:不能聲明構造器  
多繼承 vs 單繼承  