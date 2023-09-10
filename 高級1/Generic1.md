```
/** 如何自定義泛型結構：泛型類、泛型接口；泛型方法。
 *
 * 1. 關於自定義泛型類、泛型接口：
 *
 *
 *
 * @author shkstart
 * @create 2019 上午 11:09
 */
public class GenericTest1 {

    @Test
    public void test1(){
        //如果定義了泛型類，實例化沒有指明類的泛型，則認為此泛型類型為Object類型
        //要求：如果大家定義了類是帶泛型的，建議在實例化時要指明類的泛型。
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        //建議：實例化時指明類的泛型
        Order<String> order1 = new Order<String>("orderAA",1001,"order:AA");

        order1.setOrderT("AA:hello");

    }

    @Test
    public void test2(){
        SubOrder sub1 = new SubOrder();
        //由於子類在繼承帶泛型的父類時，指明了泛型類型。則實例化子類對象時，不再需要指明泛型。
        sub1.setOrderT(1122);

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("order2...");
    }

    @Test
    public void test3(){

        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        //泛型不同的引用不能相互賦值。出現編譯錯誤
//        list1 = list2;

        Person p1 = null;
        Person p2 = null;
        p1 = p2;
    }

    //測試泛型方法
    @Test
    public void test4(){
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法在調用時，指明泛型參數的類型。
        List<Integer> list = order.copyFromArrayToList(arr);

        System.out.println(list);
    }
}
```

```
/**
 * 自定義泛型類
 * @author shkstart
 * @create 2019 上午 11:05
 */
public class Order<T> {

    String orderName;
    int orderId;

    //類的內部結構就可以使用類的泛型

    T orderT;

    public Order(){
        //編譯不通過
//        T[] arr = new T[10];
        //編譯通過
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName,int orderId,T orderT){
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    //如下的三個方法都不是泛型方法
    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT){
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    //靜態方法中不能使用類的泛型。編譯不通過
    //因為靜態結構早於類的實例化，泛型是在類創建時才確定
    //show(T orderT)不是泛型方法
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

    public void show(){
        //編譯不通過
//        try{
//
//
//        }catch(T t){
//
//        }

    }

    //泛型方法：在方法中出現了泛型的結構，泛型參數與類的泛型參數沒有任何關系。
    //換句話說，泛型方法所屬的類是不是泛型類都沒有關系。
    //泛型方法，可以聲明為靜態的。原因：泛型參數是在調用方法時確定的。並非在實例化類時確定。
    public static <E>  List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }
}
```

```
/**
 * @author shkstart
 * @create 2019 上午 11:15
 */
public class SubOrder extends Order<Integer> {//SubOrder:不是泛型類


    public static <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }
}
```

```
/**
 * @author shkstart
 * @create 2019 上午 11:18
 */
public class SubOrder1<T> extends Order<T> {//SubOrder1<T>:仍然是泛型類
}
```

```
/**
 * @author shkstart
 * @create 2019 上午 11:29
 */
//異常類不能聲明為泛型類
//public class MyException<T> extends Exception{
//}

```