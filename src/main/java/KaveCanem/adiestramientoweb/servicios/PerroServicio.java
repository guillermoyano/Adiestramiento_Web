package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Imagen;
import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.ImagenRepositorio;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.RutinaRepositorio;
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
public class PerroServicio {

    @Autowired
    private PerroRepositorio perroRepositorio;
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    @Autowired
    private TutorRepositorio tutorRepositorio;

    @Transactional
    public void crearPerro(String nombre, Double edad, String raza, String salud, Integer cantPerros, Integer idTutor) throws MiException {

        validarPerro(nombre, edad, raza, salud, cantPerros, idTutor);

        Optional<Tutor> respuestaTutor = tutorRepositorio.findById(idTutor);

        Tutor tutor = new Tutor();
//        Imagen imagen = imagenRepositorio.findById(idImagen).get();

        if (respuestaTutor.isPresent()) {
            tutor = respuestaTutor.get();
        }

        Perro perro = new Perro();

        perro.setNombre(nombre);
        perro.setEdad(edad);
        perro.setRaza(raza);
        perro.setSalud(salud);
        perro.setCantPerros(cantPerros);
        perro.setTutor(tutor);
//        perro.setImagen(imagen);

        perroRepositorio.save(perro);
    }

    public List<Perro> listarPerros() {

        List<Perro> perros = new ArrayList();

        perros = perroRepositorio.findAll();

        return perros;

    }

    @Transactional
    public void modificarPerro(Integer idPerro, String nombre, Double edad, String raza, String salud, Integer cantPerros, Integer idTutor) throws MiException {

        validarPerro(nombre, edad, raza, salud, cantPerros, idTutor);

        Optional<Perro> respuesta = perroRepositorio.findById(idPerro);
        Optional<Tutor> respuestaTutor = tutorRepositorio.findById(idTutor);
//        Optional<Imagen> respuestaImagen = imagenRepositorio.findById(idImagen);

        Tutor tutor = new Tutor();
//        Imagen imagen = new Imagen();

        if (respuestaTutor.isPresent()) {
            tutor = respuestaTutor.get();
        }

//        if (respuestaImagen.isPresent()) {
//            imagen = respuestaImagen.get();
//        }
        if (respuesta.isPresent()) {
            Perro perro = respuesta.get();

            perro.setNombre(nombre);
            perro.setEdad(edad);
            perro.setRaza(raza);
            perro.setSalud(salud);
            perro.setCantPerros(cantPerros);
            perro.setTutor(tutor);
//            perro.setImagen(imagen);

            perroRepositorio.save(perro);
        }
    }

    private void validarPerro(String nombre, Double edad, String raza, String salud, Integer cantPerros, Integer idTutor) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }

        if (edad == null || edad < 0 ) {
            throw new MiException("La fecha de nacimiento no puede ser nula");
        }

        if (raza == null || raza.isEmpty()) {
            throw new MiException("La raza no puede ser nula o estar vacía");
        }

        if (salud == null || salud.isEmpty()) {
            throw new MiException("La salud no puede ser nula o estar vacía");
        }

        if (cantPerros == null || cantPerros < 0) {
            throw new MiException("La cantidad de perros no puede ser nula");
        }

//        if (idImagen == null) {
//            throw new MiException("La imagen no puede ser nula");
//        }
        if (idTutor == null) {
            throw new MiException("El tutor no puede ser nulo");
        }

    }

}
