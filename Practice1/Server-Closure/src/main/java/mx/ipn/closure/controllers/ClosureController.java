package mx.ipn.closure.controllers;

import mx.ipn.closure.services.ClosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/closure")
public class ClosureController {

    @Autowired
    private final ClosureService closureService;

    public ClosureController(ClosureService closureService) {
        this.closureService = closureService;
    }

    @GetMapping("star/{number}")
    public Map<String, String> getKleeneStar(@PathVariable("number") int number){
        return  closureService.generatekleeneStar(number);
    }

    @GetMapping("plus/{number}")
    public Map<String, String> getKleenePlus(@PathVariable("number") int number){
        return  closureService.generatekleenePlus(number);
    }

}
