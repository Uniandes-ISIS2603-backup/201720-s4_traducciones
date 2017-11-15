 /**
  * Esta clase tiene los atributos basicos de los objetos Idioma
  */
package co.edu.uniandes.csw.traducciones.entities;


import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ISIS2603
 */
@Entity
public class IdiomaEntity extends BaseEntity implements Serializable {
    private String acronimo;
    private String region;
    
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
    
}