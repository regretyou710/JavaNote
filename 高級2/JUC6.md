```
//比較兩個接口
//實現Runnable接口
class MyThread1 implements Runnable {
    @Override
    public void run() {

    }
}

//實現Callable接口
class MyThread2 implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口創建線程
        new Thread(new MyThread1(),"AA").start();

        //Callable接口,報錯
       // new Thread(new MyThread2(),"BB").start();

        //FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        //lam表達式
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });

        //創建一個線程
        new Thread(futureTask2,"lucy").start();      

        while(!futureTask2.isDone()) {
            System.out.println("wait.....");
        }
        //調用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(futureTask2.get());//只匯總一次

        System.out.println(Thread.currentThread().getName()+" come over");
        //FutureTask原理  未來任務
        /**
         * 1、老師上課，口渴了，去買票不合適，講課線程繼續。
         *   單開啟線程找班上班長幫我買水，把水買回來，需要時候直接get
         *
         * 2、4個同學， 1同學 1+2...5   ，  2同學 10+11+12....50， 3同學 60+61+62，  4同學 100+200
         *      第2個同學計算量比較大，
         *     FutureTask單開啟線程給2同學計算，先匯總 1 3 4 ，最後等2同學計算位完成，統一匯總
         *
         * 3、考試，做會做的題目，最後看不會做的題目
         *
         * 匯總一次
         *
         */

    }
}
```

```
//比較兩個接口
//實現Runnable接口
class MyThread1 implements Runnable {
    @Override
    public void run() {

    }
}

//實現Callable接口
class MyThread2 implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable");
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口創建線程
        new Thread(new MyThread1(),"AA").start();

        //Callable接口,報錯
       // new Thread(new MyThread2(),"BB").start();

        //FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        //lam表達式
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });

        //創建一個線程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"mary").start();


        //調用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName()+" come over");//子線程結束後主線程才結束
        //FutureTask原理  未來任務
        /**
         * 1、老師上課，口渴了，去買票不合適，講課線程繼續。
         *   單開啟線程找班上班長幫我買水，把水買回來，需要時候直接get
         *
         * 2、4個同學， 1同學 1+2...5   ，  2同學 10+11+12....50， 3同學 60+61+62，  4同學 100+200
         *      第2個同學計算量比較大，
         *     FutureTask單開啟線程給2同學計算，先匯總 1 3 4 ，最後等2同學計算位完成，統一匯總
         *
         * 3、考試，做會做的題目，最後看不會做的題目
         *
         * 匯總一次
         *
         */

    }
}
```