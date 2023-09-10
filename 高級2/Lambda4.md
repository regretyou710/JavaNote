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