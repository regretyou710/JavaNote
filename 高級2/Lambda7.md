# stream
```
public static void main(String[] args) {
    IntStream.of(new int[]{3, 4, 5, 6}).forEach(System.out::println);//3 4 5 6

//        IntStream.of(3, 4, 5, 6).forEach(System.out::println);//3 4 5 6
//        Stream.of(new int[]{3, 4, 5, 6}).forEach(System.out::println);//[I@2acf57e3
//        Stream.of(3, 4, 5, 6).forEach(System.out::println);//3 4 5 6

    IntStream.range(3,7).forEach(System.out::println);//3 4 5 6
    IntStream.rangeClosed(3,7).forEach(System.out::println);//3 4 5 6 7
}
```

```
   public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("hello", "world", "helloworld");

        //流轉換集合 方式一
//        List<String> l1 = stringStream.collect(Collectors.toList());
//        l1.forEach(System.out::println);

        //流轉換集合 方式二
//        List<String> l2 = stringStream.collect(ArrayList::new,
//                (list, item) -> list.add(item),
//                (list1, list2) -> list1.addAll(list2));
//        l2.forEach(System.out::println);

        //流轉換集合 方式三
        /**
         * 參數一:最終要返回的集合
         * 參數二:源集合創建的流中，將每個元素添加到參數一的集合，並產生新的流
         * 參數三:將添加過程中產生的流合併，並添加到參數一的集合
         */
        List<String> l3 = stringStream.collect(ArrayList::new,
                ArrayList::add,
                ArrayList::addAll);
        l3.forEach(System.out::println);

        /*
        在您提供的程式碼中，`l3` 可以使用方法參考是因為 `ArrayList::add` 和 `ArrayList::addAll` 都是適用於方法參考的方法參考的形式。
        在這裡，方法引用的參數和返回類型與 `collect` 方法所期望的 `BiConsumer<R, ? super T>` 參數相符。
        讓我們來解釋為什麼 `ArrayList::add` 和 `ArrayList::addAll` 可以當作方法參考使用：

        1. `ArrayList::add` 方法參考：
            - `ArrayList::add` 是一個實例方法引用，它的參數是一個元素，類型是方法引用所在類別的類型（在這種情況下是 `ArrayList`）。
            - `BiConsumer<R, ? super T>` 的第二個參數是一個元素，因此 `ArrayList::add` 的參數與 `BiConsumer` 介面的參數類型相符。

        2. `ArrayList::addAll` 方法參考：
            - `ArrayList::addAll` 是一個實例方法引用，它的參數是一個集合，類型也是方法引用所在類別的類型（在這種情況下是 `ArrayList`）。
            - `BiConsumer<R, ? super T>` 的第二個參數是一個元素，但是因為 `addAll` 接受一個集合作為參數，
            所以 `ArrayList::addAll` 的參數與 `BiConsumer` 介面的參數類型相符。

        因此，`ArrayList::add` 和 `ArrayList::addAll` 可以直接用作方法引用，而不需要明確編寫 Lambda 表達式。
        在這裡，不需要相同的返回值類型，只需要參數類型匹配。
        需要注意的是，方法引用的參數類型必須匹配函數式介面的參數類型，但不要求傳回值類型匹配。 這是 Java 中方法引用的靈活性之一。
         */
}
```

```
public static void main(String[] args) {
    //找出流中大於2的元素，然後將每個元素乘以2，然後忽略掉流中的前兩個元素，然後再取流中的前兩個元素，最後求出流中元素的總和
    Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
    IntStream intStream = stream.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2);//使用mapToInt是避免自動拆裝箱損失效能
//        int sum = intStream.sum();
//        System.out.println(sum);

    IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();
    //求和
    System.out.println(intSummaryStatistics.getSum());//32

    //求最大值
    System.out.println(intSummaryStatistics.getMax());//18

    //求最小值
    System.out.println(intSummaryStatistics.getMin());//14

    //case:篩選後沒有元素
    Stream<Integer> stream2 = Stream.iterate(1, item -> item + 2).limit(6);
    intStream = stream2.filter(item -> item > 200).mapToInt(item -> item * 2).skip(2).limit(2);
    intSummaryStatistics = intStream.summaryStatistics();

    //求和
    System.out.println(intSummaryStatistics.getSum());//0

    //求最大值
    System.out.println(intSummaryStatistics.getMax());//-2147483648

    //求最小值
    System.out.println(intSummaryStatistics.getMin());//2147483647
}
```

