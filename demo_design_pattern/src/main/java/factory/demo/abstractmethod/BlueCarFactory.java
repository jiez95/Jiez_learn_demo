package factory.demo.abstractmethod;

import factory.demo.car.AbstractCar;
import factory.demo.car.BlueCar;

/**
 * @author by Jiez
 * @classname BlueCarFactory
 * @description TODO
 * @date 2020/8/15 11:30
 */
public class BlueCarFactory implements ICarFactoryMethod {
    @Override
    public AbstractCar buildCar() {
        BlueCar blueCar = new BlueCar();
        // .....省略一大段复杂构造
        return blueCar;
    }
}
