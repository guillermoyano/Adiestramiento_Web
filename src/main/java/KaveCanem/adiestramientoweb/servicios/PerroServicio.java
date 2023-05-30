package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Imagen;
import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.ImagenRepositorio;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.RutinaRepositorio;
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
    private RutinaRepositorio rutinaRepositorio;

    @Transactional
    public void crearPerro(String nombre, Date fechaNac, String raza, String salud, Integer cantPerros, String idRutina, String idImagen) throws MiException {

        validarPerro(nombre, fechaNac, raza, salud, cantPerros, idImagen, idRutina);

        Rutina rutina = rutinaRepositorio.findById(idRutina).get();
        Imagen imagen = imagenRepositorio.findById(idImagen).get();
        Perro perro = new Perro();

        perro.setNombre(nombre);
        perro.setFechaNac(fechaNac);
        perro.setRaza(raza);
        perro.setSalud(salud);
        perro.setCantPerros(cantPerros);
        perro.setRutina(rutina);
//        perro.setImagen(imagen);

        perroRepositorio.save(perro);
    }

    public List<Perro> listarPerros() {

        List<Perro> perros = new ArrayList();

        perros = perroRepositorio.findAll();

        return perros;

    }

    @Transactional
    public void modificarPerro(String idPerro, String nombre, Date fechaNac, String raza, String salud, Integer cantPerros, String idRutina, String idImagen) throws MiException {

        validarPerro(nombre, fechaNac, raza, salud, cantPerros, idImagen, idRutina);

        Optional<Perro> respuesta = perroRepositorio.findById(idPerro);
        Optional<Rutina> respuestaRutina = rutinaRepositorio.findById(idRutina);
        Optional<Imagen> respuestaImagen = imagenRepositorio.findById(idImagen);

        Rutina rutina = new Rutina();
        Imagen imagen = new Imagen();

        if (respuestaRutina.isPresent()) {
            rutina = respuestaRutina.get();
        }

        if (respuestaImagen.isPresent()) {
            imagen = respuestaImagen.get();
        }

        if (respuesta.isPresent()) {
            Perro perro = respuesta.get();

            perro.setNombre(nombre);
            perro.setFechaNac(fechaNac);
            perro.setRaza(raza);
            perro.setSalud(salud);
            perro.setCantPerros(cantPerros);
            perro.setRutina(rutina);
//            perro.setImagen(imagen);

            perroRepositorio.save(perro);
        }
    }

    private void validarPerro(String nombre, Date fechaNac, String raza, String salud, Integer cantPerros, String idRutina, String idImagen) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }

        if (fechaNac == null) {
            throw new MiException("La fecha de nacimiento no puede ser nula");
        }

        if (raza == null || raza.isEmpty()) {
            throw new MiException("La raza no puede ser nula o estar vacía");
        }

        if (salud == null || salud.isEmpty()) {
            throw new MiException("La salud no puede ser nula o estar vacía");
        }

        if (cantPerros == null || cantPerros < 10) {
            throw new MiException("La cantidad de perros no puede ser nula");
        }

        if (idImagen == null) {
            throw new MiException("La imagen no puede ser nula");
        }

        if (idRutina == null) {
            throw new MiException("La rutina no puede ser nula");
        }

    }

}
