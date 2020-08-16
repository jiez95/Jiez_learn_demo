package factory.demo.abstractfactory;

import factory.demo.car.AbstractCar;

/**
 * @author by Jiez
 * @classname AnkieCommpany
 * @description TODO
 * @date 2020/8/15 11:34
 */
public class AnkieCommpany implements ICompanyBuildProductFactory {
    @Override
    public AbstractCar buildCar() {
        return null;
    }

    @Override
    public AbstractCar buildAircraft() {
        return null;
    }
}
