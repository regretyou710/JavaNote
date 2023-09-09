# 說明流的三種分類方式
流向：輸入流、輸出流 
數據單位：字節流、字符流 
流的角色：節點流、處理流 

# 轉換流是哪兩個類，分別的作用是什麽？請分別創建兩個類的對象。
InputStreamReader:將輸入的字節流轉換為輸入的字符流。 解碼 
OutputStreamWriter：將輸出的字符流轉換為輸出的字節流。編碼 
InputStreamReader isr = new InputStreamReader(new FileInputStream(“a.txt”),”utf-8”); 
OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(“b.txt”),”gbk”); 
