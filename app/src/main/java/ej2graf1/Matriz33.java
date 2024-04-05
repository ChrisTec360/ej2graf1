/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

/**
 *
 * @author Chris
 */
public class Matriz33 {
    
    float matriz33[][];

    public Matriz33(float[][] matriz33) {
        this.matriz33 = matriz33;
    }
    
    public float[][] getMatriz() {
        return matriz33;
    }
    
    //suma
    public Matriz33 sumar(Matriz33 otraMatriz) {
        float[][] resultado = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                resultado[i][j] = this.matriz33[i][j] + otraMatriz.getMatriz()[i][j];
            }
        }
        return new Matriz33(resultado);
    }
    
    //multiplicar 3x3 * 3x3
    public Matriz33 multiplicar(Matriz33 otraMatriz) {
        float[][] resultado = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultado[i][j] += this.matriz33[i][k] * otraMatriz.getMatriz()[k][j];
                }
            }
        }
        return new Matriz33(resultado);
    }
    
    
    //multiplicar 3x3 * 3x1
    public float[] multiMatrices(Matriz31 matriz31) {
        float[] resultado = new float[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                resultado[i] += this.matriz33[i][j] * matriz31.getElemento(j);
            }
        }
        return resultado;
    }
}
