```
 
int i1 = 128;
byte b1 = (byte)i1;
System.out.println(b1);//-128

	
byte b1 = 127;
b1++;
System.out.println(b1);//-128
	
shirt s1 = 15;
s1 += 1;//不會改變變量本身的數據類型
//s1++;//不會改變變量本身的數據類型

```
# 面向對象思想的體現:類和對象的創建及執行操作有哪三步?
1. 創建類
2. 類的實例化
3. 調用對象的結構

# 面向對象學習的三條主線
1. Java類及類的成員:屬性、方法、構造器;代碼塊、內部類。
2. 面向對象的特徵:封裝性、繼承性、多態性、(抽象性)。
3. 其他關鍵字:this、super、static、final、abstract、interface、package、import等。
	
# 面向對象與面向過程
面向過程:強調的是功能行為，以函數為最小單位，考慮怎麼做。  
面向對象:強調具備了功能的對象，以類/對象為最小單位，考慮誰來做。
	
# 面向對象的兩個重要的概念
類:對一類事物的描述，是抽象、概念上的定義。  
對象:實際存在的該類事物的每個個體，因而也稱為實例(instance)。

# 類與對象的關係
對象是由類new出來的、派生出來的。

# JVM的內存結構
編譯完源程序後，生成一個或多個字節碼(位元組 byte)的文件。 我們使用JVM中的類的加載器和解釋器對生成的字節碼文件進行解釋運行。意味著，需要將字節碼文件對應的類加載到內存中，涉及到內存解析。

# JVM規範
虛擬機棧(Stack):即為平時提到的棧結構。我們將局部變量儲存在棧結構中。  
堆(Heap):我們將new出來的結構(比如:數組、對象)加載在堆空間中。補充:對象的屬性(非static的)加載在堆空間中。  
方法區:類的加載訊息、常量池、靜態域

# 對比:屬性與局部變量
1. 相同點:  
	1.1 定義變量的格式:數據類型  變量名 = 變量值  
	1.2 先聲明，後使用  
	1.3 變量都其對應的作用域  
2. 不同點:  
	2.1 在類中聲明的位置的不同  
	屬性:直接定義在類的一對{}內  
	局部變量:聲明在方法內、方法形參、代碼塊內、構造器形參、構造器內部的變量

	2.2 關於權限修飾符的不同  
	屬性:可以在聲明屬性時，指明其權限，使用權限修飾符。
		常用的權限修飾符:private、public、缺省、protected  --->封裝性
		目前，大家聲明屬性時，都使用缺省就可以了。  
	局部變量:不可以使用權限修飾符。

	2.3 默認初始化值的情況:  
	屬性:類的屬性，根據其類型，都默認初始化值。
		整型(byte、short、int、long:0)
		浮點型(float、double:0.0)
		字符型(char:0  (或'\u0000'))
		布爾型(boolean:false)
		引用數據類型(類、數組、接口:null)

	局部變量:沒默認初始化值。
	意味著，我們在調用局部變量之前，一定要顯式賦值。
		特別地:形參在調用時，我們賦值即可。

	2.4 在內存中加載的位置:  
	屬性:加載到堆空間中   (非static)  
	局部變量:加載到棧空間

# 方法的重載的概念
定義:在同一個類中，允許存在一個以上的同名方法，只要它們的參數個數或者參數類型不同即可。	
總結:"兩同一不同":同一個類、相同方法名;參數列表不同:參數個數不同，參數類型不同。

# 構成重載的舉例:
舉例一:Arrays類中重載的sort() / binarySearch()；PrintStream中的println()  
舉例二:
如下的4個方法構成了重載

```
	public void getSum(int i,int j){
		System.out.println("1");
	}
	
	public void getSum(double d1,double d2){
		System.out.println("2");
	}
	
	public void getSum(String s ,int i){
		System.out.println("3");
	}
	
	public void getSum(int i,String s){
		System.out.println("4");
	}
```
不構成重載的舉例:  
如下的3個方法不能與上述4個方法構成重載
```
	public int getSum(int i,int j){
		return 0;
	}

	public void getSum(int m,int n){
		
	}

	private void getSum(int i,int j){
		
	}
```

# 如何判斷是否構成方法的重載?
嚴格按照定義判斷:兩同一不同。
跟方法的權限修飾符、返回值類型、形參變量名、方法體都沒關系！

# 可變形參
1. jdk 5.0新增的內容
2. 具體使用:  
	2.1 可變個數形參的格式:數據類型 ... 變量名  
	2.2 當調用可變個數形參的方法時，傳入的參數個數可以是:0個，1個,2個，。。。  
	2.3 可變個數形參的方法與本類中方法名相同，形參不同的方法之間構成重載  
	2.4 可變個數形參的方法與本類中方法名相同，形參類型也相同的數組之間不構成重載。換句話說，二者不能共存。  
	2.5 可變個數形參在方法的形參中，必須聲明在末尾  
	2.6  可變個數形參在方法的形參中,最多只能聲明一個可變形參。

# java中參數傳遞機制:值傳遞
規則:
* 如果參數是基本數據類型，此時實參賦給形參的是實參真實存儲的數據值。
* 如果參數是引用數據類型，此時實參賦給形參的是實參存儲數據的地址值。

