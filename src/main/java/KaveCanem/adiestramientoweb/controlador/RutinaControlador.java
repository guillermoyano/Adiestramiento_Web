package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.RutinaRepositorio;
import KaveCanem.adiestramientoweb.servicios.PerroServicio;
import KaveCanem.adiestramientoweb.servicios.RutinaServicio;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Guillote
 */
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@Controller
@RequestMapping("/rutina")
public class RutinaControlador {

    @Autowired
    private RutinaServicio rutinaServicio;
    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    @Autowired
    private PerroServicio perroServicio;
    @Autowired
    private PerroRepositorio perroRepositorio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo, Integer idPerro) {

        modelo.put("perro", perroRepositorio.buscarPerroPorIdPerro());

        return "rutina_form.html";
    }

    @PostMapping("/registro")
    public String registro(
            @RequestParam(required = false) String paseo,
            @RequestParam(required = false) String frecPaseo,
            @RequestParam(required = false) String herramientas,
            @RequestParam(required = false) String salida,
            @RequestParam(required = false) String observacionesPaseo,
            @RequestParam(required = false) String calle,
            @RequestParam(required = false) String comida,
            @RequestParam(required = false) String frecComida,
            @RequestParam(required = false) String observacionesComida,
            @RequestParam(required = false) String juego,
            @RequestParam(required = false) String juegaCon,
            @RequestParam(required = false) String dispoJuguetes,
            @RequestParam(required = false) String frecJuego,
            @RequestParam(required = false) String observacionesJuego,
            @RequestParam(required = false) String duerme,
            @RequestParam(required = false) String frecDuerme,
            @RequestParam(required = false) String dondePasaDia,
            @RequestParam(required = false) String educacionPrevia,
            @RequestParam(required = false) String motivoContratacion,
            @RequestParam(required = false) String observacionesEducacion,
            Integer idPerro,
            RedirectAttributes redirect, ModelMap modelo) {


        try {
            rutinaServicio.crearRutina(paseo, frecPaseo, herramientas, salida, observacionesPaseo,
                    calle, comida, frecComida, observacionesComida, juego, juegaCon, dispoJuguetes,
                    frecJuego, observacionesJuego, duerme, frecDuerme, dondePasaDia, educacionPrevia, motivoContratacion, observacionesEducacion, idPerro);

            modelo.put("exito", "La rutina fue cargada correctamente");

        } catch (MiException ex) {
            modelo.put("perro", perroRepositorio.buscarPerroPorIdPerro());

            
            modelo.put("error", ex.getMessage());

            return "rutina_form.html";
        }

        return "index.html";
    }

    @GetMapping("/lista/{idPerro}")
    public String listar(@PathVariable Integer idPerro, ModelMap modelo) {
        modelo.put("rutina", rutinaRepositorio.buscarRutinaPorIdPerro(idPerro));
        List<Rutina> rutinas = rutinaServicio.listarRutina();
        modelo.addAttribute("rutinas", rutinas);

        return "rutina_list.html";

    }

    @GetMapping("modificar/{idRutina}")
    public String modificar(@PathVariable Integer idRutina, ModelMap modelo) {

        modelo.put("rutina", rutinaServicio.getOne(idRutina));

        return "rutina_modificar.html";
    }

    @PostMapping("modificar/{idRutina}")
    public String modificar(@PathVariable Integer idRutina, String paseo,
            String frecPaseo, String herramientas, String salida, String observacionesPaseo, String calle, String comida,
            String frecComida, String observacionesComida, String juego, String juegaCon, String dispoJuguetes,
            String frecJuego, String observacionesJuego, String duerme, String frecDuerme, String dondePasaDia,
            String educacionPrevia, String motivoContratacion, String observacionesEducacion,
            ModelMap modelo) {
        try {
            rutinaServicio.modificarRutina(idRutina, paseo, frecPaseo, herramientas, salida, observacionesPaseo, calle, comida,
                    frecComida, observacionesComida, juego, juegaCon, dispoJuguetes,
                    frecJuego, observacionesJuego, duerme, frecDuerme, dondePasaDia,
                    educacionPrevia, motivoContratacion, observacionesEducacion);

            return "redirect:/perro/lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "rutina_modificar.html";
        }
    }

}
