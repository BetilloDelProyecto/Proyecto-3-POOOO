package Bloque;

import java.io.Serializable;
import javax.swing.*;

public class Bloque implements Serializable{
    int posX,posY;
    int vida;
    String imagen,tipo;
    boolean destruible,traspasable, baleable;
    JLabel lbl;
    
    //----------------------------CONSTRUCTOR------------------------------
    public Bloque(int posX, int posY, int vida, String imagen, String tipo,boolean destruible,boolean traspasable, boolean baleable) {
        this.posX = posX;
        this.posY = posY;
        this.vida = vida;
        this.imagen = imagen;
        this.tipo = tipo;
        this.destruible = destruible;
        this.traspasable = traspasable;
        this.baleable = baleable;
    }
    
    //------------------------------GET & SET------------------------------
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public boolean isDestruible() {
        return destruible;
    }

    public void setDestruible(boolean destruible) {
        this.destruible = destruible;
    }

    public boolean isTraspasable() {
        return traspasable;
    }

    public void setTraspasable(boolean traspasable) {
        this.traspasable = traspasable;
    }
    
    
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean isBaleable() {
        return baleable;
    }

    public void setBaleable(boolean baleable) {
        this.baleable = baleable;
    }

    
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Bloque{" + "posX=" + posX + ", posY=" + posY + ", vida=" + vida + ", imagen=" + imagen + ", tipo=" + tipo + ", destruible=" + destruible + ", traspasable=" + traspasable + '}';
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }
    
    public void morir(){
        posX = -1;
        posY = -1;
        lbl.setIcon(null);
        
    }    

    

    
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    //------------------------------GET & SET------------------------------

    
    
    
}
