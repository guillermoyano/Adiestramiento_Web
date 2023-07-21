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
public interface ClasesRepositorio extends JpaRepository <Clases, Integer > {
    
    @Query(value = "SELECT * FROM Clases WHERE perro_id_perro = :idPerro", nativeQuery = true)
    public List<Clases> buscarClasesPorIdPerro(@Param("idPerro") Integer idPerro);
    
    @Query(value = "SELECT * FROM Clases WHERE perro_id_perro = :idPerro order by id_clases desc", nativeQuery = true)
    List<Clases> findAllOrderByidDesc(@Param("idPerro") Integer idPerro);

    @Query(value = "SELECT * FROM Clases WHERE perro_id_perro = :idPerro order by id_clases asc", nativeQuery = true)
    List<Clases> findAllOrderByidAsc(@Param("idPerro") Integer idPerro);

}
