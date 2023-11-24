
package GestorEnemyTank;

import GUI.CampoJuego;
import java.awt.Point;
import java.time.Duration;
import java.util.ArrayList;


public class ThreadTanqueEnemigo extends Thread{
    CampoJuego ventana;
    EnemyTank tank;
    boolean isRunnig = true, isPaused = false;
    public ThreadTanqueEnemigo(CampoJuego ventana, EnemyTank tank) {
        this.ventana = ventana;
        this.tank = tank;
    }
    
    public void run(){
        try {
            while (isRunnig) {                
                System.out.println("");
                moverse();
                sleep(2000);
                while (isPaused) {                    
                    sleep(10000);
                    isPaused = false;
                }
            }
            
        } catch (InterruptedException ex) {
                System.out.println("SE PARO EL TRHEAD DEL ENEMIGO");
        }
    }
    
    public String getOrientacion(int oX, int oY, int nX, int nY){
        if (oX<nX) {
            return "UP";
        }else if(oX>nX){
            return "DOWN";
        }else if(oY<nY){
            return "RIGHT";
        }else if(oY>nY){
            return "LEFT";
        }
        return "";
    }
    public void moverse(){
        ArrayList<Point> puntos = tank.sortPossibleMoves(tank.setPossibleMoves(1), 24, 12);
        System.out.println(puntos);
        for (int i = 0; i < puntos.size(); i++) {
            Point get = puntos.get(i);
            if (ventana.validarMovimiento(get.x, get.y,tank)){
                System.out.println("flag 2");
                ventana.limpiarMapa(tank.getPosX(), tank.getPosY());
                String orientacion = getOrientacion(tank.getPosX(),tank.getPosY(),get.x,get.y);
                System.out.println("X:"+get.x+", Y:"+get.y+" Orient:"+orientacion);
                tank.setPosX(get.x);
                tank.setPosY(get.y);
                System.out.println("Pos tank after set: "+ tank.getPosX()+" , " + tank.getPosY());
                ventana.buildMapa();
                ventana.pintarTanque(tank.getImages()[tank.getPosImagen(orientacion)], tank.getPosX(), tank.getPosY());
            }
        }
            
    }
}
