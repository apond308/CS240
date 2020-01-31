package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DefaultHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            OutputStream response = exchange.getResponseBody();
            String path;
            if (exchange.getRequestURI().toString().equals("/"))
                path = new File("web/index.html").getAbsolutePath();
            else
                path = new File("web/HTML/404.html").getAbsolutePath();

            Files.copy(Paths.get(path), response);
            exchange.getResponseBody().close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
