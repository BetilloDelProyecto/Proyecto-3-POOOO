
package Commands;

import Tanque.*;


public class UpCommand implements ICommand{
    public static final String COMMAND_NAME = "UP";       
    
    @Override       
    public String getCommandName() {           
        return COMMAND_NAME;   
    }
    
    @Override       
    public void execute(FriendlyTank tanque) {           
        tanque.setPosX(tanque.getPosX()-1);
        tanque.setOrientacion(COMMAND_NAME);
        System.out.println("X: " + tanque.getPosX() + " Y: " + tanque.getPosY());
    } 
}
