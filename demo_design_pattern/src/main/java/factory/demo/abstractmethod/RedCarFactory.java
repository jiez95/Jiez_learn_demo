package factory.demo.abstractmethod;

import factory.demo.car.AbstractCar;
import factory.demo.car.RedCar;

/**
 * @author by Jiez
 * @classname a
 * @description TODO
 * @date 2020/8/15 11:29
 */
public class RedCarFactory implements ICarFactoryMethod {


    @Override
    public AbstractCar buildCar() {
        RedCar redCar = new RedCar();
        // .....省略一大段复杂构造
        return redCar;
    }
}
