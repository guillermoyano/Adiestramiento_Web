package KaveCanem.adiestramientoweb.entidad;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Perro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String idPerro;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    private String raza;
    private String salud;
    private Integer cantPerros;
    @OneToOne
    private Rutina rutina;
//    @OneToOne
//    private Imagen imagen;

}
