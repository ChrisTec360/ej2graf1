/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import javax.swing.DefaultListModel;

/**
 *
 * @author libookami
 */
public class Figura {
    private String nombre;
    public DefaultListModel<Keyframe> listakeyframes;
    public int escale=20;
    
    Canvas c;
    
    public Keyframe getKeyframeInicial(){
        return listakeyframes.get(0);
    }
   
    public void AgregarKeyframe(Keyframe k){
        listakeyframes.addElement(k); 
    }
    
    public void EliminarKeyframe(Keyframe k) throws IllegalArgumentException{
        int idx = listakeyframes.indexOf(k);
        if(idx > 0){
            listakeyframes.removeElement(k);
        }else{
            throw new IllegalArgumentException("Error! No se puede eliminar el keyframe 0");
        }
    }
    
    
    public Figura(String nombre) {
        this.nombre = nombre;
        
        listakeyframes = new DefaultListModel<>(); //creamos 
        float[] par = {0,0};
        Keyframe KeyframeInicial  = new Keyframe(0, Keyframe.transformacionKeyframe.NINGUNO, this, par);
        listakeyframes.addElement(KeyframeInicial);
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
        for (int i = 0; i < getKeyframeInicial().listaPuntos.size(); i++) {
            Punto p = getKeyframeInicial().listaPuntos.get(i);
            //getListaPuntos().get(i).dibujar(rend);  
            float valx = p.getPx() * Canvas.escala;
            float valy = p.getPy() * Canvas.escala;
            
            if(i<getKeyframeInicial().listaPuntos.size()-1){
                Punto p2 = getKeyframeInicial().listaPuntos.get(i+1);
                float valx2 = p2.getPx() * Canvas.escala;
                float valy2 = p2.getPy() * Canvas.escala;
                
                rend.setColor(0,1,0,1);
                rend.rectLine(valx, valy, valx2, valy2, 3);
                /*rend.rectLine(getListaPuntos().get(i).getPx()*Canvas.escala,
                    getListaPuntos().get(i).getPy()*Canvas.escala,
                    getListaPuntos().get(i+1).getPx()*Canvas.escala,
                    getListaPuntos().get(i+1).getPy()*Canvas.escala, 4);*/
            }
            else{
                Punto p2 = getKeyframeInicial().listaPuntos.get(0);
                float valx2 = p2.getPx() * Canvas.escala;
                float valy2 = p2.getPy() * Canvas.escala;
                
                rend.setColor(0,0,1,1);
                rend.rectLine(valx, valy, valx2, valy2, 3);
                
                /*rend.rectLine(getListaPuntos().get(i).getPx()*Canvas.escala,
                    getListaPuntos().get(i).getPy()*Canvas.escala,
                    getListaPuntos().get(0).getPx()*Canvas.escala,
                    getListaPuntos().get(0).getPy()*Canvas.escala, 4);*/
            }
            getKeyframeInicial().listaPuntos.get(i).dibujar(rend);
        }   
        
        
        
        /*
        rend.setColor(Color.RED);
        rend.rectLine(getListaPuntos().get(0).getPx() * Canvas.escala, 
                      getListaPuntos().get(0).getPy() * Canvas.escala, 
                      getListaPuntos().get(1).getPx() * Canvas.escala, 
                      getListaPuntos().get(1).getPy() * Canvas.escala, 
                      3);
        */
        
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
     *//*
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
  /*  public void setListaPuntos(DefaultListModel<Punto> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }
*/
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
