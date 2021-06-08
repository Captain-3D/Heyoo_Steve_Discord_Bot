package DaltonChichester.HeyooSteveBot;

import DaltonChichester.HeyooSteveBot.commands.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.*;
import java.util.regex.Pattern;

public class Manager 
{
    private final Map<String, Command> commands = new HashMap<>();
    private final Map<String, Command> commandsP = new HashMap<>();
    
    Manager() 
    {
    	//public commands
    	addCommand(new Help(this));
    	addCommand(new ManualSongOfTheDay());
        addCommand(new UserInfo());
        addCommand(new Ping()); 
        addCommand(new Meme());
        addCommand(new Poll());
        addCommand(new EightBall());
        addCommand(new ShowMoney());
        addCommand(new MoneyLeaderboards());
        addCommand(new StartBet());
        addCommand(new CloseBet());
        addCommand(new PayoutBet());
        addCommand(new AddBet());
        //addCommand(new Roast());
        
        //private commands
        addPCommand(new Help(this));
        addPCommand(new Ping());
        addPCommand(new Meme());
        addPCommand(new EightBall());
        addPCommand(new AddSongOfTheDay());
        //addPCommand(new Roast());
    }

    private void addCommand(Command c) 
    {
        if(!commands.containsKey(c.getCommand())) 
        {
            commands.put(c.getCommand(), c);
        }
    }
    
    private void addPCommand(Command c) 
    {
        if(!commandsP.containsKey(c.getPCommand())) 
        {
            commandsP.put(c.getPCommand(), c);
        }
    }

    public Collection<Command> getCommands() 
    {
        return commands.values();
    }
    
    public Collection<Command> getPCommands() 
    {
        return commandsP.values();
    }

    public Command getCommand(String commandName) 
    {
        if(commandName == null) 
        {
            return null;
        }
        return commands.get(commandName);
    }
    
    public Command getPCommand(String commandName) 
    {
        if(commandName == null) 
        {
            return null;
        }
        return commandsP.get(commandName);
    }

    void run(GuildMessageReceivedEvent event)
    {
        final String msg = event.getMessage().getContentRaw();
        
        if(!msg.startsWith(Constants.BotPrefix)) 
        {
        	nonCommand(event);
            return;
        }
        
        final String[] split = msg.replaceFirst("(?i)" + Pattern.quote(Constants.BotPrefix), "").split("\\s+");
        final String command = split[0].toLowerCase();
        
        if(commands.containsKey(command)) 
        {
            final List<String> args = Arrays.asList(split).subList(1, split.length);
            commands.get(command).run(args, event);
        }
    }
    
    void runP(PrivateMessageReceivedEvent event)
    {
        final String msg = event.getMessage().getContentRaw();
        
        if(!msg.startsWith(Constants.BotPrefix)) 
        {
        	//nonCommand(event);
            return;
        }
        
        final String[] split = msg.replaceFirst("(?i)" + Pattern.quote(Constants.BotPrefix), "").split("\\s+");
        final String command = split[0].toLowerCase();
        
        if(commandsP.containsKey(command)) 
        {
            final List<String> args = Arrays.asList(split).subList(1, split.length);
            commandsP.get(command).runP(args, event);
        }
    }
    
    public void nonCommand(GuildMessageReceivedEvent event)
    {
    	String[] args = event.getMessage().getContentRaw().split("\\s");
    	
    	if(event.getAuthor().isBot())
        {
            return;
        }
    	
    	for(int i = 0; i < args.length; i++)
        {
	        if(args[i].equalsIgnoreCase("wack"))
	        {
	        	event.getChannel().sendTyping().queue();
	            event.getChannel().sendMessage("*slams table*").queue();
	        }
        }
        
        for(int i = 0; i < args.length; i++)
        {
	        if(args[i].equalsIgnoreCase("wacker"))
	        {
	        	event.getChannel().sendTyping().queue();
	            event.getChannel().sendMessage(":regional_indicator_s: :regional_indicator_l: :regional_indicator_a: :regional_indicator_m:"
	            							+ " :regional_indicator_s:  :regional_indicator_t: :regional_indicator_a: :regional_indicator_b:"
	            							+ " :regional_indicator_l: :regional_indicator_e:").queue();
	        }
        }
        
        for(int i = 0; i < args.length; i++)
        {
	        if(args[i].equalsIgnoreCase("wackest"))
	        {
	        	event.getChannel().sendTyping().queue();
	            event.getChannel().sendMessage("https://3.bp.blogspot.com/-e8IbOjLwxIg/VHtYb12CvwI/AAAAAAAAScs/-uJohz_H4os/s1600/Toss%2BSuplex%2Binto%2Bthe%2BTable.gif").queue();
	        }
        }
        
        if(event.getMessage().getContentRaw().equalsIgnoreCase("heyooo") || event.getMessage().getContentRaw().equalsIgnoreCase("heyooo.") || event.getMessage().getContentRaw().equalsIgnoreCase("heyooo?") || event.getMessage().getContentRaw().equalsIgnoreCase("heyooo!"))
        {
        	event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Heyooo!").queue();
        }
        
        for(int i = 0; i < args.length; i++)
        {
	        if(args[i].equalsIgnoreCase("steve") || args[i].equalsIgnoreCase("steve.") || args[i].equalsIgnoreCase("steve?") || args[i].equalsIgnoreCase("steve!"))
	        {
	        	event.getChannel().sendTyping().queue();
	            event.getChannel().sendMessage("Heyooo!").queue();
	        }
        }
         
	    if(args[0].equalsIgnoreCase("f"))
	    {
	        event.getChannel().sendTyping().queue();
	        event.getChannel().sendMessage("F").queue();
	    }
	    
	    for(int i = 0; i < args.length; i++)
        {
			String string = "our";
			if(args[i].equalsIgnoreCase("my"))
			{
				event.getChannel().sendTyping().queue();

				for(int j = i+1; j < args.length; j++)
		        {
					if(args[j].contains("."))
					{
						string = string + " " +  args[j];
						break;
					}
					else if(args[j].contains("?") || args[j].contains("!"))
					{
						string = string + " " +  args[j];
						string = string.replace("?", ".");
						string = string.replace("!", ".");
						break;
					}
					else
					{
						string = string + " " +  args[j];
					}
		        }
				event.getChannel().sendMessage(string).queue();
			}
        }
    }

}
