```
```
/**
 * 處理流之一：緩沖流的使用
 *
 * 1.緩沖流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2.作用：提供流的讀取、寫入的速度
 *   提高讀寫速度的原因：內部提供了一個緩沖區
 *
 * 3. 處理流，就是“套接”在已有的流的基礎上。
 *
 * @author shkstart
 * @create 2019 下午 2:44
 */
public class BufferedTest {

    /*
    實現非文本文件的覆制
     */
    @Test
    public void BufferedStreamTest() throws FileNotFoundException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File("愛情與友情.jpg");
            File destFile = new File("愛情與友情3.jpg");
            //2.造流
            //2.1 造節點流
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造緩沖流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.覆制的細節：讀取、寫入
            byte[] buffer = new byte[10];
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);

//                bos.flush();//刷新緩沖區

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.資源關閉
            //要求：先關閉外層的流，再關閉內層的流
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //說明：關閉外層流的同時，內層流也會自動的進行關閉。關於內層流的關閉，我們可以省略.
//        fos.close();
//        fis.close();
        }



    }

    //實現文件覆制的方法
    public void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
            //2.造流
            //2.1 造節點流
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造緩沖流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.覆制的細節：讀取、寫入
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.資源關閉
            //要求：先關閉外層的流，再關閉內層的流
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //說明：關閉外層流的同時，內層流也會自動的進行關閉。關於內層流的關閉，我們可以省略.
//        fos.close();
//        fis.close();
        }
    }

    @Test
    public void testCopyFileWithBuffered(){
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-視頻.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\03-視頻.avi";


        copyFileWithBuffered(srcPath,destPath);


        long end = System.currentTimeMillis();

        System.out.println("覆制操作花費的時間為：" + (end - start));//618 - 176
    }


    /*
    使用BufferedReader和BufferedWriter實現文本文件的覆制

     */
    @Test
    public void testBufferedReaderBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //創建文件和相應的流
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            //讀寫操作
            //方式一：使用char[]數組
//            char[] cbuf = new char[1024];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//                bw.write(cbuf,0,len);
//    //            bw.flush();
//            }

            //方式二：使用String
            String data;
            while((data = br.readLine()) != null){
                //方法一：
//                bw.write(data + "\n");//data中不包含換行符
                //方法二：
                bw.write(data);//data中不包含換行符
                bw.newLine();//提供換行的操作

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //關閉資源
            if(bw != null){

                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
```