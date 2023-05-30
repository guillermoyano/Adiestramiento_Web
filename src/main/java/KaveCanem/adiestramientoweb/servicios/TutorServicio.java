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
import org.springframework.transaction.annotation.Transactional;

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
    public void crearTutor(String nombre, String apellido, Long telefono, String direccion, Date fechaAlta, String idPerro) throws MiException {

        verificarTutor(nombre, apellido, telefono, direccion, fechaAlta);

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

    public List<Tutor> listarTutores() {

        List<Tutor> tutores = new ArrayList();

        tutores = tutorRepositorio.findAll();

        return tutores;

    }

    @Transactional
    public void modificarTutor(String idTutor, String nombre, String apellido, Long telefono, String direccion, Date fechaAlta, String idPerro) throws MiException {

        verificarTutor(nombre, apellido, telefono, direccion, fechaAlta);

        Optional<Tutor> respuesta = tutorRepositorio.findById(idTutor);
        Optional<Perro> respuestaPerro = perroRepositorio.findById(idPerro);

        Perro perro = new Perro();

        if (respuestaPerro.isPresent()) {
            perro = respuestaPerro.get();
        }

        if (respuesta.isPresent()) {
            Tutor tutor = respuesta.get();
            
            tutor.setNombre(nombre);
            tutor.setApellido(apellido);
            tutor.setTelefono(telefono);
            tutor.setDireccion(direccion);
            tutor.setPerro(perro);

            tutorRepositorio.save(tutor);
        }
    }

    private void verificarTutor(String nombre, String apellido, Long telefono, String direccion, Date fechaAlta) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("El apellido no puede ser nulo o estar vacío");
        }

        if (telefono == null || telefono < 10) {
            throw new MiException("El telefono no puede ser nulo o tener menos de 10 números");
        }

        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("La direccion no puede ser nulo o estar vacío");
        }

        if (fechaAlta == null) {
            throw new MiException("La fecha de alta no puede ser nula");
        }

    }

}
