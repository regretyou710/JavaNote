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
   * 若父類方法沒有throws異常，當子類出現異常時只能使用try-catch-finally方式進行，不能throws異常

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