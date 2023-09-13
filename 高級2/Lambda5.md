```
/**
 * Optional類：為了在程序中避免出現空指針異常而創建的。
 *
 * 常用的方法：ofNullable(T t)
 *            orElse(T t)
 *
 * @author shkstart
 * @create 2019 下午 7:24
 */
public class OptionalTest {

/*
Optional.of(T t) : 創建一個 Optional 實例，t必須非空；
Optional.empty() : 創建一個空的 Optional 實例
Optional.ofNullable(T t)：t可以為null

 */
    @Test
    public void test1(){
        Girl girl = new Girl();
//        girl = null;
        //of(T t):保證t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);

    }

    @Test
    public void test2(){
        Girl girl = new Girl();
//        girl = null;
        //ofNullable(T t)：t可以為null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
        //orElse(T t1):如果單前的Optional內部封裝的t是非空的，則返回內部的t.
        //如果內部的t是空的，則返回orElse()方法中的參數t1.
        Girl girl1 = optionalGirl.orElse(new Girl("趙麗穎"));
        System.out.println(girl1);

    }


    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }

    @Test
    public void test3(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName(boy);
        System.out.println(girlName);

    }
    //優化以後的getGirlName():
    public String getGirlName1(Boy boy){
        if(boy != null){
            Girl girl = boy.getGirl();
            if(girl != null){
                return girl.getName();
            }
        }

        return null;

    }

    @Test
    public void test4(){
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName1(boy);
        System.out.println(girlName);

    }

    //使用Optional類的getGirlName():
    public String getGirlName2(Boy boy){

        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此時的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪麗熱巴")));

        Girl girl = boy1.getGirl();

        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("古力娜紮"));

        return girl1.getName();
    }

    @Test
    public void test5(){
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("蒼老師"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);

    }

}
```

```
public class Boy {
    private Girl girl;

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Boy() {

    }

    public Boy(Girl girl) {

        this.girl = girl;
    }
}
```

```
public class Girl {

    private String name;

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl() {

    }

    public Girl(String name) {

        this.name = name;
    }
}
```