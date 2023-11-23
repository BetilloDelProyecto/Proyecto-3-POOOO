package GestorEnemyTank;

import java.util.*;

public class GestorTankEnemigo{
    private Hashtable tanks = new Hashtable();
    
    public GestorTankEnemigo(){
        FactoryEnemyTank eFactory = new FactoryEnemyTank();
        
        EnemyTank eTank1 = eFactory.createEnemyTank("simple", -1, -1, 0, 0);
        this.addEnemigo( "simple", eTank1);
        
        EnemyTank eTank2 = eFactory.createEnemyTank("rapido", -1, -1, 0, 0);
        this.addEnemigo( "rapido", eTank2);
        
        EnemyTank eTank3 = eFactory.createEnemyTank("fuerte", -1, -1, 0, 0);
        this.addEnemigo( "fuerte", eTank3);
        
        EnemyTank eTank4 = eFactory.createEnemyTank("resistente", -1, -1, 0, 0);
        this.addEnemigo( "resistente", eTank4);
    }
    
    public void addEnemigo( String nombre, EnemyTank objEnemigo){
         this.tanks.put( nombre, objEnemigo);
    }
    
    public EnemyTank getTank( String nombre ){
         EnemyTank objPrototipo = (EnemyTank) tanks.get( nombre );
         return objPrototipo;
    }
    
    public EnemyTank getClon(String nombre){
         EnemyTank objPrototipo = (EnemyTank) tanks.get( nombre );
         return (EnemyTank) objPrototipo.clonar();
    }
}
