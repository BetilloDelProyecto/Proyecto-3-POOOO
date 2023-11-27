
package Bala;

import javax.swing.*;


public class Bala {
    int PosX, posY;
    int velX, velY;
    boolean balaAmiga;
    JLabel lblBala;

    public Bala(int PosX, int posY, int velX, int velY, JLabel lbl, boolean balaAmiga) {
        this.PosX = PosX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.lblBala = lbl;
        this.balaAmiga = balaAmiga;
    }

    public int getPosX() {
        return PosX;
    }

    public void setPosX(int PosX) {
        this.PosX = PosX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public JLabel getLblBala() {
        return lblBala;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    public void setLblBala(JLabel lblBala) {
        this.lblBala = lblBala;
    }

    public boolean isBalaAmiga() {
        return balaAmiga;
    }
    
    
}
