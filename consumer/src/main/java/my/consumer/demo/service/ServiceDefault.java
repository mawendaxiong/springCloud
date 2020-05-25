package my.consumer.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceDefault {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "queryFallback")
    public String queryFoo() {
        String s = restTemplate.getForObject("http://providerDefault/foo", String.class);
        return s;
    }

    private String queryFallback() {
        return "querry error!!!";
    }
}