# 串行流與並行流
```
public static void main(String[] args) {
    List<String> list = new ArrayList<>(5000000);
    for (int i = 0; i < 5000000; i++) {
        list.add(UUID.randomUUID().toString());
    }

    //串行流
//        System.out.println("開始排序");
//        long startTime = System.nanoTime();
//        list.stream().sorted().count();
//        long endTime = System.nanoTime();
//        System.out.println("排序耗時:" + TimeUnit.NANOSECONDS.toMicros(endTime - startTime) + "微秒");

    //並行流
    System.out.println("開始排序");
    long startTime = System.nanoTime();
    list.parallelStream().sorted().count();
    long endTime = System.nanoTime();
    System.out.println("排序耗時:" + TimeUnit.NANOSECONDS.toMicros(endTime - startTime) + "微秒");     
}
```

# 流的運作
![Lambda9.jpg](../img/高級2/Lambda9.jpg)
![Lambda10.jpg](../img/高級2/Lambda10.jpg)
```
public static void main(String[] args) {
    List<String> list = Arrays.asList("hello", "world", "hello world");
//        list.stream().mapToInt(data -> data.length()).filter(data -> data == 5).findFirst().ifPresent(System.out::println);

    list.stream().mapToInt(data -> {
        System.out.println(data);
        return data.length();
    }).filter(data -> data == 5).findFirst().ifPresent(System.out::println);//hello 5

    System.out.println("------");

    List<String> list2 = Arrays.asList("hello!", "world", "hello world");
    list2.stream().mapToInt(data -> {
        System.out.println(data);
        return data.length();
    }).filter(data -> data == 5).findFirst().ifPresent(System.out::println);//hello! world 5

    System.out.println("------");

    List<String> list3 = Arrays.asList("hello!", "world!", "hello world");
    list3.stream().mapToInt(data -> {
        System.out.println(data);
        return data.length();
    }).filter(data -> data == 5).findFirst().ifPresent(System.out::println);//hello! world! hello world

    /**
    * 流的運作:
    * 第一個元素執行完第一個操作後再執行第二個操作...，再換第二個元素執行第一個操作後再執行第二個操作...
    */        
}
```

# 練習一 flatMap
```
public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        //list.stream().map(data -> data.split(" ")).distinct().forEach(System.out::println);
        //[Ljava.lang.String;@71be98f5
        //[Ljava.lang.String;@6fadae5d
        //[Ljava.lang.String;@17f6480
        //[Ljava.lang.String;@2d6e8792

        list.stream().map(data -> data.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
        // hello
        // welcome
        // world
    }
}

```

# 練習二 flatMap
```
public static void main(String[] args) {
    List<String> list1 = Arrays.asList("hi", "hello", "你好");
    List<String> list2 = Arrays.asList("Tom", "Mary", "Eason", "Judy");
    list1.stream().flatMap(data1 ->
            list2.stream().map(data2 -> data1 + " " + data2)
    ).forEach(System.out::println);

//        hi Tom
//        hi Mary
//        hi Eason
//        hi Judy
//        hello Tom
//        hello Mary
//        hello Eason
//        hello Judy
//        你好 Tom
//        你好 Mary
//        你好 Eason
//        你好 Judy
}

public static void main(String[] args) {
    List<String> list1 = Arrays.asList("hi", "hello", "你好");
    List<String> list2 = Arrays.asList("Tom", "Mary", "Eason", "Judy");
    list1.stream().map(data1 ->
            list2.stream().map(data2 -> data1 + " " + data2)
    ).forEach(System.out::println);

//        java.util.stream.ReferencePipeline$3@17c68925
//        java.util.stream.ReferencePipeline$3@7e0ea639
//        java.util.stream.ReferencePipeline$3@3d24753a

       /**
         * 在內部嵌套的 `map` 操作中創建了一個 Stream 流，但並沒有實際對該流進行終結操作，所以它們只是 Stream 流的引用，而不是實際的結果。
         * 當您使用 `forEach` 方法叠代這些 Stream 流時，它們只是 Stream 流的對象引用，而不會執行實際的操作。
         * Stream 流的引用指的是一個指向 Stream 對象的引用，而不是 Stream 流的實際內容。在你的原始代碼中，以下部分是關鍵：
         *
         * ```java
         * list1.stream().map(data1 ->
         *     list2.stream().map(data2 -> data1 + " " + data2)
         * ).forEach(System.out::println);
         * ```
         *
         * 這里，`list1.stream()` 創建了一個 Stream 流，然後使用 `map` 操作，將 `list2.stream().map(data2 -> data1 + " " + data2)` 
         * 作為映射函數。但是，這里的 `map` 操作僅返回一個 Stream 流的引用，而不是將每個元素實際映射到新的值上。
         *
         * 所以，`list2.stream().map(data2 -> data1 + " " + data2)` 返回的是一個 Stream 流對象的引用，而不是實際的結果。
         * 當您在 `forEach` 中打印這些引用時，您會看到 `java.util.stream.ReferencePipeline$3@...` 這樣的輸出，
         * 其中 `ReferencePipeline$3` 是 Stream 流的類名稱，`@...` 是對象的哈希碼。
         *
         * 為了得到實際的結果，您需要使用 `flatMap` 操作，將嵌套的 Stream 合並成一個單一的 Stream，然後才能執行實際的操作，如之前的示例所示。
         */
}
```

