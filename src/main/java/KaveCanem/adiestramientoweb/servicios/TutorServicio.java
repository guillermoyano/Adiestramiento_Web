package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.TutorRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guillote
 */
@Service
public class TutorServicio {
    
    @Autowired
    private TutorRepositorio tutorRepositorio;
    @Autowired
    private PerroRepositorio perroRepositorio;
    
    public void crearTutor(String nombre, String apellido, Long telefono, String direccion, Date fechaAlta, String idPerro){
        
        Perro perro = perroRepositorio.findById(idPerro).get();
        Tutor tutor = new Tutor();
        
        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setTelefono(telefono);
        tutor.setDireccion(direccion);
        tutor.setFechaAlta(fechaAlta);
        tutor.setPerro(perro);
        
        tutorRepositorio.save(tutor);
        
        
    }

}


 
