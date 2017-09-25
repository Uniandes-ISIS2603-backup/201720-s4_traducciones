/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.dtos;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.TrayectoriaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.forero11
 */
public class HojaDeVidaDetailedDTO extends HojaDeVidaDTO {
    
    private List<TrayectoriaDTO> trayectorias;

    /**
     *
     */
    public HojaDeVidaDetailedDTO() {
        super();
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
        if (entity != null) {
            trayectorias = new ArrayList<>();
            for (TrayectoriaEntity entityTrayectorias : entity.getTrayectorias()) {
                trayectorias.add(new TrayectoriaDTO(entityTrayectorias));
            }

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
}
