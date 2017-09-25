package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.OfertaEntity;
import co.edu.uniandes.csw.traducciones.entities.PropuestaEntity;
import java.util.List;

/**
 *
 * @author jc.gloria
 */
public class EmpleadoDTO {
    
    
    private Long id;
    private String name;
    private Integer tipo;
    private Double calificacionPromedio;
    
    public EmpleadoDTO(){
    }
    
    public EmpleadoDTO(EmpleadoEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.tipo = entity.getTipo();
        this.calificacionPromedio = entity.getCalificacionPromedio();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
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
    
    //METODO PARA CONVERTIR DTO-ENTITY
    public EmpleadoEntity toEntity() {
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTipo(this.tipo);
        entity.setCalificacionPromedio(this.calificacionPromedio);
        
        return entity;
    }
    
}