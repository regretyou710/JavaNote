```
/**
 * Lambda表達式的使用舉例
 *
 * @author shkstart
 * @create 2019 上午 11:30
 */
public class LambdaTest {

    @Test
    public void test1(){

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我愛北京天安門");
            }
        };

        r1.run();

        System.out.println("***********************");

        Runnable r2 = () -> System.out.println("我愛北京故宮");

        r2.run();
    }


    @Test
    public void test2(){

        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        int compare1 = com1.compare(12,21);
        System.out.println(compare1);

        System.out.println("***********************");
        //Lambda表達式的寫法
        Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);

        int compare2 = com2.compare(32,21);
        System.out.println(compare2);


        System.out.println("***********************");
        //方法引用
        Comparator<Integer> com3 = Integer :: compare;

        int compare3 = com3.compare(32,21);
        System.out.println(compare3);
    }

}
```

```
/**
 * Lambda表達式的使用
 *
 * 1.舉例： (o1,o2) -> Integer.compare(o1,o2);
 * 2.格式：
 *      -> :lambda操作符 或 箭頭操作符
 *      ->左邊：lambda形參列表 （其實就是接口中的抽象方法的形參列表）
 *      ->右邊：lambda體 （其實就是重寫的抽象方法的方法體）
 *
 * 3. Lambda表達式的使用：（分為6種情況介紹）
 *
 *    總結：
 *    ->左邊：lambda形參列表的參數類型可以省略(類型推斷)；如果lambda形參列表只有一個參數，其一對()也可以省略
 *    ->右邊：lambda體應該使用一對{}包裹；如果lambda體只有一條執行語句（可能是return語句），省略這一對{}和return關鍵字
 *
 * 4.Lambda表達式的本質：作為函數式接口的實例
 *
 * 5. 如果一個接口中，只聲明了一個抽象方法，則此接口就稱為函數式接口。我們可以在一個接口上使用 @FunctionalInterface 注解，
 *   這樣做可以檢查它是否是一個函數式接口。
 *
 * 6. 所以以前用匿名實現類表示的現在都可以用Lambda表達式來寫。
 *
 * @author shkstart
 * @create 2019 上午 11:40
 */
public class LambdaTest1 {
    //語法格式一：無參，無返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我愛北京天安門");
            }
        };

        r1.run();

        System.out.println("***********************");

        Runnable r2 = () -> {
            System.out.println("我愛北京故宮");
        };

        r2.run();
    }
    //語法格式二：Lambda 需要一個參數，但是沒有返回值。
    @Test
    public void test2(){

        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("謊言和誓言的區別是什麽？");

        System.out.println("*******************");

        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一個是聽得人當真了，一個是說的人當真了");

    }

    //語法格式三：數據類型可以省略，因為可由編譯器推斷得出，稱為“類型推斷”
    @Test
    public void test3(){

        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一個是聽得人當真了，一個是說的人當真了");

        System.out.println("*******************");

        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("一個是聽得人當真了，一個是說的人當真了");

    }

    @Test
    public void test4(){

        ArrayList<String> list = new ArrayList<>();//類型推斷

        int[] arr = {1,2,3};//類型推斷

    }

    //語法格式四：Lambda 若只需要一個參數時，參數的小括號可以省略
    @Test
    public void test5(){
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("一個是聽得人當真了，一個是說的人當真了");

        System.out.println("*******************");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("一個是聽得人當真了，一個是說的人當真了");


    }

    //語法格式五：Lambda 需要兩個或以上的參數，多條執行語句，並且可以有返回值
    @Test
    public void test6(){

        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };

        System.out.println(com1.compare(12,21));

        System.out.println("*****************************");
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com2.compare(12,6));


    }

    //語法格式六：當 Lambda 體只有一條語句時，return 與大括號若有，都可以省略
    @Test
    public void test7(){

        Comparator<Integer> com1 = (o1,o2) -> {
            return o1.compareTo(o2);
        };

        System.out.println(com1.compare(12,6));

        System.out.println("*****************************");

        Comparator<Integer> com2 = (o1,o2) -> o1.compareTo(o2);

        System.out.println(com2.compare(12,21));

    }

    @Test
    public void test8(){
        Consumer<String> con1 = s -> {
            System.out.println(s);
        };
        con1.accept("一個是聽得人當真了，一個是說的人當真了");

        System.out.println("*****************************");

        Consumer<String> con2 = s -> System.out.println(s);

        con2.accept("一個是聽得人當真了，一個是說的人當真了");

    }

}
```

```
/**
 * 自定義函數式接口
 * @author shkstart
 * @create 2019 下午 2:20
 */
@FunctionalInterface
public interface MyInterface {

    void method1();

//    void method2();
}
```