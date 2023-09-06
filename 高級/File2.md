```
/**
 * 測試FileInputStream和FileOutputStream的使用
 *
 * 結論：
 * 1. 對於文本文件(.txt,.java,.c,.cpp)，使用字符流處理
 * 2. 對於非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字節流處理
 *
 *
 *
 * @author shkstart
 * @create 2019 下午 2:13
 */
public class FileInputOutputStreamTest {

    //使用字節流FileInputStream處理文本文件，可能出現亂碼。
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            //1. 造文件
            File file = new File("hello.txt");

            //2.造流
            fis = new FileInputStream(file);

            //3.讀數據
            byte[] buffer = new byte[5];
            int len;//記錄每次讀取的字節的個數
            while((len = fis.read(buffer)) != -1){

                String str = new String(buffer,0,len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                //4.關閉資源
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /*
    實現對圖片的覆制操作
     */
    @Test
    public void testFileInputOutputStream()  {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("愛情與友情.jpg");
            File destFile = new File("愛情與友情2.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //覆制的過程
            byte[] buffer = new byte[5];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    //指定路徑下文件的覆制
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //覆制的過程
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-視頻.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\02-視頻.avi";

//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";

        copyFile(srcPath,destPath);


        long end = System.currentTimeMillis();

        System.out.println("覆制操作花費的時間為：" + (end - start));//618

    }
}
```