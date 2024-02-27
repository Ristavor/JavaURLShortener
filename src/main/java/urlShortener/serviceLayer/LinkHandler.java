package urlShortener.serviceLayer;

import urlShortener.dataLayer.DataHandler;

import java.util.Map;

public class LinkHandler {
    private final DataHandler dataHandler;
    public LinkHandler(DataHandler dataHandlerInput) {
        dataHandler = dataHandlerInput;
    }
    public Map<String, String> testReturn(String message) {
        dataHandler.addNewLink("youtube.com");
        return dataHandler.getMap();
    }

    public String getShortByFull(String shortLink) {
        return dataHandler.getFullByShort(shortLink);
    }
}
