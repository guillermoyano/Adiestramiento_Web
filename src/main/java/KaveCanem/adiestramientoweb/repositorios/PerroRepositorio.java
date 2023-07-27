package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface PerroRepositorio extends JpaRepository<Perro, Integer>{

    
    
    @Query(value="SELECT * FROM Perro WHERE nombre like %:nombre%", nativeQuery = true) 
   public List<Perro> buscarPerroPorNombre(@Param("nombre") String nombre);
   
     @Query(value = "SELECT * FROM Perro p order by id_Perro desc limit 1", nativeQuery = true)
    public Perro buscarPerroPorIdPerro();
 
}
