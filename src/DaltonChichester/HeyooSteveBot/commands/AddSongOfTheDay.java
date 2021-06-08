package DaltonChichester.HeyooSteveBot.commands;

import java.io.FileWriter;
import java.util.List;
import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class AddSongOfTheDay implements Command
{
	@Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {
    	String msg = event.getMessage().getContentRaw();
    	
    	if(!args.isEmpty()) 
        {
    		if(args.get(0).contains("https://open.spotify.com/track/"))
    		{  				
    				FileWriter fWriter;
					try 
					{
						fWriter = new FileWriter("SongsOfTheDay.txt", true);
						
						fWriter.write(event.getAuthor().getIdLong() + "," + args.get(0) + "\n");

						fWriter.close();
						
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("Successfully added song").queue();
					} 
					catch (Exception e) 
					{
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("ERROR: Unexpected Exception").queue();
					}
    		}
    		else
        	{
        		Tools.wrongUsage(event.getChannel(), this);
        	}
        }
    	else
    	{
    		Tools.wrongUsage(event.getChannel(), this);
    	}
    }

    @Override
    public String getCommand() 
    {
        return "addsongoftheday";
    }
    
    @Override
    public String getPCommand() 
    {
        return "addsongoftheday";
    }

    @Override
    public String getHelp() 
    {
        return "Add your selected song to the list that I pull from to select a song of the day!\n"
        		+ "Usage: `" + Constants.BotPrefix + getCommand() + " <Spotify link>`";
    }
}
