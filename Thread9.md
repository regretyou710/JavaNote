```
/**
 * 創建線程的方式四：使用線程池
 *
 * 好處：
 * 1.提高響應速度（減少了創建新線程的時間）
 * 2.降低資源消耗（重覆利用線程池中線程，不需要每次都創建）
 * 3.便於線程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大線程數
 *      keepAliveTime：線程沒有任務時最多保持多長時間後會終止
 *
 *
 * 面試題：創建多線程有幾種方式？四種！
 * @author shkstart
 * @create 2019-02-15 下午 6:30
 */

class NumberThread implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //1. 提供指定線程數量的線程池

        //ThreadPoolExecutor -> AbstractExecutorService -> ExecutorService
        //Executors.newFixedThreadPool()方法返回值使用多態的形式表現，實際返回值類型是ThreadPoolExecutor，/所以可以向下轉型
        ExecutorService service = Executors.newFixedThreadPool(10);//向上隱含
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;//向下明確

        //設置線程池的屬性
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.執行指定的線程的操作。需要提供實現Runnable接口或Callable接口實現類的對象
        service.execute(new NumberThread());//適合適用於Runnable
        service.execute(new NumberThread1());//適合適用於Runnable

//        service.submit(Callable callable);//適合使用於Callable
        //3.關閉連接池
        service.shutdown();
    }

}
```
