package urlShortener.controllerLayer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import urlShortener.serviceLayer.LinkHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServer_my {
    private final HttpServer server;
    private final LinkHandler linkHandler;

    public HttpServer_my(LinkHandler linkHandlerInput) throws IOException {
        server = HttpServer.create(new InetSocketAddress(6969), 0);
        linkHandler = linkHandlerInput;
    }
    public void setParameters() {
        server.createContext("/", new MyHandler());
        server.createContext("/echoGet", new EchoGetHandler());
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

    class EchoGetHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            // parse request
            Map<String, Object> parameters = new HashMap<>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, parameters);

            // send response
            StringBuilder response = new StringBuilder();
            for (String key : parameters.keySet())
                response.append(key).append(" = ").append(parameters.get(key)).append("\n");
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());

            os.close();
        }

        public static void parseQuery(String query, Map<String,
                Object> parameters) throws UnsupportedEncodingException {

            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] param = pair.split("=");
                    String key = null;
                    String value = null;
                    if (param.length > 0) {
                        key = URLDecoder.decode(param[0],
                                Charset.defaultCharset().displayName());
                    }

                    if (param.length > 1) {
                        value = URLDecoder.decode(param[1],
                                Charset.defaultCharset().displayName());
                    }

                    if (parameters.containsKey(key)) {
                        Object obj = parameters.get(key);
                        if (obj instanceof List<?>) {
                            List<String> values = (List<String>) obj;
                            values.add(value);

                        } else if (obj instanceof String) {
                            List<String> values = new ArrayList<>();
                            values.add((String) obj);
                            values.add(value);
                            parameters.put(key, values);
                        }
                    } else {
                        parameters.put(key, value);
                    }
                }
            }
        }
    }


}
