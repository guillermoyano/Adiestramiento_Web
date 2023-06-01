package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface RutinaRepositorio extends JpaRepository<Rutina, Integer>{

}
