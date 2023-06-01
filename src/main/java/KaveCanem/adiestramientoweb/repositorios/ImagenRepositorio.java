package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface ImagenRepositorio extends JpaRepository <Imagen, Integer>{

}
