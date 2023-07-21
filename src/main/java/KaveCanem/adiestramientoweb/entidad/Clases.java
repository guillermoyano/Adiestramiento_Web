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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Guillote
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Clases {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idClases;
    private String comentario;
    @Temporal(TemporalType.DATE)
    private Date fechaClase;
    @OneToOne
    private Perro perro;

}
