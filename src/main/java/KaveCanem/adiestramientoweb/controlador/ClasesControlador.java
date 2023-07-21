package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Clases;
import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Usuario;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.ClasesRepositorio;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.servicios.ClasesServicio;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/clases")
public class ClasesControlador {

    @Autowired
    private ClasesServicio clasesServicio;
    @Autowired
    private PerroServicio perroServicio;
    @Autowired
    private PerroRepositorio perroRepositorio;
    @Autowired
    private ClasesRepositorio clasesRepositorio;

    @GetMapping("/nuevaClase/{idPerro}")
    public String nuevaClase(ModelMap modelo, @PathVariable Integer idPerro) {

        modelo.put("perro", perroServicio.getOne(idPerro));

        return "clases_form.html";
    }

    @PostMapping("/nuevaClase/{idPerro}")
    public String nuevaClase(HttpSession session, @RequestParam String comentario, @PathVariable Integer idPerro, ModelMap modelo, RedirectAttributes redirect) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        try {
            clasesServicio.guardar(comentario, idPerro);
            List<Perro> perros = perroServicio.listarPerros();

            redirect.addAttribute("perros", perros);
            redirect.addFlashAttribute("exito", "El comentario fue cargado correctamente");

        } catch (MiException ex) {
            List<Perro> perros = perroServicio.listarPerros();

            modelo.addAttribute("perros", perros);
            modelo.put("error", ex.getMessage());

            return "clases_form.html";
        }

        if (logueado != null && logueado.getRol() != null && logueado.getRol().toString().equals("ADMIN")) {
            return "panel.html";
        }
        return "inicio.html";

    }

    @GetMapping("/lista/{idPerro}")
    public String listar(@PathVariable("idPerro") Integer idPerro, ModelMap modelo) {

        modelo.put("perro", perroServicio.getOne(idPerro));

        List<Clases> clases = clasesRepositorio.findAllOrderByidAsc(idPerro);
        modelo.addAttribute("clases", clases);

        return "clases_list.html";
    }

    @GetMapping("modificar/{idClases}")
    public String modificar(@PathVariable Integer idClases, ModelMap modelo) throws MiException {

        modelo.put("clases", clasesServicio.getOne(idClases));

        return "clases_modificar.html";
    }

    @PostMapping("modificar/{idClases}")
    public String modificar(HttpSession session, @PathVariable Integer idClases, String comentario, ModelMap modelo, RedirectAttributes redirect) throws MiException {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        try {

            clasesServicio.actualizar(idClases, comentario);

            redirect.addFlashAttribute("exito", "Ha sido modificado correctamente.");

        } catch (MiException ex) {

            redirect.addFlashAttribute("error", ex.getMessage());
            return "clases_modificar.html";
        }

        if (logueado != null && logueado.getRol() != null && logueado.getRol().toString().equals("ADMIN")) {
            return "panel.html";
        }
        return "inicio.html";
    }

}
