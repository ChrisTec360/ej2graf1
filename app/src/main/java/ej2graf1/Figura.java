/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javax.swing.DefaultListModel;

/**
 *
 * @author libookami
 */
public class Figura {
    private String nombre;
    
    private DefaultListModel<Punto> listaPuntos;

    public Figura() {
        nombre = "f1";
        
        getListaPuntos().addElement(new Punto(1, 1));
        getListaPuntos().addElement(new Punto(2, 2));
        getListaPuntos().addElement(new Punto(2, 1));
        getListaPuntos().addElement(new Punto(5, 5));
        getListaPuntos().addElement(new Punto(7, 8));
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void dibujar(ShapeRenderer rend)
    {
        for (int i = 0; i < getListaPuntos().size(); i++) {
            getListaPuntos().get(i).dibujar(rend);   
        }
        
        rend.setColor(Color.RED);
        rend.rectLine(getListaPuntos().get(0).getPx() * Canvas.escala, 
                      getListaPuntos().get(0).getPy() * Canvas.escala, 
                      getListaPuntos().get(1).getPx() * Canvas.escala, 
                      getListaPuntos().get(1).getPy() * Canvas.escala, 
                      3);
        
        /*
        rend.setColor(Color.RED);
        rend.rectLine(p2.getPx() * Canvas.escala, 
                      p2.getPy() * Canvas.escala, 
                      p3.getPx() * Canvas.escala, 
                      p3.getPy() * Canvas.escala, 
                      3);
        
        rend.setColor(Color.RED);
        rend.rectLine(p3.getPx() * Canvas.escala, 
                      p3.getPy() * Canvas.escala, 
                      p1.getPx() * Canvas.escala, 
                      p1.getPy() * Canvas.escala, 
                      3);
        */
        
        
        
    }

    /**
     * @return the listaPuntos
     */
    public DefaultListModel<Punto> getListaPuntos() {
        
        if(listaPuntos == null)
        {
            listaPuntos = new DefaultListModel<>();
        }
        
        return listaPuntos;
    }

    /**
     * @param listaPuntos the listaPuntos to set
     */
    public void setListaPuntos(DefaultListModel<Punto> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }
}
