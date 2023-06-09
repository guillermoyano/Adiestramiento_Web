package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import KaveCanem.adiestramientoweb.servicios.RutinaServicio;
import KaveCanem.adiestramientoweb.servicios.TutorServicio;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Guillote
 */
 @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/perro")
public class PerroControlador {

    @Autowired
    private PerroServicio perroServicio;
    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
        List<Tutor> tutores = tutorServicio.listarTutores();
        modelo.addAttribute("tutores", tutores);

        return "perro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false) Double edad,
            @RequestParam(required = false) String raza, @RequestParam(required = false) Integer cantPerros,
            @RequestParam(required = false) String salud, @RequestParam(required = false) Integer idTutor,
            RedirectAttributes redirect, ModelMap modelo
            , MultipartFile archivo
    ) {

        try {
            perroServicio.crearPerro(archivo, nombre, edad, raza, salud, cantPerros, idTutor);
            List<Tutor> tutores = tutorServicio.listarTutores();

            redirect.addAttribute("tutores", tutores);
            redirect.addFlashAttribute("exito", "El perro fue cargado correctamente");

        } catch (MiException ex) {

            List<Tutor> tutores = tutorServicio.listarTutores();

            modelo.addAttribute("tutores", tutores);
            modelo.put("error", ex.getMessage());

            return "perro_form.html";
        }
        return "redirect:../rutina/registrar";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Perro> perros = perroServicio.listarPerros();
        modelo.addAttribute("perros", perros);

        return "perro_list.html";

    }

    @GetMapping("modificar/{idPerro}")
    public String modificar(@PathVariable Integer idPerro, ModelMap modelo) throws MiException {

        modelo.put("perro", perroServicio.getOne(idPerro));

        return "perro_modificar.html";
    }

    @PostMapping("modificar/{idPerro}")
    public String modificar(@PathVariable Integer idPerro,
            String nombre, Double edad, String raza, Integer cantPerros, String salud, 
            ModelMap modelo, RedirectAttributes redirect,
            MultipartFile archivo
           ) throws MiException {
        try {
            perroServicio.modificarPerro(archivo, idPerro, nombre, edad, raza, salud, cantPerros);
            redirect.addFlashAttribute("exito", "Ha sido modificada correctamente.");
            return "redirect:../lista";
        } catch (MiException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            return "perro_modificar.html";
        }
    }
    
    
}
