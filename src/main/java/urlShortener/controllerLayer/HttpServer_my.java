package urlShortener.controllerLayer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import urlShortener.serviceLayer.LinkHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServer_my {
    private final HttpServer server;
    private final LinkHandler linkHandler;

    public HttpServer_my(LinkHandler linkHandlerInput) throws IOException {
        server = HttpServer.create(new InetSocketAddress(6969), 0);
        linkHandler = linkHandlerInput;
    }
    public void setParameters() {
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
    }

    public void startServer(Integer parameter) {
        if (parameter == 0) {
            server.start();
        }
    }
    class MyHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String str = linkHandler.testReturn("testMessage").toString();
            byte[] bytes = str.getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

}
