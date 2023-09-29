# 讀寫鎖
```
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//資源類
class MyCache {
    //創建map集合
    private volatile Map<String,Object> map = new HashMap<>();

    //創建讀寫鎖對象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //放數據
    public void put(String key,Object value) {
        //添加寫鎖
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+" 正在寫操作"+key);
            //暫停一會
            TimeUnit.MICROSECONDS.sleep(300);
            //放數據
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 寫完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //釋放寫鎖
            rwLock.writeLock().unlock();
        }
    }

    //取數據
    public Object get(String key) {
        //添加讀鎖
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName()+" 正在讀取操作"+key);
            //暫停一會
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+" 取完了"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //釋放讀鎖
            rwLock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        //創建線程放數據
        for (int i = 1; i <=5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }

        TimeUnit.MICROSECONDS.sleep(300);

        //創建線程取數據
        for (int i = 1; i <=5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }

        /*
        寫->獨佔鎖，讀->共享鎖(時間阻塞越長結果越明顯)
        4 正在寫操作4
        4 寫完了4
        3 正在寫操作3
        3 寫完了3
        5 正在寫操作5
        5 寫完了5
        2 正在寫操作2
        2 寫完了2
        1 正在寫操作1
        1 寫完了1
        2 正在讀取操作2
        1 正在讀取操作1
        3 正在讀取操作3
        4 正在讀取操作4
        5 正在讀取操作5
        4 取完了4
        1 取完了1
        3 取完了3
        5 取完了5
        2 取完了2
        */
    }
}
```

# 讀寫鎖降級
```
import java.util.concurrent.locks.ReentrantReadWriteLock;

//演示讀寫鎖降級
public class Demo1 {
        /*
        Java的讀寫鎖（ReadWriteLock）允許多個線程同時讀取共享資源，但在寫操作時需要獨占鎖。讀寫鎖支持鎖降級，但不支持鎖升級的主要原因與鎖的特性有關。

        1. 鎖降級（Downgrading）：
           - 鎖降級是指一個線程在持有寫鎖的情況下獲取讀鎖，然後釋放寫鎖的過程。這是允許的，因為讀操作不會對共享資源造成修改，所以可以與其他讀操作一起執行。這種方式可以提高並發性能，因為允許多個線程同時讀取數據，只有在需要寫入時才需要爭奪寫鎖。
           - 鎖降級可以確保數據的一致性，因為在釋放寫鎖之前，線程已經獲取了讀鎖，其他線程不能獲取寫鎖，從而保證了數據的完整性。
        
        2. 鎖升級（Upgrading）：
           - 鎖升級是指一個線程在持有讀鎖的情況下嘗試獲取寫鎖的操作。在Java中，鎖升級是不允許的，因為這可能導致死鎖。
           - 假設一個線程已經持有讀鎖並嘗試獲取寫鎖，同時其他線程也在等待寫鎖。如果允許鎖升級，那麽這兩個線程可能相互等待對方釋放鎖，導致死鎖的發生。
        
        為了防止鎖升級導致的潛在死鎖問題，Java的讀寫鎖設計時不支持鎖升級操作。如果一個線程需要從讀鎖升級到寫鎖，通常的做法是先釋放讀鎖，然後再獲取寫鎖。這確保了在寫操作時不會發生鎖升級相關的問題，但可能會引入一些性能開銷，因為在鎖的釋放和重新獲取之間可能會發生競爭。
        
        總之，Java的讀寫鎖支持鎖降級是因為讀操作與其他讀操作是兼容的，而不支持鎖升級是為了避免潛在的死鎖問題。這種設計權衡了並發性能和線程安全性。
        */

    public static void main(String[] args) {
        //可重入讀寫鎖對象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//讀鎖
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//寫鎖

        //鎖降級(O)
        //1 獲取寫鎖
        writeLock.lock();
        System.out.println("atguigu");

        //2 獲取讀鎖
        readLock.lock();
        System.out.println("---read");

        //3 釋放寫鎖
        writeLock.unlock();

        //4 釋放讀鎖
        readLock.unlock();

         /*
            atguigu
            ---read
         */
    }
}
```

```
import java.util.concurrent.locks.ReentrantReadWriteLock;

//演示讀寫鎖降級
public class Demo1 {

    public static void main(String[] args) {
        //可重入讀寫鎖對象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//讀鎖
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//寫鎖

        //鎖升級(X)
        //2 獲取讀鎖
        readLock.lock();
        System.out.println("---read");

        //1 獲取寫鎖
        writeLock.lock();
        System.out.println("atguigu");

        //3 釋放寫鎖
        //writeLock.unlock();

        //4 釋放讀鎖
        //readLock.unlock();

        /*         
            ---read
         */
    }
}
```