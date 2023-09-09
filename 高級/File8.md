```
/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接繼承於java.lang.Object類，實現了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作為一個輸入流，又可以作為一個輸出流
 *
 * 3.如果RandomAccessFile作為輸出流時，寫出到的文件如果不存在，則在執行過程中自動創建。
 *   如果寫出到的文件存在，則會對原有文件內容進行覆蓋。（默認情況下，從頭覆蓋）
 *
 * 4. 可以通過相關的操作，實現RandomAccessFile“插入”數據的效果
 *
 * @author shkstart
 * @create 2019 上午 11:18
 */
public class RandomAccessFileTest {

    @Test
    public void test1() {

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.
            raf1 = new RandomAccessFile(new File("愛情與友情.jpg"),"r");
            raf2 = new RandomAccessFile(new File("愛情與友情1.jpg"),"rw");
            //2.
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//將指針調到角標為3的位置
        raf1.write("xyz".getBytes());//

        raf1.close();

    }
    
    /*
    使用RandomAccessFile實現數據的插入效果
     */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//將指針調到角標為3的位置
        //保存指針3後面的所有數據到StringBuilder中
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());//使用String拼接效率較差
        byte[] buffer = new byte[20];
        int len;
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }
        //調回指針，寫入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //將StringBuilder中的數據寫入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：將StringBuilder替換為ByteArrayOutputStream
    }
}
```