package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Imagen;
import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
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
    public void crearPerro(String nombre, Date fechaNac, String raza, String salud, Integer cantPerros, String idRutina, String idImagen) {

        Rutina rutina = rutinaRepositorio.findById(idRutina).get();
        Imagen imagen = imagenRepositorio.findById(idImagen).get();
        Perro perro = new Perro();

        perro.setNombre(nombre);
        perro.setFechaNac(fechaNac);
        perro.setRaza(raza);
        perro.setSalud(salud);
        perro.setCantPerros(cantPerros);
        perro.setRutina(rutina);
        perro.setImagen(imagen);

        perroRepositorio.save(perro);
    }

    public List<Perro> listarPerros() {

        List<Perro> perros = new ArrayList();

        perros = perroRepositorio.findAll();

        return perros;

    }

    public void modificarPerro(String idPerro, String nombre, Date fechaNac, String raza, String salud, Integer cantPerros, String idRutina, String idImagen) {

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
            perro.setImagen(imagen);

            perroRepositorio.save(perro);

        }

    }

}
