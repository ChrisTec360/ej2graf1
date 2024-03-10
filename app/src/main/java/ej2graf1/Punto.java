/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author libookami
 */
public class Punto {

    public static int radio = 5;
    public static Color color;
    
    /**
     * @return the px
     */
    public int getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(int px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public int getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(int py) {
        this.py = py;
    }
    private int px;
    private int py;

    public Punto(int px, int py) {
        this.px = px;
        this.py = py;
    }
    
    public void dibujar(ShapeRenderer rend)
    {
        if(rend != null)
        {
            rend.setColor(Color.GOLD);
            rend.circle(getPx() * Canvas.escala, getPy() * Canvas.escala, radio);
        }
    }

    public Punto() {
    }

    @Override
    public String toString() {
        return "(" + getPx() + "," + getPy() + ")";
    }
    
    
}
