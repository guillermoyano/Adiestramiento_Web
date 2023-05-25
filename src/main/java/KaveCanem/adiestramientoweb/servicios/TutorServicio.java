package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
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
    public void crearTutor(String nombre, String apellido, Long telefono, String direccion, Date fechaAlta, String idPerro) {

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

    public void modificarTutor(String idTutor, String nombre, String apellido, Long telefono, String direccion, String idPerro) {

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

}
