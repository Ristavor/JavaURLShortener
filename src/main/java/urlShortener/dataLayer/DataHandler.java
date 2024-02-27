package urlShortener.dataLayer;

import java.util.HashMap;
import java.util.Map;

public class DataHandler {
    private Integer i = 0;
    private final Map<String, String> linkMapping;

    public DataHandler() {
        linkMapping = new HashMap<>();
    }

    public void addNewLink(String link) {
        linkMapping.put(link, i.toString());
        i++;
    }

    public String getFullByShort(String shortLink) {
        return linkMapping.get(shortLink);
    }

    public Map<String, String> getMap() {
        return linkMapping;
    }
}
