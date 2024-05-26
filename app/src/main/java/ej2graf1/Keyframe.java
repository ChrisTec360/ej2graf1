/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import javax.swing.DefaultListModel;


public class Keyframe {
    
    public enum transformacionKeyframe {
        NINGUNO, TRASLACIÓN, ESCALADO, ROTACIÓN, SESGADO_X, SESGADO_Y
    }

    int num_fotograma;
    Figura figura;
    transformacionKeyframe transformacion = transformacionKeyframe.NINGUNO;
    
    float[] param = new float[2];
    public DefaultListModel<Punto> listaPuntos;
    
    public Keyframe(int num_fot, transformacionKeyframe transf, Figura figura, float[] par){
        this.num_fotograma = num_fot;
        this.transformacion = transf;
        this.figura = figura;
        this.param = par;
        
        listaPuntos = new DefaultListModel<>();
    }
    
    @Override
    public String toString(){
        return num_fotograma + ": "+ figura.getNombre() + "; "+ param[0] + param[1];
    }
}
