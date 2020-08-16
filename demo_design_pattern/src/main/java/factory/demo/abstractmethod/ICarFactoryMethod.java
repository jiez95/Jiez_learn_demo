package factory.demo.abstractmethod;

import factory.demo.car.AbstractCar;

/**
 * 工厂方法
 *
 * @author by Jiez
 * @classname FactoryMethod
 * @description TODO
 * @date 2020/8/15 11:27
 */
public interface ICarFactoryMethod {

    AbstractCar buildCar();

}
