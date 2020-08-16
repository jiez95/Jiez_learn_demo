package single.demo;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by Jiez
 * @classname EagerSingleDemo
 * @description TODO
 * @date 2020/8/15 10:07
 */
public class EagerSingleDemo {

    /**
     * 使用volatile保持多线程可见性，且阻止指令重排
     */
    private volatile static EagerSingleDemo eagerSingleDemo;

    private static Lock lock = new ReentrantLock();

    private EagerSingleDemo() {

    }

    /**
     * case 1
     * @return
     */
    public static EagerSingleDemo getInstance() {
        if (Objects.isNull(eagerSingleDemo)) {
            return eagerSingleDemo;
        }
        synchronized (EagerSingleDemo.class) {
            if (Objects.isNull(eagerSingleDemo)) {
                return eagerSingleDemo;
            }

            eagerSingleDemo = new EagerSingleDemo();

            return eagerSingleDemo;
        }
    }

    /**
     * case 2
     * @return
     */
    public static EagerSingleDemo getInstance2() throws Exception {
        if (Objects.isNull(eagerSingleDemo)) {
            return eagerSingleDemo;
        }

        lock.lock();
        try {
            if (Objects.isNull(eagerSingleDemo)) {
                return eagerSingleDemo;
            }
            eagerSingleDemo = new EagerSingleDemo();
            return eagerSingleDemo;

        } catch (Exception e) {
            throw new Exception("get eagerSingleDemo has an error", e);

        } finally {
            lock.unlock();
        }
    }
}
