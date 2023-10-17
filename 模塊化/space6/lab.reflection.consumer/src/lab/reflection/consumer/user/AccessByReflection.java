package lab.reflection.consumer.user;

import java.lang.reflect.Method;

public class AccessByReflection {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("lab.reflection.provider.api.HelloWorld");
            Method getGreeting = clazz.getMethod("getGreeting");
            System.out.println(getGreeting.invoke(clazz.getDeclaredConstructor().newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
