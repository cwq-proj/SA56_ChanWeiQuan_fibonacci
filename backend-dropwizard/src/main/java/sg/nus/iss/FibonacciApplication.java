package sg.nus.iss;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sg.nus.iss.resources.FibonacciResource;

public class FibonacciApplication extends Application<FibonacciConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FibonacciApplication().run(args);
    }

    @Override
    public String getName() {
        return "Fibonacci";
    }

    @Override
    public void initialize(Bootstrap<FibonacciConfiguration> bootstrap) {

    }

    @Override
    public void run(final FibonacciConfiguration configuration,
            final Environment environment) {
        final FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        corsFilter.setInitParameter("allowedOrigins", "*");
        corsFilter.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        corsFilter.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        environment.jersey().register(new FibonacciResource());
    }

}
