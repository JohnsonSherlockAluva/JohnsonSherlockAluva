package com.goethe.covidservice.rest;

import com.goethe.covidservice.model.Quantity;
import com.goethe.covidservice.service.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
/**
 * REST controller for managing {@link com.goethe.covidservice.model.Quantity}.
 */
@RestController
@RequestMapping("/covid")
public class QuantityController {
    @Autowired
    private QuantityService quantityService;
    private static final Logger LOGGER=LoggerFactory.getLogger(QuantityController.class);


    /**
     * {@code GET  /stateName/:stateName} : get the "stateName" quantity.
     *
     * @param stateName the id of the country to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the Quantity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(value = "/stateName/{stateName}")
    public ResponseEntity<Quantity> getUser(@PathVariable String stateName) {
        LOGGER.debug("findByStateName {}", stateName);
        return Optional.ofNullable(quantityService.findByName(stateName))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * {@code GET  /countries} : get all the Quantity.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Quantity in body.
     */
    @GetMapping("/stateName")
    public ResponseEntity<List<Quantity>> fetchAllStates() {

        LOGGER.debug("fetchAllStates");
        return Optional.ofNullable(quantityService.findAll())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }
}
