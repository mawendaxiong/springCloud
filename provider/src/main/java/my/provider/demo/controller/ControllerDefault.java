package my.provider.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerDefault {

    @GetMapping("foo")
    public String foo(){
        System.out.println("foo");
        return "foo";
    }

    @GetMapping("foo2")
    public String foo2(){
        System.out.println("foo2");
        return "foo123";
    }
}
