package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.TutorRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

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

    @Transactional
    public void crearTutor(String nombre, String apellido, Long dni, Long telefono, String direccion) throws MiException {

        verificarTutor(nombre, apellido, dni, telefono, direccion);

        Tutor tutor = new Tutor();

        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setDni(dni);
        tutor.setTelefono(telefono);
        tutor.setDireccion(direccion);

        tutorRepositorio.save(tutor);

    }

    public List<Tutor> listarTutores() {

        List<Tutor> tutores = new ArrayList();

        tutores = tutorRepositorio.findAll();

        return tutores;

    }
    
    public List<Tutor> tutorUnico(Integer idTutor) {

        List<Tutor> tutores = new ArrayList();

        tutores = tutorRepositorio.findAll();

        return tutores;

    }

    @Transactional
    public void modificarTutor(Integer idTutor, String nombre, String apellido, Long dni, Long telefono, String direccion) throws MiException {

        verificarTutor(nombre, apellido, dni, telefono, direccion);

        Optional<Tutor> respuesta = tutorRepositorio.findById(idTutor);

        Perro perro = new Perro();

        if (respuesta.isPresent()) {
            Tutor tutor = respuesta.get();
            
            tutor.setNombre(nombre);
            tutor.setApellido(apellido);
            tutor.setDni(dni);
            tutor.setTelefono(telefono);
            tutor.setDireccion(direccion);

            tutorRepositorio.save(tutor);
        }
    }
    
    public Tutor getOne(Integer idTutor){
        return tutorRepositorio.getOne(idTutor);
    }

    private void verificarTutor(String nombre, String apellido, Long dni, Long telefono, String direccion) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
        
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("El apellido no puede ser nulo o estar vacío");
        }
        
        if (dni == null) {
            throw new MiException("El dni no puede ser vacío o ser menor a 7 cifras");
        }

        if (telefono == null || telefono < 10) {
            throw new MiException("El telefono no puede ser nulo o tener menos de 10 números");
        }

        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("La direccion no puede ser nulo o estar vacío");
        }

    }
    
    public Tutor buscarPorDni(Long dni){
        
        Tutor tutor = new Tutor();
       
        tutor = tutorRepositorio.buscarTutorPorDni(dni);
        
        return tutor;
        
    }

}
