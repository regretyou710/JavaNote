```
/**
 * 1. List接口框架
 *
 *    |----Collection接口：單列集合，用來存儲一個一個的對象
 *          |----List接口：存儲有序的、可重覆的數據。  -->“動態”數組,替換原有的數組
 *              |----ArrayList：作為List接口的主要實現類；線程不安全的，效率高；底層使用Object[] elementData存儲
 *              |----LinkedList：對於頻繁的插入、刪除操作，使用此類效率比ArrayList高；底層使用雙向鏈表存儲
 *              |----Vector：作為List接口的古老實現類；線程安全的，效率低；底層使用Object[] elementData存儲
 *
 *
 *   2. ArrayList的源碼分析：
 *   2.1 jdk 7情況下
 *      ArrayList list = new ArrayList();//底層創建了長度是10的Object[]數組elementData
 *      list.add(123);//elementData[0] = new Integer(123);
 *      ...
 *      list.add(11);//如果此次的添加導致底層elementData數組容量不夠，則擴容。
 *      默認情況下，擴容為原來的容量的1.5倍，同時需要將原有數組中的數據覆制到新的數組中。
 *
 *      結論：建議開發中使用帶參的構造器：ArrayList list = new ArrayList(int capacity)
 *
 *   2.2 jdk 8中ArrayList的變化：
 *      ArrayList list = new ArrayList();//底層Object[] elementData初始化為{}.並沒有創建長度為10的數組
 *
 *      list.add(123);//第一次調用add()時，底層才創建了長度10的數組，並將數據123添加到elementData[0]
 *      ...
 *      後續的添加和擴容操作與jdk 7 無異。
 *   2.3 小結：jdk7中的ArrayList的對象的創建類似於單例的餓漢式，而jdk8中的ArrayList的對象
 *            的創建類似於單例的懶漢式，延遲了數組的創建，節省內存。
 *
 *  3. LinkedList的源碼分析：
 *      LinkedList list = new LinkedList(); 內部聲明了Node類型的first和last屬性，默認值為null
 *      list.add(123);//將123封裝到Node中，創建了Node對象。
 *
 *      其中，Node定義為：體現了LinkedList的雙向鏈表的說法
 *      private static class Node<E> {
             E item;
             Node<E> next;
             Node<E> prev;

             Node(Node<E> prev, E element, Node<E> next) {
             this.item = element;
             this.next = next;
             this.prev = prev;
             }
         }
 *
 *   4. Vector的源碼分析：jdk7和jdk8中通過Vector()構造器創建對象時，底層都創建了長度為10的數組。
 *      在擴容方面，默認擴容為原來的數組長度的2倍。
 *
 *  面試題：ArrayList、LinkedList、Vector三者的異同？
 *  同：三個類都是實現了List接口，存儲數據的特點相同：存儲有序的、可重覆的數據
 *  不同：見上
 *
 *
 *
 *   5. List接口中的常用方法
 *
 * @author shkstart
 * @create 2019 上午 11:39
 */
public class ListTest {

    /*
void add(int index, Object ele):在index位置插入ele元素
boolean addAll(int index, Collection eles):從index位置開始將eles中的所有元素添加進來
Object get(int index):獲取指定index位置的元素
int indexOf(Object obj):返回obj在集合中首次出現的位置
int lastIndexOf(Object obj):返回obj在當前集合中末次出現的位置
Object remove(int index):移除指定index位置的元素，並返回此元素
Object set(int index, Object ele):設置指定index位置的元素為ele
List subList(int fromIndex, int toIndex):返回從fromIndex到toIndex位置的子集合

總結：常用方法
增：add(Object obj)
刪：remove(int index) / remove(Object obj)
改：set(int index, Object ele)
查：get(int index)
插：add(int index, Object ele)
長度：size()
遍歷：① Iterator叠代器方式
     ② 增強for循環
     ③ 普通的循環

     */
    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator叠代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("***************");

        //方式二：增強for循環
        for(Object obj : list){
            System.out.println(obj);
        }

        System.out.println("***************");

        //方式三：普通for循環
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }



    }


    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);
        //int indexOf(Object obj):返回obj在集合中首次出現的位置。如果不存在，返回-1.
        int index = list.indexOf(4567);
        System.out.println(index);

        //int lastIndexOf(Object obj):返回obj在當前集合中末次出現的位置。如果不存在，返回-1.
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index):移除指定index位置的元素，並返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        //Object set(int index, Object ele):設置指定index位置的元素為ele
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex):返回從fromIndex到toIndex位置的左閉右開區間的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);


    }


    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1,"BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):從index位置開始將eles中的所有元素添加進來
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);
        System.out.println(list.size());//9

        //Object get(int index):獲取指定index位置的元素
        System.out.println(list.get(0));

    }
}
```

```
public class ListExer {
    /*
    區分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private void updateList(List list) {
        //remove(Object obj)=>Collection
        //remove(int index)=>List
//        list.remove(2);//因為List介面中remove()重載，實參為int 2時會調用型參為index的方法
        list.remove(new Integer(2));
    }
}

```