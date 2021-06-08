package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.Manager;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public class Help implements Command 
{
    public final Manager manager;
    String publicCommands = "";
	String privateCommands = "";

    public Help(Manager m) 
    {
        this.manager = m;
    }

    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
        if(args.size() > 1) 
        {
            Tools.wrongUsage(event.getChannel(), this);
            return;
        }
        if(args.isEmpty()) 
        {
        	manager.getCommands().forEach(command -> 
            {
            	publicCommands = publicCommands + command.getCommand() + "\n";
            });
        	
        	manager.getPCommands().forEach(command -> 
            {
            	privateCommands = privateCommands + command.getPCommand() + "\n";
            });
        	
            EmbedBuilder e = new EmbedBuilder()
                    .setTitle("A list of all my commands:")
                    .addField("Server Commands:", publicCommands, false)
            		.addField("DM Commands:", privateCommands, false);
            
            event.getChannel().sendMessage(e.build()).queue();
            
            publicCommands = "";
            privateCommands = "";
            
            return;
        }
        
        Command command = manager.getCommand(String.join("", args));
        
        if(command == null) 
        {
            event.getChannel().sendMessage("The command `" + String.join("", args) + "` does not exist!\n" +
                    "Use `" + Constants.BotPrefix + command.getCommand() + "` for a list of all my commands!").queue();
            return;
        }
        
        event.getChannel().sendMessage("Command help for `" + command.getCommand() + "`\n" +
                command.getHelp()).queue();
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {
        if(args.size() > 1) 
        {
            Tools.wrongUsage(event.getChannel(), this);
            return;
        }
        if(args.isEmpty()) 
        {
        	manager.getCommands().forEach(command -> 
            {
            	publicCommands = publicCommands + command.getCommand() + "\n";
            });
        	
        	manager.getPCommands().forEach(command -> 
            {
            	privateCommands = privateCommands + command.getPCommand() + "\n";
            });
        	
            EmbedBuilder e = new EmbedBuilder()
                    .setTitle("A list of all my commands:")
                    .addField("Server Commands:", publicCommands, false)
            		.addField("DM Commands:", privateCommands, false);
            
            event.getChannel().sendMessage(e.build()).queue();
            return;
        }
        
        Command commandP = manager.getCommand(String.join("", args));
        
        if(commandP == null) 
        {
            event.getChannel().sendMessage("The command `" + String.join("", args) + "` does not exist!\n" +
                    "Use `" + Constants.BotPrefix + commandP.getPCommand() + "` for a list of all my commands!").queue();
            return;
        }
        
        event.getChannel().sendMessage("Command help for `" + commandP.getPCommand() + "`\n" +
                commandP.getHelp()).queue();
    }


    @Override
    public String getCommand() 
    {
        return "help";
    }
    
    @Override
    public String getPCommand() {
        return "help";
    }

    @Override
    public String getHelp() 
    {
        return "Gives you a list of existing commands on TutorialBot!\n" +
                "Usage: `" + Constants.BotPrefix + getCommand() + " <command>`";
    }
}
