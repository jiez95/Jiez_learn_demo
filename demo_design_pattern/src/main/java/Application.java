/**
 * @author by Jiez
 * @classname Application
 * @description TODO
 * @date 2020/8/15 10:02
 */
public class Application {

    public static void main(String[] args) {
//        new StringTest().test1();
        String a1= "我去";
        String a2 = new String("打爆你的头");
        String a3 = a1 + a2;
        String a4 = "我去打爆你的头";
        System.out.println(a3 == a4);
    }
}


class StringTest {

    public void test1() {
        String a1= "我去";
        String a2 = new String("打爆你的头");
        String a3 = a1 + a2;
        String a4 = "我去打爆你的头";
        System.out.println(a3 == a4);
    }
}

/*
class ClinitTest {

    static {
        System.out.println("加载");
    }

    public static int a = 123;

    public ClinitTest() {
        System.out.println("调用构造器");
    }
}
*/

/*
class A {

    private B a;

    public A(B a) {
        this.a = a;
    }

    public B getA() {
        return a;
    }

    public void test() {

    }

}

class B {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public B(String name) {
        this.name = name;
    }
}
 */