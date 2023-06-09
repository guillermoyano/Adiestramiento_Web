package KaveCanem.adiestramientoweb.repositorios;

import KaveCanem.adiestramientoweb.entidad.Rutina;
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
public interface TutorRepositorio  extends JpaRepository<Tutor, Integer>{

   @Query("SELECT t FROM Tutor t WHERE t.apellido = :apellido") 
   public Tutor buscarTutorPorApellido(@Param("apellido") String apellido);
   
   
   @Query(value = "SELECT * FROM Tutor t order by id_Tutor desc limit 1", nativeQuery = true)
    public Tutor buscarTutorPorIdTutor();
   
    
   
}
