/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.traducciones.entities.SolicitudEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author aj.ayte
 */
public class SolicitudDetailedDTO extends SolicitudDTO{
    
    private ClienteDetailDTO cliente;
    
    private List<AreaDeConocimientoDTO> areasDeConocimiento;
    
    private IdiomaDTO idiomaEntrada;
    
    private IdiomaDTO idiomaSalida;
    
    public SolicitudDetailedDTO() {
        super();
    }
    
    public SolicitudDetailedDTO(SolicitudEntity entity) {
        super(entity);
        if(entity.getAreasDeConocimiento() != null){
            this.areasDeConocimiento=new ArrayList<AreaDeConocimientoDTO>();
            for (AreaDeConocimientoEntity areaDeConocimientoEntity : entity.getAreasDeConocimiento()) {
                areasDeConocimiento.add(new AreaDeConocimientoDTO(areaDeConocimientoEntity));
            }
        }
        if(entity.getCliente() != null){
            this.cliente=new ClienteDetailDTO(entity.getCliente());
        }
        if(entity.getIdiomaEntrada() != null){
            this.idiomaEntrada=new IdiomaDTO(entity.getIdiomaEntrada());
        }
        if(entity.getIdiomaSalida() != null){
            this.idiomaSalida=new IdiomaDTO(entity.getIdiomaSalida());
        }
    }
    
    
    public ClienteDetailDTO getCliente() {
        return cliente;
    }
    
    public void setCliente(ClienteDetailDTO cliente) {
        this.cliente = cliente;
    }
    
    public List<AreaDeConocimientoDTO> getAreasDeConocimiento() {
        return areasDeConocimiento;
    }
    
    public void setAreasDeConocimiento(List<AreaDeConocimientoDTO> areasDeConocimiento) {
        this.areasDeConocimiento = areasDeConocimiento;
    }
    
    public IdiomaDTO getIdiomaEntrada() {
        return idiomaEntrada;
    }
    
    public void setIdiomaEntrada(IdiomaDTO idiomaEntrada) {
        this.idiomaEntrada = idiomaEntrada;
    }
    
    public IdiomaDTO getIdiomaSalida() {
        return idiomaSalida;
    }
    
    public void setIdiomaSalida(IdiomaDTO idiomaSalida) {
        this.idiomaSalida = idiomaSalida;
    }
    
    public SolicitudEntity toEntity() {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setId(this.getId());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setFechaEntrega(this.getFechaEntrega());
        entity.setTipo(this.getTipo());
        entity.setNumPalabras(this.getNumeroDePalabras());
        entity.setDescripcion(this.getDescripcion());
        entity.setCliente(this.cliente.toEntity());
        entity.setAreasDeConocimiento(this.dtoToEntity(areasDeConocimiento));
        return entity;
    }
    
    private List<AreaDeConocimientoEntity> dtoToEntity(List<AreaDeConocimientoDTO> entityDTO) {
        List<AreaDeConocimientoEntity> list = new ArrayList<>();
        for(AreaDeConocimientoDTO dto : entityDTO) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
