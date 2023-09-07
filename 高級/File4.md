```
/**
 * 處理流之二：轉換流的使用
 * 1.轉換流：屬於字符流
 *   InputStreamReader：將一個字節的輸入流轉換為字符的輸入流
 *   OutputStreamWriter：將一個字符的輸出流轉換為字節的輸出流
 *
 * 2.作用：提供字節流與字符流之間的轉換
 *
 * 3. 解碼：字節、字節數組  --->字符數組、字符串
 *    編碼：字符數組、字符串 ---> 字節、字節數組
 *
 *
 * 4.字符集
 *ASCII：美國標準信息交換碼。
    用一個字節的7位可以表示。
 ISO8859-1：拉丁碼表。歐洲碼表
    用一個字節的8位表示。
 GB2312：中國的中文編碼表。最多兩個字節編碼所有字符
 GBK：中國的中文編碼表升級，融合了更多的中文文字符號。最多兩個字節編碼
 Unicode：國際標準碼，融合了目前人類使用的所有字符。為每個字符分配唯一的字符碼。所有的文字都用兩個字節來表示。
 UTF-8：變長的編碼方式，可用1-4個字節來表示一個字符。

 *
 *
 * @author shkstart
 * @create 2019 下午 4:25
 */
public class InputStreamReaderTest {

    /*
    此時處理異常的話，仍然應該使用try-catch-finally
    InputStreamReader的使用，實現字節的輸入流到字符的輸入流的轉換
     */
    @Test
    public void test1() throws IOException {

        FileInputStream fis = new FileInputStream("dbcp.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//使用系統默認的字符集
        //參數2指明了字符集，具體使用哪個字符集，取決於文件dbcp.txt保存時使用的字符集
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//使用系統默認的字符集

        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.print(str);
        }

        isr.close();

    }

    /*
    此時處理異常的話，仍然應該使用try-catch-finally

    綜合使用InputStreamReader和OutputStreamWriter
     */
    @Test
    public void test2() throws Exception {
        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        //2.讀寫過程
        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }

        //3.關閉資源
        isr.close();
        osw.close();
    }
}
```