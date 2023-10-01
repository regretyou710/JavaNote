C:\Users\Workerman-NB\Desktop\space3>jar --create --file message.jar -C mods/com.my1module .

C:\Users\Workerman-NB\Desktop\space3>jar --create --file test.jar --main-class tw.com.test.Test -C m
ods/main .

C:\Users\Workerman-NB\Desktop\space3>jmod create --class-path message.jar com.my1moudle.jmod

C:\Users\Workerman-NB\Desktop\space3>jmod create --class-path test.jar main.jmod

C:\Users\Workerman-NB\Desktop\space3>jlink --module-path parts --add-modules java.base,com.my1module
,main --output newmodule

C:\Users\Workerman-NB\Desktop\space3>C:\Users\Workerman-NB\Desktop\space3\newmodule\bin\java --modul
e main