推廣:  
* 如果變量是基本數據類型，此時賦值的是變量所保存的數據值。  
* 如果變量是引用數據類型，此時賦值的是變量所保存的數據的地址值。

# 構造器的作用是什麽?使用中有哪些注意點(>=3條)
1. 創建對象
2. 初始化對象結構 

* 使用說明:  
  1. 如果沒顯式的定義類的構造器的話，則系統默認提供一個空參的構造器  
  2. 定義構造器的格式:權限修飾符  類名(形參列表){}  
  3. 一個類中定義的多個構造器，彼此構成重載  
  4. 一旦我們顯式的定義了類的構造器之後，系統就不再提供默認的空參構造器  
  5. 一個類中，至少會有一個構造器。

# 關於類的屬性的賦值，有幾種賦值的方式。談談賦值的先後順序
默認初始化-顯式初始化-構造器中初始化-對象.方法 或 對象.屬性 給屬性賦值

# this關鍵字可以用來調用哪些結構，簡單說明一下其使用。
this:屬性、方法；構造器  
this:理解為當前對象，當前正在創建的對象

* this調用屬性、方法:
  1. 在類的方法中，我們可以使用"this.屬性"或"this.方法"的方式，調用當前對象屬性或方法。但是，通常情況下，我們都擇省略"this."。特殊情況下，如果方法的形參和類的屬性同名時，我們必須顯式的使用"this.變量"的方式，表明此變量是屬性，而非形參。  
  2. 在類的構造器中，我們可以使用"this.屬性"或"this.方法"的方式，調用當前正在創建的對象屬性或方法。但是，通常情況下，我們都擇省略"this."。特殊情況下，如果構造器的形參和類的屬性同名時，我們必須顯式的使用"this.變量"的方式，表明此變量是屬性，而非形參。

* this調用構造器:
  1. 我們在類的構造器中，可以顯式的使用"this(形參列表)"方式，調用本類中指定的其他構造器
  2. 構造器中不能通過"this(形參列表)"方式調用自己
  3. 如果一個類中有n個構造器，則最多有 n - 1構造器中使用了"this(形參列表)"
  4. 規定:"this(形參列表)"必須聲明在當前構造器的首行
  5. 構造器內部，最多只能聲明一個"this(形參列表)"，用來調用其他的構造器


# 為什麽要引入封裝性?
我們程序設計追求“高內聚，低耦合”。  
高內聚 :類的內部數據操作細節自己完成，不允許外部幹涉；  
低耦合 :僅對外暴露少量的方法用於使用。

隱藏對象內部的覆雜性，只對外公開簡單的接口。便於外界調用，從而提高系統的可擴展性、可維護性。通俗的說，把該隱藏的隱藏起來，該暴露的暴露出來。這就是封裝性的設計思想。

# 封裝性思想具體的代碼體現:
體現一:將類的屬性xxx私化(private),同時，提供公共的(public)方法來獲取(getXxx)和設置(setXxx)此屬性的值  
體現二:不對外暴露的私有的方法  
體現三:單例模式(將構造器私有化)  
體現四:如果不希望類在包外被調用，可以將類設置為缺省的。

# Java規定的四種權限修飾符
* 權限從小到大順序為:private <  缺省 < protected < public
* 具體的修飾範圍:
* 權限修飾符可用來修飾的結構說明:4種權限都可以用來修飾類的內部結構:屬性、方法、構造器、內部類。
修飾類的話，只能使用:缺省、public

# 所謂JavaBean，是指符合如下標準的Java類:
1. 類是公共的
2. 一個無參的公共的構造器
3. 屬性，且對應的get、set方法

# package的使用:
1. 為了更好的實現項目中類的管理，提供包的概念
2. 使用package聲明類或接口所屬的包，聲明在源文件的首行
3. 包，屬於標識符，遵循標識符的命名規則、規範(xxxyyyzzz)、“見名知意”
4. 每"."一次，就代表一層文件目錄。

# import的使用:
1. 在源文件中顯式的使用import結構導入指定包下的類、接口
2. 聲明在包的聲明和類的聲明之間
3. 如果需要導入多個結構，則並列寫出即可
4. 可以使用"xxx.*"的方式，表示可以導入xxx包下的所結構
5. 如果使用的類或接口是java.lang包下定義的，則可以省略import結構
6. 如果使用的類或接口是本包下定義的，則可以省略import結構
7. 如果在源文件中，使用了不同包下的同名的類，則必須至少一個類需要以全類名的方式顯示。
8. 使用"xxx.*"方式表明可以調用xxx包下的所結構。但是如果使用的是xxx子包下的結構，則仍需要顯式導入
9. import static:導入指定類或接口中的靜態結構:屬性或方法。 

# 為什麽要有類的繼承性?(繼承性的好處)
1. 減少了代碼的冗余，提高了代碼的覆用性
2. 便於功能的擴展
3. 為之後多態性的使用，提供了前提

# 繼承性的格式:
class A extends B{}
* A:子類、派生類、subclass
* B:父類、超類、基類、superclass

