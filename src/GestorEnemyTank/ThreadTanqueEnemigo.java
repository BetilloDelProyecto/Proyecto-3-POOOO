
package GestorEnemyTank;

import Bala.BulletFactory;
import Bala.ThreadBala;
import ConfigurationManager.ConfigurationManager;
import GUI.CampoJuego;
import Tanque.Tanque;
import java.awt.Point;
import java.util.ArrayList;


public class ThreadTanqueEnemigo extends Thread{
    CampoJuego ventana;
    EnemyTank tank;
    ConfigurationManager config;
    boolean isRunnig = true, isPaused = false;
    public ThreadTanqueEnemigo(CampoJuego ventana, EnemyTank tank, ConfigurationManager config) {
        this.ventana = ventana;
        this.tank = tank;
        this.config = config;
    }
    
    public void run(){
        try {
            int contDisp = tank.getVelocidadDisparo(), contMov = tank.getVelocidadMovimiento();
            while (isRunnig) {   
                contDisp-=1000;
                if (contDisp <= 0) {
                    disparar();
                    contDisp = tank.getVelocidadDisparo(); 
                }
                contMov-=1000;
                if (contMov <= 0){
                    moverse();
                    contMov = tank.getVelocidadMovimiento(); 
                }
                sleep(1000);
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
            return "DOWN";
        }else if(oX>nX){
            return "UP";
        }else if(oY<nY){
            return "RIGHT";
        }else if(oY>nY){
            return "LEFT";
        }
        return "";
    }
    public void moverse(){
        ArrayList<Point> puntos = tank.sortPossibleMoves(tank.setPossibleMoves(1), 24, 12);
        for (int i = 0; i < puntos.size(); i++) {
            Point get = puntos.get(i);
            if (ventana.validarMovimiento(get.x, get.y,tank)){
                ventana.limpiarMapa(tank.getPosX(), tank.getPosY());
                String orientacion = getOrientacion(tank.getPosX(),tank.getPosY(),get.x,get.y);
                tank.setPosX(get.x);
                tank.setPosY(get.y);
                ventana.buildMapa();
                ventana.pintarTanque(tank.getImages()[tank.getPosImagen(orientacion)], tank.getPosX(), tank.getPosY());
            }
        }
            
    }

    public EnemyTank getTanque() {
        return tank;
    }
    
    public void disparar(){
        new ThreadBala(BulletFactory.createBullet((Tanque)tank, tank.getOrientacion(), false), ventana).start();
    }
    
    public void setIsRunnig(boolean isRunnig) {
        this.isRunnig = isRunnig;
    }

    
    
    
    
}
