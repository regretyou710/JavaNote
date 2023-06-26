# 線程安全問題

* Runnable接口 & 同步代碼塊
```
/**
 * 例子：創建三個窗口賣票，總票數為100張.使用實現Runnable接口的方式
 *
 * 1.問題：賣票過程中，出現了重票、錯票 -->出現了線程的安全問題
 * 2.問題出現的原因：當某個線程操作車票的過程中，尚未操作完成時，其他線程參與進來，也操作車票。
 * 3.如何解決：當一個線程a在操作ticket的時候，其他線程不能參與進來。直到線程a操作完ticket時，其他
 *            線程才可以開始操作ticket。這種情況即使線程a出現了阻塞，也不能被改變。
 *
 *
 * 4.在Java中，我們通過同步機制，來解決線程的安全問題。
 *
 *  方式一：同步代碼塊
 *
 *   synchronized(同步監視器){
 *      //需要被同步的代碼
 *
 *   }
 *  說明：1.操作共享數據的代碼，即為需要被同步的代碼。  -->不能包含代碼多了，也不能包含代碼少了。
 *       2.共享數據：多個線程共同操作的變量。比如：ticket就是共享數據。
 *       3.同步監視器，俗稱：鎖。任何一個類的對象，都可以充當鎖。
 *          要求：多個線程必須要共用同一把鎖。
 *
 *       補充：在實現Runnable接口創建多線程的方式中，我們可以考慮使用this充當同步監視器。
 *  方式二：同步方法。
 *     如果操作共享數據的代碼完整的聲明在一個方法中，我們不妨將此方法聲明同步的。
 *
 *
 *  5.同步的方式，解決了線程的安全問題。---好處
 *    操作同步代碼時，只能有一個線程參與，其他線程等待。相當於是一個單線程的過程，效率低。 ---局限性
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:47
 */
class Window1 implements Runnable{
    private int ticket = 100;
//    Object obj = new Object();
//    Dog dog = new Dog();
    @Override
    public void run() {
//        Object obj = new Object();
        while(true){
            synchronized (this){//此時的this:唯一的Window1的對象   //方式二：synchronized (dog) {
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":賣票，票號為：" + ticket);


                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}


class Dog{

}
```

* 繼承Thread類 & 同步代碼塊
```
/**
 * @author shkstart
 * @create 2019-02-15 上午 11:15
 */
/**
 * 使用同步代碼塊解決繼承Thread類的方式的線程安全問題
 *
 * 例子：創建三個窗口賣票，總票數為100張.使用繼承Thread類的方式
 *
 * 說明：在繼承Thread類創建多線程的方式中，慎用this充當同步監視器，考慮使用當前類充當同步監視器。
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:20
 */
class Window2 extends Thread{
    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {

        while(true){
            //正確的
//            synchronized (obj){
            synchronized (Window2.class){//Class clazz = Window2.class,Window2.class只會加載一次
                //錯誤的方式：this代表著t1,t2,t3三個對象
//              synchronized (this){

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：賣票，票號為：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}


public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
```

* Runnable接口 & 同步方法
```
/**
 * 使用同步方法解決實現Runnable接口的線程安全問題
 *
 *
 *  關於同步方法的總結：
 *  1. 同步方法仍然涉及到同步監視器，只是不需要我們顯式的聲明。
 *  2. 非靜態的同步方法，同步監視器是：this
 *     靜態的同步方法，同步監視器是：當前類本身
 *
 * @author shkstart
 * @create 2019-02-15 上午 11:35
 */


class Window3 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
            //沒有寫離開迴圈
        }
    }

    private synchronized void show(){//同步監視器：this
        //synchronized (this){
            if (ticket > 0) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //因為不是繼承Thread而是Object，所以不能從this身上拿到getName()
                System.out.println(Thread.currentThread().getName() + ":賣票，票號為：" + ticket);

                ticket--;
            }
        //}
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
```

* 繼承Thread類 & 同步
```
/**
 * 使用同步方法處理繼承Thread類的方式中的線程安全問題
 *
 * @author shkstart
 * @create 2019-02-15 上午 11:43
 */
class Window4 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {

            show();
        }

    }

    private static synchronized void show(){//同步監視器：Window4.class
        //private synchronized void show(){ //同步監視器：t1,t2,t3。此種解決方式是錯誤的
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //因為show()為靜態，靜態方法中也不能使用this，所以無法從當前對象獲取非靜態方法，
            System.out.println(Thread.currentThread().getName() + "：賣票，票號為：" + ticket);
            ticket--;
        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
```