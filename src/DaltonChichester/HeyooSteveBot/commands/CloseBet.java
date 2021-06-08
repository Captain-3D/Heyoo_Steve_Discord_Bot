package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloseBet implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event)
    {
    	if(!args.isEmpty()) 
        {
    		int betID = Integer.parseInt(args.get(0));
    		List<String> values = new ArrayList<String>();
    		
    		try 
    		{
				values = Files.readAllLines(Paths.get("Bets/Bet#" + betID + ".txt"));
				
				if(values.get(0).equals((event.getAuthor().getName())))
				{
					if(values.get(6).equals(("OPEN")))
		    		{
		    			FileWriter fWriter;
		    			try 
		    			{
		    				fWriter = new FileWriter("Bets/Bet#" + betID + ".txt", false);
		    			
		    				for(int i = 0; i < 6; i++)
		    				{
		    					fWriter.write(values.get(i) + "\n");
		    				}
		    				
		    				fWriter.write("CLOSED" + "\n");
		    				
		    				for(int i = 7; i < values.size(); i++)
		    				{
		    					fWriter.write(values.get(i) + "\n");
		    				}
	
		    				fWriter.close();
		    				
		    				EmbedBuilder targetInfo = new EmbedBuilder();
		    				targetInfo.setTitle(event.getAuthor().getName() + " has closed Bet#" + betID + "!")
	                        .setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
	                        .setThumbnail(event.getAuthor().getEffectiveAvatarUrl())
	                        .setDescription("Bet #" + betID + ": " + values.get(1));
	                        
		    				float odds1 = 0;
		    				float odds2 = 0;

	                        targetInfo.addField("Final Pot for " + values.get(2), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(4)))), true);
	                        targetInfo.addField("Final Pot for " + values.get(3), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(5)))), true);
	                        	
                            if((Float.parseFloat(values.get(4))) > Float.parseFloat(values.get(5)))
                            {
                            	odds2 = 10 * (Float.parseFloat(values.get(5))) / ((Float.parseFloat(values.get(4))) + Float.parseFloat(values.get(5)));
                            	odds1 = 1 + (Float.parseFloat(values.get(4))) / ((Float.parseFloat(values.get(4))) + Float.parseFloat(values.get(5)));
                            	
                            	if(odds1 == 2)
                        		{
                        			odds1 -= 0.9;
                        		}
                            }
                            else
                            {
                            	odds2 = 1 + (Float.parseFloat(values.get(5))) / ((Float.parseFloat(values.get(4))) + Float.parseFloat(values.get(5)));
                            	
                            	if(odds2 == 2)
                        		{
                        			odds2 -= 0.9;
                        		}
                            	
                            	odds1 = 10 * (Float.parseFloat(values.get(4))) / ((Float.parseFloat(values.get(4))) + Float.parseFloat(values.get(5)));
                            }
		    				
	                        targetInfo.addField("Final odds for " + values.get(2) + " are: ", Constants.dfMoney.format(odds1) + " to 1", false);
	                        targetInfo.addField("Final odds for " + values.get(3) + " are: ", Constants.dfMoney.format(odds2) + " to 1", true); 
	                        
	                        event.getChannel().sendMessage(targetInfo.build()).queue();
		    			} 
		    			catch (Exception e1) 
		    			{
		    			}
		    		}
		    		else
		    		{
		    			event.getChannel().sendMessage("The betting for this bet has already closed!").queue();
		    		}
				}
				else
		    	{
		    		event.getChannel().sendMessage("You are not the creator of this Bet!").queue();
		    	}
			} 
    		catch (IOException e)
    		{
    			event.getChannel().sendMessage("No Bet with that ID exists!").queue();
			}
        }
    	else
    	{
    		Tools.wrongUsage(event.getChannel(), this);
    	}
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {

    }

    @Override
    public String getCommand() 
    {
        return "closebet";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "close an existing bet!\nUsage: `" + Constants.BotPrefix + getCommand() + " <BetID>`";
    }
}
