package ConfigurationManager;


public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private int velocidadDisparo;
    private int velocidadMovimiento;
    private int tiempoAparicion;
    
    private ConfigurationManager(){}
    
    public static ConfigurationManager getInstance(){
        if(configurationManager == null)
            configurationManager =  new ConfigurationManager();
        return configurationManager;
    }

    public int getVelocidadDisparo() {
        return velocidadDisparo;
    }

    public void setVelocidadDisparo(int velocidadDisparo) {
        this.velocidadDisparo = velocidadDisparo;
    }

    public int getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public void setVelocidadMovimiento(int velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public int getTiempoAparicion() {
        return tiempoAparicion;
    }

    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }
    
    
}
