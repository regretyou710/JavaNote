# final:最終的
   * 可以用來修飾:類、方法、變量
   * final 用來修飾一個類:此類不能被其他類所繼承。  
	比如:String類、System類、StringBuffer類
   * final 用來修飾方法:表明此方法不可以被重寫  
	比如:Object類中getClass();
   * final 用來修飾變量:此時的"變量"就稱為是一個常量
     1. final修飾屬性:可以考慮賦值的位置:顯式初始化、代碼塊中初始化、構造器中初始化
     2. final修飾局部變量:尤其是使用final修飾形參時，表明此形參是一個常量。當我們調用此方法時，給常量形參賦一個實參。一旦賦值以後，就只能在方法體內使用此形參，但不能進行重新賦值。
	 3. 建構式無法初始化常量(意指重新賦值)
   * static final 用來修飾屬性:全局常量

# 面試題一
```
public class Something {
	public int addOne(final int x) {
		return ++x;
		// return x + 1;
	}
}

```

# 面試題二
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