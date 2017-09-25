/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.ClienteEntity;
import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author aj.ayte
 */
public class SolicitudDetailedDTO extends SolicitudDTO{
private ClienteDTO cliente;
   
 
  
   private List<AreaDeConocimientoDTO> areasDeConocimiento;
    
    private IdiomaDTO idiomaEntrada;
   private IdiomaDTO idiomaSalida;
    private PropuestaDTO propuestaElejida;

    private List<PropuestaDTO> propuestas;
    public SolicitudDetailedDTO() {
        super();
    }

    public SolicitudDetailedDTO(SolicitudEntity entity) {
        super(entity);
        this.areasDeConocimiento=new ArrayList<AreaDeConocimientoDTO>();
        for (AreaDeConocimientoEntity areaDeConocimientoEntity : entity.getAreasDeConocimiento()) {
            areasDeConocimiento.add(new AreaDeConocimientoDetailedDTO(areaDeConocimientoEntity));
        }
        this.cliente=;
        
        this.idiomaEntrada=new IdiomaDTO(entity.getIdiomaEntrada());
        this.idiomaSalida=new IdiomaDTO(entity.getIdiomaSalida());
        
        this.propuestas=entity.getPropuestas();
        this.propuestaElejida=entity.getPropuestaElejida().toEntity();
        
                
                
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<AreaDeConocimientoEntity> getAreasDeConocimiento() {
        return areasDeConocimiento;
    }

    public void setAreasDeConocimiento(List<AreaDeConocimientoEntity> areasDeConocimiento) {
        this.areasDeConocimiento = areasDeConocimiento;
    }

    public IdiomaEntity getIdiomaEntrada() {
        return idiomaEntrada;
    }

    public void setIdiomaEntrada(IdiomaEntity idiomaEntrada) {
        this.idiomaEntrada = idiomaEntrada;
    }

    public IdiomaEntity getIdiomaSalida() {
        return idiomaSalida;
    }

    public void setIdiomaSalida(IdiomaEntity idiomaSalida) {
        this.idiomaSalida = idiomaSalida;
    }

    public PropuestaEntity getPropuestaElejida() {
        return propuestaElejida;
    }

    public void setPropuestaElejida(PropuestaEntity propuestaElejida) {
        this.propuestaElejida = propuestaElejida;
    }

    public List<PropuestaEntity> getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(List<PropuestaEntity> propuestas) {
        this.propuestas = propuestas;
    }
    public SolicitudEntity toEntity() {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setId(this.id);
        entity.setFechaInicio(this.fechaInicio);
        entity.setFechaEntrega(this.fechaEntrega);
        entity.setTipo(this.tipo);
        entity.setNumPalabras(numeroDePalabras);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
}
