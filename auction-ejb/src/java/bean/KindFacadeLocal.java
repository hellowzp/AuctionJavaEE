/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.Kind;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benchun
 */
@Local
public interface KindFacadeLocal {

    void create(Kind kind);

    void edit(Kind kind);

    void remove(Kind kind);

    Kind find(Object id);

    List<Kind> findAll();

    List<Kind> findRange(int[] range);

    int count();
    
}
