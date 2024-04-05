/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2graf1;

/**
 *
 * @author Chris
 */
public class MatrizIdentidad {
    public static float[][] obtenerMatrizIdentidad() {
        float[][] identidad = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    identidad[i][j] = 1.0f; // Elemento en la diagonal principal
                } else {
                    identidad[i][j] = 0.0f; // Resto de elementos
                }
            }
        }
        return identidad;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        float[][] matrizIdentidad = obtenerMatrizIdentidad();
        // Imprimir la matriz identidad
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrizIdentidad[i][j] + " ");
            }
            System.out.println();
        }
    }
}
