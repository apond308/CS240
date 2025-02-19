package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DefaultHandler implements HttpHandler {

    /**
     * Default file handler for server
     * @param exchange HTTP request
     */
    @Override
    public void handle(HttpExchange exchange) {

        try {

            OutputStream response = exchange.getResponseBody();
            String path;
            if (exchange.getRequestURI().toString().equals("/")) {
                path = new File("web/index.html").getAbsolutePath();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
            else {
                try {
                    path = new File("web" + exchange.getRequestURI()).getAbsolutePath();
                    if (!Files.exists(Paths.get(path)))
                        throw new NoSuchFileException(path);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                catch (NoSuchFileException e){
                    path = new File("web/HTML/404.html").getAbsolutePath();
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                }
            }
            Files.copy(Paths.get(path), response);
            exchange.getResponseBody().close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}