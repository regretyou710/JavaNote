package lab.reflection.consumer.user;

import lab.reflection.provider.api.HelloWorld;

public class AccessByNormal {
    public static void main(String[] args) {
        try {
            HelloWorld om = new HelloWorld();
            System.out.println(om.getGreeting());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
