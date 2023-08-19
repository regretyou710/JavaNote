```
/**
 * 集合元素的遍歷操作，使用叠代器Iterator接口
 * 1.內部的方法：hasNext() 和  next()
 * 2.集合對象每次調用iterator()方法都得到一個全新的叠代器對象，
 * 默認遊標都在集合的第一個元素之前。
 * 3.內部定義了remove(),可以在遍歷的時候，刪除集合中的元素。此方法不同於集合直接調用remove()
 *
 * @author shkstart
 * @create 2019 上午 10:44
 */
public class IteratorTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();
        //方式一：
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //報異常：NoSuchElementException
//        System.out.println(iterator.next());

        //方式二：不推薦
//        for(int i = 0;i < coll.size();i++){
//            System.out.println(iterator.next());
//        }

        //方式三：推薦
        ////hasNext():判斷是否還有下一個元素
        while(iterator.hasNext()){
            //next():①指針下移 ②將下移以後集合位置上的元素返回
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //錯誤方式一：
//        Iterator iterator = coll.iterator();
//        while((iterator.next()) != null){
//            System.out.println(iterator.next());
//        }

        //錯誤方式二：
        //集合對象每次調用iterator()方法都得到一個全新的叠代器對象，默認遊標都在集合的第一個元素之前。
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }


    }

    //測試Iterator中的remove()
    //如果還未調用next()或在上一次調用 next 方法之後已經調用了 remove 方法，
    // 再調用remove都會報IllegalStateException。
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //刪除集合中"Tom"
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
//            iterator.remove();
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
//                iterator.remove();
            }

        }
        //遍歷集合(因為上一個iterator已經遍歷結束指針已經走完，所以要重新調用方法遍歷)
        iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
```