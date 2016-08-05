package delivery.controller;

import delivery.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class DeliveryController {

    @Autowired
    private ConcurrentHashMap<String, Delivery> concurrentHashMap;

    @RequestMapping(method = GET, value = "/delivery/")
    public ResponseEntity<Collection<Delivery>> listMap() {
        return new ResponseEntity<Collection<Delivery>>(concurrentHashMap.values(), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/delivery/{key}")
    public ResponseEntity<String> getDelivery(@PathVariable("key") String key) {
        Delivery delivery = concurrentHashMap.get(key);

        if (delivery == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(delivery.getValue(), HttpStatus.OK);
    }


    @RequestMapping(method = POST, value = "/delivery/")
    public ResponseEntity<Void> addDelivery(@RequestBody Delivery delivery) {
        String key = delivery.getKey();

        if (concurrentHashMap.containsKey(key))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        concurrentHashMap.put(key,delivery);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = PUT, value = "/delivery/{key}")
    public ResponseEntity<Void> updateDelivery(@PathVariable ("key") String key, @RequestBody Delivery delivery) {

        Delivery deliveryService = concurrentHashMap.get(key);

        if (deliveryService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concurrentHashMap.put(key,delivery);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method=DELETE, value="/delivery/{key}")
    public ResponseEntity<Void> deleteDelivery (@PathVariable String key)
    {
        Delivery delivery = concurrentHashMap.get(key);

        if (delivery == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concurrentHashMap.remove(key);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
