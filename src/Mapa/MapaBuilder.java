
package Mapa;

import Bloque.Bloque;
import java.util.ArrayList;


public class MapaBuilder {
    ArrayList<Bloque> bloques = new ArrayList<>();

    public MapaBuilder() {
    }

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }
    
    
    
    public MapaBuilder agregarBloque(Bloque bloque){
        bloques.add(bloque);
        return this;
    }
    
    public Mapa build(){
        return new Mapa(this);
    }
}
