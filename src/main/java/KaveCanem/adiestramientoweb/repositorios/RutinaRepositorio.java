package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guillote
 */
@Repository
public interface RutinaRepositorio extends JpaRepository<Rutina, Integer>{

    @Query(value = "SELECT * FROM Rutina WHERE perro_id_perro = :idPerro", nativeQuery = true)
    public Rutina buscarRutinaPorIdPerro(@Param("idPerro") Integer idPerro);
}
