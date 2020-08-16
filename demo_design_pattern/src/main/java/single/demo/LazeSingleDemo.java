package single.demo;

/**
 * @author by Jiez
 * @classname LazeSingleDemo
 * @description TODO
 * @date 2020/8/15 10:05
 */
public class LazeSingleDemo {

    private static LazeSingleDemo lazeSingleDemo = new LazeSingleDemo();

    private LazeSingleDemo() {

    }

    public static LazeSingleDemo getInstance() {
        return lazeSingleDemo;
    }
}
