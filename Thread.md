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

```
/**
 * 測試Thread中的常用方法：
 * 1. start():啟動當前線程；調用當前線程的run()
 * 2. run(): 通常需要重寫Thread類中的此方法，將創建的線程要執行的操作聲明在此方法中
 * 3. currentThread():靜態方法，返回執行當前代碼的線程
 * 4. getName():獲取當前線程的名字
 * 5. setName():設置當前線程的名字
 * 6. yield():釋放當前cpu的執行權
 *            6.1 暫停當前正在執行的線程,把執行機會讓給優先及相同或更高的線程(有可能被暫停的線程又重新分配到執行權)
 *            6.2 若對列中沒有同優先級的線程,忽略此方法
 * 7. join():在線程a中調用線程b的join(),此時線程a就進入阻塞狀態，直到線程b完全執行完以後，線程a才
 *           結束阻塞狀態。
 * 8. stop():已過時。當執行此方法時，強制結束當前線程。
 * 9. sleep(long millitime):讓當前線程“睡眠”指定的millitime毫秒。在指定的millitime毫秒時間內，當前
 *                          線程是阻塞狀態。
 * 10. isAlive():判斷當前線程是否存活
 *
 *
 * 線程的優先級：
 * 1.
 * MAX_PRIORITY：10
 * MIN _PRIORITY：1
 * NORM_PRIORITY：5  -->默認優先級
 * 2.如何獲取和設置當前線程的優先級：
 *   getPriority():獲取線程的優先級
 *   setPriority(int p):設置線程的優先級
 *
 *   說明：高優先級的線程要搶占低優先級線程cpu的執行權。但是只是從概率上講，高優先級的線程高概率的情況下
 *   被執行。並不意味著只有當高優先級的線程執行完以後，低優先級的線程才執行。
 *
 *
 * @author shkstart
 * @create 2019-02-13 下午 2:26
 */
class HelloThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){

//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i % 20 == 0){
//                yield();
//            }

        }

    }

    public HelloThread(String name){
        super(name);
    }
}


public class ThreadMethodTest {
    public static void main(String[] args) {

        HelloThread h1 = new HelloThread("Thread：1");

//        h1.setName("線程一");
        //設置分線程的優先級
        h1.setPriority(Thread.MAX_PRIORITY);

        h1.start();

        //給主線程命名
        Thread.currentThread().setName("主線程");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if(i == 20){
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

//        System.out.println(h1.isAlive());

    }
}
```