package delivery.configuration;

import delivery.Delivery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DeliveryConfiguration {


    private ConcurrentHashMap<String, Delivery> concurrentHashMap = new ConcurrentHashMap<>();

    @Bean
    ConcurrentHashMap<String, Delivery> initialiseMap ()
    {
        concurrentHashMap.put("lazylogin",new Delivery("lazylogin","http://lazylogin.trafalgar.ws/\""));
        concurrentHashMap.put("recordserver",new Delivery("recordserver","http://recordserver.trafalgar.ws/"));
        concurrentHashMap.put("gamemanager",new Delivery("gamemanager","http://gamemanager.trafalgar.ws/"));
        concurrentHashMap.put("discovery",new Delivery("discovery","http://discovery.trafalgar.ws/"));

        return concurrentHashMap;
    }

}
