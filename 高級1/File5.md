```
/**
 * 其他流的使用
 * 1.標準的輸入、輸出流
 * 2.打印流
 * 3.數據流
 *
 * @author shkstart
 * @create 2019 下午 6:11
 */
public class OtherStreamTest {

    /*
    1.標準的輸入、輸出流
    1.1
    System.in:標準的輸入流，默認從鍵盤輸入
    System.out:標準的輸出流，默認從控制台輸出
    1.2
    System類的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定輸入和輸出的流。

    1.3練習：
    從鍵盤輸入字符串，要求將讀取到的整行字符串轉成大寫輸出。然後繼續進行輸入操作，
    直至當輸入“e”或者“exit”時，退出程序。

    方法一：使用Scanner實現，調用next()返回一個字符串
    方法二：使用System.in實現。System.in  --->  轉換流 ---> BufferedReader的readLine()

     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("請輸入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序結束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /*
    2. 打印流：PrintStream 和PrintWriter

    2.1 提供了一系列重載的print() 和 println()
    2.2 練習：
     */

    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\IO\\text.txt"));
            // 創建打印輸出流,設置為自動刷新模式(寫入換行符或字節 '\n' 時都會刷新輸出緩沖區)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把標準輸出流(控制台輸出)改成文件
                System.setOut(ps);
            }


            for (int i = 0; i <= 255; i++) { // 輸出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50個數據一行
                    System.out.println(); // 換行
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

    /*
    3. 數據流
    3.1 DataInputStream 和 DataOutputStream
    3.2 作用：用於讀取或寫出基本數據類型的變量或字符串

    練習：將內存中的字符串、基本數據類型的變量寫出到文件中。

    注意：處理異常的話，仍然應該使用try-catch-finally.
     */
    @Test
    public void test3() throws IOException {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2.
        dos.writeUTF("劉建辰");
        dos.flush();//刷新操作，將內存中的數據寫入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        //3.
        dos.close();


    }
    /*
    將文件中存儲的基本數據類型變量和字符串讀取到內存中，保存在變量中。

    注意點：讀取不同類型的數據的順序要與當初寫入文件時，保存的數據的順序一致！

     */
    @Test
    public void test4() throws IOException {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        //2.
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);

        //3.
        dis.close();

    }
}
```