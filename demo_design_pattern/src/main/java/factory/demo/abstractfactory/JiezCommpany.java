package factory.demo.abstractfactory;

import factory.demo.car.AbstractCar;

/**
 * @author by Jiez
 * @classname JiezCommpany
 * @description TODO
 * @date 2020/8/15 11:34
 */
public class JiezCommpany implements ICompanyBuildProductFactory {

    @Override
    public AbstractCar buildCar() {
        return null;
    }

    @Override
    public AbstractCar buildAircraft() {
        return null;
    }
}
