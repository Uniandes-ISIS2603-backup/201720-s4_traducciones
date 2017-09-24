/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jc.gloria
 */
@Entity
public class EmpleadoEntity extends BaseEntity implements Serializable{
    
    private Integer tipo;
    
    private Double calificacionPromedio;
    
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaDeConocimientoEntity> areasdeconocimiento = new ArrayList<AreaDeConocimientoEntity>();

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
 
}