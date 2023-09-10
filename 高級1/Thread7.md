```
/**
 * 線程通信的應用：經典例題：生產者/消費者問題
 *
 * 生產者(Productor)將產品交給店員(Clerk)，而消費者(Customer)從店員處取走產品，
 * 店員一次只能持有固定數量的產品(比如:20），如果生產者試圖生產更多的產品，店員
 * 會叫生產者停一下，如果店中有空位放產品了再通知生產者繼續生產；如果店中沒有產品
 * 了，店員會告訴消費者等一下，如果店中有產品了再通知消費者來取走產品。
 *
 * 分析：
 * 1. 是否是多線程問題？是，生產者線程，消費者線程
 * 2. 是否有共享數據？是，店員（或產品）
 * 3. 如何解決線程的安全問題？同步機制,有三種方法
 * 4. 是否涉及線程的通信？是
 *
 * 如果一個對象既有同步方法，又有同步塊，那麽當其中任意一個同步方法或者同步塊被某個線程執行時，
 * 這個對象就被鎖定了，其他線程無法在此時訪問這個對象的同步方法，也不能執行同步塊。
 * 當生產者執行produceProduct()時除了自身方法上鎖，Clerk類中的consumeProduct()也會上鎖
 *
 * @author shkstart
 * @create 2019-02-15 下午 4:48
 */
class Clerk{

    private int productCount = 0;
    //生產產品
    public synchronized void produceProduct() {

        if(productCount < 20){
            productCount++;

            //在輸出前生產有可能出現阻塞

            System.out.println(Thread.currentThread().getName() + ":開始生產第" + productCount + "個產品");

            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //消費產品
    public synchronized void consumeProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + ":開始消費第" + productCount + "個產品");

            //在商品減少前消費有可能出現阻塞

            productCount--;

            notify();
        }else{
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Producer extends Thread{//生產者

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":開始生產產品.....");

        while(true){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }

    }
}

class Consumer extends Thread{//消費者
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":開始消費產品.....");

        while(true){

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("生產者1");

        Consumer c1 = new Consumer(clerk);
        c1.setName("消費者1");
        Consumer c2 = new Consumer(clerk);
        c2.setName("消費者2");

        p1.start();
        c1.start();
        c2.start();
    }
}
```