# 子類繼承父類以後有哪些不同?
1. 體現:一旦子類A繼承父類B以後，子類A中就獲取了父類B中聲明的所有的屬性和方法。
   * 特別的，父類中聲明為private的屬性或方法，子類繼承父類以後，仍然認為獲取了父類中私的結構。只因為封裝性的影響，使得子類不能直接調用父類的結構而已。
2. 子類繼承父類以後，還可以聲明自己特有的屬性或方法:實現功能的拓展。
    * 子類和父類的關系，不同於子集和集合的關系。
    * extends:延展、擴展

# Java中繼承性的說明
1. 一個類可以被多個子類繼承。
2. Java中類的單繼承性:一個類只能有一個父類
3. 子父類是相對的概念。
4. 子類直接繼承的父類，稱為:直接父類。間接繼承的父類稱為:間接父類
5. 子類繼承父類以後，就獲取了直接父類以及所間接父類中聲明的屬性和方法

# java.lang.Object類的理解
1. 如果我們沒顯式的聲明一個類的父類的話，則此類繼承於java.lang.Object類
2. 所的java類(除java.lang.Object類之外都直接或間接的繼承於java.lang.Object類
3. 意味著，所的java類具有java.lang.Object類聲明的功能。

# 什麽是多態性?什麽是虛擬方法調用?
對象的多態性:父類的引用指向子類的對象。
Person p = new Man();
p.eat();
調用方法時，編譯時看左邊，運行時看右邊。

# 一個類可以有幾個直接父類?
只有一個

# 一個父類可有多少個子類?
多個

# 子類能獲取直接父類的父類中的結構嗎?
可以

# 子類能否獲取父類中private權限的屬性或方法?
可以

# 方法的重寫(override/overwrite)的具體規則有哪些?
方法名、形參列表相同  
權限修飾符  
返回值  
拋出的異常  

# super調用構造器，有哪些具體的注意點
this(形參列表):本類重載的其它的構造器  
super(形參列表):調用父類中指定的構造器  
n – 1 

# 什麽是方法的重寫(override 或 overwrite)?
子類繼承父類以後，可以對父類中同名同參數的方法，進行覆蓋操作

* 應用:  
重寫以後，當創建子類對象以後，通過子類對象調用子父類中的同名同參數的方法時，實際執行的是子類重寫父類的方法。

* 重寫的規則:
```
	方法的聲明: 權限修飾符  返回值類型  方法名(形參列表) throws 異常的類型{
  						//方法體
  					}
  		約定俗稱:子類中的叫重寫的方法，父類中的叫被重寫的方法
  		  1. 子類重寫的方法的方法名和形參列表與父類被重寫的方法的方法名和形參列表相同
          2. 子類重寫的方法的權限修飾符不小於父類被重寫的方法的權限修飾符
       	>特殊情況:子類不能重寫父類中聲明為private權限的方法
          3. 返回值類型:
       	>父類被重寫的方法的返回值類型是void，則子類重寫的方法的返回值類型只能是void
       	>父類被重寫的方法的返回值類型是A類型，則子類重寫的方法的返回值類型可以是A類或A類的子類
       	>父類被重寫的方法的返回值類型是基本數據類型(比如:double)，則子類重寫的方法的返回值類型必須是相同的基本數據類型(必須也是double)
 		  4. 子類重寫的方法拋出的異常類型不大於父類被重寫的方法拋出的異常類型(具體放到異常處理時候講)
 	**********************************************************************
     子類和父類中的同名同參數的方法要麽都聲明為非static的(考慮重寫，要麽都聲明為static的(不是重寫))。	
```

# 區分方法的重寫和重載?
答:
1. 二者的概念:
2. 重載和重寫的具體規則
3. 重載:不表現為多態性。重寫:表現為多態性。  
重載，是指允許存在多個同名方法，而這些方法的參數不同。編譯器根據方法不同的參數表，對同名方法的名稱做修飾。對於編譯器而言，這些同名方法就成了不同的方法。它們的調用地址在編譯期就綁定了。Java的重載是可以包括父類和子類的，即子類可以重載父類的同名不同參數的方法。
所以:對於重載而言，在方法調用之前，編譯器就已經確定了所要調用的方法，這稱為“早綁定”或“靜態綁定”；

而對於多態，只等到方法調用的那一刻，解釋運行器才會確定所要調用的具體方法，這稱為“晚綁定”或“動態綁定”。 

引用一句Bruce Eckel的話:“不要犯傻，如果它不是晚綁定，它就不是多態。”

# super
1. super 關鍵字可以理解為:父類的
2. 可以用來調用的結構:
屬性、方法、構造器
3. super調用屬性、方法:  
3.1 我們可以在子類的方法或構造器中。通過使用"super.屬性"或"super.方法"的方式，顯式的調用父類中聲明的屬性或方法。但是，通常情況下，我們習慣省略"super."  
3.2 特殊情況:當子類和父類中定義了同名的屬性時，我們要想在子類中調用父類中聲明的屬性，則必須顯式的使用"super.屬性"的方式，表明調用的是父類中聲明的屬性。    
3.3 特殊情況:當子類重寫了父類中的方法以後，我們想在子類的方法中調用父類中被重寫的方法時，則必須顯式的使用"super.方法"的方式，表明調用的是父類中被重寫的方法。

4. super調用構造器:  
4.1  我們可以在子類的構造器中顯式的使用"super(形參列表)"的方式，調用父類中聲明的指定的構造器  
4.2 "super(形參列表)"的使用，必須聲明在子類構造器的首行！  
4.3 我們在類的構造器中，針對於"this(形參列表)"或"super(形參列表)"只能二選一，不能同時出現  
4.4 在構造器的首行，沒顯式的聲明"this(形參列表)"或"super(形參列表)"，則默認調用的是父類中空參的構造器:super()  
4.5 在類的多個構造器中，至少一個類的構造器中使用了"super(形參列表)"，調用父類中的構造器

# 子類對象實例化全過程
1. 從結果上看:繼承性
   * 子類繼承父類以後，就獲取了父類中聲明的屬性或方法。
   * 創建子類的對象，在堆空間中，就會加載所父類中聲明的屬性。
2. 從過程上看:  
當我們通過子類的構造器創建子類對象時，我們一定會直接或間接的調用其父類的構造器，進而調用父類的父類的構造器，...直到調用了java.lang.Object類中空參的構造器為止。正因為加載過所的父類的結構，所以才可以看到內存中父類中的結構，子類對象才可以考慮進行調用。

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

# java.lang.Object類的說明:
1. Object類是所Java類的根父類
2. 如果在類的聲明中未使用extends關鍵字指明其父類，則默認父類為java.lang.Object類 
3. Object類中的功能(屬性、方法)就具通用性。
   * 屬性:無
   * 方法:equals() / toString() / getClass() /hashCode() / clone() / finalize()
   * wait() 、 notify()、notifyAll()  
4. Object類只聲明了一個空參的構造器

# equals()方法
1. equals()的使用:
   * 是一個方法，而非運算符
   * 只能適用於引用數據類型
   * Object類中equals()的定義:
		```
		public boolean equals(Object obj) {
				return (this == obj);
		}
		```
   * 說明:Object類中定義的equals()和==的作用是相同的:比較兩個對象的地址值是否相同.即兩個引用是否指向同一個對象實體  
   * 像String、Date、File、包裝類等都重寫了Object類中的equals()方法。重寫以後，比較的不是兩個引用的地址是否相同，而是比較兩個對象的"實體內容"是否相同。
 
    * 通常情況下，我們自定義的類如果使用equals()的話，也通常是比較兩個對象的"實體內容"是否相同。那麽，我們就需要對Object類中的equals()進行重寫。重寫的原則:比較兩個對象的實體內容是否相同。

```
int i = 10;
char c = 10;
System.out.println(c == i);//true

char c1 = 'A';
char c2 = 65;
System.out.println(c1 == c2);//true
```
2. 如何重寫equals()
   * 手動重寫舉例:
		```
		class User{
		String name;
		int age;
			//重寫其equals()方法
			public boolean equals(Object obj){
				if(obj == this){
					return true;
				}
				if(obj instanceof User){
					User u = (User)obj;
					return this.age == u.age && this.name.equals(u.name);
				}
				return false;
			}
		}
		```
	* 開發中如何實現:自動生成的

# 回顧 == 運算符的使用:
 * == :運算符
 * 可以使用在基本數據類型變量和引用數據類型變量中
 * 如果比較的是基本數據類型變量:比較兩個變量保存的數據是否相等。(不一定類型要相同)
 * 如果比較的是引用數據類型變量:比較兩個對象的地址值是否相同.即兩個引用是否指向同一個對象實體
	>補充: == 符號使用時，必須保證符號左右兩邊的變量類型一致。

# toString()方法
1. toString()的使用:
   * 當我們輸出一個對象的引用時，實際上就是調用當前對象的toString()  
   * Object類中toString()的定義:
		```
		public String toString() {
			return getClass().getName() + "@" + Integer.toHexString(hashCode());
		}
		```
	* 像String、Date、File、包裝類等都重寫了Object類中的toString()方法。
	* 使得在調用對象的toString()時，返回"實體內容"信息   
    * 自定義類也可以重寫toString()方法，當調用此方法時，返回對象的"實體內容"

2. 如何重寫toString()  
舉例:
	```
	//自動實現
		@Override
		public String toString() {
			return "Customer [name=" + name + ", age=" + age + "]";
		}
	```

# final、finally、finalize的區別?

# == 和 equals() 區別

# 為什麽要有包裝類(或封裝類)
為了使基本數據類型的變量具有類的特征，引入包裝類。

* 應用場景舉例:  
	Vector類中關於添加元素，只定義了形參為Object類型的方法:  
	v.addElement(Object obj);   //基本數據類型 --->包裝類 --->使用多態

# 需要掌握的類型間的轉換:(基本數據類型、包裝類、String)
* 基本數據類型<--->包裝類:JDK 5.0 新特性:自動裝箱 與自動拆箱
* 基本數據類型、包裝類--->String:調用String重載的valueOf(Xxx xxx)
* String--->基本數據類型、包裝類:調用包裝類的parseXxx(String s)
	>注意:轉換時，可能會報NumberFormatException

```
	@Test
	public void test1() {
		Object o1 = true ? new Integer(1) : new Double(2.0);
		System.out.println(o1);// 1.0，三元運算編譯時數據類型會自動提升

	}

	@Test
	public void test2() {
		Object o2;
		if (true)
			o2 = new Integer(1);
		else
			o2 = new Double(2.0);
		System.out.println(o2);// 1

	}

	@Test
	public void test3() {
		Integer i = new Integer(1);
		Integer j = new Integer(1);
		System.out.println(i == j);//false，因為new 比較地址值
		
		//Integer內部定義了IntegerCache結構，IntegerCache中定義了Integer[],
		//保存了從-128~127範圍的整數。如果我們使用自動裝箱的方式，給Integer賦值的範圍在
		//-128~127範圍內時，可以直接使用數組中的元素，不用再去new了。目的：提高效率
		
		Integer m = 1;
		Integer n = 1;
		System.out.println(m == n);//true，自動裝箱，比較value

		Integer x = 128;//相當於new了一個Integer對象
		Integer y = 128;//相當於new了一個Integer對象
		System.out.println(x == y);//false
	}

```
	
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

# 類的成員之四:代碼塊(初始化塊)(重要性較屬性、方法、構造器差一些)
   * 代碼塊的作用:用來初始化類、對象的信息
   * 分類:靜態代碼塊  vs 非靜態代碼塊
     * 說明:代碼塊要是使用修飾符，只能使用static
   * 靜態代碼塊:
		>1. 內部可以輸出語句
		>2. 隨著類的加載而執行,而且只執行一次
		>3. 作用:初始化類的信息
		>4. 如果一個類中定義了多個靜態代碼塊，則按照聲明的先後順序執行
		>5. 靜態代碼塊的執行要優先於非靜態代碼塊的執行
		>6. 靜態代碼塊內只能調用靜態的屬性、靜態的方法，不能調用非靜態的結構
   * 非靜態代碼塊:
		>1. 內部可以輸出語句
		>2. 隨著對象的創建而執行
		>3. 每創建一個對象，就執行一次非靜態代碼塊
		>4. 作用:可以在創建對象時，對對象的屬性等進行初始化
		>5. 如果一個類中定義了多個非靜態代碼塊，則按照聲明的先後順序執行
		>6. 非靜態代碼塊內可以調用靜態的屬性、靜態的方法，或非靜態的屬性、非靜態的方法
   * 實例化子類對象時，涉及到父類、子類中靜態代碼塊、非靜態代碼塊、構造器的加載順序:  
     1. 對應的練習:LeafTest.java / Son.java  
     2. 由父及子，靜態先行。   
   * 屬性的賦值順序  
    ①默認初始化  
    ②顯式初始化/⑤在代碼塊中賦值  
    ③構造器中初始化  
    ④有了對象以後，可以通過"對象.屬性"或"對象.方法"的方式，進行賦值  
    執行的先後順序:① - ② / ⑤ - ③ - ④

	
```
//總結：由父及子，靜態先行
class Root{
	static{
		System.out.println("Root的靜態初始化塊");
	}
	{
		System.out.println("Root的普通初始化塊");
	}
	public Root(){
		super();
		System.out.println("Root的無參數的構造器");
	}
}
class Mid extends Root{
	static{
		System.out.println("Mid的靜態初始化塊");
	}
	{
		System.out.println("Mid的普通初始化塊");
	}
	public Mid(){
		super();
		System.out.println("Mid的無參數的構造器");
	}
	public Mid(String msg){
		//通過this調用同一類中重載的構造器
		this();
		System.out.println("Mid的帶參數構造器，其參數值："
			+ msg);
	}
}
class Leaf extends Mid{
	static{
		System.out.println("Leaf的靜態初始化塊");
	}
	{
		System.out.println("Leaf的普通初始化塊");
	}	
	public Leaf(){
		//通過super調用父類中有一個字符串參數的構造器
		super("尚矽谷");
		System.out.println("Leaf的構造器");
	}
}
public class LeafTest{
	public static void main(String[] args){
		new Leaf(); 
		System.out.println();
		new Leaf();
	}
}
```

```
class Father {
	static {
		System.out.println("11111111111");
	}
	{
		System.out.println("22222222222");
	}

	public Father() {
		System.out.println("33333333333");

	}

}

public class Son extends Father {
	static {
		System.out.println("44444444444");
	}
	{
		System.out.println("55555555555");
	}
	public Son() {
		System.out.println("66666666666");
	}


	public static void main(String[] args) { // 由父及子 靜態先行
		System.out.println("77777777777");
		System.out.println("************************");
		new Son();//main方法在Son中執行，所以mian執行new Son()時會先執行父類靜態代碼塊
			
		System.out.println("************************");
		new Son();
		System.out.println("************************");
		new Father();
	}

}
```

# final:最終的
   * 可以用來修飾:類、方法、變量
   * final 用來修飾一個類:此類不能被其他類所繼承。  
	比如:String類、System類、StringBuffer類
   * final 用來修飾方法:表明此方法不可以被重寫  
	比如:Object類中getClass();
   * final 用來修飾變量:此時的"變量"就稱為是一個常量
     1. final修飾屬性:可以考慮賦值的位置:顯式初始化、代碼塊中初始化、構造器中初始化
     2. final修飾局部變量:尤其是使用final修飾形參時，表明此形參是一個常量。當我們調用此方法時，給常量形參賦一個實參。一旦賦值以後，就只能在方法體內使用此形參，但不能進行重新賦值。
   * static final 用來修飾屬性:全局常量

```
public class Something {
	public int addOne(final int x) {
		return ++x;
		// return x + 1;
	}
}

```
	
```
public class Something {
	public static void main(String[] args) {
		Other o = new Other();
		new Something().addOne(o);
	}
	
	public void addOne(final Other o) {
		// o = new Other();
		o.i++;
		}
	}
	
class Other {
	public int i;
}
```

# abstract關鍵字的使用
1. abstract:抽象的
2. abstract可以用來修飾的結構：類、方法
3. abstract修飾類：抽像類
	1. 此類不能實例化
	2. 抽像類中一定有構造器，便於子類實例化時調用（涉及：子類對象實例化的全過程）
	3. 開發中，都會提供抽像類的子類，讓子類對象實例化，完成相關的操作
4. abstract修飾方法：抽象方法
	1. 抽象方法只有方法的聲明，沒有方法體
	2. 包含抽象方法的類，一定是一個抽像類。反之，抽像類中可以沒有抽象方法的。
	3. 若子類重寫了父類中的所有的抽象方法後，此子類方可實例化
	若子類沒有重寫父類中的所有的抽象方法，則此子類也是一個抽像類，需要使用abstract修飾

# abstract使用上的注意點：
1. abstract不能用來修飾：屬性、構造器等結構   
2. abstract不能用來修飾私有方法、靜態方法、final的方法、final的類
	
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

# interface的使用
1. 接口使用interface來定義
2. Java中，接口和類是並列的兩個結構
3. 如何定義接口：定義接口中的成員		
	* 3.1 JDK7及以前：只能定義全局常量和抽象方法
		1. 全局常量：public static final的.但是書寫時，可以省略不寫
		2. 抽象方法：public abstract的
	* 3.2 JDK8：除了定義全局常量和抽象方法之外，還可以定義靜態方法、默認方法（略）
4. 接口中不能定義構造器的！意味著接口不可以實例化
5. Java開發中，接口通過讓類去實現(implements)的方式來使用.  
   如果實現類覆蓋了接口中的所有抽象方法，則此實現類就可以實例化  
   如果實現類沒有覆蓋接口中所有的抽象方法，則此實現類仍為一個抽像類   
6. Java類可以實現多個接口--->彌補了Java單繼承性的局限性
	* 格式：class AA extends BB implements CC,DD,EE  
7. 接口與接口之間可以繼承，而且可以多繼承
8. 接口的具體使用，體現多態性
9. 接口，實際上可以看做是一種規範

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

# 接口練習
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
		ball = new Ball("Football");
		System.out.println(ball.getName());
	}
}
```

# abstract 能修飾哪些結構? 修飾以後，有什麽特點?
類、方法。  

類不能實例化，提供子類   
抽象方法，只定義了一種功能的標準。具體的執行，需要子類去實現。  


# 接口是否能繼承接口? 抽象類是否能實現(implements)接口? 抽象類是否能繼承非抽象的類? 
能，能，能

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

# 抽象類和接口有哪些共同點和區別? 
相同點:不能實例化，都可以被繼承  
不同點:抽象類:有構造器。 接口:不能聲明構造器  
多繼承 vs 單繼承  

# 如何創建靜態成員內部類和非靜態成員內部類的對象?
Person  static Dog   Bird
Person.Dog dog = new Person.Dog();
Person p = new Person();
Person.Bird bird = p.new Bird();

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
     2. abstract不能用來修飾私方法、靜態方法、final的方法、final的類
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
   * Java8中關於接口的新規範
     1. 接口中定義的靜態方法，只能通過接口來調用。
     2. 通過實現類的對象，可以調用接口中的默認方法。如果實現類重寫了接口中的默認方法，調用時，仍然調用的是重寫以後的方法
     3. 如果子類(或實現類)繼承的父類和實現的接口中聲明了同名同參數的默認方法，那麽子類在沒重寫此方法的情況下，默認調用的是父類中的同名同參數的方法。-->類優先原則
     4. 如果實現類實現了多個接口，而這多個接口中定義了同名同參數的默認方法，那麽在實現類沒重寫此方法的情況下，報錯。-->接口沖突。這就需要我們必須在實現類中重寫此方法
     5. 如何在子類(或實現類)的方法中調用父類、接口中被重寫的方法
		```
		public void myMethod(){
			method3();//調用自己定義的重寫的方法
			super.method3();//調用的是父類中聲明的
			//調用接口中的默認方法
			CompareA.super.method3();
			CompareB.super.method3();
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

# 內部類:類的第五個成員
   * 定義:Java中允許將一個類A聲明在另一個類B中，則類A就是內部類，類B稱為外部類.
   * 內部類的分類:
	* 成員內部類(靜態、非靜態) vs 局部內部類(方法內、代碼塊內、構造器內)
   * 成員內部類的理解:
     * 一方面，作為外部類的成員:
       1. 調用外部類的結構
       2. 可以被static修飾
       3. 可以被4種不同的權限修飾 
     * 另一方面，作為一個類:
       1. 類內可以定義屬性、方法、構造器等
       2. 可以被final修飾，表示此類不能被繼承。言外之意，不使用final，就可以被繼承
       3. 可以被abstract修飾
     * 成員內部類:
       * 如何創建成員內部類的對象?(靜態的，非靜態的)
			```
			//創建靜態的Dog內部類的實例(靜態的成員內部類):
			Person.Dog dog = new Person.Dog();

			//創建非靜態的Bird內部類的實例(非靜態的成員內部類):
			//Person.Bird bird = new Person.Bird();//錯誤的
			Person p = new Person();
			Person.Bird bird = p.new Bird();
			```
       * 如何在成員內部類中調用外部類的結構?
			```
			class Person{
				String name = "小明";
			public void eat(){
			}
			//非靜態成員內部類
				class Bird{
					String name = "杜鵑";
					public void display(String name){
						System.out.println(name);//方法的形參
						System.out.println(this.name);//內部類的屬性
						System.out.println(Person.this.name);//外部類的屬性
					//Person.this.eat();
					}
				}
			}
			```
     * 局部內部類的使用:
		```
		//返回一個實現了Comparable接口的類的對象
			public Comparable getComparable(){
				
				//創建一個實現了Comparable接口的類:局部內部類
				//方式一:
		//		class MyComparable implements Comparable{
		//
		//			@Override
		//			public int compareTo(Object o) {
		//				return 0;
		//			}
		//			
		//		}
		//		
		//		return new MyComparable();
				
				//方式二:
				return new Comparable(){

					@Override
					public int compareTo(Object o) {
						return 0;
					}
					
				};		
			}
		```
     * 注意點:
       * 在局部內部類的方法中(比如:show如果調用局部內部類所聲明的方法(比如:method)中的局部變量(比如:num)的話,要求此局部變量聲明為final的。
			```
				public void method(){
					//局部變量
					int num = 10;
					
					class AA{
						public void show(){
							//num = 20;
							System.out.println(num);							
						}
					}
				}
			```
       * jdk 7及之前版本:要求此局部變量顯式的聲明為final的
       * jdk 8及之後的版本:可以省略final的聲明
     * 總結:
       1. 成員內部類和局部內部類，在編譯以後，都會生成字節碼文件。
       2. 格式:
            * 成員內部類:外部類$內部類名.class
            * 局部內部類:外部類$數字 內部類名.class

# 異常的體系結構
```
   java.lang.Throwable
   		|-----java.lang.Error:一般不編寫針對性的代碼進行處理。
   		|-----java.lang.Exception:可以進行異常的處理
   			|------編譯時異常(checked)
   					|-----IOException
   						|-----FileNotFoundException
   					|-----ClassNotFoundException
   			|------運行時異常(unchecked,RuntimeException)
   					|-----NullPointerException
   					|-----ArrayIndexOutOfBoundsException
   					|-----ClassCastException
   					|-----NumberFormatException
   					|-----InputMismatchException
   					|-----ArithmeticException
```

# 從程序執行過程，看編譯時異常和運行時異常
   * 編譯時異常:執行javac.exe命名時，可能出現的異常
   * 運行時異常:執行java.exe命名時，出現的異常
  
# 常見的異常類型，請舉例說明:
```
//******************以下是運行時異常***************************
	//ArithmeticException
	@Test
	public void test6(){
		int a = 10;
		int b = 0;
		System.out.println(a / b);
	}
	
	//InputMismatchException
	@Test
	public void test5(){
		Scanner scanner = new Scanner(System.in);
		int score = scanner.nextInt();
		System.out.println(score);
		
		scanner.close();
	}
	
	//NumberFormatException
	@Test
	public void test4(){
		
		String str = "123";
		str = "abc";
		int num = Integer.parseInt(str);		
	}
	
	//ClassCastException
	@Test
	public void test3(){
		Object obj = new Date();
		String str = (String)obj;
	}
	
	//IndexOutOfBoundsException
	@Test
	public void test2(){
		//ArrayIndexOutOfBoundsException
//		int[] arr = new int[10];
//		System.out.println(arr[10]);
		//StringIndexOutOfBoundsException
		String str = "abc";
		System.out.println(str.charAt(3));
	}
	
	//NullPointerException
	@Test
	public void test1(){		
//		int[] arr = null;
//		System.out.println(arr[3]);
		
		String str = "abc";
		str = null;
		System.out.println(str.charAt(0));		
	}

	//******************以下是編譯時異常***************************
	@Test
	public void test7(){
//		File file = new File("hello.txt");
//		FileInputStream fis = new FileInputStream(file);
//		
//		int data = fis.read();
//		while(data != -1){
//			System.out.print((char)data);
//			data = fis.read();
//		}
//		
//		fis.close();		
	}
```

# java異常處理的抓拋模型
   * 過程一:"拋":程序在正常執行的過程中，一旦出現異常，就會在異常代碼處生成一個對應異常類的對象，並將此對象拋出。一旦拋出對象以後，其後的代碼就不再執行。  		
       * 關於異常對象的產生:
         1. 系統自動生成的異常對象
         2. 手動的生成一個異常對象，並拋出(throw）  
   * 過程二:"抓":可以理解為異常的處理方式:
       1. try-catch-finally
       2. throws

# 異常處理方式一:try-catch-finally
   * 使用說明:
		```
		//可能出現異常的代碼 
		}catch(異常類型1 變量名1){
				//處理異常的方式1
		}catch(異常類型2 變量名2){
				//處理異常的方式2
		}catch(異常類型3 變量名3){
				//處理異常的方式3
		}
		....
		finally{
				//一定會執行的代碼
		}
		
		說明:
		1. finally是可的。
		2. 使用try將可能出現異常代碼包裝起來，在執行過程中，一旦出現異常，就會生成一個對應異常類的對象，根據此對象的類型，去catch中進行匹配
		3. 一旦try中的異常對象匹配到某一個catch時，就進入catch中進行異常的處理。一旦處理完成，就跳出當前的try-catch結構(在沒寫finally的情況。繼續執行其後的代碼
		4. catch中的異常類型如果沒子父類關系，則誰聲明在上，誰聲明在下無所謂。
			catch中的異常類型如果滿足子父類關系，則要求子類一定聲明在父類的上面。否則，報錯
		5. 常用的異常對象處理的方式: ① String  getMessage()    ② printStackTrace()
		6. 在try結構中聲明的變量，再出了try結構以後，就不能再被調用
		7. try-catch-finally結構可以嵌套
		總結:如何看待代碼中的編譯時異常和運行時異常?

		體會1:使用try-catch-finally處理編譯時異常，是得程序在編譯時就不再報錯，但是運行時仍可能報錯。相當於我們使用try-catch-finally將一個編譯時可能出現的異常，延遲到運行時出現。			
		體會2:開發中，由於運行時異常比較常見，所以我們通常就不針對運行時異常編寫try-catch-finally了。針對於編譯時異常，我們說一定要考慮異常的處理。
		```
   * finally的再說明:
     1. finally是可選的     
     2. finally中聲明的是一定會被執行的代碼。即使catch中又出異常了，try中return語句，catch中return語句等情況。     
     3. 像數據庫連接、輸入輸出流、網絡編程Socket等資源，JVM是不能自動的回收的，我們需要自己手動的進行資源的釋放。此時的資源釋放，就需要聲明在finally中。

# 面試題
   * final、finally、finalize三者的區別?
   * 類似:
     1. throw 和 throws
     2. Collection 和 Collections
     3. String 、StringBuffer、StringBuilder
     4. ArrayList 、 LinkedList
     5. HashMap 、LinkedHashMap
     6. 重寫、重載
   * 結構不相似的:
     1. 抽象類、接口
     2. == 、 equals()
     3. sleep()、wait()

# 異常處理方式二:
"throws + 異常類型"寫在方法的聲明處。指明此方法執行時，可能會拋出的異常類型。  
一旦當方法體執行時，出現異常，仍會在異常代碼處生成一個異常類的對象，此對象滿足throws後異常類型時，就會被拋出。異常代碼後續的代碼，就不再執行！

# 對比兩種處理方式
try-catch-finally:真正的將異常給處理掉了。  
throws的方式只是將異常拋給了方法的調用者。並沒真正將異常處理掉。 

# 體會開發中應該如何選擇兩種處理方式?
   * 如果父類中被重寫的方法沒throws方式處理異常，則子類重寫的方法也不能使用throws，意味著如果子類重寫的方法中異常，必須使用try-catch-finally方式處理。
   * 執行的方法a中，先後又調用了另外的幾個方法，這幾個方法是遞進關系執行的。我們建議這幾個方法使用throws的方式進行處理。而執行的方法a可以考慮使用try-catch-finally方式進行處理。

# 異常處理補充:
   * 方法重寫的規則之一:子類重寫的方法拋出的異常類型不大於父類被重寫的方法拋出的異常類型

# 手動拋出異常對象
   * 使用說明:在程序執行中，除了自動拋出異常對象的情況之外，我們還可以手動的throw一個異常類的對象。

   * [面試題] 
     * throw 和  throws區別:
       * throw 表示拋出一個異常類的對象，生成異常對象的過程。聲明在方法體內。
       * throws 屬於異常處理的一種方式，聲明在方法的聲明處。
   * 典型例題
		```
		class Student{			
			private int id;
			
			public void regist(int id) throws Exception {
				if(id > 0){
					this.id = id;
				}else{
					//手動拋出異常對象
		//			throw new RuntimeException("您輸入的數據非法！");
		//			throw new Exception("您輸入的數據非法！");
					throw new MyException("不能輸入負數");
				}				
			}

			@Override
			public String toString() {
				return "Student [id=" + id + "]";
			}
		}
		```

# 自定義異常類
```
/*
 * 如何自定義異常類?
 * 1. 繼承於現的異常結構:RuntimeException 、Exception
 * 2. 提供全局常量:serialVersionUID
 * 3. 提供重載的構造器
 * 
 */
public class MyException extends Exception{
	
	static final long serialVersionUID = -7034897193246939L;
	
	public MyException(){
		
	}
	
	public MyException(String msg){
		super(msg);
	}
}
```