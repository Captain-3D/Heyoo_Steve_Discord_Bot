package DaltonChichester.HeyooSteveBot.tools;

import DaltonChichester.HeyooSteveBot.Command;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

public class Tools 
{
    public static void wrongUsage(TextChannel tc, Command c) 
    {
        tc.sendMessage("Wrong Command Usage!\n" + c.getHelp()).queue();
    }
    
    public static void wrongUsage(PrivateChannel pc, Command c) 
    {
        pc.sendMessage("Wrong Command Usage!\n" + c.getHelp()).queue();
    }
}
