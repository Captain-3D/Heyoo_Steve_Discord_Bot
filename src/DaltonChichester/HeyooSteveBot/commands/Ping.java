package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public class Ping implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
        if(args.size() == 0)
        {
            event.getChannel().sendMessage("Pong!").queue(msg -> 
            {
                msg.editMessage(event.getJDA().getGatewayPing()+"ms").queue();
            });
        } 
        else 
        {
            Tools.wrongUsage(event.getChannel(), this);
        }
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {
        if(args.size() == 0)
        {
            event.getChannel().sendMessage("Pong!").queue(msg -> 
            {
                msg.editMessage(event.getJDA().getGatewayPing()+"ms").queue();
            });
        } 
        else 
        {
            Tools.wrongUsage(event.getChannel(), this);
        }
    }

    @Override
    public String getCommand() 
    {
        return "ping";
    }
    
    @Override
    public String getPCommand() {
        return "ping";
    }

    @Override
    public String getHelp() 
    {
        return "Gives you the gateway ping of the bot!\n" +
                "Usage: `" + Constants.BotPrefix + getCommand() + "`";
    }

}
