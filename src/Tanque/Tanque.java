package Tanque;

import Commands.*;
import java.util.HashMap;


public class Tanque {
    int vida;
    int posX,posY;
    String[] images = new String[4];
    String orientacion = "";
    
    
    public Tanque(int vida) {
        this.vida = vida;
        this.posX = -1;
        this.posY = -1;
        
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
    
    public void addImage(String image){
        for (int i = 0; i < 4; i++) 
            if(images[i] == null){
                images[i] = image;
                break;
            }
    }
    
    
    public int getPosImagen(String orientacion){
        switch(orientacion){
            case "UP":
                return 0;
            case "DOWN":
                return 3;
            case "LEFT":
                return 2;
            case "RIGHT":
                return 1;
        } 
        return 0;
    }
    
    
    
    
}
