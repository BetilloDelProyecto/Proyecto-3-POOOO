package Tanque;

import Commands.*;
import java.util.*;

public class FriendlyTank extends Tanque{
    
    private static final HashMap<String, Class<? extends ICommand>> COMMANDS = new HashMap<String, Class<? extends ICommand>>();
    
    public FriendlyTank(int vida) {
        super(vida);
        registCommand("UP", UpCommand.class);
        registCommand("RIGHT", RightCommand.class);
        registCommand("DOWN", DownCommand.class);
        registCommand("LEFT", LeftCommand.class);
    }
    
    public ICommand getCommand(String commandName) {           
        if (COMMANDS.containsKey(commandName.toUpperCase())) {               
            try {   
                   //retorna nueva isntancia de comando solicitado
                return COMMANDS.get(commandName.toUpperCase()).newInstance();
            } catch (Exception e) {   
                System.out.println("error en friendlytank ln 24");  
                return null;
            }           
        }else {
            System.out.println("no encontro el bucket friendlytank ln 28");   
        }
        return null;
    }
    
    public void registCommand(String commandName, Class<? extends ICommand> command) {   
        COMMANDS.put(commandName.toUpperCase(), command);   
    }
    
}
