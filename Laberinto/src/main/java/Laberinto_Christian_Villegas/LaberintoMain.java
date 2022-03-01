
package Laberinto_Christian_Villegas;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LaberintoMain{
    // Declaramos las variables estáticas para que se acumulen (reportes)
    static int partidasGanadas = 0;
    static int partidasIniciadas = 0;
    static int oroAcumulado = 0;
    static int movimientosAcumulados = 0;
    static int vecesAtrapado = 0;
    public static void main(String[] args) throws IOException {
        // Creamos una clase "Casilla" que tendrá todos sus valores inicializados en falso
        class Casilla {
            // Esta bandera indica si el jugador está sobre esta casilla 
            public boolean tieneAlJugador = false;

            // Esta bandera indica si el el bot está sobre esta casilla 
            public boolean tieneAlBot  = false;

            // Esta bandera indica si esta casilla es una salida
            public boolean esSalida  = false;

            // Esta bandera indica si esta casilla es una pared
            public boolean esPared  = false;

            // Esta bandera indica si esta casilla  tiene oro
            public boolean tieneOro  = false;

            // Esta bandera indica si esta casilla está vacía
            public boolean esVacia = false;
                                                
            // Este numero indica la cantidad de oro en esta casilla
            public int cantidadDeOro = 0;
            
            
        };

        // Clase que sirve para llevar el estado del tablero / laberinto
        class Tablero {
            // Atributos del tablero:

            // Una matriz de tipo 'Casilla' que va a ser nuestro tablero
            public Casilla[][] tablero;

            // Numero de filas del tablero
            int filas;

            // Numero de columnas del tablero
            int columnas;

            // Fila donde se posiciona el jugador (tiene un valor inicial de 0)
            int filaDondeEstaElJugador = 0;

            // Columna donde se posiciona el jugador (tiene un valor inicial de 0)
            int columnaDondeEstaElJugador = 0;
            //Fila donde se posiciona el bot (tiene un valor inicial de 0)
            int filaDondeEstaElBot = 0;
            //Fila donde se posiciona el bot (tiene un valor inicial de 0)
            int columnaDondeEstaElBot = 0; 

            // Constructor donde indicamos el número de filas y columnas que tendrá el tablero / laberinto
            public Tablero(int filas, int columnas) {
                this.filas = filas;
                this.columnas = columnas;
                // Inicializamos nuestra matriz de casillas, indicando cuantas columnas (eje x) y filas (eje y) tendrá
                tablero = new Casilla[columnas][filas];
                for (int i = 0;i<filas;i++){
                    for(int j=0;j<columnas;j++){
                        tablero[j][i] = new Casilla();
                    }
                }

                // Llenamos el tablero con paredes, oro, salidas, posicionamos el jugador, etc
                this.construirTablero();
            }

            // Metodo que usaremos para construir el laberinto en primer lugar
            public void construirTablero() {
                for (int f = 0; f < this.filas; f++){
                    for(int c = 0; c < this.columnas; c++){
                        // Ponemos todas las casillas como vacías por defecto, aunque luego lo cambiemos
                        tablero[c][f].esVacia = true;
                        //Vamos llenando el tablero con paredes, salidas o casillas de oro
                        if (f == 0) {
                            if (c == 8) {
                                tablero[c][f].esSalida = true;
                                tablero[c][f].esVacia = false;
                            } else {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }
                        } else if (f == 29) { 
                            if (c == 13) {
                                tablero[c][f].esSalida = true;
                                tablero[c][f].esVacia = false;
                            } else {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }                            
                        } else if (c == 0) {
                            if (f == 1) {
                                tablero[c][f].esSalida = true;
                                tablero[c][f].esVacia = false;
                            } else {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }
                        } else if (c == 29) {
                            if (f == 1) {
                                tablero[c][f].esSalida = true;
                                tablero[c][f].esVacia = false;
                            } else if (f == 21) {
                                tablero[c][f].esSalida = true;
                                tablero[c][f].esVacia = false;
                            } else {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }
                        } else if (f == 1) {
                            if (c == 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 17) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }
                        } else if (f == 2) {
                            if (c > 0 && c < 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 7 && c < 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 11 && c < 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 18 && c < 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            }
                        } else if (f == 3) {
                            if (c == 3) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 5;
                                tablero[c][f].esVacia = false;
                            } else if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 7 && c < 10) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                if (c == 8) {
                                    tablero[c][f].cantidadDeOro = 7;
                                } else if (c == 9) {
                                    tablero[c][f].cantidadDeOro = 3;
                               
                            } 
                            } else if (c == 10) {
                               tablero[c][f].esPared = true;
                               tablero[c][f].esVacia = false;
                            } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 13) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                tablero[c][f].cantidadDeOro = 5;
                            } else if (c == 14) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 17) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                tablero[c][f].cantidadDeOro = 5;
                            } else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 23 && c < 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 4 ){
                                if (c > 1 && c < 4) {
                              
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 11) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 2;
                                tablero[c][f].esVacia = false;
                            } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 14) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 15 && c<18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 22) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 5 ){
                                if (c == 1) {
                                tablero[c][f].cantidadDeOro = 3;
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;                               
                            } else if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c > 6 && c < 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 14) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 21 && c < 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 6 ){
                                if (c > 1 && c < 4) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;                               
                            } else if (c == 10) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 22) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 26 && c < 29) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                
                                } else if (f == 7 ){
                                if (c == 1) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                 
                            } else if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                            } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 11 & c < 14) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 14 && c < 17) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 17 && c < 23) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 24) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 25 && c < 28) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 28) {                                    
                                tablero[c][f].cantidadDeOro = 7;
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 8 ){
                                if (c == 3) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                
                                } else if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 24) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 28) {                                    
                                tablero[c][f].cantidadDeOro = 1;
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 9 ){
                                if (c > 2 && c < 4) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 4 && c < 8) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 12 && c < 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 17 && c < 21) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 21 && c < 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 26) {
                                tablero[c][f].cantidadDeOro = 1;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 10 ){
                                if (c == 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 19) {
                                tablero [c][f].cantidadDeOro = 2;                                
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 23) {
                                tablero[c][f].cantidadDeOro = 1;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 25) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 11 ){
                                if (c > 0 && c < 3) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 3) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 1;
                                tablero[c][f].esVacia = false;
                                } else if (c == 4) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 12 && c < 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 15 && c < 21) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c == 22) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 23 && c < 26) {
                                tablero[c][f].cantidadDeOro = 1;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 12 ){
                                if (c == 3) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 5 && c < 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 27) {
                                }
                                } else if (f == 13 ){
                                if (c == 1) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 2 && c < 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 16 && c < 19) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 23) {
                                tablero[c][f].cantidadDeOro = 1;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 25) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false; 
                                }else if (c == 27) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 14 ){
                                if (c == 3) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 7 && c < 10) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 12) {
                                tablero[c][f].cantidadDeOro = 2;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 14 && c < 17) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c == 19) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 22) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 25) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false; 
                                }else if (c == 27) {                                    
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 15 ){
                                if (c == 1) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 2 && c < 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 8) {
                                tablero[c][f].cantidadDeOro = 1;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 10 && c < 14) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 21) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c > 23 && c < 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 16 ){
                                if (c == 3) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 7 && c < 10) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 16 && c < 22) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 23) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 26) {
                                tablero[c][f].cantidadDeOro = 4;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 17 ){
                                if (c == 1) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 4 && c < 8) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 17) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }else if (c == 18) {
                                tablero[c][f].cantidadDeOro = 2;    
                                tablero[c][f].tieneOro = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c > 25 && c < 28) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 18 ){
                                if (c == 1) {
                                tablero[c][f].cantidadDeOro = 2;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 2) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 4) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 11 && c < 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                             
                                  
                                
                                    
                                } else if (f == 19 ){
                                if (c == 1) {
                                tablero[c][f].cantidadDeOro = 7;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 2) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 5 && c < 8) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].cantidadDeOro = 7;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 17) {
                                tablero[c][f].cantidadDeOro = 2;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 21) {
                                tablero[c][f].cantidadDeOro = 3;    
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].esVacia = false;
                                }else if (c > 23 && c < 26) {
                                tablero[c][f].esPared = true;    
                                tablero[c][f].esVacia = false;
                                }else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 20 ){
                                if (c > 1 && c < 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;                               
                                } else if (c > 8 && c < 23) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 26 && c < 29) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 21 ){
                                if (c == 2) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 8) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 23) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 22 ){
                                if (c == 4) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 10 && c < 17) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 17 && c < 21) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 26 && c < 29) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 23 ){
                                if (c > 0 && c < 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 8) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 3;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 21 && c < 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 27) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 24 ){
                                if (c == 2) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 6) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 8) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 3;
                                tablero[c][f].esVacia = false;
                                } else if (c == 9) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 10 && c < 15) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 15 && c < 19) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 20) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 25 ){
                                if (c > 1 && c < 5) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 5 && c < 10) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 17) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 9;
                                tablero[c][f].esVacia = false;
                                } else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 19 && c < 23) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 24) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 25) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 5;
                                tablero[c][f].esVacia = false;
                                } else if (c > 25 && c < 28) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                }
                                } else if (f == 26 ){
                                if (c == 2) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 13) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 16) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 24) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                
                                }
                                } else if (f == 27 ){
                                if (c > 1 && c < 11) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 13 && c < 17) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 18) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c > 19 && c < 25) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 28) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                
                                }
                                } else if (f == 28 ){
                                if (c == 6) {
                                tablero[c][f].tieneOro = true;
                                tablero[c][f].cantidadDeOro = 8;
                                tablero[c][f].esVacia = false;
                                } else if (c == 7) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 12) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                } else if (c == 26) {
                                tablero[c][f].esPared = true;
                                tablero[c][f].esVacia = false;
                                
                                } 
                                } 
                            }
                        }
                    
                  
            
                        
                    
                
                    
                // Ahora ponemos el jugador en una casilla aleatoria que esté vacía
                Random random = new Random();

                // Seleccionamos una fila al azar para poner al jugador
                int filaParaPonerElJugador = random.nextInt(this.filas);
                // Seleccionamos una columna al azar para poner al jugador
                int columnaParaPonerElJugador = random.nextInt(this.columnas);
                // Vemos si la casilla seleccionada está vacía
                boolean esCasillaValida = this.tablero[columnaParaPonerElJugador][filaParaPonerElJugador].esVacia;
                // Si la casilla no está vacía, entramos a un ciclo donde elegiremos otra casilla aleatoria hasta que tengamos una que sea vacía
                while (!esCasillaValida) {
                    filaParaPonerElJugador = random.nextInt(this.filas);
                    columnaParaPonerElJugador = random.nextInt(this.columnas);
                    esCasillaValida = this.tablero[columnaParaPonerElJugador][filaParaPonerElJugador].esVacia;
                }
                // Si estamos fuera del ciclo, es porque tenemos una fila y columna valida para poner al jugador
                this.tablero[columnaParaPonerElJugador][filaParaPonerElJugador].tieneAlJugador = true;
                filaDondeEstaElJugador = filaParaPonerElJugador;
                columnaDondeEstaElJugador = columnaParaPonerElJugador;
                //Aqui seleccionamos igual una fila para el bot 
                int filaParaPonerElBot = random.nextInt(this.filas);
                //Seleccionamos una columna para el bot
                int columnaParaPonerElBot = random.nextInt(this.columnas);
                //Mientras la casilla sea vacia, el bot puede posicionarse
                boolean esCasillaPermitida = this.tablero[columnaParaPonerElBot][filaParaPonerElBot].esVacia;
                this.tablero[columnaParaPonerElBot][filaParaPonerElBot].tieneAlBot = true;
                //Hacemos un ciclo por si no ecnuentra una casilla vacia
                while (!esCasillaPermitida) {
                    filaParaPonerElBot = random.nextInt(this.filas);
                    columnaParaPonerElBot= random.nextInt(this.columnas);
                    esCasillaPermitida = this.tablero[columnaParaPonerElBot][filaParaPonerElBot].esVacia;
                }
                //Al salir del ciclo ya encontraremos una casilla vacia y la decalaramos como true para el bot
                this.tablero[columnaParaPonerElBot][filaParaPonerElBot].tieneAlBot = true;
                filaDondeEstaElBot = filaParaPonerElBot;
                columnaDondeEstaElBot = columnaParaPonerElBot;
               
                   
            
            
        }       
            //Metodo para retornar el tablero
            public Casilla[][] obtenerTablero() {
                return this.tablero;
            }
             
             //Metodo para imprimir el tablero con los caracteres      
            public void imprimirTodoElTablero() {
                for (int f = 0;f<filas;f++){
                    for(int c=0;c<columnas;c++){
                        if (tablero[c][f].esPared) {
                            System.out.print("#");
                        } else if (tablero[c][f].tieneAlJugador) {
                            System.out.print("J");
                        } else if (tablero[c][f].tieneAlBot) {
                            System.out.print("B");
                        } else if (tablero[c][f].tieneOro) {
                            System.out.print("G");
                        } else if (tablero[c][f].esSalida) {
                            System.out.print("S");
                        } else if (tablero[c][f].esVacia) {
                            System.out.print("O");
                        }
                    }
                    System.out.println();
                }
            }
     
            //Metodo para el comando mirar
            public void imprimir5x5() {
                int filaDeInicioParaImprimir = this.filaDondeEstaElJugador - 2;
                int columnaDeInicioParaImprimir = this.columnaDondeEstaElJugador - 2;

                int filaFinalParaImprimir = this.filaDondeEstaElJugador + 2;
                int columnaFinalParaImprimir = this.columnaDondeEstaElJugador + 2;

                // De primero nos aseguramos de que no se pueda imprimir desde una fila negativa
                // o una fila mayor al número de filas establecidas
                if (filaDeInicioParaImprimir < 0) {
                    filaDeInicioParaImprimir = 0;
                }
                if (filaFinalParaImprimir > this.filas - 1) {
                    filaFinalParaImprimir = this.filas - 1;
                }

                // Nos aseguramos de que no se pueda imprimir desde una columna negativa
                // o una columna mayor al número de columnas establecidas
                if (columnaDeInicioParaImprimir < 0) {
                    columnaDeInicioParaImprimir = 0;
                }
                if (columnaFinalParaImprimir > this.columnas - 1) {
                    columnaFinalParaImprimir = this.columnas - 1;
                }

                // Imprimimos desde la fila inicial para imprimir, hasta la final para imprimir (eje Y)
                for (int f = filaDeInicioParaImprimir; f <= filaFinalParaImprimir; f++){
                    // Imprimimos desde la columna inicial para imprimir, hasta la final para imprimir (eje X)
                    for(int c = columnaDeInicioParaImprimir; c <= columnaFinalParaImprimir; c++){
                        if (tablero[c][f].esPared) {
                            System.out.print("#");
                        } else if (tablero[c][f].tieneAlJugador) {
                            System.out.print("J");
                        } else if (tablero[c][f].tieneAlBot) {
                            System.out.print("B");
                        } else if (tablero[c][f].tieneOro) {
                            System.out.print("G");
                        } else if (tablero[c][f].esSalida) {
                            System.out.print("S");
                        } else if (tablero[c][f].esVacia) {
                            System.out.print("O");
                        }
                    }
                    System.out.println();
                }
            }
            //Metodo para colocar el oro que nos quiten
            public void colocarOroEnCasillaVacia(int oroAColocar) {
                // Ahora ponemos oro en una casilla aleatoria que esté vacía
                Random random = new Random();

                // Seleccionamos una fila al azar para poner al jugador
                int filaParaPonerElOro = random.nextInt(this.filas);
                // Seleccionamos una columna al azar para poner al jugador
                int columnaParaPonerElOro = random.nextInt(this.columnas);
                boolean esCasillaValida = this.tablero[columnaParaPonerElOro][filaParaPonerElOro].esVacia;
                // Si la casilla no está vacía, entramos a un ciclo donde elegiremos otra casilla aleatoria hasta que tengamos una que sea vacía
                while (!esCasillaValida) {
                    filaParaPonerElOro = random.nextInt(this.filas);
                    columnaParaPonerElOro = random.nextInt(this.columnas);
                    esCasillaValida = this.tablero[columnaParaPonerElOro][filaParaPonerElOro].esVacia;
                }
                // Si estamos fuera del ciclo, es porque tenemos una fila y columna valida para poner el oro
                this.tablero[columnaParaPonerElOro][filaParaPonerElOro].tieneOro = true;
                this.tablero[columnaParaPonerElOro][filaParaPonerElOro].cantidadDeOro = oroAColocar;
            }
        };
        //Declaramos las variables y un booleano para saber que el menu se tiene que volver a imprimir
        boolean juegoGeneralEnProceso;
        //Ciclo mientras el Juego se esté ejecutando
        while (juegoGeneralEnProceso = true){
        int opcionMenu;
        int opcionMapa;
        int comandosInexistentesConsecutivos = 0;
        int oroObtenido = 0;
        int oroRequerido = 5;
        int MovimientosJ = 0;        
        int movimientosBot = 0;
        
        
        // Creamos un tablero de 30 x 30 casillas
        Tablero tablero = new Tablero(30, 30);
        
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("¡Bienvenido al laberinto chilero! digite el numero de la accion que desea realizar");
        System.out.println("1. Jugar");
        System.out.println("2. Crear Mapa");
        System.out.println("3. Reportes");
        System.out.println("4. Visualizar Mapa");
        System.out.println("5. Salir");
        opcionMenu = scanner.nextInt();
        
        if (opcionMenu == 1){
            
            System.out.println("1. Mapa Predeterminado");
            System.out.println("2. Mapa creado");
            opcionMapa = scanner.nextInt();
            
            if (opcionMapa == 1){   
                partidasIniciadas = 1 + partidasIniciadas;
               // Aqui podemos impimir todo el tablero con: tablero.imprimirTodoElTablero();
                boolean juegoEnProgreso = true;
                // Vamos a estar en un ciclo mientras el juego esté en progreso
                while (juegoEnProgreso) {
                    
                  //  tablero.imprimirTodoElTablero();
                    System.out.println("Introduzca su jugada: ");
                    System.out.println("(MOVER N ,MOVER S, MOVER E, MOVER O, etc) ");
                    String jugada = scanner.nextLine();
                    if (jugada.equals("MOVER N")) {
                        MovimientosJ = MovimientosJ + 1;
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Primero nos aseguramos que sí haya una casilla arriba del jugador
                        if (tablero.filaDondeEstaElJugador > 0) {
                            // Vemos si la casilla arriba del jugador NO es una pared. 
                            if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador - 1].esPared == false) {
                                // Si la casilla arriba no fuera una pared, movemos el jugador...

                                // Primero, decimos que la casilla actual donde está al jugador, ya no va a tener más al jugador 
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneAlJugador = false;

                                // Luego decimos que la casilla de arriba SÍ tendrá al jugador
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador - 1].tieneAlJugador = true;
                                // Después actualizamos nuestras variables que indican donde está el jugador actualmente
                                tablero.columnaDondeEstaElJugador = tablero.columnaDondeEstaElJugador; // Sigue siendo lo mismo
                                tablero.filaDondeEstaElJugador = tablero.filaDondeEstaElJugador - 1; // Actualizamos la fila donde está el jugador (fila es el eje y)
                                System.out.println("Se pudo mover el jugador con éxito");
                            } else {
                                System.out.println("El jugador no se pudo mover");
                            }
                        } else {
                            System.out.println("El jugador no se pudo mover");
                        }
                    } else if (jugada.equals("MOVER S")) {
                        MovimientosJ = MovimientosJ + 1;
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Primero nos aseguramos que sí haya una casilla abajo del jugador
                        if (tablero.filaDondeEstaElJugador < tablero.filas - 1) {
                            // Vemos si la casilla abajo del jugador NO es una pared. 
                            if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador + 1].esPared == false) {
                                // Si la casilla abajo no fuera una pared, movemos el jugador...

                                // Primero, decimos que la casilla actual donde está al jugador, ya no va a tener más al jugador 
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneAlJugador = false;

                                // Luego decimos que la casilla de arriba SÍ tendrá al jugador
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador + 1].tieneAlJugador = true;
                                // Después actualizamos nuestras variables que indican donde está el jugador actualmente
                                tablero.columnaDondeEstaElJugador = tablero.columnaDondeEstaElJugador; // Sigue siendo lo mismo
                                tablero.filaDondeEstaElJugador = tablero.filaDondeEstaElJugador + 1; // Actualizamos la fila donde está el jugador (fila es el eje y)
                                System.out.println("Se pudo mover el jugador con éxito");
                            } else {
                                System.out.println("El jugador no se pudo mover");
                            }
                        } else {
                            System.out.println("El jugador no se pudo mover");
                        }
                    } else if (jugada.equals("MOVER O")) {
                        MovimientosJ = MovimientosJ + 1;
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Primero nos aseguramos que sí haya una casilla izquierda del jugador
                        if (tablero.columnaDondeEstaElJugador > 0) {
                            // Vemos si la casilla a la izquierda del jugador NO es una pared. 
                            if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador - 1][tablero.filaDondeEstaElJugador].esPared == false) {
                                // Si la casilla izquierda no fuera una pared, movemos el jugador...

                                // Primero, decimos que la casilla actual donde está al jugador, ya no va a tener más al jugador 
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneAlJugador = false;

                                // Luego decimos que la casilla de la izquierda SÍ tendrá al jugador
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador - 1][tablero.filaDondeEstaElJugador].tieneAlJugador = true;
                                // Después actualizamos nuestras variables que indican donde está el jugador actualmente
                                tablero.columnaDondeEstaElJugador = tablero.columnaDondeEstaElJugador - 1; // Actualizamos la columna donde está el jugador (columna es el eje x)
                                tablero.filaDondeEstaElJugador = tablero.filaDondeEstaElJugador; // Sigue siendo lo mismo
                                System.out.println("Se pudo mover el jugador con éxito");
                            } else {
                                System.out.println("El jugador no se pudo mover");
                            }
                        } else {
                            System.out.println("El jugador no se pudo mover");
                        }
                    } else if (jugada.equals("MOVER E")) {
                        MovimientosJ = MovimientosJ + 1;
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Primero nos aseguramos que sí haya una casilla derecha del jugador
                        if (tablero.columnaDondeEstaElJugador < tablero.columnas - 1) {
                            // Vemos si la casilla a la derecha del jugador NO es una pared. 
                            if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador + 1][tablero.filaDondeEstaElJugador].esPared == false) {
                                // Si la casilla derecha no fuera una pared, movemos el jugador...

                                // Primero, decimos que la casilla actual donde está al jugador, ya no va a tener más al jugador 
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneAlJugador = false;

                                // Luego decimos que la casilla de la derecha SÍ tendrá al jugador
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador + 1][tablero.filaDondeEstaElJugador].tieneAlJugador = true;
                                // Después actualizamos nuestras variables que indican donde está el jugador actualmente
                                tablero.columnaDondeEstaElJugador = tablero.columnaDondeEstaElJugador + 1; // Actualizamos la columna donde está el jugador (columna es el eje x)
                                tablero.filaDondeEstaElJugador = tablero.filaDondeEstaElJugador; // Sigue siendo lo mismo
                                System.out.println("Se pudo mover el jugador con éxito");
                            } else {
                                System.out.println("El jugador no se pudo mover");
                            }
                        } else {
                            System.out.println("El jugador no se pudo mover");
                        }                                                                        
                    } else if (jugada.equals("LEVANTAR")) {
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Miramos si la casilla sobre la que está el jugador tiene oro o no
                        if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneOro == true) {
                            // OBTENEMOS CUANTO ORO TIENE LA CASILLA
                            int oro = tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].cantidadDeOro;
                            
                            // Quitamos el oro de la casilla y la dejamos como vacia
                            tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneOro = false;
                            tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].cantidadDeOro = 0;
                            tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].esVacia = true;
                            
                            // Le damos el oro al jugador
                            oroObtenido = oroObtenido + oro;
                            System.out.println("Sí se pudo levantar oro. Oro levantado: " + oro);
                            System.out.println("Cantidad de oro del jugador: " + oroObtenido);
                        } else {
                            System.out.println("No hay oro que se pueda levantar en esta casilla");
                        }
                    } else if (jugada.equals("ORO_REQUERIDO")) {
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        System.out.println("La cantidad de oro requerido para salir es: " + oroRequerido);
                    } else if (jugada.equals("ORO")) {
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        System.out.println("La cantidad de oro que tiene actualmente es: " + oroObtenido);
                    } else if (jugada.equals("MIRAR")) {
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        tablero.imprimir5x5();
                    } else if (jugada.equals("SALIR")) {
                        comandosInexistentesConsecutivos = 0; // Reiniciamos los comandos inexistentes consecutivos
                        // Primero miramos que el jugador esté sobre una casilla de salida
                        if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].esSalida == true) {
                            // Ahora necesitamos verificar que el jugador tenga suficiente oro
                            if (oroObtenido >= oroRequerido) {
                                movimientosAcumulados = MovimientosJ + movimientosAcumulados;
                                oroAcumulado = oroObtenido + oroAcumulado;
                                partidasGanadas = partidasGanadas + 1;
                                System.out.println("!Felicidaes! Ha completado el juego.");
                                juegoEnProgreso = false;
                                //Mostramos los reportes finales
                                System.out.println("Oro recoletado: " + oroObtenido);
                                System.out.println("Movimientos del bot realizados: " + movimientosBot);
                                System.out.println("Movimientos realizados: " + MovimientosJ);
                            } else {
                                System.out.println("No tiene suficiente oro para salir");
                            }
                        } else {
                            System.out.println("No está sobre una casilla de salida");
                        }
                    } else {
                        // Comando NO reconocido
                        comandosInexistentesConsecutivos = comandosInexistentesConsecutivos + 1; // Reiniciamos los comandos inexistentes consecutivos
                        // Vemos si ya han sido 3 comandos consecutivos no reconocidos
                        if (comandosInexistentesConsecutivos >= 3) {
                            System.out.println("Ha perdido el juego debido a ingresar consecutivamente 3 comandos no reconocidos");
                            juegoEnProgreso = false;
                            System.out.println("Oro recoletado: " + oroObtenido);
                            System.out.println("Movimientos del bot realizados: " + movimientosBot);
                            System.out.println("Movimientos realizados: " + MovimientosJ);
                           
                        } else {
                            // De lo contrario, le quitamos oro al jugador y lo ponemos en alguna casilla (si es que ya tenemos oro)
                            if (oroObtenido > 0) {
                                int oroAQuitar = 1;
                                oroObtenido = oroObtenido - oroAQuitar; // Quita una cantidad de oro
                                // Y ponemos esa cantidad de oro quitada en una casilla vacia
                                tablero.colocarOroEnCasillaVacia(oroAQuitar);
                            }
                            
                        }
                    }
                    
                    //Configuramos también los movimientos del bot
                    Random random = new Random();
                    //Hacemos un random para que hayan 4 diferentes movimientos
                    int MoverBot = random.nextInt(4);
                    
                    if (MoverBot == 0){  
                         movimientosBot = movimientosBot + 1;
                        if (tablero.filaDondeEstaElBot > 0) {                                                  
                                //Primero decimos que la casilla del bot sea mayor a 0
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot].tieneAlBot = false;
                                //Como el bot se movió, colocamos esa casilla como false
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot - 1].tieneAlBot = true;
                                // Después actualizamos nuestras variables que indican donde está el Bot ahora
                                tablero.columnaDondeEstaElBot = tablero.columnaDondeEstaElBot; 
                                tablero.filaDondeEstaElBot = tablero.filaDondeEstaElBot - 1; // Actualizamos la fila donde está el Bot (fila es el eje y)
                       }     
                     }
                    if (MoverBot == 1){
                         movimientosBot = movimientosBot + 1;
                        if (tablero.filaDondeEstaElBot < tablero.filas - 1) {                           
                            //Primero verificamos que la fila donde está el jugador es menor a 28, ya que si es mayor, se moverá a una pared
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot].tieneAlBot = false;

                                // Luego decimos que la casilla de arriba SÍ tendrá al Bot
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot + 1].tieneAlBot = true;
                                // Después actualizamos nuestras variables que indican donde está el Bot ahora
                                tablero.columnaDondeEstaElBot = tablero.columnaDondeEstaElBot; 
                                tablero.filaDondeEstaElBot = tablero.filaDondeEstaElBot + 1; // Actualizamos la fila donde está el Bot (fila es el eje y)
               }
                    }
                    
                    if (MoverBot == 2){ 
                         movimientosBot = movimientosBot + 1;
                        if (tablero.columnaDondeEstaElBot < tablero.columnas - 1) {                          
                                // Primero, decimos que la casilla actual donde está al Bot, ya no va a tener más al Bot
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot].tieneAlBot = false;

                                // Luego decimos que la casilla de la derecha SÍ tendrá al jugador
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot + 1][tablero.filaDondeEstaElBot].tieneAlBot = true;
                                // Después actualizamos nuestras variables que indican donde está el Bot ahora
                                tablero.columnaDondeEstaElBot = tablero.columnaDondeEstaElBot + 1; // Actualizamos la columna donde está el jugador (columna es el eje x)
                                tablero.filaDondeEstaElBot = tablero.filaDondeEstaElBot; 
                           }           
                          } 
                    if (MoverBot == 3){
                        movimientosBot = movimientosBot + 1;
                        if (tablero.columnaDondeEstaElBot > 0) {
                            // Primero, decimos que la casilla actual donde está al Bot, ya no va a tener más al jugador 
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot][tablero.filaDondeEstaElBot].tieneAlBot = false;
                                
                                // Luego decimos que la casilla de la izquierda SÍ tendrá al Bot
                                tablero.obtenerTablero()[tablero.columnaDondeEstaElBot - 1][tablero.filaDondeEstaElBot].tieneAlBot = true;
                                // Después actualizamos nuestras variables que indican donde está el Bot actualmente
                                tablero.columnaDondeEstaElBot = tablero.columnaDondeEstaElBot - 1; // Actualizamos la columna donde está el jugador (columna es el eje x)
                                tablero.filaDondeEstaElBot = tablero.filaDondeEstaElBot; 
                          
                        }
                    }         
                    //Configuramos que pasaría si el bot atrapara al jugador
                 if (tablero.obtenerTablero()[tablero.columnaDondeEstaElJugador][tablero.filaDondeEstaElJugador].tieneAlBot == true) {
                vecesAtrapado = vecesAtrapado + 1;
                juegoEnProgreso = false;
                //Colocamos los reportes finales en caso de que el bot atrape al jugador
                System.out.println("Haz perdido la partida, Te ha atrapado el Bot!");   
                System.out.println("Oro recoletado: " + oroObtenido);
                System.out.println("Movimientos del bot realizados: " + movimientosBot);
                System.out.println("Movimientos realizados: " + MovimientosJ);
            }
            }        
        }
            //Este si no es del if de opcionMapa
            else{
                System.out.println("No hay un mapa creado, ya que la funcion no está disponible");
            }
        
            //Como no pude hacer el aparatado de crear mapa, imprimimos que no está disponible
        }else if(opcionMenu == 2){
            System.out.println("Función No disponible");
            }
        
        else if ((opcionMenu == 3)){ 
            //Si ya se inició almenos una partida, ya hay reportes generales
            if (partidasIniciadas > 0){                           
                System.out.println("Veces atrapado por el Bot: "+ vecesAtrapado);           
                System.out.println("Partidas Ganadas: "+ partidasGanadas);
                System.out.println("Movimientos hechos: "+ movimientosAcumulados);
                System.out.println("Partidas iniciadas: "+ partidasIniciadas);
                int oroPromedio;
                oroPromedio = oroAcumulado / partidasIniciadas;
                System.out.println("Oro Promedio: "+ oroPromedio);
                int movimientosPromedio;
                movimientosPromedio = movimientosAcumulados / partidasIniciadas;
                System.out.println("El mapa más jugado es: Mapa Predeterminado");
                System.out.println("Mapa en el que más se ha ganado: Mapa Predeterminado");
                System.out.println("Mapa en el que más se ha perdido: Mapa Predeterminado");
              }
            //Si no se ha iniciado almenos 1 partida, no se pueden mostrar reportes
            else{
                System.out.println("No se pueden mostrar los reportes, ya que no ha iniciado su primera partida");
            }
              
                        
    }
        //Imprimimos el mapa para que el usuario lo visualice
        else if (opcionMenu == 4){
        
        tablero.imprimirTodoElTablero();        
       //Salimos de la ejecucion del juego         
}     else if(opcionMenu == 5){
        System.exit(0);       
    }            
}
}
}