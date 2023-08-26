# 集合Collection中存儲的如果是自定義類的對象，需要自定義類重寫哪個方法？為什麽？
equals()方法。  contains() /remove()/retainsAll() ….
List：equals()方法
Set：(HashSet、LinkedHashSet為例)：equals()、hashCode()
     (TreeSet為例)：Comparable：compareTo(Object obj)
			        Comparator：compare(Object o1,Object o2)
# ArrayList,LinkedList,Vector三者的相同點與不同點？【面試題】
List  Map  Set

# List 接口的常用方法有哪些？(增、刪、改、查、插、長度、遍歷)
* add(Object obj)
* remove(Object obj)/remove(int index)
* set(int index,Object obj)
* get(int index)
* add(int index,Object obj)
* size() 
* 使用Iterator;foreach;普通的for

# 如何使用Iterator和增強for循環遍歷List。舉例說明

# Set存儲數據的特點是什麽？常見的實現類有什麽？說明一下彼此的特點。
HashSet  LinkedHashSet  TreeSet
HashMap    LinkedHashMap   TreeMap
