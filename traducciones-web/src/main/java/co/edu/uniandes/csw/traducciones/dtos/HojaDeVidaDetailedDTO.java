/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.EmpleadoEntity;
import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.forero11
 */
public class HojaDeVidaDetailedDTO extends HojaDeVidaDTO {
    
    private List<TrayectoriaDTO> trayectorias;
    private List<IdiomaDTO> idiomas;
    private EmpleadoDTO empleado;
    /**
     *
     */
    public HojaDeVidaDetailedDTO() {
        //metodo obligatorio
    }
    
    /**
     * Crea un objeto HojaDeVidaDetailedDTO a partir de un objeto HojaDeVidaEntity
     * incluyendo los atributos de HojaDeVidaDetailedDTO.
     *
     * @param entity Entidad HojaDeVidaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public HojaDeVidaDetailedDTO(HojaDeVidaEntity entity) {
        super(entity);
        trayectorias = new ArrayList<>();
        idiomas = new ArrayList<>();
        empleado = new EmpleadoDTO();
        if (entity.getTrayectorias() != null) {
            for (TrayectoriaEntity entityTrayectorias : entity.getTrayectorias()) {
                trayectorias.add(new TrayectoriaDTO(entityTrayectorias));
            }
            
        }
        if (entity.getIdiomas() != null) {
            for (IdiomaEntity entityIdiomas : entity.getIdiomas()) {
                idiomas.add(new IdiomaDTO(entityIdiomas));
            }
            
        }
        if(entity.getEmpleado()!=null)
        {
            empleado= new EmpleadoDTO(entity.getEmpleado());        
        }
        
    }
    
    /**
     * Convierte un objeto HojaDeVidaDetailedDTO a HojaDeVidaEntity incluyendo los
     * atributos de HojaDeVidaDTO.
     *
     * @return Nueva objeto HojaDeVidaEntity.
     *
     */
    @Override
    public HojaDeVidaEntity toEntity() {
        HojaDeVidaEntity entity = super.toEntity();
        if (trayectorias != null) {
            List<TrayectoriaEntity> trayectoriasEntity = new ArrayList<>();
            for (TrayectoriaDTO dtoTrayectorias : trayectorias) {
                trayectoriasEntity.add(dtoTrayectorias.toEntity());
            }
            entity.setTrayectorias(trayectoriasEntity);
        }
        else
        {
            entity.setTrayectorias(new ArrayList<>());
        }
        if (idiomas != null) {
            List<IdiomaEntity> idiomasEntity = new ArrayList<>();
            for (IdiomaDTO dtoIdiomas : idiomas) {
                idiomasEntity.add(dtoIdiomas.toEntity());
            }
            entity.setIdiomas(idiomasEntity);
        }
        else
        {
            entity.setIdiomas(new ArrayList<>());
        }
        if(empleado!=null)
        {
            entity.setEmpleado(empleado.toEntity());
        }
        else
        {
            entity.setEmpleado(new EmpleadoEntity());
        }
        
        return entity;
    }
    
    /**
     * @return the trayectorias     *
     */
    public List<TrayectoriaDTO> getTrayectorias() {
        return trayectorias;
    }
    
    /**
     * @param trayectorias the trayectorias to set
     */
    public void setTrayectorias(List<TrayectoriaDTO> trayectorias) {
        this.trayectorias = trayectorias;
    }

    /**
     * @return the empleado
     */
    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the idiomas
     */
    public List<IdiomaDTO> getIdiomas() {
        return idiomas;
    }

    /**
     * @param idiomas the idiomas to set
     */
    public void setIdiomas(List<IdiomaDTO> idiomas) {
        this.idiomas = idiomas;
    }
}
