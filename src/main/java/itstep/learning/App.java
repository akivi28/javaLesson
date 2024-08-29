package itstep.learning;

import com.google.inject.Guice;
import itstep.learning.ioc.ServicesModule;
import itstep.learning.ioc.IocDemo;
import itstep.learning.oop.Shop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        new Basics().run();
//        new Shop().run();
        Guice.createInjector(new ServicesModule()).getInstance(IocDemo.class).run();
    }
}
