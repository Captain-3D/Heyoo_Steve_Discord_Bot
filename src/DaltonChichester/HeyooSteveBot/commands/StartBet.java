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
import java.util.Arrays;
import java.util.List;

public class StartBet implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event)
    {
    	String msg = event.getMessage().getContentRaw();
    	String temp = "";
    	
    	List<String> betArgs = Arrays.asList(msg.split(" ", 2)[1]);
    	
    	for(int i = 0 ; i < betArgs.size(); i++) 
        {
    		System.out.println(temp);
    		temp = temp + betArgs.get(i).trim();
        }
    	String[] args2 = temp.split(",");
    	
    	if(!args.isEmpty() && args2.length == 3) 
        {
    		boolean fileChecker = false; 
    		EmbedBuilder targetInfo = new EmbedBuilder();
    		int betNum = 1;
    		FileWriter fWriter;
    		
    		while(fileChecker != true)
    		{
	    		try 
	    		{
					FileInputStream fis = new FileInputStream("Bets/Bet#" + betNum + ".txt");
					try 
					{
						fis.close();
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					betNum++;
				}
	    		catch (FileNotFoundException e)
	    		{
	    			File file = new File("Bets/Bet#" + betNum + ".txt");

	    			targetInfo.setTitle(event.getAuthor().getName() + " Started a Bet!")
	                        .setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
	                        .setThumbnail(event.getAuthor().getEffectiveAvatarUrl())
	                        .setDescription("Bet #" + betNum + ": ");
	    			
	    			fileChecker = true;
				}
    		}
    		
    		List<String> bettemp = Arrays.asList(msg.split(" ", 2)[1].split(","));
    		
    		for(int i = 0; i < bettemp.size()-2; i++) 
            {
    			targetInfo.appendDescription(" **" + bettemp.get(i).trim() + "**\n");
            }
    		
    		targetInfo.addField("Add bet to " + bettemp.get(bettemp.size()-2).trim() + " with:", Constants.BotPrefix + "addbet <" + betNum + ">, <1>, <wager>", false);
    		targetInfo.addField("Add bet to " + bettemp.get(bettemp.size()-1).trim() + " with:", Constants.BotPrefix + "addbet <" + betNum + ">, <2>, <wager>", false);
    		
    		try 
    		{
				fWriter = new FileWriter("Bets/Bet#" + betNum + ".txt", true);
				
				fWriter.write(event.getAuthor().getName() + "\n");
				
				for(int i = 0; i < bettemp.size()-2; i++) 
	            {
					fWriter.write("" + bettemp.get(i).trim() + "\n");
	            }
				
				fWriter.write(bettemp.get(bettemp.size()-2).trim() + "\n");
				fWriter.write(bettemp.get(bettemp.size()-1).trim() + "\n");
				fWriter.write(0.0 + "\n");
				fWriter.write(0.0 + "\n");
				fWriter.write("OPEN" + "\n");

				fWriter.close();
			} 
    		catch (IOException e) 
    		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		event.getChannel().sendMessage(targetInfo.build()).queue();	
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
        return "startbet";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "Start a bet to get people to wager their dollarydoos!\nUsage: `" + Constants.BotPrefix + getCommand() + " <what you are betting on> , <Outcome1>, <Outcome2>`";
    }
}
