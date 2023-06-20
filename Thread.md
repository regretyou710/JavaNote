```
/**
 * 多線程的創建，方式一：繼承於Thread類
 * 1. 創建一個繼承於Thread類的子類
 * 2. 重寫Thread類的run() --> 將此線程執行的操作聲明在run()中
 * 3. 創建Thread類的子類的對象
 * 4. 通過此對象調用start()
 * <p>
 * 例子：遍歷100以內的所有的偶數
 *
 * @author shkstart
 * @create 2019-02-13 上午 11:46
 */

//1. 創建一個繼承於Thread類的子類
class MyThread extends Thread {
    //2. 重寫Thread類的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        //3. 創建Thread類的子類的對象
        MyThread t1 = new MyThread();

        //4.通過此對象調用start():①啟動當前線程 ② 調用當前線程的run()
        t1.start();
        //問題一：我們不能通過直接調用run()的方式啟動線程。
//        t1.run();

        //問題二：再啟動一個線程，遍歷100以內的偶數。不可以還讓已經start()的線程去執行。會報IllegalThreadStateException
//        t1.start();
        //我們需要重新創建一個線程的對象
        MyThread t2 = new MyThread();
        t2.start();


        //如下操作仍然是在main線程中執行的。
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i + "***********main()************");
            }
        }
    }

}
```

```
/**
 * 練習：創建兩個分線程，其中一個線程遍歷100以內的偶數，另一個線程遍歷100以內的奇數
 *
 *
 * @author shkstart
 * @create 2019-02-13 下午 2:16
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThread1 m1 = new MyThread1();
//        MyThread2 m2 = new MyThread2();
//
//        m1.start();
//        m2.start();

        //創建Thread類的匿名子類的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    }
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    }
                }
            }
        }.start();

    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);

            }
        }

    }
}


class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);

            }
        }

    }
}
```