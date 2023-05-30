package KaveCanem.adiestramientoweb.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/tutor")
public class TutorControlador {

    @GetMapping("/registrar")
    public String registrar(){
        return "tutor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        System.out.println("nombre " +nombre);
        
        return "index.html";
    }
    
}
