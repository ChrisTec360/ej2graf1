/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
   
    public void AgregarKeyframe(Keyframe k)throws IllegalArgumentException{
        boolean encontrado = Arrays.stream(listakeyframes.toArray()).anyMatch(x -> ((Keyframe)x).num_fotograma == k.num_fotograma);
        
        if (encontrado) {
            throw new IllegalArgumentException("Eror: Ya existe un keyframe en ese fotograma");
        }
        
        listakeyframes.addElement(k); 
        ordenarKeyframes();
        System.out.println("agregado, nuevo size:  " + listakeyframes.size());
    }
    
    public void EliminarKeyframe(Keyframe k) throws IllegalArgumentException{
        int idx = listakeyframes.indexOf(k);
        if(idx > 0){
            listakeyframes.removeElement(k);
        }else{
            throw new IllegalArgumentException("Error! No se puede eliminar el keyframe 0");
        }
        ordenarKeyframes();
    }
    
    public void ordenarKeyframes(){
        List<Object> lista_ordenada = Arrays.asList(listakeyframes.toArray());
        lista_ordenada.sort(Comparator.comparing(x -> ((Keyframe)x).num_fotograma));
        
        listakeyframes.clear(); 
        
        for(Object k : lista_ordenada){
            listakeyframes.addElement((Keyframe)k);
        }
         
    }
    
    public Keyframe getKeyframeAnterior(int num_fotograma){
        Keyframe k = listakeyframes.get(0);
        if(listakeyframes.size() > 1 && num_fotograma > 0){
            
            for (int i = 0; i < listakeyframes.size(); i++) {
                k = listakeyframes.get(i);
                
                if(i < listakeyframes.size()-1
                    && num_fotograma >= listakeyframes.get(i).num_fotograma
                    && num_fotograma < listakeyframes.get(i+1).num_fotograma)
                {
                    break;
                }
            }
        }
        return k; 
    }
    
    public Keyframe GetKeyframeSiguiente(Keyframe anterior){
        int idx_anterior = listakeyframes.indexOf(anterior);
        
        if(idx_anterior < listakeyframes.size()-1){
            return listakeyframes.get(idx_anterior+1); 
        }else{
            return null;
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
    
    public void ActualizarAnimacion(int num_fotograma){
        Keyframe anterior = getKeyframeAnterior(num_fotograma);
        
        //Keyframe siguiente  = GetKeyframeSiguiente(anterior);
        
        System.out.println("Anterior: " + anterior.num_fotograma);
        /*
        if(siguiente != null){
            System.out.println("Siguiente: " + siguiente.num_fotograma);    
        }else{
            System.out.println("Siguiente: null");    
        }*/
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
