/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej2graf1;

/**
 *
 * @author Chris
 */
import java.awt.Color;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class MiButton extends JButton implements MouseListener{

    public Color colorHover = new Color(153, 0, 0);
    public Color coloPressed = new Color (0, 0, 0);
    public Color colorNormal = new Color(250, 0, 0);
    
    public Color colorTextHover = new Color(253, 254, 254);
    public Color colorTextPressed = new Color (253, 254, 254);
    public Color colorTextNormal = new Color(253, 254, 254);
    
    public MiButton(){
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.setBackground(Color.green);
        this.setForeground(Color.BLACK);
        this.setPreferredSize(new Dimension(200, 40));
        this.setSize(50, 100);
        this.setCursor(new Cursor(12));
        
        addMouseListener(this);
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(this.coloPressed);
        this.setForeground(this.colorTextPressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       this.setBackground(this.colorHover);
       this.setForeground(this.colorTextHover);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //al momento de darle enter va a cambiar set background
        this.setBackground(this.colorHover);
        this.setForeground(this.colorTextHover);
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColoPressed() {
        return coloPressed;
    }

    public void setColoPressed(Color coloPressed) {
        this.coloPressed = coloPressed;
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public void setColorNormal(Color colorNormal) {
        this.colorNormal = colorNormal;
    }

    public Color getColorTextHover() {
        return colorTextHover;
    }

    public void setColorTextHover(Color colorTextHover) {
        this.colorTextHover = colorTextHover;
    }

    public Color getColorTextPressed() {
        return colorTextPressed;
    }

    public void setColorTextPressed(Color colorTextPressed) {
        this.colorTextPressed = colorTextPressed;
    }

    public Color getTextNormal() {
        return colorTextNormal;
    }

    public void setTextNormal(Color TextNormal) {
        this.colorTextNormal = TextNormal;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(this.colorNormal);
        this.setForeground(this.colorTextNormal);
        //cambiar al momento de salir del boton, va a cambiarse a un color normal
        //setforegground. texto con un color predeterminado
        //public get color over retornar accion correspondiente del color
        //tendra un parametro va a hacer referencia a si mismo. el mismo parametro que entra va a salir
        //background metodo del color hover
        //getcolor pressed retornar valores correspondientes a color pressed
        //set color pressed, va a hacer referencia a si mismo, los parametros que tiene va a hacer igual
        //setcolornormal tendrá un parametro de color normal
        //asi sucesivamente
        /*
        tools -> pallete -> swing
        
        cuando ya esté el botón
        tools -> add to palette -> buscar categoria -> MiButton
        
        refactor
        */
    }
    
}
