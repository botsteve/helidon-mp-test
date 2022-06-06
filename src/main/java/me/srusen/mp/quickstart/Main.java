package me.srusen.mp.quickstart;

import io.helidon.config.Config;
import io.helidon.config.ConfigSources;
import io.helidon.config.PollingStrategies;
import io.helidon.microprofile.server.Server;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {
        Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/greet");
    }

    static Server startServer() {
        return Server.builder()
//                .config(config())
                .build()
                .start();
    }

    private static Config config() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        return Config.create(
                ConfigSources.file("application.yaml")
                        .pollingStrategy(
                                PollingStrategies.regular(Duration.ofSeconds(1))
                                        .executor(executor)));
    }
}