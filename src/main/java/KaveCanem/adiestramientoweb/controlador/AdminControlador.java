package KaveCanem.adiestramientoweb.controlador;

import KaveCanem.adiestramientoweb.entidad.Usuario;
import KaveCanem.adiestramientoweb.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Guillote
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("logueado", usuarioServicio.getOne(logueado.getId()));
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        
        return "usuario_list.html";
    }
    
    @GetMapping("modificarRol/{id}")
    public String cambiarRol(@PathVariable Integer id){
        usuarioServicio.cambiarRol(id);
        
        return "redirect:/admin/usuarios";
    }

}
