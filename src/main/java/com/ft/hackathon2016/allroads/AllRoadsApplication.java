package com.ft.hackathon2016.allroads;

import com.ft.hackathon2016.allroads.config.AllRoadsConfiguration;
import com.ft.hackathon2016.allroads.resources.AllRoadsResource;

import com.ft.hackathon2016.allroads.service.SuggestedContentSvc;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class AllRoadsApplication extends Application<AllRoadsConfiguration> {

  public static void main(final String[] args) throws Exception {
    System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    new AllRoadsApplication().run(args);
  }

  @Override
  public void initialize(final Bootstrap<AllRoadsConfiguration> bootstrap) {
    // Enable variable substitution with environment variables
    bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(
                    bootstrap.getConfigurationSourceProvider(),
                    new EnvironmentVariableSubstitutor())
    );

  }

  @Override
  public void run(final AllRoadsConfiguration configuration, final Environment environment) throws Exception {

    SuggestedContentSvc suggestedContentSvc = new SuggestedContentSvc(configuration);
    environment.jersey().register(new AllRoadsResource(suggestedContentSvc));
  }
}
