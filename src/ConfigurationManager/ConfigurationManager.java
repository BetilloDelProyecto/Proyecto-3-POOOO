package ConfigurationManager;


public class ConfigurationManager {
    
    private static ConfigurationManager configurationManager;
    private int vDispSimple = 3000;
    private int vDispRapido = 2000;
    private int vDispFuerte = 2000;
    private int vDispResistente = 2000;
    
    
    private int vMovSimple = 2000;
    private int vMovRapido = 1000;
    private int vMovFuerte = 2000;
    private int vMovResistente = 4000;
    
    private int tiempoAparicion;
    
    private ConfigurationManager(){}
    
    public static ConfigurationManager getInstance(){
        if(configurationManager == null)
            configurationManager =  new ConfigurationManager();
        return configurationManager;
    }
    public int getTiempoAparicion() {
        return tiempoAparicion;
    }
    
    public int getVelDips(String tipo){
        switch (tipo){
            case "simple":
                return vDispSimple;
            case "rapido":
                return vDispRapido;
            case "fuerte":
                return vDispFuerte;
            case "resistente":
                return vDispResistente;
            default:
                return 1000;
        }
    }
    
    public int getVelMov(String tipo){
        switch (tipo){
            case "simple":
                return vMovSimple;
            case "rapido":
                return vMovRapido;
            case "fuerte":
                return vMovFuerte;
            case "resistente":
                return vMovResistente;
            default:
                return 1000;
        }
    }
            
    
    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }
    
    
}
