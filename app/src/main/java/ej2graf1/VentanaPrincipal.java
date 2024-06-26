/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ej2graf1;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author libookami
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    Canvas canvas;
    Figura figura;
    private String rutaArchivoActual = null;
    Figura FiguraSeleccionada = null;
    Punto puntoSeleccionado = null;
    Keyframe KeyframeSeleccionado = null;
    Obj3D select3D = null;
    
    
    /**
     * @return the puntoSeleccionado
     */
    public Punto getPuntoSeleccionado() {
        return puntoSeleccionado;
    }

    /**
     * @param puntoSeleccionado the puntoSeleccionado to set
     */
    public void setPuntoSeleccionado(Punto puntoSeleccionado) {
        this.puntoSeleccionado = puntoSeleccionado;
        
        if(this.puntoSeleccionado != null)
        {
            cajaPuntoX.setText((String.valueOf(getPuntoSeleccionado().getPx())));
            cajaPuntoY.setText((String.valueOf(getPuntoSeleccionado().getPy())));
        }
        else
        {
            cajaPuntoX.setText("");
            cajaPuntoY.setText("");
        }
    }
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        Figuras3D.setModel(new DefaultComboBoxModel<tipoPrimitiva>(tipoPrimitiva.values()));
        grupoRadios.add(radio2D);
        grupoRadios.add(radio3D);
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.vSyncEnabled = true;
        config.foregroundFPS = 60;
        
        canvas = new Canvas(this);
        LwjglAWTCanvas awtcanvas = new LwjglAWTCanvas(canvas, config);
        plano.add(awtcanvas.getCanvas());
        
        JLSTFiguras.setModel(canvas.ListaFiguras);
        lista3D.setModel(canvas.ListaFiguras3D);
     
        JLSTFiguras.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                FiguraSeleccionada = JLSTFiguras.getSelectedValue();
                
                if(FiguraSeleccionada != null)
                {
                    jTextField4.setName(FiguraSeleccionada.getNombre());
                    JLST_PUNTOS.setModel(FiguraSeleccionada.listakeyframes.get(0).listaPuntos);
                    JLST_Keyframes.setModel(FiguraSeleccionada.listakeyframes);
                }else{
                    jTextField4.setText("");
                }
            }
        });
        
        JLST_PUNTOS.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(FiguraSeleccionada != null){
                    puntoSeleccionado = JLST_PUNTOS.getSelectedValue();
                    //setPuntoSeleccionado(JLST_PUNTOS.getSelectedValue()); 
                    if(puntoSeleccionado != null){
                        cajaPuntoX.setText(""+puntoSeleccionado.getPx());
                        cajaPuntoY.setText(""+puntoSeleccionado.getPy());
                    }else{
                        cajaPuntoX.setText("");
                        cajaPuntoY.setText("");
                    }
                }
                     
                //System.out.println(JLST_PUNTOS.getSelectedValue().toString());
            }
        });
        
        JLST_Keyframes.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(FiguraSeleccionada != null){
                    KeyframeSeleccionado = JLST_Keyframes.getSelectedValue();
                    
                    if(KeyframeSeleccionado != null){
                        canvas.setFotograma(KeyframeSeleccionado.num_fotograma);
                        cajaTransformer.setSelectedItem(KeyframeSeleccionado.transformacion);
                        
                        cajaParam1.setText(""+KeyframeSeleccionado.param[0]);
                        cajaParam2.setText(""+KeyframeSeleccionado.param[1]);
                    }else{
                        cajaTransformer.setSelectedIndex(0);
                        
                        cajaParam1.setText("0");
                        cajaParam2.setText("0");
                    }
                }
                
                //System.out.println(JLST_PUNTOS.getSelectedValue().toString());
            }
        });
        
        lista3D.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                select3D = lista3D.getSelectedValue();
                //System.out.println(JLST_PUNTOS.getSelectedValue().toString());
            }
        });
        
        SliderTiempo.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(SliderTiempo.getValueIsAdjusting()){
                    canvas.setFotograma(SliderTiempo.getValue());
                }
            }
        });
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadios = new javax.swing.ButtonGroup();
        mantel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        tabbedMundos = new javax.swing.JTabbedPane();
        plano = new javax.swing.JPanel();
        tabAnimacion = new javax.swing.JTabbedPane();
        controles = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelFiguras = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JLSTFiguras = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        btnAgregarFig = new ej2graf1.MiButton();
        btnEditarFig = new ej2graf1.MiButton();
        btnEliminarFig = new ej2graf1.MiButton();
        panelPuntos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JLST_PUNTOS = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        cajaPuntoX = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cajaPuntoY = new javax.swing.JTextField();
        btnEliminarPunto = new ej2graf1.MiButton();
        btnAgregarPunto = new ej2graf1.MiButton();
        btnEditarPunto = new ej2graf1.MiButton();
        jLabel3 = new javax.swing.JLabel();
        textEscala = new javax.swing.JTextField();
        panelModos = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        rotacion = new javax.swing.JPanel();
        btnRotar = new ej2graf1.MiButton();
        jLabel5 = new javax.swing.JLabel();
        cajaRotar = new javax.swing.JTextField();
        traslacion = new javax.swing.JPanel();
        btnTrasladar = new ej2graf1.MiButton();
        labelcajaTraslacionX = new javax.swing.JLabel();
        labelcajaTraslacionY = new javax.swing.JLabel();
        cajaTraslacionX = new javax.swing.JTextField();
        cajaTraslacionY = new javax.swing.JTextField();
        escalado = new javax.swing.JPanel();
        btnEscalar = new ej2graf1.MiButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cajaEscalarX = new javax.swing.JTextField();
        cajaEscalarY = new javax.swing.JTextField();
        sesgo = new javax.swing.JPanel();
        btnSesgar = new ej2graf1.MiButton();
        boxSesgo = new javax.swing.JComboBox<>();
        cajaSesgar = new javax.swing.JTextField();
        ctrls3d = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista3D = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        Figuras3D = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nombreFig3D = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ejeX = new javax.swing.JTextField();
        ejeY = new javax.swing.JTextField();
        ejeZ = new javax.swing.JTextField();
        btnAgregarFig3D = new ej2graf1.MiButton();
        btnEliminarFig3D = new ej2graf1.MiButton();
        btnEliminarFig3D1 = new ej2graf1.MiButton();
        botonAtras = new ej2graf1.MiButton();
        jLabel18 = new javax.swing.JLabel();
        botonAdelante = new ej2graf1.MiButton();
        botonIzquierda = new ej2graf1.MiButton();
        botonDerecha = new ej2graf1.MiButton();
        botonArriba = new ej2graf1.MiButton();
        botonAbajo = new ej2graf1.MiButton();
        jPanel2 = new javax.swing.JPanel();
        btnPlay = new ej2graf1.MiButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        SliderTiempo = new javax.swing.JSlider();
        LBL_Tiempo = new javax.swing.JLabel();
        JLST_Keyframesss = new javax.swing.JPanel();
        cajaTransformer = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cajaParam1 = new javax.swing.JTextField();
        cajaParam2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        JLST_Keyframes = new javax.swing.JList<>();
        btnAgregarFrame = new ej2graf1.MiButton();
        btnEditarFrame = new ej2graf1.MiButton();
        btnEliminarFrame = new ej2graf1.MiButton();
        jLabel17 = new javax.swing.JLabel();
        radio2D = new javax.swing.JRadioButton();
        radio3D = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        guardarItem = new javax.swing.JMenuItem();
        cargarItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 153));
        setMinimumSize(new java.awt.Dimension(420, 420));

        mantel.setBackground(new java.awt.Color(255, 153, 255));
        mantel.setPreferredSize(new java.awt.Dimension(1090, 720));

        jSplitPane1.setDividerLocation(350);

        plano.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        plano.setLayout(new java.awt.GridLayout(1, 0));
        tabbedMundos.addTab("2D", plano);

        jSplitPane1.setRightComponent(tabbedMundos);

        jSplitPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSplitPane2.setDividerLocation(150);

        panelFiguras.setBackground(new java.awt.Color(102, 102, 102));
        panelFiguras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFiguras.setMaximumSize(new java.awt.Dimension(700, 700));
        panelFiguras.setPreferredSize(new java.awt.Dimension(149, 670));

        jScrollPane1.setViewportView(JLSTFiguras);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre:");

        btnAgregarFig.setText("Agregar");
        btnAgregarFig.setColorNormal(new java.awt.Color(0, 255, 0));
        btnAgregarFig.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnAgregarFig.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnAgregarFig.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarFig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFigActionPerformed(evt);
            }
        });

        btnEditarFig.setText("Editar");
        btnEditarFig.setColorNormal(new java.awt.Color(0, 255, 0));
        btnEditarFig.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarFig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFigActionPerformed(evt);
            }
        });

        btnEliminarFig.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminarFig.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarFig.setText("Eliminar");
        btnEliminarFig.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarFig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarFig, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarFig, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarFig, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarFig, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditarFig, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarFig, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelFigurasLayout = new javax.swing.GroupLayout(panelFiguras);
        panelFiguras.setLayout(panelFigurasLayout);
        panelFigurasLayout.setHorizontalGroup(
            panelFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelFigurasLayout.setVerticalGroup(
            panelFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(panelFiguras);

        panelPuntos.setBackground(new java.awt.Color(102, 102, 102));
        panelPuntos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelPuntos.setPreferredSize(new java.awt.Dimension(199, 670));

        jScrollPane2.setViewportView(JLST_PUNTOS);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cajaPuntoX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaPuntoXActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("X:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Y:");

        btnEliminarPunto.setBackground(new java.awt.Color(250, 0, 0));
        btnEliminarPunto.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPunto.setText("Eliminar");
        btnEliminarPunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPuntoActionPerformed(evt);
            }
        });

        btnAgregarPunto.setText("Agregar");
        btnAgregarPunto.setColorNormal(new java.awt.Color(0, 255, 0));
        btnAgregarPunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPuntoActionPerformed(evt);
            }
        });

        btnEditarPunto.setText("Editar");
        btnEditarPunto.setColorNormal(new java.awt.Color(0, 255, 0));
        btnEditarPunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPuntoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAgregarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaPuntoX))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaPuntoY, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaPuntoX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaPuntoY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Escala:");

        textEscala.setText("20");
        textEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEscalaActionPerformed(evt);
            }
        });
        textEscala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEscalaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelPuntosLayout = new javax.swing.GroupLayout(panelPuntos);
        panelPuntos.setLayout(panelPuntosLayout);
        panelPuntosLayout.setHorizontalGroup(
            panelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelPuntosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPuntosLayout.setVerticalGroup(
            panelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textEscala))
                .addGap(81, 81, 81))
        );

        jSplitPane2.setRightComponent(panelPuntos);

        panelModos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelModosLayout = new javax.swing.GroupLayout(panelModos);
        panelModos.setLayout(panelModosLayout);
        panelModosLayout.setHorizontalGroup(
            panelModosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelModosLayout.setVerticalGroup(
            panelModosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );

        btnRotar.setText("Rotar");
        btnRotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ángulo:");

        javax.swing.GroupLayout rotacionLayout = new javax.swing.GroupLayout(rotacion);
        rotacion.setLayout(rotacionLayout);
        rotacionLayout.setHorizontalGroup(
            rotacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rotacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rotacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRotar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rotacionLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaRotar)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        rotacionLayout.setVerticalGroup(
            rotacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rotacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rotacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cajaRotar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRotar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Rotación", rotacion);

        btnTrasladar.setText("Trasladar");
        btnTrasladar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrasladarActionPerformed(evt);
            }
        });

        labelcajaTraslacionX.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelcajaTraslacionX.setText("x:");

        labelcajaTraslacionY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelcajaTraslacionY.setText("y:");

        javax.swing.GroupLayout traslacionLayout = new javax.swing.GroupLayout(traslacion);
        traslacion.setLayout(traslacionLayout);
        traslacionLayout.setHorizontalGroup(
            traslacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(traslacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(traslacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrasladar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(traslacionLayout.createSequentialGroup()
                        .addComponent(labelcajaTraslacionX)
                        .addGap(6, 6, 6)
                        .addComponent(cajaTraslacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelcajaTraslacionY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaTraslacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        traslacionLayout.setVerticalGroup(
            traslacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, traslacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(traslacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelcajaTraslacionX)
                    .addComponent(labelcajaTraslacionY)
                    .addComponent(cajaTraslacionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaTraslacionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrasladar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Traslación", traslacion);

        btnEscalar.setText("Escalar");
        btnEscalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscalarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("x:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("y:");

        javax.swing.GroupLayout escaladoLayout = new javax.swing.GroupLayout(escalado);
        escalado.setLayout(escaladoLayout);
        escaladoLayout.setHorizontalGroup(
            escaladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escaladoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(escaladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEscalar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(escaladoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaEscalarX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaEscalarY)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        escaladoLayout.setVerticalGroup(
            escaladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escaladoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(escaladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cajaEscalarX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaEscalarY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEscalar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Escalado", escalado);

        btnSesgar.setText("Sesgar");
        btnSesgar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSesgarActionPerformed(evt);
            }
        });

        boxSesgo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boxSesgo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "x", "y" }));
        boxSesgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSesgoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sesgoLayout = new javax.swing.GroupLayout(sesgo);
        sesgo.setLayout(sesgoLayout);
        sesgoLayout.setHorizontalGroup(
            sesgoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sesgoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sesgoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxSesgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSesgar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cajaSesgar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        sesgoLayout.setVerticalGroup(
            sesgoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sesgoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sesgoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxSesgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaSesgar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSesgar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Sesgado", sesgo);

        javax.swing.GroupLayout controlesLayout = new javax.swing.GroupLayout(controles);
        controles.setLayout(controlesLayout);
        controlesLayout.setHorizontalGroup(
            controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelModos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tab))
                .addContainerGap())
        );
        controlesLayout.setVerticalGroup(
            controlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlesLayout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(panelModos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabAnimacion.addTab("2D Ctrls", controles);

        jScrollPane3.setViewportView(lista3D);

        Figuras3D.setToolTipText("figuras");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tamaño");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Figura");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nombre");

        nombreFig3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFig3DActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Z:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Y:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("X:");

        btnAgregarFig3D.setText("Agregar");
        btnAgregarFig3D.setColorNormal(new java.awt.Color(0, 255, 0));
        btnAgregarFig3D.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnAgregarFig3D.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnAgregarFig3D.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarFig3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFig3DActionPerformed(evt);
            }
        });

        btnEliminarFig3D.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarFig3D.setText("Eliminar");
        btnEliminarFig3D.setColorNormal(new java.awt.Color(255, 51, 51));
        btnEliminarFig3D.setColorTextHover(new java.awt.Color(255, 255, 255));
        btnEliminarFig3D.setColorTextPressed(new java.awt.Color(255, 255, 255));
        btnEliminarFig3D.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarFig3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFig3DActionPerformed(evt);
            }
        });

        btnEliminarFig3D1.setBackground(new java.awt.Color(153, 0, 153));
        btnEliminarFig3D1.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarFig3D1.setText("Color");
        btnEliminarFig3D1.setColoPressed(new java.awt.Color(255, 51, 102));
        btnEliminarFig3D1.setColorNormal(new java.awt.Color(153, 0, 153));
        btnEliminarFig3D1.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnEliminarFig3D1.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnEliminarFig3D1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarFig3D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFig3D1ActionPerformed(evt);
            }
        });

        botonAtras.setText("Atrás");
        botonAtras.setColorNormal(new java.awt.Color(0, 255, 0));
        botonAtras.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonAtras.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonAtras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("MOVER CÁMARA");

        botonAdelante.setText("Adelante");
        botonAdelante.setColorNormal(new java.awt.Color(0, 255, 0));
        botonAdelante.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonAdelante.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonAdelante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAdelanteActionPerformed(evt);
            }
        });

        botonIzquierda.setText("Izquierda");
        botonIzquierda.setColorNormal(new java.awt.Color(0, 255, 0));
        botonIzquierda.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonIzquierda.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonIzquierda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIzquierdaActionPerformed(evt);
            }
        });

        botonDerecha.setText("Derecha");
        botonDerecha.setColorNormal(new java.awt.Color(0, 255, 0));
        botonDerecha.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonDerecha.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonDerecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDerechaActionPerformed(evt);
            }
        });

        botonArriba.setText("Arriba");
        botonArriba.setColorNormal(new java.awt.Color(0, 255, 0));
        botonArriba.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonArriba.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonArriba.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArribaActionPerformed(evt);
            }
        });

        botonAbajo.setText("Abajo");
        botonAbajo.setColorNormal(new java.awt.Color(0, 255, 0));
        botonAbajo.setColorTextHover(new java.awt.Color(0, 0, 0));
        botonAbajo.setColorTextPressed(new java.awt.Color(0, 0, 0));
        botonAbajo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(Figuras3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminarFig3D1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nombreFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ejeZ, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(111, 111, 111)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ejeX, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ejeY, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAgregarFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminarFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botonAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Figuras3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(ejeX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(ejeY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ejeZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarFig3D1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarFig3D, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132))
        );

        javax.swing.GroupLayout ctrls3dLayout = new javax.swing.GroupLayout(ctrls3d);
        ctrls3d.setLayout(ctrls3dLayout);
        ctrls3dLayout.setHorizontalGroup(
            ctrls3dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrls3dLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ctrls3dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ctrls3dLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ctrls3dLayout.setVerticalGroup(
            ctrls3dLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrls3dLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        tabAnimacion.addTab("3D Ctrls", ctrls3d);

        btnPlay.setText("Play");
        btnPlay.setColorNormal(new java.awt.Color(0, 255, 0));
        btnPlay.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnPlay.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnPlay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        SliderTiempo.setMaximum(3600);
        SliderTiempo.setValue(0);

        LBL_Tiempo.setText("jLabel14");

        JLST_Keyframesss.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cajaTransformer.setModel(new DefaultComboBoxModel<Keyframe.transformacionKeyframe>(Keyframe.transformacionKeyframe.values())
        );
        cajaTransformer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTransformerActionPerformed(evt);
            }
        });

        jLabel14.setText("Transformaciones");

        jLabel15.setText("Param. 1:");

        jLabel16.setText("Param. 2:");

        cajaParam1.setText("0");
        cajaParam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaParam1ActionPerformed(evt);
            }
        });

        cajaParam2.setText("0");
        cajaParam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaParam2ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(JLST_Keyframes);

        btnAgregarFrame.setText("Agregar");
        btnAgregarFrame.setColorNormal(new java.awt.Color(0, 255, 0));
        btnAgregarFrame.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnAgregarFrame.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnAgregarFrame.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregarFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFrameActionPerformed(evt);
            }
        });

        btnEditarFrame.setBackground(new java.awt.Color(204, 102, 0));
        btnEditarFrame.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarFrame.setText("Editar");
        btnEditarFrame.setColorNormal(new java.awt.Color(255, 102, 0));
        btnEditarFrame.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnEditarFrame.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnEditarFrame.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditarFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFrameActionPerformed(evt);
            }
        });

        btnEliminarFrame.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarFrame.setText("Eliminar");
        btnEliminarFrame.setColorNormal(new java.awt.Color(204, 0, 0));
        btnEliminarFrame.setColorTextHover(new java.awt.Color(0, 0, 0));
        btnEliminarFrame.setColorTextPressed(new java.awt.Color(0, 0, 0));
        btnEliminarFrame.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFrameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JLST_KeyframesssLayout = new javax.swing.GroupLayout(JLST_Keyframesss);
        JLST_Keyframesss.setLayout(JLST_KeyframesssLayout);
        JLST_KeyframesssLayout.setHorizontalGroup(
            JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLST_KeyframesssLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JLST_KeyframesssLayout.createSequentialGroup()
                        .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addGap(26, 26, 26)
                        .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cajaParam2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaParam1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaTransformer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addGroup(JLST_KeyframesssLayout.createSequentialGroup()
                        .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JLST_KeyframesssLayout.createSequentialGroup()
                                .addComponent(btnAgregarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(btnEditarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        JLST_KeyframesssLayout.setVerticalGroup(
            JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JLST_KeyframesssLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaTransformer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cajaParam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cajaParam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JLST_KeyframesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel17.setText("KeyFrames");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SliderTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLST_Keyframesss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSiguiente))
                            .addComponent(LBL_Tiempo)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addGap(18, 18, 18)
                .addComponent(SliderTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBL_Tiempo)
                .addGap(3, 3, 3)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLST_Keyframesss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabAnimacion.addTab("Animación", jPanel2);

        jSplitPane1.setLeftComponent(tabAnimacion);

        radio2D.setSelected(true);
        radio2D.setText("2D");

        radio3D.setText("3D");
        radio3D.setHideActionText(true);

        javax.swing.GroupLayout mantelLayout = new javax.swing.GroupLayout(mantel);
        mantel.setLayout(mantelLayout);
        mantelLayout.setHorizontalGroup(
            mantelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mantelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
            .addGroup(mantelLayout.createSequentialGroup()
                .addGap(499, 499, 499)
                .addComponent(radio2D)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radio3D)
                .addContainerGap(542, Short.MAX_VALUE))
        );
        mantelLayout.setVerticalGroup(
            mantelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mantelLayout.createSequentialGroup()
                .addGroup(mantelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio2D)
                    .addComponent(radio3D))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Menú 2D");

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemNuevo.setText("Nuevo");
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevo);

        guardarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        guardarItem.setText("Guardar");
        guardarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarItemActionPerformed(evt);
            }
        });
        jMenu1.add(guardarItem);

        cargarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        cargarItem.setText("Cargar");
        cargarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarItemActionPerformed(evt);
            }
        });
        jMenu1.add(cargarItem);

        jMenuItem1.setText("Acerca de");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Herramientas 3D");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mantel, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mantel, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEscalaActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Escala: " + textEscala.getText());
        Canvas.setEscala(Integer.valueOf(textEscala.getText()));
    }//GEN-LAST:event_textEscalaActionPerformed

    private void textEscalaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEscalaKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar();
        String text = textEscala.getText();

        // Verificar si es un dígito
        if (!Character.isDigit(key)) {
            evt.consume(); // Consumir evento si no es dígito
            return;
        }
        if (textEscala.getText().length() == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_textEscalaKeyTyped

    private void btnAgregarFigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFigActionPerformed
        // TODO add your handling code here:
        if(!jTextField4.getText().isEmpty())
        {
            canvas.ListaFiguras.addElement(new Figura(jTextField4.getText()));
        }
    }//GEN-LAST:event_btnAgregarFigActionPerformed

    private void btnEditarFigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFigActionPerformed
        // TODO add your handling code here:
        if(!jTextField4.getText().isEmpty() && FiguraSeleccionada != null)
        {
            FiguraSeleccionada.setNombre(jTextField4.getText());
        }
        
        JLSTFiguras.updateUI();
    }//GEN-LAST:event_btnEditarFigActionPerformed

    private void btnEliminarFigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFigActionPerformed
        // TODO add your handling code here:
        if(FiguraSeleccionada != null)
        {
            puntoSeleccionado = null;
            canvas.ListaFiguras.removeElement(FiguraSeleccionada);
        }
        
        JLSTFiguras.updateUI();
    }//GEN-LAST:event_btnEliminarFigActionPerformed

    private void btnEliminarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPuntoActionPerformed
        // TODO add your handling code here:
        if(FiguraSeleccionada != null && getPuntoSeleccionado() != null)
        {
            FiguraSeleccionada.getKeyframeInicial().listaPuntos.removeElement(getPuntoSeleccionado());
        }
        
        JLST_PUNTOS.updateUI();
    }//GEN-LAST:event_btnEliminarPuntoActionPerformed

    private void btnAgregarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPuntoActionPerformed
        // TODO add your handling code here:
        if(FiguraSeleccionada != null){
            int px = Ints.tryParse(cajaPuntoX.getText()).intValue();
            int py = Ints.tryParse(cajaPuntoY.getText()).intValue();
            
            cajaPuntoX.setText(""+px);
            cajaPuntoX.setText(""+py);
            
            FiguraSeleccionada.getKeyframeInicial().listaPuntos.addElement(new Punto(px,py));
        }
        
        JLST_PUNTOS.updateUI();
    }//GEN-LAST:event_btnAgregarPuntoActionPerformed

    private void btnEditarPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPuntoActionPerformed
        // TODO add your handling code here:
        if(getPuntoSeleccionado() != null)
        {
            getPuntoSeleccionado().setPx(Float.parseFloat(cajaPuntoX.getText()));
            getPuntoSeleccionado().setPy(Float.parseFloat(cajaPuntoY.getText()));
        }
        
        JLST_PUNTOS.updateUI();
    }//GEN-LAST:event_btnEditarPuntoActionPerformed

    private void cajaPuntoXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaPuntoXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaPuntoXActionPerformed

    private void btnTrasladarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrasladarActionPerformed
        // TODO add your handling code here:
     if(FiguraSeleccionada!=null){
        if(cajaTraslacionX.getText().isEmpty() || cajaTraslacionY.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debes llenar los dos campos");
        } else {
            float Tx = Float.parseFloat(cajaTraslacionX.getText());
            float Ty = Float.parseFloat(cajaTraslacionY.getText());

            float[][] valores3x3 = {
                {1, 0, Tx},
                {0, 1, Ty},
                {0, 0, 1}
            };

            Matriz33 m33 = new Matriz33(valores3x3);

            for(int i = 0; i < FiguraSeleccionada.getKeyframeInicial().listaPuntos.getSize(); i++){
                Punto elactual = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);
                float x = elactual.getPx();
                float y = elactual.getPy();
                
                float[] valores3x1 = {x, y, 1};
                Matriz31 m31 = new Matriz31(valores3x1);
                
                float[] resultado = m33.multiMatrices(m31);
                
                elactual.setPx(resultado[0]);
                elactual.setPy(resultado[1]);
            }

            // Imprimir la matriz resultante en la consola
            imprimirMatriz(m33.getMatriz());



            JLST_PUNTOS.updateUI(); 
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debes seleccionar una figura");
    }
    }//GEN-LAST:event_btnTrasladarActionPerformed

    private void guardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarItemActionPerformed
        // TODO add your handling code here:
    
        // Obtener la ruta del archivo actual
        String path = rutaArchivoActual; 
        
        // Verificar si hay un archivo actual para guardar
        if (path != null && !path.isEmpty()) {
            String s = "";
            int opcion = JOptionPane.showConfirmDialog(null, "¿Guardar los cambios?");
            if (opcion == JOptionPane.YES_OPTION) {               
                for (int i = 0; i < canvas.ListaFiguras.size(); i++) {
                    Figura f = canvas.ListaFiguras.get(i);
                    s += f.getNombre();

                    for (int j = 0; j < f.getKeyframeInicial().listaPuntos.size(); j++) {
                        Punto p = f.getKeyframeInicial().listaPuntos.get(j);

                        s += "," + p.getPx() + "," + p.getPy();
                    }
                    s += "\n";
                }

                System.out.println("El contendido del archivo será " + s);
                JOptionPane.showMessageDialog(null, "Datos guardados");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                    writer.append(s);
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(this, "Error de IO: " + ioex.getMessage());
                }
            }
        } else {
            // Si no hay un archivo actual para guardar, mostrar un mensaje al usuario
            JOptionPane.showMessageDialog(null, "No hay un archivo actual para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
      
    }//GEN-LAST:event_guardarItemActionPerformed

    private void guardarRutaArchivoActual(String rutaArchivo) {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.put("rutaArchivoActual", rutaArchivo);
    }

    private void cargaInicio(){
        // Obtener las preferencias del usuario
        Preferences prefs = Preferences.userNodeForPackage(getClass());

        // Recuperar la ruta del último archivo guardado de las preferencias del usuario
        String rutaArchivoActual = prefs.get("rutaArchivoActual", null);

        if (rutaArchivoActual != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivoActual))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                    String[] tokens = linea.split(","); // Separar todo los separado por coma

                    Figura f = new Figura(tokens[0]);
                    for (int i = 1; i < tokens.length; i += 2) {
                        float x = Floats.tryParse(tokens[i]);
                        float y = Floats.tryParse(tokens[i + 1]);

                        Punto p = new Punto(x, y);
                        f.getKeyframeInicial().listaPuntos.addElement(p);
                    }
                    canvas.ListaFiguras.addElement(f); // Traemos el nombre de la figura
                }
            } catch (IOException iox) {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo guardado.");
            }
        } else {
            // Si no hay ninguna ruta de archivo guardada en las preferencias, podrías mostrar un mensaje o realizar alguna acción predeterminada
            JOptionPane.showMessageDialog(null, "No se encontró ningún archivo guardado.");
        }

    }
    
    private void cargarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarItemActionPerformed
        // TODO add your handling code here:
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el diálogo de selección de archivo
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado por el usuario
            File archivoSeleccionado = fileChooser.getSelectedFile();

            // Establecer la ruta del archivo actual
            rutaArchivoActual = archivoSeleccionado.getAbsolutePath();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                canvas.ListaFiguras.clear();
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                    String[] tokens = linea.split(","); // Separar todo los separado por coma

                    Figura f = new Figura(tokens[0]);
                    for (int i = 1; i < tokens.length; i += 2) {
                        float x = Floats.tryParse(tokens[i]);
                        float y = Floats.tryParse(tokens[i + 1]);

                        Punto p = new Punto(x, y);
                        f.getKeyframeInicial().listaPuntos.addElement(p);
                    }
                    canvas.ListaFiguras.addElement(f); // Traemos el nombre de la figura
                    
                }
                
                JLSTFiguras.updateUI();
                JLST_PUNTOS.updateUI();
            } catch (IOException iox) {
                JOptionPane.showMessageDialog(null, "Error al cargar las figuras.");
            }
        }
        //Guardar la ruta del archivo actual en las preferencias
        guardarRutaArchivoActual(rutaArchivoActual);
    }//GEN-LAST:event_cargarItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
                JOptionPane.showMessageDialog(null, "Graficación grupo 1 - Equipo 4"
                + "\n\nBazán Calderón Christian Emmanuel"
                + "\nFranco Vidrio Guillermo"
                + "\nSánchez Arenas Perla Vanessa"
                + "\nSedeño García José Daniel"
                + "\nVillanueva Gutiérrez Miriam Alejandra"
                + "\nVillafán Contreras Luis Gustavo");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnEscalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscalarActionPerformed
        // TODO add your handling code here:
        if (FiguraSeleccionada != null) {
            if (cajaEscalarX.getText().isEmpty() || cajaEscalarY.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes llenar los dos campos");
            } else {
                float Sx = Float.valueOf(cajaEscalarX.getText());
                float Sy = Float.valueOf(cajaEscalarY.getText());

                // 1. Calcular el centro de la figura
                float centroX = 0;
                float centroY = 0;
                int numPuntos = FiguraSeleccionada.getKeyframeInicial().listaPuntos.getSize();
                for (int i = 0; i < numPuntos; i++) {
                    Punto punto = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);
                    centroX += punto.getPx();
                    centroY += punto.getPy();
                }
                centroX /= numPuntos;
                centroY /= numPuntos;

                // 2. Trasladar todos los puntos de la figura al origen (0, 0)
                Matriz33 traslacion1 = new Matriz33(new float[][]{
                    {1, 0, -centroX},
                    {0, 1, -centroY},
                    {0, 0, 1}
                });

                // 3. Aplicar la escala
                Matriz33 escala = new Matriz33(new float[][]{
                    {Sx, 0, 0},
                    {0, Sy, 0},
                    {0, 0, 1}
                });

                // 4. Trasladar de nuevo todos los puntos a su posición original
                Matriz33 traslacion2 = new Matriz33(new float[][]{
                    {1, 0, centroX},
                    {0, 1, centroY},
                    {0, 0, 1}
                });

                for (int i = 0; i < numPuntos; i++) {
                    Punto punto = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);
                    float[] puntoArray = {punto.getPx(), punto.getPy(), 1};
                    Matriz31 puntoMatriz = new Matriz31(puntoArray);

                    // Aplicar la transformación completa
                    puntoMatriz = new Matriz31(traslacion1.multiMatrices(puntoMatriz));
                    puntoMatriz = new Matriz31(escala.multiMatrices(puntoMatriz));
                    puntoMatriz = new Matriz31(traslacion2.multiMatrices(puntoMatriz));

                    // Actualizar las coordenadas del punto
                    punto.setPx(puntoMatriz.getElemento(0));
                    punto.setPy(puntoMatriz.getElemento(1));
                }
                imprimirMatriz(escala.getMatriz());
                // Actualizar la interfaz de usuario
                JLST_PUNTOS.updateUI();
            }
        }   

    }//GEN-LAST:event_btnEscalarActionPerformed

    private void btnRotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotarActionPerformed
        // TODO add your handling code here:
        if (FiguraSeleccionada != null) {
            if (cajaRotar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes llenar el campo");
            } else {
                float angulo = Float.parseFloat(cajaRotar.getText());
                float cos = (float) Math.cos(Math.toRadians(angulo));
                float sen = (float) Math.sin(Math.toRadians(angulo));

                // 1. Calcular el centro de la figura
                float centroX = 0;
                float centroY = 0;
                int numPuntos = FiguraSeleccionada.getKeyframeInicial().listaPuntos.getSize();
                for (int i = 0; i < numPuntos; i++) {
                    Punto punto = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);
                    centroX += punto.getPx();
                    centroY += punto.getPy();
                }
                centroX /= numPuntos;
                centroY /= numPuntos;

                // 2. Trasladar todos los puntos de la figura al origen (0, 0)
                Matriz33 traslacion1 = new Matriz33(new float[][]{
                    {1, 0, -centroX},
                    {0, 1, -centroY},
                    {0, 0, 1}
                });

                // 3. Aplicar la rotación alrededor del origen
                Matriz33 rotacion = new Matriz33(new float[][]{
                    {cos, -sen, 0},
                    {sen, cos, 0},
                    {0, 0, 1}
                });

                // 4. Trasladar de nuevo todos los puntos a su posición original
                Matriz33 traslacion2 = new Matriz33(new float[][]{
                    {1, 0, centroX},
                    {0, 1, centroY},
                    {0, 0, 1}
                });

                for (int i = 0; i < numPuntos; i++) {
                    Punto punto = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);
                    float[] puntoArray = {punto.getPx(), punto.getPy(), 1};

                    Matriz31 puntoMatriz = new Matriz31(puntoArray);

                    // Aplicar la transformación completa
                    puntoMatriz = new Matriz31(traslacion1.multiMatrices(puntoMatriz));
                    puntoMatriz = new Matriz31(rotacion.multiMatrices(puntoMatriz));
                    puntoMatriz = new Matriz31(traslacion2.multiMatrices(puntoMatriz));

                    // Actualizar las coordenadas del punto
                    punto.setPx(puntoMatriz.getElemento(0));
                    punto.setPy(puntoMatriz.getElemento(1));
                }
                imprimirMatriz(rotacion.getMatriz());

                // Actualizar la interfaz de usuario
                JLST_PUNTOS.updateUI();
            }
        }
    }//GEN-LAST:event_btnRotarActionPerformed

    public void imprimirMatriz(float[][] matriz) {
        System.out.println("Matriz resultante:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void btnSesgarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSesgarActionPerformed
        // TODO add your handling code here:
        if(FiguraSeleccionada!=null){
            if(cajaSesgar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debes llenar los dos campos");
            }else{
                float Sh = Float.valueOf(cajaSesgar.getText());
                String tipo = (String) boxSesgo.getSelectedItem();
                switch (tipo) {
                    case "x":
                        for (int i = 0; i < FiguraSeleccionada.getKeyframeInicial().listaPuntos.getSize(); i++) {
                            Punto elactual = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);

                            float x = elactual.getPx();
                            float y = elactual.getPy();

                            float[][] valores3x3 = {
                                {1, Sh, 0},
                                {0,  1, 0},
                                {0,  0, 1}
                            };

                            float[] valores3x1 = {x, y, 1};
                            Matriz33 m33 = new Matriz33(valores3x3);
                            Matriz31 m31 = new Matriz31(valores3x1);

                            float[] resultado = m33.multiMatrices(m31);
                            System.out.println("Resultado de la multiplicación:");
                            for (float valor : resultado) {
                                System.out.println(valor);
                            }
                            imprimirMatriz(m33.getMatriz());
                            elactual.setPx(resultado[0]);
                            elactual.setPy(resultado[1]);
                            JLST_PUNTOS.updateUI();

                        }//for
                        break;
                    
                    case "y":
                        for (int i = 0; i < FiguraSeleccionada.getKeyframeInicial().listaPuntos.getSize(); i++) {
                            Punto elactual = FiguraSeleccionada.getKeyframeInicial().listaPuntos.get(i);

                            float x = elactual.getPx();
                            float y = elactual.getPy();

                            float[][] valores3x3 = {    
                                {1, 0, 0},
                                {Sh, 1, 0},
                                {0, 0, 1}
                            };

                            float[] valores3x1 = {x, y, 1};
                            Matriz33 m33 = new Matriz33(valores3x3);
                            Matriz31 m31 = new Matriz31(valores3x1);

                            float[] resultado = m33.multiMatrices(m31);
                            System.out.println("Resultado de la multiplicación:");
                            for (float valor : resultado) {
                                System.out.println(valor);
                            }
                            imprimirMatriz(m33.getMatriz());
                            elactual.setPx(resultado[0]);
                            elactual.setPy(resultado[1]);
                            JLST_PUNTOS.updateUI();

                        }//for
                        break;
                    default:
                        throw new AssertionError();
                }//termina switch     
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debes seleccionar una figura");
        }
    }//GEN-LAST:event_btnSesgarActionPerformed

    private void boxSesgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSesgoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSesgoActionPerformed

    private void cargarFigurasDesdeArchivo(String rutaArchivo) {
       
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                String[] tokens = linea.split(","); // Separar todo los separado por coma

                Figura f = new Figura(tokens[0]);
                for (int i = 1; i < tokens.length; i += 2) {
                    float x = Floats.tryParse(tokens[i]);
                    float y = Floats.tryParse(tokens[i + 1]);

                    Punto p = new Punto(x, y);
                    f.getKeyframeInicial().listaPuntos.addElement(p);
                }
                canvas.ListaFiguras.addElement(f); // Traemos el nombre de la figura
            }
        } catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Error al cargar las figuras.");
        }
         
    }

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        // TODO add your handling code here:
        // Obtener la ubicación predeterminada para guardar archivos del usuario
        String path = System.getProperty("user.home");

        // Mostrar un cuadro de diálogo para elegir la ruta y el nombre del archivo
        JFileChooser fileChooser = new JFileChooser();

        // Establecer un filtro para mostrar solo archivos con la extensión ".txt"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // El usuario ha seleccionado una ubicación para guardar el archivo
            File archivo = fileChooser.getSelectedFile();
            path = archivo.getAbsolutePath();
            // Limpiar la lista de figuras antes de cargar nuevas figuras
            canvas.ListaFiguras.clear();
            // Verificar si el nombre de archivo tiene la extensión ".txt"
            if (!path.toLowerCase().endsWith(".txt")) {
                // Si no tiene la extensión ".txt", agregarla manualmente
                archivo = new File(path + ".txt");
            }

            // Guardar la ruta del archivo en las preferencias del usuario
            Preferences prefs = Preferences.userNodeForPackage(getClass());
            prefs.put("rutaArchivoActual", archivo.getAbsolutePath());
            
            // Crear un nuevo archivo vacío
            try {
                if (!archivo.exists()) {
                    if (!archivo.createNewFile()) {
                        // No se pudo crear el archivo
                        JOptionPane.showMessageDialog(null, "No se pudo crear el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else{
                        // Se creó el archivo exitosamente, cargar las figuras desde el nuevo archivo
                        rutaArchivoActual = archivo.getAbsolutePath();
                        cargarFigurasDesdeArchivo(archivo.getAbsolutePath());                    }
                }
            } catch (IOException ex) {
                // Manejar cualquier excepción de E/S
                JOptionPane.showMessageDialog(null, "Error al crear el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (seleccion == JFileChooser.CANCEL_OPTION) {
            // El usuario canceló la selección del archivo
            System.out.println("Selección de archivo cancelada.");
        }
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void nombreFig3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFig3DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreFig3DActionPerformed
    ModelBuilder MoB = new ModelBuilder();
    private void btnAgregarFig3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFig3DActionPerformed
        // TODO add your handling code here:
        if(nombreFig3D.getText().isEmpty() ||
                ejeX.getText().isEmpty() ||
                ejeY.getText().isEmpty() ||
                ejeZ.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Rellena todos los campos");
        }else{
            String nombfig = nombreFig3D.getText();
            float x = Float.parseFloat(ejeX.getText());
            float y = Float.parseFloat(ejeY.getText());
            float z = Float.parseFloat(ejeZ.getText());

            float r = c.getRed() / 255f;
            float g = c.getGreen() / 255f;
            float b = c.getBlue() / 255f;
            float a = c.getAlpha() / 255f;

            com.badlogic.gdx.graphics.Color nuevoCol = new com.badlogic.gdx.graphics.Color(r, g, b, a);
            tipoPrimitiva tipo1 = (tipoPrimitiva) Figuras3D.getSelectedItem();

            canvas.ListaFiguras3D.addElement(new Obj3D(nombfig, tipo1, nuevoCol, x, y, z, MoB));    
        }
        
    }//GEN-LAST:event_btnAgregarFig3DActionPerformed

    private void btnEliminarFig3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFig3DActionPerformed
        // TODO add your handling code here:
        canvas.ListaFiguras3D.removeElement(select3D);
    }//GEN-LAST:event_btnEliminarFig3DActionPerformed

    Color c;
    private void btnEliminarFig3D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFig3D1ActionPerformed
        // TODO add your handling code here:
        c = JColorChooser.showDialog(this, "Seleccionar color", Color.WHITE);
    }//GEN-LAST:event_btnEliminarFig3D1ActionPerformed

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void botonAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAdelanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAdelanteActionPerformed

    private void botonIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIzquierdaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonIzquierdaActionPerformed

    private void botonDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDerechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonDerechaActionPerformed

    private void botonArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArribaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonArribaActionPerformed

    private void botonAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAbajoActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        canvas.Reproduciendo = !(canvas.Reproduciendo);
        
        if(canvas.Reproduciendo){
            btnPlay.setText("Pause");
        }else{
            btnPlay.setText("Play");
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        canvas.fotogramaSiguiente();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void cajaParam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaParam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaParam1ActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        canvas.fotogramaAnterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void cajaParam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaParam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaParam2ActionPerformed

    private void btnAgregarFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFrameActionPerformed
        if(FiguraSeleccionada != null){
            if(cajaParam1.getText().isEmpty() || cajaParam2.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
            }else{
                int frame = canvas.fotograma_actual;
                float[] par = {0,0};
                par[0] = Floats.tryParse(cajaParam1.getText());
                par[1] = Floats.tryParse(cajaParam2.getText());

                Keyframe.transformacionKeyframe transf = (Keyframe.transformacionKeyframe)cajaTransformer.getSelectedItem();

                Keyframe k = new Keyframe(frame, transf, FiguraSeleccionada,par);    

                try {
                    FiguraSeleccionada.AgregarKeyframe(k);        
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                FiguraSeleccionada.ordenarKeyframes();
                JLST_Keyframes.updateUI();
            }
        }        
    }//GEN-LAST:event_btnAgregarFrameActionPerformed

    private void btnEditarFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFrameActionPerformed
        if(FiguraSeleccionada != null){
            if(KeyframeSeleccionado != null){
                if(JLST_Keyframes.getSelectedIndex() > 0){
                    int frame = canvas.fotograma_actual;
                    float[] par = {0,0};
                    par[0] = Floats.tryParse(cajaParam1.getText());
                    par[1] = Floats.tryParse(cajaParam2.getText());

                    Keyframe.transformacionKeyframe transf = (Keyframe.transformacionKeyframe)cajaTransformer.getSelectedItem();

                    KeyframeSeleccionado.num_fotograma = frame;
                    KeyframeSeleccionado.transformacion = transf;
                    KeyframeSeleccionado.param = par;

                    FiguraSeleccionada.ordenarKeyframes();
                    
                    JLST_Keyframes.updateUI();
                }else{
                    JOptionPane.showMessageDialog(this, "Error! No se puede editar el keyframe 0");
                }
            }
        }
    }//GEN-LAST:event_btnEditarFrameActionPerformed

    private void btnEliminarFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFrameActionPerformed
        if(FiguraSeleccionada != null && KeyframeSeleccionado != null){
            try {
                int resp = JOptionPane.showConfirmDialog(null, "Sí o No",
                "Quieres eliminar este frame?", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                switch (resp) {
                    case 0:
                        FiguraSeleccionada.EliminarKeyframe(KeyframeSeleccionado);
                        break;
                    case 1:    
                        break;
                    }
        
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            JLST_Keyframes.updateUI();
        }
    }//GEN-LAST:event_btnEliminarFrameActionPerformed

    private void cajaTransformerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaTransformerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaTransformerActionPerformed
    Canvas y;

 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal v = new VentanaPrincipal();
                v.cargaInicio();
                v.setVisible(true);
                //new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<tipoPrimitiva> Figuras3D;
    private javax.swing.JList<Figura> JLSTFiguras;
    private javax.swing.JList<Keyframe> JLST_Keyframes;
    private javax.swing.JPanel JLST_Keyframesss;
    private javax.swing.JList<Punto> JLST_PUNTOS;
    public javax.swing.JLabel LBL_Tiempo;
    public javax.swing.JSlider SliderTiempo;
    public ej2graf1.MiButton botonAbajo;
    public ej2graf1.MiButton botonAdelante;
    public ej2graf1.MiButton botonArriba;
    public ej2graf1.MiButton botonAtras;
    public ej2graf1.MiButton botonDerecha;
    public ej2graf1.MiButton botonIzquierda;
    private javax.swing.JComboBox<String> boxSesgo;
    private ej2graf1.MiButton btnAgregarFig;
    private ej2graf1.MiButton btnAgregarFig3D;
    public ej2graf1.MiButton btnAgregarFrame;
    private ej2graf1.MiButton btnAgregarPunto;
    private javax.swing.JButton btnAnterior;
    private ej2graf1.MiButton btnEditarFig;
    public ej2graf1.MiButton btnEditarFrame;
    private ej2graf1.MiButton btnEditarPunto;
    private ej2graf1.MiButton btnEliminarFig;
    private ej2graf1.MiButton btnEliminarFig3D;
    private ej2graf1.MiButton btnEliminarFig3D1;
    public ej2graf1.MiButton btnEliminarFrame;
    private ej2graf1.MiButton btnEliminarPunto;
    private ej2graf1.MiButton btnEscalar;
    public ej2graf1.MiButton btnPlay;
    private ej2graf1.MiButton btnRotar;
    private ej2graf1.MiButton btnSesgar;
    private javax.swing.JButton btnSiguiente;
    private ej2graf1.MiButton btnTrasladar;
    private javax.swing.JTextField cajaEscalarX;
    private javax.swing.JTextField cajaEscalarY;
    private javax.swing.JTextField cajaParam1;
    private javax.swing.JTextField cajaParam2;
    private javax.swing.JTextField cajaPuntoX;
    private javax.swing.JTextField cajaPuntoY;
    private javax.swing.JTextField cajaRotar;
    private javax.swing.JTextField cajaSesgar;
    private javax.swing.JComboBox<Keyframe.transformacionKeyframe> cajaTransformer;
    private javax.swing.JTextField cajaTraslacionX;
    private javax.swing.JTextField cajaTraslacionY;
    private javax.swing.JMenuItem cargarItem;
    private javax.swing.JPanel controles;
    private javax.swing.JPanel ctrls3d;
    private javax.swing.JTextField ejeX;
    private javax.swing.JTextField ejeY;
    private javax.swing.JTextField ejeZ;
    private javax.swing.JPanel escalado;
    private javax.swing.ButtonGroup grupoRadios;
    private javax.swing.JMenuItem guardarItem;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel labelcajaTraslacionX;
    private javax.swing.JLabel labelcajaTraslacionY;
    private javax.swing.JList<Obj3D> lista3D;
    private javax.swing.JPanel mantel;
    private javax.swing.JTextField nombreFig3D;
    private javax.swing.JPanel panelFiguras;
    private javax.swing.JPanel panelModos;
    private javax.swing.JPanel panelPuntos;
    private javax.swing.JPanel plano;
    public javax.swing.JRadioButton radio2D;
    public javax.swing.JRadioButton radio3D;
    private javax.swing.JPanel rotacion;
    private javax.swing.JPanel sesgo;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTabbedPane tabAnimacion;
    private javax.swing.JTabbedPane tabbedMundos;
    private javax.swing.JTextField textEscala;
    private javax.swing.JPanel traslacion;
    // End of variables declaration//GEN-END:variables
}
