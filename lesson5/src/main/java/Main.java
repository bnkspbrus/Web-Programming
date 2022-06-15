import ru.itmo.wp.web.page.IndexPage;

import java.lang.reflect.Method;

enum Enum {
    ONE, TWO, THREE
}

public class Main {
    static Enum one;
    public static void main(String[] args) {
//        int i;
//        Enum two = TWO;
        System.out.println(one);
    }
}
