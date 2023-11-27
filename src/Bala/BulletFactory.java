
package Bala;

import Tanque.Tanque;
import javax.swing.JLabel;

public class BulletFactory {
    public static Bala createBullet(Tanque t, String dir, boolean amigo){
        switch(dir){
            case "UP":
                return new Bala(t.getPosX()-1, t.getPosY(), -1, 0, new JLabel(), amigo);
            case "DOWN":
                return new Bala(t.getPosX()+2, t.getPosY()+1, 1, 0, new JLabel(), amigo);
            case "LEFT":
                return new Bala(t.getPosX()+1, t.getPosY()-1, 0, -1, new JLabel(), amigo);
            case "RIGHT":
                return new Bala(t.getPosX(), t.getPosY()+2, 0, 1, new JLabel(), amigo);
            default:
                return null;
        }
    }
}
