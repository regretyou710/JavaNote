package zoo.legacy;

import sun.misc.Unsafe;

import java.time.LocalDate;
import java.util.List;

public class UnsafeBean {
    private List<String> list;
    private LocalDate date;

    public UnsafeBean(List<String> list, LocalDate date) {
        this.list = list;
        this.date = date;
    }

    public void unsafeMethod() {
        Unsafe unsafe = Unsafe.getUnsafe();
    }
}
