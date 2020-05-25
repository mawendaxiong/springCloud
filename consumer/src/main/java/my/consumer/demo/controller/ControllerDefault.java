package my.consumer.demo.controller;

import my.consumer.demo.service.ServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ControllerDefault {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ServiceDefault serviceDefault;
    @GetMapping("foo")
    public String foo(){
        String foo = restTemplate.getForObject("http://providerDefault/foo", String.class);
        return foo;
    }

    @GetMapping("foo2")
    public String foo2(){
        String foo = restTemplate.getForObject("http://providerDefault/foo2", String.class);
        return foo;
    }

    @GetMapping("test")
    public void test(){
        String id="providerDefault";
        for (int i=0;i<100;i++){
            ServiceInstance choose = loadBalancerClient.choose(id);
            System.out.println(choose.getHost()+":"+choose.getPort());
        }
    }

    @GetMapping("testHystrix")
    public String testHystrix(){
        return serviceDefault.queryFoo();
    }
}
