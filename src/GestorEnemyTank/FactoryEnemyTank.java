
package GestorEnemyTank;

public class FactoryEnemyTank implements EnemyTankFactoryMethod{

    @Override
    public EnemyTank createEnemyTank(String tipo, int posX, int posY,int velocidadMovimiento,int velocidadDisparo) {
        switch(tipo){
            case "simple":
                EnemyTank t1 = new EnemyTank(2,posX,posY,velocidadMovimiento,velocidadDisparo);
                t1.addImage("Imgs\\etankSu.png");
                t1.addImage("Imgs\\etankSr.png");
                t1.addImage("Imgs\\etankSl.png");
                t1.addImage("Imgs\\etankSd.png");
                return t1;
            case "rapido":
                EnemyTank t2 = new EnemyTank(1,posX,posY,velocidadMovimiento,velocidadDisparo);
                t2.addImage("Imgs\\etankRu.png");
                t2.addImage("Imgs\\etankRr.png");
                t2.addImage("Imgs\\etankRl.png");
                t2.addImage("Imgs\\etankRd.png");
                return t2;
            case "fuerte":
                EnemyTank t3 = new EnemyTank(1,posX,posY,velocidadMovimiento,velocidadDisparo);
                t3.addImage("Imgs\\etankFu.png");
                t3.addImage("Imgs\\etankFr.png");
                t3.addImage("Imgs\\etankFl.png");
                t3.addImage("Imgs\\etankFd.png");
                return t3;
            case "resistente":
                EnemyTank t4 = new EnemyTank(4,posX,posY,velocidadMovimiento,velocidadDisparo);
                t4.addImage("Imgs\\etankRRu.png");
                t4.addImage("Imgs\\etankRRr.png");
                t4.addImage("Imgs\\etankRRl.png");
                t4.addImage("Imgs\\etankRRd.png");
                return t4;
            default:
                return null;
        }
    }
    
    
}
