/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Kind;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benchun
 */
@Stateless
public class KindFacade extends AbstractFacade<Kind> implements KindFacadeLocal {
    @PersistenceContext(unitName = "auction-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KindFacade() {
        super(Kind.class);
    }
    
}
