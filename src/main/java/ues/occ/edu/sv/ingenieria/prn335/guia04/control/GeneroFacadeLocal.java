/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia04.control;

import java.util.List;
import javax.ejb.Local;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Genero;

/**
 *
 * @author CF14010
 */
@Local
public interface GeneroFacadeLocal {

    void create(Genero genero);

    void edit(Genero genero);

    void remove(Genero genero);

    Genero find(Object id);

    List<Genero> findAll();

    //List<Genero> findRange(int[] range);

    int count();
    
}
