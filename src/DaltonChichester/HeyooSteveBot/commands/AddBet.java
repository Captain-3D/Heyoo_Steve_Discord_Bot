package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import DaltonChichester.HeyooSteveBot.tools.Money;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddBet implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
    	String[] args2 = args.get(0).split(",");
    	
    	if(!args.isEmpty() && args2.length == 3) 
        {
    		int betID = Integer.parseInt(args2[0]);
    		int outcomeNum = Integer.parseInt(args2[1]);
    		float wager = Float.parseFloat(args2[2]);
    		float wagerN = wager;
    		List<String> values = new ArrayList<String>();
    		List<String> values2 = new ArrayList<String>();
    		
    		values2 = Money.GetMoney(event.getMember());
    		
    		if(Float.parseFloat(values2.get(0)) < wager)
    		{
    			event.getChannel().sendMessage("You currently do not have enough funds to wager that amount!").queue();
    		}
    		else
    		{
    			Money.ChangeMoney(event.getMember(), (-1 * wager), 1);
    			
    			try 
        		{
    				values = Files.readAllLines(Paths.get("Bets/Bet#" + betID + ".txt"));
    				
    				if(values.get(6).equals(("OPEN")))
    	    		{
    					int index = values.size();
    					
    					for(int i = 7; i < values.size(); i++)
		    			{
		    				String[] tempTarget = values.get(i).split(",");
		    				if(tempTarget[0].equals(event.getAuthor().getName()))
		    				{
		    					index = i;
		    					wagerN = (Float.parseFloat(tempTarget[2]) + wager);
		    					
		    					if(Integer.parseInt(tempTarget[1]) != outcomeNum)
		    					{
		    						event.getChannel().sendMessage("You have already placed a bet on the other outcome! You may only have wagers placed on one outcome at a time.").queue();
		    						return;
		    					}
		    					else
		    					{
		    						break;
		    					}
		    				}
		    			}
    					
    					FileWriter fWriter;
    	    			try 
    	    			{
    						fWriter = new FileWriter("Bets/Bet#" + betID + ".txt", false);
        	    			
    	    				fWriter.write(values.get(0) + "\n");
    	    				fWriter.write(values.get(1) + "\n");
    	    				fWriter.write(values.get(2) + "\n");
    	    				fWriter.write(values.get(3) + "\n");
    	    				
    	    				if(outcomeNum == 1)
    	    				{
    	    					fWriter.write(Float.parseFloat(values.get(4)) + wager + "\n");
    	    					
    	    					fWriter.write(values.get(5) + "\n");
    	    				}
    	    				else if(outcomeNum == 2)
    	    				{
    	    					fWriter.write(values.get(4) + "\n");
    	    					
    	    					fWriter.write(Float.parseFloat(values.get(5)) + wager + "\n");
    	    				}
    	    				
    	    				for(int ii = 6; ii < index; ii++)
    	    				{
    	    					fWriter.write(values.get(ii) + "\n");
    	    				}
    	    				
    	    				fWriter.write(event.getAuthor().getName() + "," + outcomeNum + "," + wagerN + "\n");

    	    				fWriter.close();
    	    				
    	    				EmbedBuilder targetInfo = new EmbedBuilder();
    	    				targetInfo.setTitle(event.getAuthor().getName() + " put money into Bet#" + betID + "!")
                            .setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
                            .setThumbnail(event.getAuthor().getEffectiveAvatarUrl())
                            .setDescription("Bet #" + betID + ": " + values.get(1));
                            
    	    				float odds1 = 0;
    	    				float odds2 = 0;
    	    				
                            if(outcomeNum == 1)
    	    				{
                            	targetInfo.addField("Current Pot for " + values.get(2), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(4)) + wager)), true);
                            	targetInfo.addField("Current Pot for " + values.get(3), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(5)))), true);
                            	
                            	if((Float.parseFloat(values.get(4)) + wager) > Float.parseFloat(values.get(5)))
                            	{
                            		odds2 = 10 * (Float.parseFloat(values.get(5))) / ((Float.parseFloat(values.get(4)) + wager) + Float.parseFloat(values.get(5)));
                            		odds1 = 1 + (Float.parseFloat(values.get(4)) + wager) / ((Float.parseFloat(values.get(4)) + wager) + Float.parseFloat(values.get(5)));
                            		
                            		if(odds1 == 2)
                            		{
                            			odds1 -= 0.9;
                            		}
                            	}
                            	else
                            	{
                            		odds2 = 1 + (Float.parseFloat(values.get(5))) / ((Float.parseFloat(values.get(4)) + wager) + Float.parseFloat(values.get(5)));
                            		
                            		if(odds2 == 2)
                            		{
                            			odds2 -= 0.9;
                            		}
                            		
                            		odds1 = 10 * (Float.parseFloat(values.get(4)) + wager) / ((Float.parseFloat(values.get(4)) + wager) + Float.parseFloat(values.get(5)));
                            	}
    	    				}
    	    				else if(outcomeNum == 2)
    	    				{
    	    					targetInfo.addField("Current Pot for " + values.get(2), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(4)))), true);
    	    					targetInfo.addField("Current Pot for " + values.get(3), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(5)) + wager)), true);
    	    					
    	    					if((Float.parseFloat(values.get(5)) + wager) > Float.parseFloat(values.get(4)))
                            	{
                            		odds2 = 1 + (Float.parseFloat(values.get(5)) + wager) / ((Float.parseFloat(values.get(5)) + wager) + Float.parseFloat(values.get(4)));
                            		
                            		if(odds2 == 2)
                            		{
                            			odds2 -= 0.9;
                            		}
                            		
                            		odds1 = 10 * (Float.parseFloat(values.get(4))) / ((Float.parseFloat(values.get(5)) + wager) + Float.parseFloat(values.get(4)));
                            	}
                            	else
                            	{
                            		odds2 = 10 * (Float.parseFloat(values.get(5)) + wager) / ((Float.parseFloat(values.get(5)) + wager) + Float.parseFloat(values.get(4)));
                            		odds1 = 1 + (Float.parseFloat(values.get(4))) / ((Float.parseFloat(values.get(5)) + wager) + Float.parseFloat(values.get(4)));
                            		
                            		if(odds1 == 2)
                            		{
                            			odds1 -= 0.9;
                            		}
                            	}
    	    				}
    	    				
                            targetInfo.addField("Current odds for " + values.get(2) + " are: ", Constants.dfMoney.format(odds1) + " to 1", false);
                            targetInfo.addField("Current odds for " + values.get(3) + " are: ", Constants.dfMoney.format(odds2) + " to 1", true); 
                            
                            if(outcomeNum == 1)
    	    				{
                            	targetInfo.addField("If " + values.get(2) + " wins, your predicted payout currently would be:","$" + Constants.dfMoney.format((wagerN * odds1)), false);
    	    				}
    	    				else if(outcomeNum == 2)
    	    				{
    	    					targetInfo.addField("If " + values.get(3) + " wins, your predicted payout currently would be:","$" + Constants.dfMoney.format((wagerN * odds2)), false);
    	    				}
                            
                            targetInfo.addField("Your Money: ", "$" + Constants.dfMoney.format(Float.parseFloat(values2.get(0))) + " --> $" + Constants.dfMoney.format((Float.parseFloat(values2.get(0)) - wager)), false); 
                            
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
        		catch (IOException e)
        		{
        			event.getChannel().sendMessage("No Bet with that ID exists!").queue();
    			}
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
        return "addbet";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "Add a wager to a exsisting bet!\nUsage: `" + Constants.BotPrefix + getCommand() + " <betId>, <outcome#>, <wager>`";
    }
}
