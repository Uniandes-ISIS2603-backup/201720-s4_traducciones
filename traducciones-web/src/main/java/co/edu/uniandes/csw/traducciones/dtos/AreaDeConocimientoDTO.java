package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.AreaDeConocimientoEntity;

/**
 * @author jc.gloria
 */
public class AreaDeConocimientoDTO {
    
    private Long id;
    private String name;
    private String descripcion;
    
    public AreaDeConocimientoDTO(){
        //Metodo obligatorio 
    }
    
    /**
     * Crea un DTO a partir de una entidad
     * @param entity objeto entidad el cual se va a convertir en DTO.
     */
    public AreaDeConocimientoDTO(AreaDeConocimientoEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.descripcion = entity.getDescripcion();
    }
    
    /**
     * Convierte el DTO actual en una entidad
     * @return entidad con los atributos del DTO actual.
     */
    public AreaDeConocimientoEntity toEntity(){
        AreaDeConocimientoEntity entity = new AreaDeConocimientoEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescripcion(this.descripcion);
        return entity;
    }
    
    /**
     * Devuelve el ID.
     * @return un long con el id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Asigna un ID
     * @param id id que sera asignado.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Devuelve el nombre
     * @return cadena de caracteres con el nombre.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Asigna un nombre.
     * @param name nombre que sera asignado.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Devuelve la descripcion 
     * @return cadena de caracteres con la descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Asigna una descripcion.
     * @param descripcion descripcion que sera asignada.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        
    }
}
