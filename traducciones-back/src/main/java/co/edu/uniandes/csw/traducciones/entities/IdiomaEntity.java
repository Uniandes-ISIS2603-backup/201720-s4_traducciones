 /**
  * Esta clase tiene los atributos basicos de los objetos Idioma
  */
package co.edu.uniandes.csw.traducciones.entities;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603
 */
@Entity
public class IdiomaEntity extends BaseEntity implements Serializable {

   private String acronimo;
   private String region;
   
   @PodamExclude
   @ManyToOne()    
   private HojaDeVidaEntity hojaDeVida;
   

    
    /**
     * Devuelve el acronimo del idioma
     * @return cadena de caracteres con el acronimo
     */

    public String getAcronimo() {
        return acronimo;
    }
    /**
     * Asigna el acronimo del idioma
     * @param acronimo acronimo que se va a asignar
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
    /**
     * Devuelve la region de el idioma
     * @return cadena de caracteres con la region
     */
    public String getRegion() {
        return region;
    }
    
    /**
     * Asigna una region al idioma
     * @param region region que se va a asignar
     */
    
    public void setRegion(String region) {
        this.region = region;
    }


    /**
     * @return the hojaDeVida
     */
    public HojaDeVidaEntity getHojaDeVida() {
        return hojaDeVida;
    }

    /**
     * @param hojaDeVida the hojaDeVida to set
     */
    public void setHojaDeVida(HojaDeVidaEntity hojaDeVida) {
        this.hojaDeVida = hojaDeVida;
    }
   

}