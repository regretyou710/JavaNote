```
/**
 * java內置的4大核心函數式接口
 *
 * 消費型接口 Consumer<T>     void accept(T t)
 * 供給型接口 Supplier<T>     T get()
 * 函數型接口 Function<T,R>   R apply(T t)
 * 斷定型接口 Predicate<T>    boolean test(T t)
 *
 *
 * @author shkstart
 * @create 2019 下午 2:29
 */
public class LambdaTest2 {

    @Test
    public void test1(){

        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("學習太累了，去天上人間買了瓶礦泉水，價格為：" + aDouble);
            }
        });

        System.out.println("********************");

        happyTime(400,money -> System.out.println("學習太累了，去天上人間喝了口水，價格為：" + money));
    }

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }


    @Test
    public void test2(){
        List<String> list = Arrays.asList("北京","南京","天津","東京","西京","普京");

        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });

        System.out.println(filterStrs);


        List<String> filterStrs1 = filterString(list,s -> s.contains("京"));
        System.out.println(filterStrs1);
    }

    //根據給定的規則，過濾集合中的字符串。此規則由Predicate的方法決定
    public List<String> filterString(List<String> list, Predicate<String> pre){

        ArrayList<String> filterList = new ArrayList<>();

        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }

        return filterList;

    }

}
```