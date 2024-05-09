/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import javax.swing.DefaultListModel;

/**
 *
 * @author libookami
 */
public class Canvas implements ApplicationListener{

    public static int escala = 20;
    
    public DefaultListModel<Figura> ListaFiguras;
    public DefaultListModel<Obj3D> ListaFiguras3D;
    
    VentanaPrincipal v;
    
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer rend;

    //3D
    Environment env;
    ModelBatch batch3d;
    ModelBuilder builder3d;
    PerspectiveCamera cam;
    CameraInputController caminput;
    
    Model m1, m2;
    ModelInstance m1instance, m2instance;
    
    public Canvas(VentanaPrincipal padre) {
        this.v = padre;
        ListaFiguras = new DefaultListModel<>();
        ListaFiguras3D = new DefaultListModel<>();
    }
    
    void inicializar2d(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        rend = new ShapeRenderer();
    }
    void inicializar3d(){
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        env.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        DefaultShader.Config shader_config = new DefaultShader.Config();
        shader_config.numDirectionalLights = 1;
        shader_config.numPointLights = 0;
        shader_config.numBones = 16;

        batch3d = new ModelBatch(new DefaultShaderProvider(shader_config));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        builder3d = new ModelBuilder();

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model;
        //String figuraSeleccionada = (String) v.Figuras3D.getSelectedItem();

        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.PINK)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        m1instance = new ModelInstance(model);
        
        /*
        m1 = builder3d.createBox(5f, 5f, 5f, //Tamaño
                new Material(ColorAttribute.createDiffuse(Color.GOLD)), //Color
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        m1instance = new ModelInstance(m1);*/
        
        caminput = new CameraInputController(cam);
        Gdx.input.setInputProcessor(caminput);
    }
    @Override
    public void create() {
        System.out.println("Ejecutado create");
        
    }

    @Override
    public void resize(int i, int i1) {
        System.out.println("Ejecutado resize: " + i + ", " + i1);
        inicializar2d();
        inicializar3d();
    }

    
    void render2d() {
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

    void render3D()
    {
         //Limpiar con color de fondo.       
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (v.botonArriba.getModel().isPressed()) {
            cam.position.y += 0.1f;
        } else if (v.botonAbajo.getModel().isPressed()) {
            cam.position.y += -0.1f;
        } else if (v.botonIzquierda.getModel().isPressed()) {
            cam.position.x += -0.1f;
        } else if (v.botonDerecha.getModel().isPressed()) {
            cam.position.x += 0.1f;
        } else if (v.botonAdelante.getModel().isPressed()) {
            cam.position.z += -0.1f;
        } else if (v.botonAtras.getModel().isPressed()) {
            cam.position.z += 0.1f;
        }

        cam.update();
        caminput.update();

        batch3d.begin(cam);
        for (int i = 0; i < ListaFiguras3D.size(); i++) {
            Obj3D objeto = ListaFiguras3D.get(i);
            if(objeto.model== null | objeto.instance==null){
                objeto.InicializarModel();
            }
            objeto.dibujar(batch3d, env);
        }
        batch3d.end();

        
        /*
        //Limpiar con color de fondo.       
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        cam.update();
        caminput.update();
        
        batch3d.begin(cam);
        batch3d.render(m1instance,env);
        batch3d.end();
*/    
}
    
    @Override
    public void render(){
        if(v.radio2D.isSelected()){
            render2d();
        }else{
            render3D();            
        }
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

    public static void setEscala(int escala) {
        Canvas.escala = escala;
    }
    
}
