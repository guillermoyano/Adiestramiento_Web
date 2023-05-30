package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import KaveCanem.adiestramientoweb.servicios.RutinaServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @Autowired
    private RutinaServicio rutinaServicio;

    @GetMapping("/registrar")
    public String registrar() {

        return "perro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false)  Date fechaNac, 
            @RequestParam(required = false)  String raza, @RequestParam(required = false)  Integer cantPerros, 
            @RequestParam(required = false)  String salud, @RequestParam(required = false)  String idRutina, 
            @RequestParam(required = false) String idImagen, ModelMap modelo) {

        try {
            perroServicio.crearPerro(nombre, fechaNac, raza, salud, cantPerros, idRutina, idImagen);
            
            modelo.put("exito", "El perro fue cargado correctamente");

        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            
            return "perro_form.html";
        }
        return "index.html";
    }

}
