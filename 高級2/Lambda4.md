```
/**
 * 1. Stream關注的是對數據的運算，與CPU打交道
 *    集合關注的是數據的存儲，與內存打交道
 *
 * 2.
 * ①Stream 自己不會存儲元素。
 * ②Stream 不會改變源對象。相反，他們會返回一個持有結果的新Stream。
 * ③Stream 操作是延遲執行的。這意味著他們會等到需要結果的時候才執行
 *
 * 3.Stream 執行流程
 * ① Stream的實例化
 * ② 一系列的中間操作（過濾、映射、...)
 * ③ 終止操作
 *
 * 4.說明：
 * 4.1 一個中間操作鏈，對數據源的數據進行處理
 * 4.2 一旦執行終止操作，就執行中間操作鏈，並產生結果。之後，不會再被使用
 *
 *
 *  測試Stream的實例化
 *
 * @author shkstart
 * @create 2019 下午 4:25
 */
public class StreamAPITest {

    //創建 Stream方式一：通過集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

//        default Stream<E> stream() : 返回一個順序流
        Stream<Employee> stream = employees.stream();

//        default Stream<E> parallelStream() : 返回一個並行流
        Stream<Employee> parallelStream = employees.parallelStream();

    }

    //創建 Stream方式二：通過數組
    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //調用Arrays類的static <T> Stream<T> stream(T[] array): 返回一個流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);

    }
    //創建 Stream方式三：通過Stream的of()
    @Test
    public void test3(){

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

    }

    //創建 Stream方式四：創建無限流
    @Test
    public void test4(){

//      叠代
//      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍歷前10個偶數
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);


//      生成
//      public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }

}
```

```
/**
 * 測試Stream的中間操作
 *
 * @author shkstart
 * @create 2019 下午 4:42
 */
public class StreamAPITest1 {

    //1-篩選與切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
//        filter(Predicate p)——接收 Lambda ， 從流中排除某些元素。
        Stream<Employee> stream = list.stream();
        //練習：查詢員工表中薪資大於7000的員工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();
//        limit(n)——截斷流，使其元素不超過給定數量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

//        skip(n) —— 跳過元素，返回一個扔掉了前 n 個元素的流。若流中元素不足 n 個，則返回一個空流。與 limit(n) 互補
        list.stream().skip(3).forEach(System.out::println);

        System.out.println();
//        distinct()——篩選，通過流所生成元素的 hashCode() 和 equals() 去除重覆元素

        list.add(new Employee(1010,"劉強東",40,8000));
        list.add(new Employee(1010,"劉強東",41,8000));
        list.add(new Employee(1010,"劉強東",40,8000));
        list.add(new Employee(1010,"劉強東",40,8000));
        list.add(new Employee(1010,"劉強東",40,8000));

//        System.out.println(list);

        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2(){
//        map(Function f)——接收一個函數作為參數，將元素轉換成其他形式或提取信息，該函數會被應用到每個元素上，並將其映射成一個新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

//        練習1：獲取員工姓名長度大於3的員工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();
        //練習2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });
        System.out.println();
//        flatMap(Function f)——接收一個函數作為參數，將流中的每個值都換成另一個流，然後把所有流連接成一個流。
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

    }

    //將字符串中的多個字符構成的集合轉換為對應的Stream的實例
    public static Stream<Character> fromStringToStream(String str){//aa
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
       return list.stream();

    }



    @Test
    public void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);

    }

    //3-排序
    @Test
    public void test4(){
//        sorted()——自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);
        //拋異常，原因:Employee沒有實現Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);


//        sorted(Comparator com)——定制排序

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted( (e1,e2) -> {

           int ageValue = Integer.compare(e1.getAge(),e2.getAge());
           if(ageValue != 0){
               return ageValue;
           }else{
               return -Double.compare(e1.getSalary(),e2.getSalary());
           }

        }).forEach(System.out::println);
    }

}
```

```
/**
 * 測試Stream的終止操作
 *
 * @author shkstart
 * @create 2019 下午 6:37
 */
public class StreamAPITest2 {

    //1-匹配與查找
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

//        allMatch(Predicate p)——檢查是否匹配所有元素。
//          練習：是否所有的員工的年齡都大於18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

//        anyMatch(Predicate p)——檢查是否至少匹配一個元素。
//         練習：是否存在員工的工資大於 10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

//        noneMatch(Predicate p)——檢查是否沒有匹配的元素。
//          練習：是否存在員工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
//        findFirst——返回第一個元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);
//        findAny——返回當前流中的任意元素
        Optional<Employee> employee1 = employees.parallelStream().findAny();
        System.out.println(employee1);

    }

    @Test
    public void test2(){
        List<Employee> employees = EmployeeData.getEmployees();
        // count——返回流中元素的總個數
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);
//        max(Comparator c)——返回流中最大值
//        練習：返回最高的工資：
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);
//        min(Comparator c)——返回流中最小值
//        練習：返回最低工資的員工
        Optional<Employee> employee = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employee);
        System.out.println();
//        forEach(Consumer c)——內部叠代
        employees.stream().forEach(System.out::println);

        //使用集合的遍歷操作
        employees.forEach(System.out::println);
    }

    //2-歸約
    @Test
    public void test3(){
//        reduce(T identity, BinaryOperator)——可以將流中元素反覆結合起來，得到一個值。返回 T，identity初始值
//        練習1：計算1-10的自然數的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);


//        reduce(BinaryOperator) ——可以將流中元素反覆結合起來，得到一個值。返回 Optional<T>
//        練習2：計算公司所有員工工資的總和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
//        Optional<Double> sumMoney = salaryStream.reduce(Double::sum);
        Optional<Double> sumMoney = salaryStream.reduce((d1,d2) -> d1 + d2);
        System.out.println(sumMoney.get());

    }

    //3-收集
    @Test
    public void test4(){
//        collect(Collector c)——將流轉換為其他形式。接收一個 Collector接口的實現，用於給Stream中元素做匯總的方法
//        練習1：查找工資大於6000的員工，結果返回為一個List或Set

        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());

        employeeList.forEach(System.out::println);
        System.out.println();
        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());

        employeeSet.forEach(System.out::println);
    }
}
```

```
public class Employee {

	private int id;
	private String name;
	private int age;
	private double salary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee() {
		System.out.println("Employee().....");
	}

	public Employee(int id) {
		this.id = id;
		System.out.println("Employee(int id).....");
	}

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(int id, String name, int age, double salary) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", salary=" + salary + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Employee employee = (Employee) o;

		if (id != employee.id)
			return false;
		if (age != employee.age)
			return false;
		if (Double.compare(employee.salary, salary) != 0)
			return false;
		return name != null ? name.equals(employee.name) : employee.name == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + age;
		temp = Double.doubleToLongBits(salary);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
```

```
public class EmployeeData {
	
	public static List<Employee> getEmployees(){
		List<Employee> list = new ArrayList<>();
		
		list.add(new Employee(1001, "馬化騰", 34, 6000.38));
		list.add(new Employee(1002, "馬雲", 12, 9876.12));
		list.add(new Employee(1003, "劉強東", 33, 3000.82));
		list.add(new Employee(1004, "雷軍", 26, 7657.37));
		list.add(new Employee(1005, "李彥宏", 65, 5555.32));
		list.add(new Employee(1006, "比爾蓋茨", 42, 9500.43));
		list.add(new Employee(1007, "任正非", 26, 4333.32));
		list.add(new Employee(1008, "紮克伯格", 35, 2500.32));
		
		return list;
	}
	
}
```