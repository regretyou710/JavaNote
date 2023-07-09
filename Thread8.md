```
/**
 * 創建線程的方式三：實現Callable接口。 --- JDK 5.0新增
 *
 *
 * 如何理解實現Callable接口的方式創建多線程比實現Runnable接口創建多線程方式強大？
 * 1. call()可以有返回值的。
 * 2. call()可以拋出異常，被外面的操作捕獲(如get()捕獲異常)，獲取異常的信息
 * 3. Callable是支持泛型的
 *
 * @author shkstart
 * @create 2019-02-15 下午 6:01
 */

//1.創建一個實現Callable的實現類
class NumThread implements Callable{
    //2.實現call方法，將此線程需要執行的操作聲明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }

        return sum;//Object obj = sum;(自動裝箱&多態)
    }
}


public class ThreadNew {
    public static void main(String[] args) {
        //3.創建Callable接口實現類的對象
        NumThread numThread = new NumThread();

        //4.將此Callable接口實現類的對象作為傳遞到FutureTask構造器中，創建FutureTask的對象
        FutureTask futureTask = new FutureTask(numThread);

        //5.將FutureTask的對象作為參數傳遞到Thread類的構造器中，創建Thread對象，並調用start()
        new Thread(futureTask).start();

        try {
            //6.獲取Callable中call方法的返回值
            //get()返回值即為FutureTask構造器參數Callable實現類重寫的call()的返回值。
            Object sum = futureTask.get();

            System.out.println("總和為：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```