package factory.demo.abstractfactory;

import factory.demo.car.AbstractCar;

/**
 * @author by Jiez
 * @classname ICompanyBuildProdutFactory
 * @description TODO
 * @date 2020/8/15 11:32
 */
public interface ICompanyBuildProductFactory {

    AbstractCar buildCar();

    AbstractCar buildAircraft();
}
