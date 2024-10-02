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

    // Mostrar el formulario inicial
    @GetMapping("/")
    public String index() {
        return "index";  // Mostrar el formulario vacío inicialmente
    }

    // Procesar el número ingresado
    @GetMapping("/getCerraduras")
    public String getCerraduras(@RequestParam("number") int number, Model model) {
        if (number <= 0) {
            model.addAttribute("error", "El número debe ser mayor que 0.");
            return "index";  // Volver al formulario con el mensaje de error
        }

        model.addAttribute("kleeneStar", closureService.generatekleeneStar(number));
        model.addAttribute("kleenePlus", closureService.generatekleenePlus(number));
        model.addAttribute("number", number);
        return "index";  // Volver a la vista con los resultados
    }
}
