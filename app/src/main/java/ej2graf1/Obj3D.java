/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Disposable;
import static ej2graf1.tipoPrimitiva.CILINDRO;
import static ej2graf1.tipoPrimitiva.CONO;
import static ej2graf1.tipoPrimitiva.CUBO;
import static ej2graf1.tipoPrimitiva.ESFERA;

/**
 *
 * @author Chris
 */

enum tipoPrimitiva {
    CUBO, CILINDRO, ESFERA, CONO
}

public class Obj3D implements Disposable{
    public String Nombre;
    public Color C;
    public float sx, sy, sz;
    public tipoPrimitiva tipo;
    public Model model;
    public ModelInstance instance = null;

    //inicializar model builde
    ModelBuilder modelBuilder;

    public Obj3D(String nombre, tipoPrimitiva tipo, Color c, float sx, float sy, float sz,ModelBuilder ModelB) {
        this.Nombre = nombre;
        this.tipo=tipo;
        this.C = c;
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        this.modelBuilder = ModelB;
    }
    
    public void InicializarModel() {
        switch (tipo) {
            case CUBO:
                model = modelBuilder.createBox(sx, sy, sz,
                        new Material(ColorAttribute.createDiffuse(this.C)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                break;
            case CONO:
                model = modelBuilder.createCone(sx, sy, sz, 20, // Tamaño y segmentos
                        new Material(ColorAttribute.createDiffuse(this.C)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                
                break;
            case CILINDRO:
                model = modelBuilder.createCylinder(sx, sy, sz, 20, // Tamaño y segmentos
                        new Material(ColorAttribute.createDiffuse(this.C)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                
                break;
            case ESFERA:
                model = modelBuilder.createSphere(sx, sy, sz, 20, 20, // Tamaño y segmentos
                        new Material(ColorAttribute.createDiffuse(this.C)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                
                break;
            default:
                // Figura no válida
                return;
        }
        instance = new ModelInstance(model);
    }
    
    public void dibujar(ModelBatch mb, Environment env) {
        if (model != null && instance != null) {
            mb.render(instance, env);
        }
    }
    
    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        return Nombre;
    }
    
    
    
    
}
