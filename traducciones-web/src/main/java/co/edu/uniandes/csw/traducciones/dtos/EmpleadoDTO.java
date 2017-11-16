package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;

/**
 * @author jc.gloria
 */
public class EmpleadoDTO {
    
    
    private Long id;
    private String name;
    private Integer tipo;
    private Double calificacionPromedio;
    
    public EmpleadoDTO(){
        //Metodo default
    }
    
    /**
     * Crea un DTO a partir de una entidad
     * @param entity objeto entidad el cual se va a convertir en DTO.
     */
    public EmpleadoDTO(EmpleadoEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.tipo = entity.getTipo();
        this.calificacionPromedio = entity.getCalificacionPromedio();
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
     * Devuelve el tipo del empleado
     * @return entero con el tipo del empleado
     */
    public Integer getTipo() {
        return tipo;
    }
    
    /**
     * Asigna un tipo al empleado
     * @param tipo entero con el tipo que se quiere asignar.
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
     * Asigna calificacion promedio
     * @param calificacionPromedio que se quiere asignar.
     */
    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }
    
    /**
     * Convierte el DTO actual en una entidad
     * @return entidad con los atributos del DTO actual.
     */
    public EmpleadoEntity toEntity() {
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTipo(this.tipo);
        entity.setCalificacionPromedio(this.calificacionPromedio);
        
        return entity;
    }
}