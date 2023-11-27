package Commands;

import Tanque.*;

public class RightCommand implements ICommand {
    public static final String COMMAND_NAME = "RIGHT";       
    
    @Override       
    public String getCommandName() {           
        return COMMAND_NAME;   
    }
    
    @Override       
    public void execute(FriendlyTank tanque) {           
        tanque.setPosY(tanque.getPosY()+1);
        tanque.setOrientacion(COMMAND_NAME);
    }
}
