package Commands;

import Tanque.*;

public class LeftCommand implements ICommand{
    public static final String COMMAND_NAME = "LEFT";       
    
    @Override       
    public String getCommandName() {           
        return COMMAND_NAME;   
    }
    
    @Override       
    public void execute(FriendlyTank tanque) {           
        tanque.setPosY(tanque.getPosY()-1);
        tanque.setOrientacion(COMMAND_NAME);
        System.out.println("X: " + tanque.getPosX() + " Y: " + tanque.getPosY());
    } 
}
