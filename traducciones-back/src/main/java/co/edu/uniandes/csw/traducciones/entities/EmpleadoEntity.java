/*
Esta clase tiene los atributos basicos de empleado
*/
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.gloria
 */
@Entity
public class EmpleadoEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaDeConocimientoEntity> areasdeconocimiento;
    
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HojaDeVidaEntity> hojadevida;
    
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropuestaEntity> propuestas;
    
    @PodamExclude
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEntity> ofertas;
    
    private Integer tipo;
    
    private Double calificacionPromedio;
    
    /**
    Obtiene el tipo de empleado (1 si es traductor, 2 si es corrector, 3 si es ambos)
    @return el tipo de empleado
    */
    public Integer getTipo() {
        return tipo;
    }
    
    /**
     * Asigna un tipo al empleado
     * @param tipo entero que representa el tipo.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Devuelve la calificacion promedio del empleado
     * @return double con la calificacion
     */
    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }
    
    /**
     * Asigna una calificacion al empelado
     * @param calificacionPromedio  un numero double con la calificacion que se desea asignar.
     */
    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }
    
    /**
     * Devuelve las areas de conocimientos del empleado
     * @return lista de areas de conocimientos
     */
    public List<AreaDeConocimientoEntity> getAreasdeconocimiento() {
        return areasdeconocimiento;
    }
    
    /**
     * Asigna una lista de areas de conocimiento al empleado
     * @param areasdeconocimiento la lista que se va a asignar.
     */
    public void setAreasdeconocimiento(List<AreaDeConocimientoEntity> areasdeconocimiento) {
        this.areasdeconocimiento = areasdeconocimiento;
    }
    
    /**
     * Devuelve una lista de hojas de vida al empleado, la lista solo tiene un objeto HojaDeVida
     * @return la lista con la hoja de vida.
     */
    public List<HojaDeVidaEntity> getHojadevida() {
        return hojadevida;
    }
    
    /**
     * Asigna una lista de hojas de vida al empleado, la lista solo tiene un objeto HojaDeVida
     * @param hojadevida la lista con la hoja de vida.
     */
    public void setHojadevida(List<HojaDeVidaEntity> hojadevida) {
        this.hojadevida = hojadevida;
    }
    
    /**
     * Devuelve las propuestas que posee el empleado
     * @return una lista con las propuestas.
     */
    public List<PropuestaEntity> getPropuestas() {
        return propuestas;
    }
    
    /**
     * Asigna una lista de propuestas al empleado
     * @param propuestas la lista que se va a asignar.
     */
    public void setPropuestas(List<PropuestaEntity> propuestas) {
        this.propuestas = propuestas;
    }
    
   /**
     * Devuelve las ofertas que posee el empleado
     * @return una lista con las ofertas.
     */
    public List<OfertaEntity> getOfertas() {
        return ofertas;
    }
    
     /**
     * Asigna una lista de ofertas al empleado
     * @param ofertas la lista que se va a asignar.
     */
    public void setOfertas(List<OfertaEntity> ofertas) {
        this.ofertas = ofertas;
    }
    
    
  
}
