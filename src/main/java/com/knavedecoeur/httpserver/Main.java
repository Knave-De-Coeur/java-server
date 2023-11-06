package com.knavedecoeur.httpserver;

import com.knavedecoeur.httpserver.config.Configuration;
import com.knavedecoeur.httpserver.config.ConfigurationManager;
import com.knavedecoeur.httpserver.core.ServerListenerThread;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        System.out.println("Server starting...");

        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("port " + conf.getPort());
        LOGGER.info("webroot " + conf.getWebroot());

        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}