package com.knavedecoeur.httpserver.core;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;

    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException{
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket  = serverSocket.accept();

                LOGGER.info("Connection accepted: " + socket.getInetAddress());

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                String html = "<html><head><title>Simple Java</title></head><body><h1>This was served from java</h1></body></html>";

                final String CRLF = "\\n\r";

                String response =
                        "HTTP/1.1 200 OK " + CRLF +
                                "Content-Length: " + html.getBytes().length + CRLF +
                                CRLF +
                                html +
                                CRLF + CRLF; // Status line : HTTP VERSION RESPONSE CODE RESPONSE MESSAGE

                // TODO read

                // TODO write
                outputStream.write(response.getBytes());

                inputStream.close();
                outputStream.close();
                socket.close();
            }
//            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
