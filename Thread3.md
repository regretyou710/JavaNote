# 死鎖問題

```
/**
 * 演示線程的死鎖問題
 *
 * 1.死鎖的理解：不同的線程分別占用對方需要的同步資源不放棄，
 * 都在等待對方放棄自己需要的同步資源，就形成了線程的死鎖
 *
 * 2.說明：
 * 1）出現死鎖後，不會出現異常，不會出現提示，只是所有的線程都處於阻塞狀態，無法繼續
 * 2）我們使用同步時，要避免出現死鎖。
 *
 * @author shkstart
 * @create 2019-02-15 下午 3:20
 */
public class ThreadTest {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        new Thread(){
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }


                }

            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
```

```
//死锁的演示
class A {
	public synchronized void foo(B b) { //同步監視器：A類的對象：a
		System.out.println("當前線程名: " + Thread.currentThread().getName()
				+ " 進入了A實例的foo方法"); // ①
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("當前線程名: " + Thread.currentThread().getName()
				+ " 企圖調用B實例的last方法"); // ③
		b.last();
	}

	public synchronized void last() {//同步監視器：A類的對象：a
		System.out.println("進入了A類的last方法內部");
	}
}

class B {
	public synchronized void bar(A a) {//同步監視器：b
		System.out.println("當前線程名: " + Thread.currentThread().getName()
				+ " 進入了B實例的bar方法"); // ②
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("當前線程名: " + Thread.currentThread().getName()
				+ " 企圖調用A實例的last方法"); // ④
		a.last();
	}

	public synchronized void last() {//同步監視器：b
		System.out.println("進入了B類的last方法內部");
	}
}

public class DeadLock implements Runnable {
	A a = new A();
	B b = new B();

	public void init() {
		Thread.currentThread().setName("主線程");
		// 調用a對象的foo方法
		a.foo(b);
		System.out.println("進入了主線程之後");
	}

	public void run() {
		Thread.currentThread().setName("副線程");
		// 調用b對象的bar方法
		b.bar(a);
		System.out.println("進入了副線程之後");
	}

	public static void main(String[] args) {
		DeadLock dl = new DeadLock();
		new Thread(dl).start();


		dl.init();

        //根據代碼的執行順序是不確定的，因此無法確定主線程和副線程哪個會先執行。
        //這取決於線程調度器的具體實現和運行時環境的狀態。
	}
}
```