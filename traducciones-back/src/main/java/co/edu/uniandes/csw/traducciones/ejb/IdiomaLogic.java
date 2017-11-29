package co.edu.uniandes.csw.traducciones.ejb;

import co.edu.uniandes.csw.traducciones.entities.IdiomaEntity;
import co.edu.uniandes.csw.traducciones.persistence.IdiomaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ra.forero11
 */
public class IdiomaLogic {
    private static final Logger LOGGER = Logger.getLogger(IdiomaLogic.class.getName());
    
    @Inject
    private IdiomaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Se crea un idioma nuevo en el sistema
     * @param entity entidad que se agregara a la base de datos
     * @return entidad que se acaba de agregar
     */
    public IdiomaEntity createIdioma(IdiomaEntity entity) {
        LOGGER.info("Inicia proceso de creación de Idioma");
        // Invoca la persistencia para crear la Idioma
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Idioma");
        return entity;
    }
    
    /**
     * Obtener todas las Idiomas existentes en la base de datos.
     * @return una lista de Idiomaes.
     */
    public List<IdiomaEntity> getIdiomas() {
        LOGGER.info("Inicia proceso de consultar todas las Idiomas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<IdiomaEntity> idiomas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Idiomas");
        return idiomas;
    }
    /**
     * Devuelve un idioma especifico
     * @param id id del idioma que se quiere buscar
     * @return
     */
    public IdiomaEntity getIdiomaId(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Borra un idioma
     * @param id id del idioma que se quiere borrar
     */
    public void deleteIdiomaId(Long id)
    {
        persistence.delete(id);
    }
    /**
     * Actualiza un idioma
     * @param id del idioma que se quiere actualizar
     * @param entrada entidad idioma por el que se quiere reemplazar
     * @return la entidad ya actualizada
     */
    public IdiomaEntity updateIdioma(Long id,IdiomaEntity entrada)
    {
        entrada.setId(id);
        return persistence.update(entrada);
    }
    /**
     * Verificar si ya existe un idioma dado un id
     * @param id id que se quiere consultar
     * @return true si existe, false si no.
     */
    public boolean existeIdiomaId(Long id)
    {
        return persistence.find(id)!=null;
    }
}
