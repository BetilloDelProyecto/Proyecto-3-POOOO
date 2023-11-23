package Bloque;

import java.io.Serializable;

public class Bloque implements Serializable{
    int posX,posY;
    int vida;
    String imagen,tipo;
    boolean destruible,traspasable;
    
    //----------------------------CONSTRUCTOR------------------------------
    public Bloque(int posX, int posY, int vida, String imagen, String tipo,boolean destruible,boolean traspasable) {
        this.posX = posX;
        this.posY = posY;
        this.vida = vida;
        this.imagen = imagen;
        this.tipo = tipo;
        this.destruible = destruible;
        this.traspasable = traspasable;
    }
    
    //------------------------------GET & SET------------------------------
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public boolean getDestruible() {
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

    

    
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    //------------------------------GET & SET------------------------------

    
    
    
}
