package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface PerroRepositorio extends JpaRepository<Perro, String>{

    @Query("SELECT p FROM Perro p WHERE p.nombre = :nombre") 
   public Perro buscarPerroPorNombre(@Param("nombre") String nombre);
    
}
