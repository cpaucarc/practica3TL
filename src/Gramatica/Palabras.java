/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gramatica;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Frank Paucar
 */
public class Palabras {

    private final Random random = new Random();
    
    public String generarPalabras(ArrayList<String> vocabulario, int longitudCadena){
        String cadenaAleatoria = "";
        while(cadenaAleatoria.length() < longitudCadena){
            cadenaAleatoria += vocabulario.get(obtenerNumeroAleatorioEntreDosNumeros(0, vocabulario.size()));
        }
        return cadenaAleatoria;
    }
    
    public int generarLongitudAleatoria(ArrayList<Integer> valoresNoAceptados){
        int numeroGenerado = 0;
        do{            
            numeroGenerado = obtenerNumeroAleatorioEntreDosNumeros(1, 10);//genero un numero aleatorio mayor a 1 y 10          
        }while(valoresNoAceptados.contains(numeroGenerado) || numeroGenerado == 0);        
        return numeroGenerado;
    }
    
    public int obtenerNumeroAleatorioEntreDosNumeros(int menorValor, int mayorValor){
        return random.nextInt(mayorValor - menorValor) + menorValor;
    }
    
}
