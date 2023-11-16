/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.entities.Videogame;

/**
 *
 * @author deim
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Videogame> findVideogamesByType(String type) {
        Query query = getEntityManager().createQuery("SELECT v FROM Videogame v WHERE v.type = :type");
        query.setParameter("type", type);
        return query.getResultList();
    }

    public List<Videogame> findVideogamesByConsole(String console) {
        Query query = getEntityManager().createQuery("SELECT v FROM Videogame v WHERE v.console = :console");
        query.setParameter("console", console);
        return query.getResultList();
    }

    public List<Videogame> findVideogamesByTypeAndConsole(String type, String console) {
        Query query = getEntityManager().createQuery("SELECT v FROM Videogame v WHERE v.type = :type AND v.console = :console");
        query.setParameter("type", type);
        query.setParameter("console", console);
        return query.getResultList();
    }

}
