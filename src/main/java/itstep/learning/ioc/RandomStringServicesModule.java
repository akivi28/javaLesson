package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import itstep.learning.services.randomString.*;
import com.google.inject.name.Names;

public class RandomStringServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FileNameGenerator.class).annotatedWith(Names.named("fileName")).to(FileNameGenerator.class);
        bind(OtpGenerator.class).annotatedWith(Names.named("otp")).to(OtpGenerator.class);
        bind(PasswordGenerator.class).annotatedWith(Names.named("password")).to(PasswordGenerator.class);
        bind(SaltGenerator.class).annotatedWith(Names.named("salt")).to(SaltGenerator.class);
    }
}
