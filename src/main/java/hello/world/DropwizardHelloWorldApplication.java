package hello.world;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardHelloWorldApplication extends Application<DropwizardHelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardHelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard Hello World";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardHelloWorldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardHelloWorldConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
