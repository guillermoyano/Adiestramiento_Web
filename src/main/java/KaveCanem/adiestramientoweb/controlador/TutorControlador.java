package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.servicios.TutorServicio;
import java.util.Date;
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
@RequestMapping("/tutor")
public class TutorControlador {
    
    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping("/registrar")
    public String registrar(){
        return "tutor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam(required = false) String apellido, @RequestParam Long telefono,
            @RequestParam Date fechaAlta, @RequestParam String idPerro){
        
        
        try {
            tutorServicio.crearTutor(nombre, apellido, telefono, nombre, fechaAlta, idPerro);
        } catch (MiException ex) {
            Logger.getLogger(TutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "tutor_form.html";
        }
        return "index.html";
    }
    
}
