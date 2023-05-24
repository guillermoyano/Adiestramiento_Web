package KaveCanem.adiestramientoweb.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String paseo;
    private String frecPaseo;
    private String herramientas;
    private String salida;
    private String observacionesPaseo;
    private String calle;
    private String comida;
    private String frecComida;
    private String observacionesComida;
    private String juego;
    private String juegaCon;
    private String dispoJuguetes;
    private String frecJuego;
    private String observacionesJuego;
    private String duerme;
    private String frecDuerme;
    private String dondePasaDia;
    private String educacionPrevia;
    private String motivoContratacion;
    private String observacionesEducacion;

}
