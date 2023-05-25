package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.repositorios.RutinaRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guillote
 */
@Service
public class RutinaServicio {
    
    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    
    @Transactional
    public void crearRutina(String paseo, String frecPaseo, String herramientas, String salida, String observacionesPaseo,
            String calle, String comida, String frecComida, String observacionesComida, String juego, String juegaCon,
            String dispoJuguetes, String frecJuego, String observacionesJuego, String duerme, String frecDuerme,
            String dondePasaDia, String educacionPrevia, String motivoContratacion, String observacionesEducacion){
        
        Rutina rutina = new Rutina();
        
        rutina.setPaseo(paseo);
        rutina.setFrecPaseo(frecPaseo);
        rutina.setHerramientas(herramientas);
        rutina.setSalida(salida);
        rutina.setObservacionesPaseo(observacionesPaseo);
        rutina.setCalle(calle);
        rutina.setComida(comida);
        rutina.setFrecComida(frecComida);
        rutina.setObservacionesComida(observacionesComida);
        rutina.setJuego(juego);
        rutina.setJuegaCon(juegaCon);
        rutina.setDispoJuguetes(dispoJuguetes);
        rutina.setFrecJuego(frecJuego);
        rutina.setObservacionesJuego(observacionesJuego);
        rutina.setDuerme(duerme);
        rutina.setFrecDuerme(frecDuerme);
        rutina.setDondePasaDia(dondePasaDia);
        rutina.setEducacionPrevia(educacionPrevia);
        rutina.setMotivoContratacion(motivoContratacion);
        rutina.setObservacionesEducacion(observacionesEducacion);
        
        rutinaRepositorio.save(rutina);
        
    }

    
     public List<Rutina> listarRutina(){
        
        List<Rutina> rutinas = new ArrayList();
        
        rutinas = rutinaRepositorio.findAll();
        
        return rutinas;
        
    }
     
     public void modificarRutina(){
         
     }
    
}