# 練習三 groupingBy
```
public static void main(String[] args) {
    Student stu1 = new Student("Tom", 18, 98);
    Student stu2 = new Student("Eason", 18, 76);
    Student stu3 = new Student("Judy", 17, 83);
    Student stu4 = new Student("Tom", 20, 83);
    List<Student> list = Arrays.asList(stu1, stu2, stu3, stu4);

    Map<String, List<Student>> map1 = list.stream().collect(Collectors.groupingBy(Student::getName));
    map1.forEach((k, v) -> System.out.println(k + " " + v));
    /**
        * Judy [Student{name='Judy', age=17, score=83}]
        * Tom [Student{name='Tom', age=18, score=98}, Student{name='Tom', age=20, score=83}]
        * Eason [Student{name='Eason', age=18, score=76}]
        */

    System.out.println("------");

    Map<Integer, List<Student>> map2 = list.stream().collect(Collectors.groupingBy(Student::getScore));
    map2.forEach((k, v) -> System.out.println(k + " " + v));
    /**
        * 98 [Student{name='Tom', age=18, score=98}]
        * 83 [Student{name='Judy', age=17, score=83}, Student{name='Tom', age=20, score=83}]
        * 76 [Student{name='Eason', age=18, score=76}]
        */

    System.out.println("------");

    Map<String, Long> map3 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
    map3.forEach((k, v) -> System.out.println(k + " " + v + "位"));
    /**
        * Judy 1位
        * Tom 2位
        * Eason 1位
        */

    System.out.println("------");

    Map<String, Double> map4 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingInt(Student::getScore)));
    map4.forEach((k, v) -> System.out.println(k + " 平均:" + v));
    /**
        * Judy 平均:83.0
        * Tom 平均:90.5
        * Eason 平均:76.0
        */
}
```

# 練習四 partitioningBy
```
public static void main(String[] args) {
    Student stu1 = new Student("Tom", 18, 98);
    Student stu2 = new Student("Eason", 18, 76);
    Student stu3 = new Student("Judy", 17, 83);
    Student stu4 = new Student("Tom", 20, 83);
    List<Student> list = Arrays.asList(stu1, stu2, stu3, stu4);

    //分區:分組的一種變形(只會有兩種結果)
    Map<Boolean, List<Student>> map1 = list.stream().collect(Collectors.partitioningBy(data -> data.getScore() > 80));
    map1.forEach((k, v) -> System.out.println(k + " " + v));
    /**
        * false [Student{name='Eason', age=18, score=76}]
        * true [Student{name='Tom', age=18, score=98}, Student{name='Judy', age=17, score=83}, Student{name='Tom', age=20, score=83}]
        */
}
```

# collect 收集器
1. Collector作為collect方法的參數
2. Collector是一個接口，它是一個可變的匯聚操作，將輸入元素累積到一個可變的結果容器中;
它會在所有元素都處理完畢後，將累積的結果轉換為一個最終的表示(這是一個可選操作);
它支持串行與並行兩種方式執行。	
3. Collectors本身提供了關於Collector的常見匯聚實現，Collectors本身實際上是一個工廠。
4. 為了確保串行與並行操作結果的等價性，Collector函數須滿足兩個條件:identity(同一性)與associativity(結合性)。
5. a == combiner.apply(a, supplier.get()) // (List<String> list1, List<String> list2) -> {list1.addAll(list2); return list1;}
