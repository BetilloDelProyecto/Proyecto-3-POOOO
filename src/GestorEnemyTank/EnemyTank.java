package GestorEnemyTank;

import Tanque.*;

public class EnemyTank extends Tanque {
    int velocidadMovimiento;
    int velocidadDisparo;
    public EnemyTank(int vida,int posX,int posY,int velocidadMovimiento,int velocidadDisparo) {
        super(vida);
        this.setPosX(posX);
        this.setPosY(posY);
        this.velocidadMovimiento = velocidadMovimiento;
        this.velocidadDisparo = velocidadDisparo;
    }

    public int getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public void setVelocidadMovimiento(int velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public int getVelocidadDisparo() {
        return velocidadDisparo;
    }

    public void setVelocidadDisparo(int velocidadDisparo) {
        this.velocidadDisparo = velocidadDisparo;
    }
    
    public EnemyTank clonar(){
        EnemyTank t1 = new EnemyTank(this.velocidadDisparo, this.velocidadDisparo, this.velocidadDisparo, this.velocidadMovimiento, this.velocidadDisparo);
        t1.setImages(this.getImages());
        return t1;
    }
    
    
    
}
