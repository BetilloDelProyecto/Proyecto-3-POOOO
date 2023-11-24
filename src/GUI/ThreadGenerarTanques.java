package GUI;

import java.util.*;

public class ThreadGenerarTanques extends Thread {
    CampoJuego ventana;
    int cont = 0;

    public ThreadGenerarTanques(CampoJuego ventana) {
        this.ventana = ventana;
    }
    
    
    public void run(){
        try {
            while (cont < 1){
                // el cont 13 y 19 van a ser tanques rojos
                ventana.generarEnemigo(tipoRandom());
                cont++;
                sleep(4000);
            }
        } catch (InterruptedException ex) {
                System.out.println("SE PARO EL TRHEAD GENERAR ENEMIGO");
        }
    }
    
    public static int generarNumero() {
        Random random = new Random();
        return random.nextInt(4);
    }
    
    public String tipoRandom(){
        int numRan = generarNumero();
        switch(numRan){
            case 0:
                return "simple";
            case 1:
                return "rapido";
            case 2:
                return "fuerte";
            case 3:
                return "resistente";
            default:
                return "simple";
        }
    }
    
     
}
