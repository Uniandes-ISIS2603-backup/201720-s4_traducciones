/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.HojaDeVidaEntity;
import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.traducciones.persistence.HojaDeVidaPersistence;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
@Stateless
public class HojaDeVidaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(HojaDeVidaLogic.class.getName());

    @Inject
    private HojaDeVidaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private IdiomaLogic idiomaLogic;
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HojaDeVidaEntity createHojaDeVida(HojaDeVidaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de HojaDeVida");
        // Invoca la persistencia para crear la HojaDeVida
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de HojaDeVida");
        return entity;
    }

    /**
     * 
     * Obtener todas las HojaDeVidaes existentes en la base de datos.
     *
     * @return una lista de HojaDeVidaes.
     */
    public List<HojaDeVidaEntity> getHojasDeVida() {
        LOGGER.info("Inicia proceso de consultar todas las HojaDeVidas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<HojaDeVidaEntity> hojasDeVida = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las HojaDeVidas");
        return hojasDeVida;
    }
    
    /**
     * Obtiene una hoja de vida por su Id
     * @param id de la hoja de vida a buscar
     * @return Hoja de vida en entity
     */
    public HojaDeVidaEntity getHojaDeVidaId(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * 
     * Elimina la hoja de vida con el id especificado
     * @param id de la hoja de vida a borrar
     */
     public void deleteHojaDeVidaId(Long id)
    {
        persistence.delete(id);
    }
     
     /**
      * 
      * Actualiza la inforamcion de una hoja de vida
      * @param id id de la hoja de vida a actualizar
      * @param entrada datos con los que se actualiza la hoja de vida
      * @return hoja de vida en entity actualizada
      */
     public HojaDeVidaEntity updateHojaDeVida(Long id,HojaDeVidaEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
     
     /**
      * 
      * Dice si existe o no una hoja de vida especifica en la base de datos
      * @param id id de la hoja a validar existencia
      * @return True si existe la hoja, false de lo contrario
      */
     public boolean existeHojaDeVidaId(Long id)
     {
     return persistence.find(id)!=null;
    
     }
     
     /**
      * 
      * Obtiene la lista de idiomas de una hoja de vida
      * @param hojaDeVidaId id de la hoja de vida
      * @return la lista de idiomas de la hojas
      * @throws BusinessLogicException si no existe la hoja
      */
     public List<IdiomaEntity> getIdiomas(Long hojaDeVidaId) throws BusinessLogicException {
        if(getHojaDeVidaId(hojaDeVidaId) == null){
            throw new BusinessLogicException("El hojaDeVida no existe");
        }
        else
        {
            return getHojaDeVidaId(hojaDeVidaId).getIdiomas();
        }
    }
    /**
     * Añade un idioma a la hoja de vida
     * @param idioma entity idioma a añadir a la hoja de vida
     * @param hojaDeVidaId id de la hoja a la que se e añadira un idioma
     * @return entity del idioma agragado
     * @throws BusinessLogicException si no existe la hoja de vida
     */
    public IdiomaEntity addIdioma(IdiomaEntity idioma, Long hojaDeVidaId) throws BusinessLogicException {
        idioma.setHojaDeVida(getHojaDeVidaId(hojaDeVidaId));
        idiomaLogic.createIdioma(idioma);
        getHojaDeVidaId(hojaDeVidaId).getIdiomas().add(idioma);
        return idioma;
    }
    
  
    /**
     * Elimina un idioma de una hoja de vida especifica
     * @param idiomaId id del idioma a borrar
     * @param hojaDeVidaId id de la hoja de vida que contiene el idioma
     * @throws BusinessLogicException si no existe aguna de las 2
     */
    public void removeIdioma(Long idiomaId, Long hojaDeVidaId) throws BusinessLogicException {
        List<IdiomaEntity> idiomas = getHojaDeVidaId(hojaDeVidaId).getIdiomas();
        boolean seBorro = false;
        for(int i = 0; i<idiomas.size() && !seBorro; i++){
            if(Objects.equals(idiomas.get(i).getId(), idiomaId)){
                idiomaLogic.deleteIdiomaId(idiomas.get(i).getId());
                getHojaDeVidaId(hojaDeVidaId).getIdiomas().remove(i);
                seBorro = true;
            }
        }
        if(!seBorro){
            throw new BusinessLogicException("Este hojaDeVida no tiene un area de conocimiento con el id:" + idiomaId);
        }
    }
}
