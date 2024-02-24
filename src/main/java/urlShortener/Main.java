package urlShortener;

import urlShortener.controllerLayer.HttpServer_my;
import urlShortener.serviceLayer.LinkHandler;

public class Main {
    public static void main(String[] args) throws Exception{
        LinkHandler linkHandler = new LinkHandler();
        HttpServer_my server = new HttpServer_my(linkHandler);
        server.setParameters();
        server.startServer();
    }


}
