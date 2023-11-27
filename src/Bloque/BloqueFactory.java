
package Bloque;


public class BloqueFactory implements BloqueFactoryMethod{
    public Bloque createBloque(String tipo,int posX,int posY){
        switch(tipo){
            case "grass":
                return new Bloque(posX, posY, 0, "Imgs\\grass.png", "grass",false,true, false);
            case "brick":
                return new Bloque(posX, posY, 1, "Imgs\\brick.png", "brick",true,false, true);
            case "steel":
                return new Bloque(posX, posY, 0, "Imgs\\steel.png", "steel",false,false, true);
            case "water":
                return new Bloque(posX, posY, 0, "Imgs\\water.png", "water",false,false, false);
            case"eagle":
                return new Bloque(posX, posY, 1, "Imgs\\eagle.png", "eagle",true,false, true);
            
            default:
                return null;
        }
    }

    
}
