package com.goethe.covidservice.service;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import com.goethe.covidservice.model.Quantity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * Service Implementation for managing {@link Quantity}.
 */
@Service
public class QuantityService {
    private static final Logger LOGGER= LoggerFactory.getLogger(QuantityService.class);

    public static HashMap<String, Quantity> covidMap = new HashMap<String, Quantity>();

    public Quantity findByName(String stateName) {
        LOGGER.debug("findByName {}", stateName);
        if (covidMap.isEmpty()) {
            readFile();
        }
        LOGGER.debug("Exit findByName {}", covidMap);
        return covidMap.get(stateName);
    }

    public List<Quantity> findAll() {
        LOGGER.info("Exit findAll {}");
        if (covidMap.isEmpty()) {
            readFile();
        }
        ArrayList<Quantity> quantities
                = covidMap.values().stream().collect(
                Collectors.toCollection(ArrayList::new));
        LOGGER.debug("Exit findAll {}",quantities);
        return quantities;
    }


    private void parseEmployeeObject(JSONObject quantityJson) {
        LOGGER.debug("parseEmployeeObject {}",quantityJson);
        JSONArray covidDataList = (JSONArray) quantityJson.get("records");
        Iterator<JSONObject> iterator = covidDataList.iterator();
        while (iterator.hasNext()) {
            Quantity quantity = new Quantity();

            JSONObject recordObject = (JSONObject) iterator.next();

            quantity.setUpdated_on((String) recordObject.get("record_timestamp"));

            JSONObject fieldsObject = (JSONObject) recordObject.get("fields");
            quantity.setCases((Long) fieldsObject.get("cases"));
            quantity.setCounty((String) fieldsObject.get("county"));
            quantity.setStateName((String) fieldsObject.get("name"));
            quantity.setDeaths((Long) fieldsObject.get("deaths"));
            quantity.setCases_per_population((Double) fieldsObject.get("cases_per_population"));
            quantity.setDeath_rate((Double) fieldsObject.get("death_rate"));
            quantity.setEwz((Long) fieldsObject.get("ewz"));
            covidMap.put(quantity.getStateName(), quantity);


        }
        LOGGER.debug("Exit parseEmployeeObject {}",quantityJson);
    }

    private void readFile() {

        LOGGER.info(" readFile ");
        URL resource = getClass().getClassLoader().getResource("covid_data.json");
        if (resource == null) {
            LOGGER.error(" file not found! ");
            throw new IllegalArgumentException("file not found!");
        } else {

            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(new File(resource.toURI()))) {
                Object obj = jsonParser.parse(reader);
                JSONArray quantities = (JSONArray) obj;
                quantities.forEach(emp -> parseEmployeeObject((JSONObject) emp));
            } catch (Exception e) {
                LOGGER.error(" exception from readFile",e.getMessage());
            }

        }

        LOGGER.debug("Exit readFile {}");
    }


}