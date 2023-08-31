```
/**
 * Collections:操作Collection、Map的工具類
 *
 *
 * 面試題：Collection 和 Collections的區別？
 * Collection:實現List和Set的接口
 *
 * @author shkstart
 * @create 2019 下午 4:19
 */
public class CollectionsTest {

/*
reverse(List)：反轉 List 中元素的順序
shuffle(List)：對 List 集合元素進行隨機排序
sort(List)：根據元素的自然順序對指定 List 集合元素按升序排序
sort(List，Comparator)：根據指定的 Comparator 產生的順序對 List 集合元素進行排序
swap(List，int， int)：將指定 list 集合中的 i 處元素和 j 處元素進行交換

Object max(Collection)：根據元素的自然順序，返回給定集合中的最大元素
Object max(Collection，Comparator)：根據 Comparator 指定的順序，返回給定集合中的最大元素
Object min(Collection)
Object min(Collection，Comparator)
int frequency(Collection，Object)：返回指定集合中指定元素的出現次數
void copy(List dest,List src)：將src中的內容覆制到dest中
boolean replaceAll(List list，Object oldVal，Object newVal)：使用新值替換 List 對象的所有舊值

 */
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        //報異常：IndexOutOfBoundsException("Source does not fit in dest")
//        List dest = new ArrayList();
//        Collections.copy(dest,list);
        //正確的：
        List dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest.size());//list.size();
        Collections.copy(dest,list);

        System.out.println(dest);


        /*
        Collections 類中提供了多個 synchronizedXxx() 方法，
        該方法可使將指定集合包裝成線程同步的集合，從而可以解決
        多線程並發訪問集合時的線程安全問題

         */
        //返回的list1即為線程安全的List
        List list1 = Collections.synchronizedList(list);


    }

    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(765);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);

//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
//        Collections.swap(list,1,2);
        int frequency = Collections.frequency(list, 123);

        System.out.println(list);
        System.out.println(frequency);

    }
}
```