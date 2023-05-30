package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.servicios.RutinaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/rutina")
public class RutinaControlador {
    
    @Autowired
    private RutinaServicio rutinaServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "rutina_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String paseo, @RequestParam(required = false) String frecPaseo, @RequestParam(required = false) String herramientas,
           @RequestParam(required = false) String salida, @RequestParam(required = false) String observacionesPaseo, @RequestParam(required = false) String calle, @RequestParam String comida,
           @RequestParam(required = false) String frecComida, @RequestParam(required = false) String observacionesComida, @RequestParam String juego,
           @RequestParam(required = false) String juegaCon, @RequestParam(required = false) String dispoJuguetes, @RequestParam(required = false) String frecJuego,
           @RequestParam(required = false) String observacionesJuego, @RequestParam(required = false) String duerme, @RequestParam(required = false) String frecDuerme,
           @RequestParam(required = false) String dondePasaDia, @RequestParam(required = false) String educacionPrevia,
           @RequestParam String  motivoContratacion, @RequestParam(required = false) String observacionesEducacion){
        
        try {
            rutinaServicio.crearRutina(paseo, frecPaseo, herramientas, salida, observacionesPaseo, calle, comida, frecComida, observacionesComida, juego, juegaCon, dispoJuguetes, frecJuego, observacionesJuego, duerme, frecDuerme, dondePasaDia, educacionPrevia, motivoContratacion, observacionesEducacion);
            
            
        } catch (MiException ex) {
            Logger.getLogger(RutinaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "rutina_form.html";
        }
        
        return "index.html";
    }

}

    