package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import KaveCanem.adiestramientoweb.servicios.TutorServicio;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/tutor")
public class TutorControlador {

    @Autowired
    private TutorServicio tutorServicio;
    @Autowired
    private PerroServicio perroServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "tutor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Long telefono, @RequestParam(required = false) String direccion, 
             RedirectAttributes redirect, ModelMap modelo) {
        try {
            tutorServicio.crearTutor(nombre, apellido, telefono, direccion);
            redirect.addFlashAttribute("exito", "salió todo bien");
            
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            return "tutor_form.html";
        }
         return "redirect:../perro/registrar";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Tutor>tutores = tutorServicio.listarTutores();
        modelo.addAttribute("tutores", tutores);
        
        return "tutor_list.html";
        
    }
    
     @GetMapping("/modificar/{idTutor}")
    public String modificar(@PathVariable Integer idTutor, ModelMap modelo) {

        modelo.put("tutor", tutorServicio.getOne(idTutor));
        
        return "tutor_modificar.html";
    }
    
    @PostMapping("modificar/{idTutor}")
     public String modificar(@PathVariable Integer idTutor, 
             String nombre, String apellido, Long telefono, String direccion, ModelMap modelo) {
        try {
            tutorServicio.modificarTutor(idTutor, nombre, apellido, telefono, direccion);
            return  "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "tutor_modificar.html";
        }
     }

}
