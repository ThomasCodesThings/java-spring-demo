package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import server.controller.CustomerController;

@SpringBootApplication
public class SpringServer {

    private final CustomerController customerController;

    public SpringServer(CustomerController customerController) {
        this.customerController = customerController;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringServer.class, args);
    }

    @GetMapping("/hello")
    public Response hello(){
        return new Response("new value");
    }

    record Response(String key){}
}
