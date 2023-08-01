package KaveCanem.adiestramientoweb.controlador;
import KaveCanem.adiestramientoweb.entidad.Usuario;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.UsuarioRepositorio;
import KaveCanem.adiestramientoweb.servicios.UsuarioServicio;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/")
    public String index() {

        return "login.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo, MultipartFile archivo) throws IOException {

        try {
            usuarioServicio.registrar(archivo, nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo, RedirectAttributes redirectAttributes) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.put("logueado", usuarioServicio.getOne(logueado.getId()));

        if (logueado.getRol().toString().equals("ADMIN")) {
            redirectAttributes.addFlashAttribute("exito", "Usuario Logueado!!!");
            return "redirect:/admin/dashboard";
        }

        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuarioServicio.getOne(usuario.getId()));
        return "usuario_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil")
    public String actualizar(MultipartFile archivo, @RequestParam(required = false) Integer id, @RequestParam(required = false) String nombre, ModelMap modelo, HttpSession session) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        try {

            usuarioServicio.actualizar(archivo, id, nombre);

            modelo.put("exito", "Usuario actualizado correctamente!");

            if (logueado.getRol().toString().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }

            return "redirect:/inicio";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);

            return "redirect:/perfil";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/modificarPass")
    public String perfilCambiar(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Usuario usuarioActualizado = usuarioServicio.getOne(usuario.getId());
        modelo.addAttribute("usuarioActualizado", usuarioActualizado);
        return "usuario_modificar_pass.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/modificarPass")
    public String perfilCambiar(@RequestParam String claveActual, @RequestParam Integer id, @RequestParam String clave,
            @RequestParam String clave2, ModelMap model) {
        try {
            usuarioServicio.cambiarClave(claveActual, id, clave, clave2);
            model.put("exito", "La contraseña ha sido actualizada correctamente.");
            return "usuario_modificar_pass.html";
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return "usuario_modificar_pass.html";
        }
    }
}
