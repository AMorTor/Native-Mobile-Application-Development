package mx.ipn.closure;

import mx.ipn.closure.services.ClosureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClosureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClosureApplication.class, args);
        ClosureService closureService = new ClosureService();
        System.out.println(closureService.generatekleeneStar(3));

    }

}
