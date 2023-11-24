package GestorEnemyTank;

import Tanque.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;

public class EnemyTank extends Tanque {
    int velocidadMovimiento;
    int velocidadDisparo;
    JLabel lblTank;
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

    public JLabel getLblTank() {
        return lblTank;
    }

    public void setLblTank(JLabel lblTank) {
        this.lblTank = lblTank;
    }
    
    public EnemyTank clonar(){
        EnemyTank t1 = new EnemyTank(this.velocidadDisparo, this.velocidadDisparo, this.velocidadDisparo, this.velocidadMovimiento, this.velocidadDisparo);
        t1.setImages(this.getImages());
        t1.setLblTank(new JLabel());
        return t1;
    }
    
    public double getDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }
    
    public ArrayList<Point> sortPossibleMoves(ArrayList<Point> array, int objX, int objY){
        if (!array.isEmpty()){
             for (int i = 0; i < array.size()-1; i++) {
                for (int j = 0; j < array.size() - 1; j++) {
                    if (getDistance(array.get(j).x,array.get(j).y,objX,objY) < getDistance(array.get(j+1).x,array.get(j+1).y,objX,objY)){
                        Point p1 = new Point(array.get(j));
                        Point p2 = new Point(array.get(j+1));
                        array.set(j, p2);
                        array.set(j+1, p1);
                    }
                }
            }
        }
        return array;
    }
    
    public ArrayList<Point> setPossibleMoves(int cant){
        ArrayList<Point> puntos = new ArrayList<Point>();
        puntos.add(new Point(getPosX()+cant,getPosY()));
        puntos.add(new Point(getPosX()-cant,getPosY()));
        puntos.add(new Point(getPosX(),getPosY()+cant));
        puntos.add(new Point(getPosX(),getPosY()-cant));
        
        Iterator<Point> iterator = puntos.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point punto = iterator.next();
            if(punto.x < 0 || punto.x > 25){
                iterator.remove();
            }else if (punto.y < 0 || punto.y > 25){
                iterator.remove(); 
            }
        }
        return puntos;
    }
}
