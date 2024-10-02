package mx.ipn.closure.controllers;

import mx.ipn.closure.services.ClosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    private ClosureService closureService;

    // Método para mostrar el formulario al entrar a la página
    @GetMapping("/")
    public String index() {
        return "index";  // Muestra la vista index.html sin resultados al inicio
    }

    // Método para procesar el número enviado por el formulario y mostrar los resultados
    @GetMapping("/getCerraduras")
    public String getCerraduras(@RequestParam("number") int number, Model model) {
        model.addAttribute("kleeneStar", closureService.generatekleeneStar(number));
        model.addAttribute("kleenePlus", closureService.generatekleenePlus(number));
        model.addAttribute("number", number);
        return "index";  // Vuelve a la vista index con los resultados generados
    }
}
