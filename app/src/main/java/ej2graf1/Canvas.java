/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javax.swing.DefaultListModel;

/**
 *
 * @author libookami
 */
public class Canvas implements ApplicationListener{

    public static int escala = 20;
    
    public DefaultListModel<Figura> ListaFiguras;
    
    VentanaPrincipal v;
    
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer rend;

    public Canvas(VentanaPrincipal padre) {
        this.v = padre;
        ListaFiguras = new DefaultListModel<>();
    }
    
    @Override
    public void create() {
        System.out.println("Ejecutado create");
        batch = new SpriteBatch();
        font = new BitmapFont();
        rend = new ShapeRenderer();
    }

    @Override
    public void resize(int i, int i1) {
        System.out.println("Ejecutado resize: " + i + ", " + i1);
    }

    @Override
    public void render() {
        //Limpiar con color de fondo.
        Gdx.gl.glClearColor(0.25f, .25f, .25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        rend.begin(ShapeRenderer.ShapeType.Line);
            rend.setColor(Color.WHITE);
            rend.line(1 * escala, 0 , 1 * escala, Gdx.graphics.getHeight());
            rend.line(0, 1 * escala , Gdx.graphics.getWidth(),1 * escala);
        rend.end();
        
        batch.begin();
            font.draw(batch, "Hola mundo", 100, 100);
        batch.end();
        
        rend.begin(ShapeRenderer.ShapeType.Filled);
        
            for (int i = 0; i < ListaFiguras.size(); i++) {
                ListaFiguras.get(i).dibujar(rend);
            }

            int h = Gdx.graphics.getHeight(); ///400
            int w = Gdx.graphics.getWidth();  ///400
            int i=0, j=0, x=0;

            for(i=0; i<=h; i++){
                rend.rectLine(0,i*escala, w, i*escala, 1, com.badlogic.gdx.graphics.Color.WHITE, com.badlogic.gdx.graphics.Color.WHITE);    
            }
            for(j=0; j<=w; j++){
                rend.rectLine(j*escala,0, j*escala, h, 1, com.badlogic.gdx.graphics.Color.WHITE, com.badlogic.gdx.graphics.Color.WHITE);    
            }
            //rend.setColor(Color.GOLD);
            //rend.circle((Integer)v.jSpinner1.getValue(), 200, 50);
            //rend.setColor(Color.RED);
            //rend.rectLine(100,300, 200, 400, 5);

        rend.end();
    }

    @Override
    public void pause() {
        System.out.println("Ejecutado pause");
    }

    @Override
    public void resume() {
        System.out.println("Ejecutado resume");
    }

    @Override
    public void dispose() {
        System.out.println("Ejecutado dispose");
        batch.dispose();
        font.dispose();
        rend.dispose();
    }
    
}
