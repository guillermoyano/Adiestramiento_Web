package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
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
public interface TutorRepositorio extends JpaRepository<Tutor, Integer> {

     List<Tutor> findByDni(Long keyword);
    
    @Query("SELECT t FROM Tutor t WHERE t.apellido = :apellido")
    public Tutor buscarTutorPorApellido(@Param("apellido") String apellido);

    @Query(value = "SELECT * FROM Tutor t order by id_Tutor desc limit 1", nativeQuery = true)
    public Tutor buscarTutorPorIdTutor();

    @Query("SELECT t FROM Tutor t WHERE t.dni = :dni")
    public Tutor buscarTutorPorDni(@Param("dni") Long dni);
    
    @Query(value="SELECT * FROM Tutor WHERE dni like %:dni%", nativeQuery = true)
    public List<Tutor> buscarTutorPorDni1(@Param("dni") Long dni);

}
