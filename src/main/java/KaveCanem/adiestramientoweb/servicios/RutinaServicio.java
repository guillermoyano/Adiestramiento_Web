package KaveCanem.adiestramientoweb.servicios;

import KaveCanem.adiestramientoweb.entidad.Perro;
import KaveCanem.adiestramientoweb.entidad.Rutina;
import KaveCanem.adiestramientoweb.entidad.Tutor;
import KaveCanem.adiestramientoweb.excepciones.MiException;
import KaveCanem.adiestramientoweb.repositorios.PerroRepositorio;
import KaveCanem.adiestramientoweb.repositorios.RutinaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private PerroRepositorio perroRepositorio;

    @Transactional
    public void crearRutina(String paseo, String frecPaseo, String herramientas, String salida, String observacionesPaseo,
            String calle, String comida, String frecComida, String observacionesComida, String juego, String juegaCon,
            String dispoJuguetes, String frecJuego, String observacionesJuego, String duerme, String frecDuerme,
            String dondePasaDia, String educacionPrevia, String motivoContratacion, String observacionesEducacion) throws MiException {

        validarRutina(paseo, comida, juego, motivoContratacion);
        
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

    public List<Rutina> listarRutina() {

        List<Rutina> rutinas = new ArrayList();

        rutinas = rutinaRepositorio.findAll();

        return rutinas;

    }

    @Transactional
    public void modificarRutina(String idRutina, String paseo, String frecPaseo, String herramientas, String salida, String observacionesPaseo,
            String calle, String comida, String frecComida, String observacionesComida, String juego, String juegaCon,
            String dispoJuguetes, String frecJuego, String observacionesJuego, String duerme, String frecDuerme,
            String dondePasaDia, String educacionPrevia, String motivoContratacion, String observacionesEducacion, String idPerro) throws MiException {

        validarRutina(paseo, comida, juego, motivoContratacion);
        
        Optional<Rutina> respuesta = rutinaRepositorio.findById(idRutina);

        Rutina rutina = new Rutina();

        if (respuesta.isPresent()) {

            rutina = respuesta.get();

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
    }
    
    private void validarRutina(String paseo, String comida, String juego, String motivoContratacion) throws MiException{
        
        if(paseo == null || paseo.isEmpty()){
            throw new MiException("El paseo no puedo ser nulo o estar vacío");
        }
        if(comida == null || comida.isEmpty()){
            throw new MiException("La comida no puedo ser nula o estar vacía");
        }
         if(juego == null || juego.isEmpty()){
            throw new MiException("El juego no puedo ser nulo o estar vacío");
        }
             if(motivoContratacion == null || motivoContratacion.isEmpty()){
            throw new MiException("El motivoContratacion no puedo ser nulo o estar vacío");
        }
    }

}
