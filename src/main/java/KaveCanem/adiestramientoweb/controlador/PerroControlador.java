package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/perro")
public class PerroControlador {

    @Autowired
    private PerroServicio perroServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "perro_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam Date fechaNac, @RequestParam String raza, 
            @RequestParam Integer cantPerros, @RequestParam String salud, @RequestParam String idRutina){
        
        perroServicio.crearPerro(nombre, fechaNac, raza, salud, cantPerros, idRutina);
        
    }
    
}
