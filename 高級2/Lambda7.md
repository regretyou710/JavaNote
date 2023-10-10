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