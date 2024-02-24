package urlShortener;

import urlShortener.controllerLayer.HttpServer_my;
import urlShortener.dataLayer.DataHandler;
import urlShortener.serviceLayer.LinkHandler;

public class Main {
    public static void main(String[] args) throws Exception{
        DataHandler dataHandler = new DataHandler();
        LinkHandler linkHandler = new LinkHandler(dataHandler);
        HttpServer_my server = new HttpServer_my(linkHandler);
        server.setParameters();
        server.startServer(1);
    }


}
