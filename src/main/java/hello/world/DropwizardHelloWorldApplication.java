package hello.world;

import hello.world.health.TemplateHealthCheck;
import hello.world.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(eventDateFormat);
    }

}
