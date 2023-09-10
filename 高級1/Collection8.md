```
/**
 * 一、Map的實現類的結構：
 *  |----Map:雙列數據，存儲key-value對的數據   ---類似於高中的函數：y = f(x)
 *         |----HashMap:作為Map的主要實現類；線程不安全的，效率高；存儲null的key和value
 *              |----LinkedHashMap:保證在遍歷map元素時，可以按照添加的順序實現遍歷。
 *                      原因：在原有的HashMap底層結構基礎上，添加了一對指針，指向前一個和後一個元素。
 *                      對於頻繁的遍歷操作，此類執行效率高於HashMap。
 *         |----TreeMap:保證按照添加的key-value對進行排序，實現排序遍歷。此時考慮key的自然排序或定制排序
 *                      底層使用紅黑樹
 *         |----Hashtable:作為古老的實現類；線程安全的，效率低；不能存儲null的key和value
 *              |----Properties:常用來處理配置文件。key和value都是String類型
 *
 *
 *      HashMap的底層：數組+鏈表  （jdk7及之前）
 *                    數組+鏈表+紅黑樹 （jdk 8）
 *
 *
 *  面試題：
 *  1. HashMap的底層實現原理？
 *  2. HashMap 和 Hashtable的異同？
 *  3. CurrentHashMap 與 Hashtable的異同？（暫時不講）
 *
 *  二、Map結構的理解：
 *    Map中的key:無序的、不可重覆的，使用Set存儲所有的key  ---> key所在的類要重寫equals()和hashCode() （以HashMap為例）
 *    Map中的value:無序的、可重覆的，使用Collection存儲所有的value --->value所在的類要重寫equals()
 *    一個鍵值對：key-value構成了一個Entry對象。
 *    Map中的entry:無序的、不可重覆的，使用Set存儲所有的entry
 *
 *  三、HashMap的底層實現原理？以jdk7為例說明：
 *      HashMap map = new HashMap():
 *      在實例化以後，底層創建了長度是16的一維數組Entry[] table。
 *      ...可能已經執行過多次put...
 *      map.put(key1,value1):
 *      首先，調用key1所在類的hashCode()計算key1哈希值，此哈希值經過某種算法計算以後，得到在Entry數組中的存放位置。
 *      如果此位置上的數據為空，此時的key1-value1添加成功。 ----情況1
 *      如果此位置上的數據不為空，(意味著此位置上存在一個或多個數據(以鏈表形式存在)),比較key1和已經存在的一個或多個數據
 *      的哈希值：
 *              如果key1的哈希值與已經存在的數據的哈希值都不相同，此時key1-value1添加成功。----情況2
 *              如果key1的哈希值和已經存在的某一個數據(key2-value2)的哈希值相同，繼續比較：調用key1所在類的equals(key2)方法，比較：
 *                      如果equals()返回false:此時key1-value1添加成功。----情況3
 *                      如果equals()返回true:使用value1替換value2。
 *
 *       補充：關於情況2和情況3：此時key1-value1和原來的數據以鏈表的方式存儲。
 *
 *      在不斷的添加過程中，會涉及到擴容問題，當超出臨界值(且要存放的位置非空)時，擴容。默認的擴容方式：擴容為原來容量的2倍，並將原有的數據覆制過來。
 *
 *      jdk8 相較於jdk7在底層實現方面的不同：
 *      1. new HashMap():底層沒有創建一個長度為16的數組
 *      2. jdk 8底層的數組是：Node[],而非Entry[]
 *      3. 首次調用put()方法時，底層創建長度為16的數組
 *      4. jdk7底層結構只有：數組+鏈表。jdk8中底層結構：數組+鏈表+紅黑樹。
 *         4.1 形成鏈表時，七上八下（jdk7:新的元素指向舊的元素。jdk8：舊的元素指向新的元素）
           4.2 當數組的某一個索引位置上的元素以鏈表形式存在的數據個數 > 8 且當前數組的長度 > 64時，此時此索引位置上的所數據改為使用紅黑樹存儲。
 *
 *      DEFAULT_INITIAL_CAPACITY : HashMap的默認容量，16
 *      DEFAULT_LOAD_FACTOR：HashMap的默認加載因子：0.75
 *      threshold：擴容的臨界值，=容量*填充因子：16 * 0.75 => 12
 *      TREEIFY_THRESHOLD：Bucket中鏈表長度大於該默認值，轉化為紅黑樹:8
 *      MIN_TREEIFY_CAPACITY：桶中的Node被樹化時最小的hash表容量:64
 *
 *  四、LinkedHashMap的底層實現原理（了解）
 *      源碼中：
 *      static class Entry<K,V> extends HashMap.Node<K,V> {
             Entry<K,V> before, after;//能夠記錄添加的元素的先後順序
             Entry(int hash, K key, V value, Node<K,V> next) {
                super(hash, key, value, next);
             }
         }
 *
 *
 *   五、Map中定義的方法：
 添加、刪除、修改操作：
 Object put(Object key,Object value)：將指定key-value添加到(或修改)當前map對象中
 void putAll(Map m):將m中的所有key-value對存放到當前map中
 Object remove(Object key)：移除指定key的key-value對，並返回value
 void clear()：清空當前map中的所有數據
 元素查詢的操作：
 Object get(Object key)：獲取指定key對應的value
 boolean containsKey(Object key)：是否包含指定的key
 boolean containsValue(Object value)：是否包含指定的value
 int size()：返回map中key-value對的個數
 boolean isEmpty()：判斷當前map是否為空
 boolean equals(Object obj)：判斷當前map和參數對象obj是否相等
 元視圖操作的方法：
 Set keySet()：返回所有key構成的Set集合
 Collection values()：返回所有value構成的Collection集合
 Set entrySet()：返回所有key-value對構成的Set集合

 *總結：常用方法：
 * 添加：put(Object key,Object value)
 * 刪除：remove(Object key)
 * 修改：put(Object key,Object value)
 * 查詢：get(Object key)
 * 長度：size()
 * 遍歷：keySet() / values() / entrySet()
 *
 *
 * @author shkstart
 * @create 2019 上午 11:15
 */
public class MapTest {

    /*
 元視圖操作的方法：
 Set keySet()：返回所有key構成的Set集合
 Collection values()：返回所有value構成的Collection集合
 Set entrySet()：返回所有key-value對構成的Set集合

     */


    @Test
    public void test5(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,1234);
        map.put("BB",56);

        //遍歷所有的key集：keySet()
        Set set = map.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
        }
        System.out.println();
        //遍歷所有的value集：values()
        Collection values = map.values();
        for(Object obj : values){
            System.out.println(obj);
        }
        System.out.println();
        //遍歷所有的key-value
        //方式一：entrySet()
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }
        System.out.println();
        //方式二：
        Set keySet = map.keySet();
        Iterator iterator2 = keySet.iterator();
        while(iterator2.hasNext()){
            Object key = iterator2.next();
            Object value = map.get(key);
            System.out.println(key + "=====" + value);

        }

    }


    /*
 元素查詢的操作：
 Object get(Object key)：獲取指定key對應的value
 boolean containsKey(Object key)：是否包含指定的key
 boolean containsValue(Object value)：是否包含指定的value
 int size()：返回map中key-value對的個數
 boolean isEmpty()：判斷當前map是否為空
 boolean equals(Object obj)：判斷當前map和參數對象obj是否相等
     */
    @Test
    public void test4(){
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        // Object get(Object key)
        System.out.println(map.get(45));
        //containsKey(Object key)
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);

        isExist = map.containsValue(123);
        System.out.println(isExist);

        map.clear();

        System.out.println(map.isEmpty());

    }

    /*
     添加、刪除、修改操作：
 Object put(Object key,Object value)：將指定key-value添加到(或修改)當前map對象中
 void putAll(Map m):將m中的所有key-value對存放到當前map中
 Object remove(Object key)：移除指定key的key-value對，並返回value
 void clear()：清空當前map中的所有數據
     */
    @Test
    public void test3(){
        Map map = new HashMap();
        //添加
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        //修改
        map.put("AA",87);

        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);

        map.putAll(map1);

        System.out.println(map);

        //remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        //clear()
        map.clear();//與map = null操作不同
        System.out.println(map.size());
        System.out.println(map);
    }

    @Test
    public void test2(){
        Map map = new HashMap();
        map = new LinkedHashMap();
        map.put(123,"AA");
        map.put(345,"BB");
        map.put(12,"CC");

        System.out.println(map);
    }


    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new Hashtable();
        map.put(null,123);

    }
}
```