package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.TutorRepositorio;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import KaveCanem.adiestramientoweb.servicios.TutorServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Guillote
 */
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/tutor")
public class TutorControlador {

    @Autowired
    private TutorServicio tutorServicio;
    @Autowired
    private PerroServicio perroServicio;
    @Autowired
    private TutorRepositorio tutorRepositorio;

    @GetMapping("/buscarTutor")
    public String buscarTutor(ModelMap modelo){
             return "buscarTutor.html";
            
    }
    
    
    @PostMapping("/buscarTutor")
    public String buscarTutorPorDni(@RequestParam Long dni, RedirectAttributes redirect, ModelMap modelo) {
        Tutor tutor = tutorServicio.buscarPorDni(dni);
        
        if (tutor != null) {
            return "redirect:../tutor/listaUnico/" + tutor.getIdTutor();
        } else {
           return "tutor_form.html";
        }
    }
    
    @GetMapping("/registrar")
    public String registrar() {
        
        return "tutor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido,
           @RequestParam(required = false) Long dni, @RequestParam(required = false) Long telefono,  @RequestParam(required = false) String direccion, 
             RedirectAttributes redirect, ModelMap modelo) {
        try {
            if(tutorServicio.buscarPorDni(dni)==null){
            tutorServicio.crearTutor(nombre, apellido, dni, telefono, direccion);
            redirect.addFlashAttribute("exito", "salió todo bien");
            return "redirect:../perro/registrar1";
            }
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            return "tutor_form.html";
        }
         return "tutor_list.html";
    }
    
      @GetMapping("/lista")
    public String listar(ModelMap modelo, @Param("keyword")Long keyword){
        try{
            List<Tutor> tutores = new ArrayList<>();

            if(keyword==null){
                tutorRepositorio.findAll().forEach(tutores::add);
            }else{
                tutorRepositorio.buscarTutorPorDni1(keyword).forEach(tutores::add);
                modelo.addAttribute("keyword", keyword);
            }
            modelo.addAttribute("tutores", tutores);
        }catch(Exception e){
            modelo.addAttribute("error", e.getMessage());
        }
        return "tutor_list.html";
        
    }
    
    @GetMapping("/listaUnico/{idTutor}")
    public String listarunico(ModelMap modelo, @PathVariable Integer idTutor){
        Tutor tutores = tutorServicio.getOne(idTutor);
        modelo.addAttribute("tutores", tutores);
        
        return "tutor_unico.html";
        
    }
    
     @GetMapping("/modificar/{idTutor}")
    public String modificar(@PathVariable Integer idTutor, ModelMap modelo) {

        modelo.put("tutor", tutorServicio.getOne(idTutor));
        
        return "tutor_modificar.html";
    }
    
    @PostMapping("modificar/{idTutor}")
     public String modificar(@PathVariable Integer idTutor, 
             String nombre, String apellido, Long dni, Long telefono, String direccion, ModelMap modelo) {
        try {
            tutorServicio.modificarTutor(idTutor, nombre, apellido, dni, telefono, direccion);
            return  "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "tutor_modificar.html";
        }
     }

}
