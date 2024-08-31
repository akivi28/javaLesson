package itstep.learning;

import com.google.inject.Guice;
import itstep.learning.db.DbDemo;
import itstep.learning.fs.FileDemo;
import itstep.learning.ioc.*;
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
//          Guice.createInjector(new ServicesModule()).getInstance(IocDemo.class).run();
//          Guice.createInjector(new RandomStringServicesModule()).getInstance(IocRandomDemo.class).run();
        Guice.createInjector(new ServicesModule(), new DbModule()).getInstance(DbDemo.class).run("--journal");
   Guice.createInjector(new ServicesModule(), new DbModule()).getInstance(FileDemo.class).run();
    }
}
