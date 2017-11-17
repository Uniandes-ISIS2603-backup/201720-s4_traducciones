package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;

/**
 * @author Dxhl
 */
public class IdiomaDTO {
    
    private long id;

    private String nombre;
    private String acronimo;
    private String region;

    
    public IdiomaDTO() {
        //Constructor default requerido
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param idioma: Es la entidad que se va a convertir a DTO
     */
    public IdiomaDTO(IdiomaEntity idioma) {
        this.id = idioma.getId();
        this.nombre = idioma.getName();
        this.acronimo = idioma.getAcronimo();
        this.region = idioma.getRegion();
    }
    
    /**
     * Devuelve el ID.
     * @return un long con el id.
     */
    public long getId() {
        return id;
    }
    
    /**
     * Asigna un ID
     * @param id id que sera asignado.
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Devuelve el nombre
     * @return cadena de caracteres con el nombre.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Asigna un nombre.
     * @param nombre nombre que sera asignado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Devuelve el acronimo
     * @return acronimo del idioma
     */
    public String getAcronimo() {
        return acronimo;
    }
    
    /**
     * Asigna un acronimo
     * @param acronimo que sera asignado
     */
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
    /**
     * Devuelve la region del idioma
     * @return region del idioma
     */
    
    public String getRegion() {
        return region;
    }
    
    /**
     * Asigna una region al idioma
     * @param region que sera asignada.
     */
    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO
     */
    public IdiomaEntity toEntity() {
        IdiomaEntity entity = new IdiomaEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setAcronimo(this.acronimo);
        entity.setRegion(this.region);
        return entity;
    }
}