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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Guillote
 */
@Service
public class PerroServicio {

    @Autowired
    private PerroRepositorio perroRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private TutorRepositorio tutorRepositorio;

    @Transactional
    public void crearPerro(
            MultipartFile archivo,
            String nombre, Double edad, String raza, String salud, Integer cantPerros, Integer idTutor) throws MiException, IOException {

        validarPerroCrear(nombre, edad, raza, salud, cantPerros, idTutor);

        Optional<Tutor> respuestaTutor = tutorRepositorio.findById(idTutor);

        Tutor tutor = new Tutor();

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
         if (archivo.getSize() == 0) {
            Imagen imagen = imagenServicio.obtenerImagenPorDefectoPerro();
            perro.setImagen(imagen);
        } else {
            Imagen imagen = imagenServicio.guardar(archivo);
            perro.setImagen(imagen);
        }

        perroRepositorio.save(perro);
    }

    public List<Perro> listarPerros() {

        List<Perro> perros = new ArrayList();

        perros = perroRepositorio.findAll();

        return perros;

    }

    @Transactional
    public void modificarPerro(MultipartFile archivo, Integer idPerro, String nombre, Double edad, String raza, String salud, Integer cantPerros) throws MiException {
       
        validarPerro(nombre, edad, raza, salud, cantPerros);
        Optional<Perro> respuesta = perroRepositorio.findById(idPerro);
        if (respuesta.isPresent()) {
            Perro perro = respuesta.get();
            perro.setNombre(nombre);
            perro.setEdad(edad);
            perro.setRaza(raza);
            perro.setSalud(salud);
            perro.setCantPerros(cantPerros);
            
            Integer idImagen = null;

            if (perro.getImagen() != null) {
                idImagen = perro.getImagen().getIdImagen();

            }
            if (!archivo.isEmpty()) {

             Imagen imagen = imagenServicio.actualizar(archivo, idImagen);

                perro.setImagen(imagen);
        }
            
            perroRepositorio.save(perro);
        }
    }

    public Perro getOne(Integer idPerro) {
        return perroRepositorio.getOne(idPerro);
    }

    private void validarPerro(String nombre, Double edad, String raza, String salud, Integer cantPerros) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }

        if (edad == null || edad < 0) {
            throw new MiException("La fecha de nacimiento no puede ser nula");
        }

        if (raza == null || raza.isEmpty()) {
            throw new MiException("La raza no puede ser nula o estar vacía");
        }

        if (salud == null || salud.isEmpty()) {
            throw new MiException("La salud no puede ser nula o estar vacía");
        }

//        if (idImagen == null) {
//            throw new MiException("La imagen no puede ser nula");
//        }
        if (cantPerros == null || cantPerros < 0) {
            throw new MiException("La cantidad de perros no puede ser nula");
        }
    }

    private void validarPerroCrear(String nombre, Double edad, String raza, String salud, Integer cantPerros, Integer idTutor) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }

        if (edad == null || edad < 0) {
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

        if (idTutor == null) {
            throw new MiException("El tutor no puede ser nulo");
        }

    }
}
