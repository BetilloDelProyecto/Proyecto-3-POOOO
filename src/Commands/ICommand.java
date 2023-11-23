package Commands;

import Tanque.*;

public interface ICommand {       
    public String getCommandName();       
    public void execute(FriendlyTank tanque);   
}
