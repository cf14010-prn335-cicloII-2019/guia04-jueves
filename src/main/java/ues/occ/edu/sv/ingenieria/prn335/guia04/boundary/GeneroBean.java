/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia04.boundary;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import ues.occ.edu.sv.ingenieria.prn335.cinedata.entity.Genero;
import ues.occ.edu.sv.ingenieria.prn335.guia04.control.GeneroFacadeLocal;

/**
 *
 * @author CF14010
 */
@Named(value = "GeneroBean")
@ViewScoped
public class GeneroBean implements Serializable{

    @EJB
    GeneroFacadeLocal gRepo; 
    private Genero g = new Genero();
    private  List<Genero> gList ;
    
    public GeneroBean() {
    }
    
    @PostConstruct
    public void post(){
        gList = gRepo.findAll();
    }
    
    public Genero obtener(){       
        return gRepo.find(g);
    }
    
      public void crear(){
       gRepo.create(g);
       g = new Genero();
       generarTabla();
       FacesContext.getCurrentInstance().addMessage("myForm", new FacesMessage("Se creó una nuevo registro de Genero"));
    }
      
     public void modificar(){
       gRepo.edit(g);
       generarTabla();
       FacesContext.getCurrentInstance().addMessage("myForm", new FacesMessage("Se actualizo un registro de Genero"));
    }
     
      public void eliminar(){
       gRepo.remove(g);
       generarTabla();
       FacesContext.getCurrentInstance().addMessage("myForm", new FacesMessage("Se eliminó unregistro de Genero"));
    }

        
    public boolean generarTabla(){
      gList = gRepo.findAll();
        return true;
     }

    public GeneroFacadeLocal getgRepo() {
        return gRepo;
    }

    public void setgRepo(GeneroFacadeLocal gRepo) {
        this.gRepo = gRepo;
    }

    public Genero getG() {
        return g;
    }

    public void setG(Genero g) {
        this.g = g;
    }

    public List<Genero> getgList() {
        return gList;
    }

    public void setgList(List<Genero> gList) {
        this.gList = gList;
    }
    
    
}