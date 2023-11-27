
package Bala;

import Bloque.Bloque;
import GUI.CampoJuego;
import GestorEnemyTank.ThreadTanqueEnemigo;
import Tanque.Tanque;

public class ThreadBala extends Thread {
    Bala bala;
    CampoJuego ventana;
    boolean isRunning;
    public ThreadBala(Bala bala, CampoJuego ventana) {
        this.bala = bala;
        this.ventana = ventana;
        this.isRunning = true;
    }
    
    public void run(){
        try {
            
            ventana.setCenteredImage(bala.getLblBala(), "Imgs\\bullet.png");
            bala.getLblBala().setLocation(bala.getPosY()*30,bala.getPosX()*30);
            ventana.getPnlCampo().add(bala.getLblBala());
            if (!(0<=bala.getPosX() && bala.getPosX()<=25 && 0<=bala.getPosY() && bala.getPosY()<=25))
                isRunning = false;
            while (isRunning){
                colisionBala();
                moverBala();
                sleep(50);
            }
        } catch (InterruptedException ex) {
                System.out.println("SE PARO EL Thread de la bala");
        }
    }
    
    public void moverBala(){
        bala.setPosX(bala.getPosX()+bala.getVelX());
        bala.setPosY(bala.getPosY()+bala.getVelY());
        if (!(0<=bala.getPosX() && bala.getPosX()<=25 && 0<=bala.getPosY() && bala.getPosY()<=25))
            isRunning = false;
        bala.getLblBala().setLocation(bala.getPosY()*30,bala.getPosX()*30);
    }
    
    public void colisionBala(){
        if (bala.isBalaAmiga()) {
            Bloque b = ventana.estaBloque(bala.getPosX(), bala.getPosY());
            if(b != null && b.isBaleable()){
                if (b.isDestruible()){
                    b.setVida(b.getVida()-1);
                    if (b.getVida() <= 0)
                    b.morir();
                }
                isRunning = false;
                bala.getLblBala().setIcon(null);
            }
            ThreadTanqueEnemigo t = ventana.estaTanque(bala.getPosX(), bala.getPosY());
            if(t != null){
                t.getTanque().setVida(t.getTanque().getVida()-1);
                System.out.println("Vida tanque enemigo colisionado: " + t.getTanque().getVida());
                if (t.getTanque().getVida() <= 0) {
                    ventana.getMapaActual().getETanks().remove(t.getTanque());
                    ventana.limpiarMapa(t.getTanque().getPosX(), t.getTanque().getPosY());
                    ventana.getEnemigos().remove(t);
                    t.setIsRunnig(false);
                    System.out.println("Tamanno de array de MapaActual.eTanks: " + ventana.getMapaActual().getETanks().size());
                }
                isRunning = false;
                bala.getLblBala().setIcon(null);
            }
        }else{
            Bloque b = ventana.estaBloque(bala.getPosX(), bala.getPosY());
            if(b != null && b.isBaleable()){
                if (b.isDestruible()){
                    b.setVida(b.getVida()-1);
                    if (b.getVida() <= 0)
                    b.morir();
                }
                isRunning = false;
                bala.getLblBala().setIcon(null);
            }
            if(ventana.estaAguila(bala.getPosX(), bala.getPosY())){
                System.out.println("Se pierde la partida");
                isRunning = false;
                bala.getLblBala().setIcon(null);
            }
            if(ventana.estaFTanque(bala.getPosX(), bala.getPosY())){
                ventana.getfTank().setVida(ventana.getfTank().getVida()-1);
                if(ventana.getfTank().getVida()<=0){
                    System.out.println("Se pierde la partida");
                }
                isRunning = false;
                bala.getLblBala().setIcon(null);
            }
            
        }
    }
}
