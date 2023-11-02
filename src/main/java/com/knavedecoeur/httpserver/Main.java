package com.knavedecoeur.httpserver;

import com.knavedecoeur.httpserver.config.Configuration;
import com.knavedecoeur.httpserver.config.ConfigurationManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port " + conf.getPort());
    }
}