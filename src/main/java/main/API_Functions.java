package main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.logging.Logger;


public class API_Functions {
    Logger log;
    JSONParser parser;

    public API_Functions() {
        log = Logger.getLogger(API_Functions.class.getName());
        parser = new JSONParser();
    }

    public void hitAPI(){
        log.info("hitAPI() entered");
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(Constants.URL).build());
        // getting JSON data
        String response = service.path("fact").accept(MediaType.APPLICATION_JSON).get(String.class);

        JSONObject res = convertStringToJson(response);

        log.info((String) res.get("fact"));
    }

    private JSONObject convertStringToJson(String stringToConvert){
        try {
            return (JSONObject) parser.parse(stringToConvert);
        } catch (ParseException e) {
            log.info(e.toString());
            return null;
        }

    }
}
