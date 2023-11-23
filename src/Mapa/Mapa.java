
package Mapa;

import Bloque.*;
import Tanque.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Mapa implements Serializable {
    ArrayList<Bloque> bloques = new ArrayList<>();
    ArrayList<Tanque> eTanks = new ArrayList<>();
    
    public Mapa(MapaBuilder mapaBuilder) {
        this.bloques = mapaBuilder.getBloques();
    }

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }
    
    
    public Mapa agregarBloque(Bloque bloque){
        bloques.add(bloque);
        return this;
    }
    
    public Mapa build(){
        return this;
    }
    
    public void addTanque(Tanque t){
        eTanks.add(t);
    }
    
    public void imprimir(){
        for(Bloque b : bloques)
            System.out.println(b);
          
    }

    public ArrayList<Tanque> getETanks() {
        return eTanks;
    }

    public void seteTanks(ArrayList<Tanque> eTanks) {
        this.eTanks = eTanks;
    }
    
    
}
