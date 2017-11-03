/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
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
    
    public Integer getTipo() {
        return tipo;
    }
    
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }
    
    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }
    
    public List<AreaDeConocimientoEntity> getAreasdeconocimiento() {
        return areasdeconocimiento;
    }
    
    public void setAreasdeconocimiento(List<AreaDeConocimientoEntity> areasdeconocimiento) {
        this.areasdeconocimiento = areasdeconocimiento;
    }

    public List<HojaDeVidaEntity> getHojadevida() {
        return hojadevida;
    }

    public void setHojadevida(List<HojaDeVidaEntity> hojadevida) {
        this.hojadevida = hojadevida;
    }

    public List<PropuestaEntity> getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(List<PropuestaEntity> propuestas) {
        this.propuestas = propuestas;
    }

    public List<OfertaEntity> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<OfertaEntity> ofertas) {
        this.ofertas = ofertas;
    }
    
    
  
}
