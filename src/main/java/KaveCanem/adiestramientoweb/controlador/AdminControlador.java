package KaveCanem.adiestramientoweb.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
   @GetMapping("/dashboard")
   public String panelAdministrativo(){
       return "panel.html";
   }
}
