# static
1. 可以用來修飾的結構:主要用來修飾類的內部結構
   * 屬性、方法、代碼塊、內部類
2. static修飾屬性:靜態變量(或類變量)
   1. 屬性，是否使用static修飾，又分為:靜態屬性  vs 非靜態屬性(實例變量)
      * 實例變量:我們創建了類的多個對象，每個對象都獨立的擁一套類中的非靜態屬性。當修改其中一個對象中的非靜態屬性時，不會導致其他對象中同樣的屬性值的修改。
      * 靜態變量:我們創建了類的多個對象，多個對象共享同一個靜態變量。當通過某一個對象修改靜態變量時，會導致其他對象調用此靜態變量時，是修改過了的。
   2. static修飾屬性的其他說明:
      * 靜態變量隨著類的加載而加載。可以通過"類.靜態變量"的方式進行調用
      * 靜態變量的加載要早於對象的創建。
      * 由於類只會加載一次，則靜態變量在內存中也只會存在一份:存在方法區的靜態域中。
  
      * 
        ||調用類變量|調用實例變量|
        |---|---|---|
        |類|yes|no|
        |對象|yes|yes|          
   3. 靜態屬性舉例:  
      * System.out
      * Math.PI
3. static修飾方法:靜態方法、類方法
   * 隨著類的加載而加載，可以通過"類.靜態方法"的方式進行調用

   * 
     ||靜態方法|非靜態方法|
	 |---|---|---|
	 |   類|yes|no|
	 |  對象|yes|yes|
   * 靜態方法中，只能調用靜態的方法或屬性
   * 非靜態方法中，既可以調用非靜態的方法或屬性，也可以調用靜態的方法或屬性
4. static的注意點:
   * 在靜態的方法內，不能使用this關鍵字、super關鍵字
   * 關於靜態屬性和靜態方法的使用，大家都從生命周期的角度去理解。
5. 如何判定屬性和方法應該使用static關鍵字:
   * 關於屬性
     1. 屬性是可以被多個對象所共享的，不會隨著對象的不同而不同的。
     2. 類中的常量也常常聲明為static
   * 關於方法
     1. 操作靜態屬性的方法，通常設置為static的
     2. 工具類中的方法，習慣上聲明為static的。 比如:Math、Arrays、Collections
6. 使用舉例:  
	舉例一:Arrays、Math、Collections等工具類  
	舉例二:單例模式

# 單例模式
   * 要解決的問題:  
	所謂類的單例設計模式，就是采取一定的方法保證在整個的軟件系統中，對某個類只能存在一個對象實例。
   * 具體代碼的實現:
     * 餓漢式1:  
		```
		class Bank{
			
			//1.私化類的構造器
			private Bank(){
				
			}
			
			//2.內部創建類的對象
			//4.要求此對象也必須聲明為靜態的
			private static Bank instance = new Bank();
			
			//3.提供公共的靜態的方法，返回類的對象
			public static Bank getInstance(){
				return instance;
			}
		}
		```
     * 餓漢式2:使用了靜態代碼塊
   		```
   		class Order{
   			
   			//1.私化類的構造器
   			private Order(){
   				
   			}
   			
   			//2.聲明當前類對象，沒初始化
   			//4.此對象也必須聲明為static的
   			private static Order instance = null;

   			static{
   				instance = new Order();
   			}
   			
   			//3.聲明public、static的返回當前類對象的方法
   			public static Order getInstance(){
   				return instance;
   			}   			
   		}
   		```
     * 懶漢式:
     	```
     	class Order{
     		
     		//1.私化類的構造器
     		private Order(){
     			
     		}
     		
     		//2.聲明當前類對象，沒初始化
     		//4.此對象也必須聲明為static的
     		private static Order instance = null;
     		
     		//3.聲明public、static的返回當前類對象的方法
     		public static Order getInstance(){
     			
     			if(instance == null){     				
     				instance = new Order();     				
     			}
     			return instance;
     		}	
     	}
     	```
   * 兩種方式的對比:
     * 餓漢式:  
		壞處:對象加載時間過長。  
		好處:餓漢式是線程安全的。  
     * 懶漢式:
		好處:延遲對象的創建。  
		目前的寫法壞處:線程不安全。--->到多線程內容時，再修改