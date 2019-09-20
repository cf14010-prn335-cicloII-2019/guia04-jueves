/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia04.control;
 
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author CF14010
 */
/**
 * Generador de una clase abstracta
 * que recibe como parametro un generico <T>
 * @author StevenCastro
 * @param <T> 
 */
public abstract class AbstractFacade<T> {
  private Class <T> entityClass;
  
/**
 * Clase abstracta para cualquier entity mapeada 
 * que recibe como parameto un entityClass
 * @param entityClass 
 */  
public AbstractFacade(Class<T> entityClass) {
    this.entityClass = entityClass;
}
        
/**
 * 
 * Aqui se hereda el entity Manager descrito
 * en el EntityFacade
 */
 protected abstract EntityManager getEntityManager();
 
 /**
  * Metodo abstracto para crear 
  * un entity de tipo T entity
  * @param entity
  * @return Puede devolver un nuevo registro 
  * en la entity T o puede mostrar una excepcion 
  * en la creacion del nuevo registro de la entity T
  */
 public void create(T entity) {
        try {
            getEntityManager().persist(entity);
        } catch (Exception e) {
        }   
    }

    
 /**
  *Metodo abstracto para editar
  * un entity de tipo T entity
  * @param entity
  * @return Puede devolver un registro y modificarlo
  * en la entity T o puede mostrar una excepcion 
  * en la modificacion del nuevo registro de la entity T
  */
    public void edit(T entity) {
        try {
            getEntityManager().merge(entity);
        } catch (Exception e) {
        }
    }
/**
 * Metodo abstracto para borrar
  * un entity de tipo T entity
 * @param entity
 * @return Remueve una entidad de tipo T entity 
 */
     public void remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (Exception e) {
        }
    }

     /**
      * Metodo abstracto para buscar por id pasado 
      * como parametro un entity de tipo T entity
      * @param id
      * @return Devuelve un registro con ese id
      */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Metodo abstracto para buscar todos los registros de entity tipo T entity
     * @return Devuelve los registros contenidos de la entity T
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Metodo abstracto que recibe dos parametros y busca los registro entre este rango
     * @param desde primer parametro para buscar registro
     * @param hasta ultimo parametro para buscar registro
     * @return Devuelve una lista con los datos entre ese rango 
     */
   public List<T> findRange(int desde, int hasta) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(hasta);
        q.setFirstResult(desde);
        return q.getResultList();
    }

   /**
    * Metodo abstracto para contar los registros de una entity tipo T
    * @return Devuelve el numero de registro en una entity tipo T
    */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    /**
     * Metodo abstracto para buscar por parametros obtenidos del usuario
     * @return Devuelve una lista con todos los elementos que cumplen los filtros
     */
    public List<T> findByFiltro() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    } 
}