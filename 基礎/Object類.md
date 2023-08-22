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