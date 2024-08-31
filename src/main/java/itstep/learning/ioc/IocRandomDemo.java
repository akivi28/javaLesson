package itstep.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import itstep.learning.services.randomString.FileNameGenerator;
import itstep.learning.services.randomString.OtpGenerator;
import itstep.learning.services.randomString.PasswordGenerator;
import itstep.learning.services.randomString.SaltGenerator;

import java.util.Random;

public class IocRandomDemo {
    private final FileNameGenerator fileNameGenerator;
    private final OtpGenerator otpGenerator;
    private final PasswordGenerator passwordGenerator;
    private final SaltGenerator saltGenerator;

    @Inject
    public IocRandomDemo(@Named("fileName") FileNameGenerator fileNameGenerator,
                   @Named("otp") OtpGenerator otpGenerator,
                   @Named("password") PasswordGenerator passwordGenerator,
                   @Named("salt") SaltGenerator saltGenerator){
        this.fileNameGenerator = fileNameGenerator;
        this.otpGenerator = otpGenerator;
        this.passwordGenerator = passwordGenerator;
        this.saltGenerator = saltGenerator;
    }

    public void run(){
        System.out.println("file name: " + fileNameGenerator.generate());
        System.out.println("one time password: " + otpGenerator.generate());
        System.out.println("password: " +passwordGenerator.generate());
        System.out.println("salt: " + saltGenerator.generate());
    }
}
