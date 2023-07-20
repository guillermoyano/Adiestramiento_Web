package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Clases;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Guillote
 */
public interface ClasesRepositorio extends JpaRepository <Clases, String > {
    
    @Query(value = "SELECT * FROM Clases WHERE perro_id_perro = :idPerro", nativeQuery = true)
    public List<Clases> buscarClasesPorIdPerro(@Param("idPerro") Integer idPerro);

}
