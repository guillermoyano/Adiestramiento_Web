package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Clases;
import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.ClasesRepositorio;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guillote
 */
@Service
public class ClasesServicio {

    @Autowired
    private ClasesRepositorio clasesRepositorio;

    @Autowired
    private PerroRepositorio perroRepositorio;

    @Transactional
    public void guardar(String comentario, Integer idPerro) throws MiException {

        validarClases(comentario);
        Clases clases = new Clases();

        Optional<Perro> respuestaPerro = perroRepositorio.findById(idPerro);
        Perro perro = new Perro();

        if (respuestaPerro.isPresent()) {
            perro = respuestaPerro.get();
        }
        clases.setPerro(perro);
        clases.setFechaClase(new Date());
        clases.setComentario(comentario);

        clasesRepositorio.save(clases);

    }

    @Transactional
    public Clases actualizar(String comentario) throws MiException {

        validarClases(comentario);
        Clases clases = new Clases();

        clases.setComentario(comentario);

        return clasesRepositorio.save(clases);

    }

    public List<Clases> listarTodos() {
        return clasesRepositorio.findAll();
    }

    public Clases getOne(String idClases) {
        return clasesRepositorio.getOne(idClases);
    }

    private void validarClases(String comentario) throws MiException {

        if (comentario.isEmpty()) {
            throw new MiException("El campo comentario debe estar completado");
        }

    }

}
