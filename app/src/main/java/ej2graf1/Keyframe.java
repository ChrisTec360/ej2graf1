/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

public class Keyframe {
    int num_fotograma;
    Figura figura;
    float[] param = new float[2];
    
    public Keyframe(){
        
    }
    
    @Override
    public String toString(){
        return num_fotograma + ": "+ figura.getNombre() + "; "+ param[0] + param[1];
    }
}
