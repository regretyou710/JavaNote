# JUC輔助類SemaphoreDemo
```
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//6輛汽車，停3個車位
public class SemaphoreDemo {
    public static void main(String[] args) {
        //創建Semaphore，設置許可數量
        Semaphore semaphore = new Semaphore(3);

        //模擬6輛汽車
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                try {
                    //搶占(線程阻塞)
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName()+" 搶到了車位");

                    //設置隨機停車時間
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+" ------離開了車位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //釋放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
```