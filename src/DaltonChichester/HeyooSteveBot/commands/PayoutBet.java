package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import DaltonChichester.HeyooSteveBot.tools.Money;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
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

public class PayoutBet implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event)
    {
    	if(!args.isEmpty()) 
        {
    		String[] args2 = args.get(0).split(",");
    		
    		int betID = Integer.parseInt(args2[0]);
    		int outcomeNum = Integer.parseInt(args2[1]);
    		List<String> values = new ArrayList<String>();
    		
    		try 
    		{
				values = Files.readAllLines(Paths.get("Bets/Bet#" + betID + ".txt"));
				
				if(values.get(0).equals((event.getAuthor().getName())))
				{
					if(values.get(6).equals(("CLOSED")))
		    		{
						List<String[]> earners = new ArrayList<String[]>();
						
						float odds1 = 0;
	    				float odds2 = 0;
	    				
		    			for(int i = 7; i < values.size(); i++)
		    			{
		    				String[] tempTarget = values.get(i).split(",");
		    				
		    				if(event.getGuild().getMembersByName(tempTarget[0], false) != null)
		    				{
		    					List<Member> tempTarget2 = event.getGuild().getMembersByName(tempTarget[0], false);
		    					Member target =  tempTarget2.get(0);
		    					float wager = Float.parseFloat(tempTarget[2]);
		    					float payout = 0;

		    					if(Integer.parseInt(tempTarget[1]) == outcomeNum)
		    					{
		    						if(outcomeNum == 1)
		    	    				{
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
		                            	
		                            	payout = (wager * odds1);
		    	    				}
		    	    				else if(outcomeNum == 2)
		    	    				{
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
		    	    					
		    	    					payout = (wager * odds2);
		    	    				}
		                            
		    						String[] temp = {target.getUser().getName(), String.valueOf(payout)};
			    					earners.add(temp);
		    						
		                            Money.ChangeMoney(target, payout, 1);
		                            
		                            List<String> values2 = new ArrayList<String>();
		                            values2 = Money.GetMoney(target);
		                            
		                            EmbedBuilder targetInfoP = new EmbedBuilder();
		    		    			targetInfoP.setTitle(event.getAuthor().getName() + " has determined the outcome for Bet#" + betID + "!")
		    	                    	.setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
		    	                    	.setThumbnail(event.getAuthor().getEffectiveAvatarUrl())
		    	                    	.setDescription("Bet #" + betID + ": " + values.get(1));

		    		    			targetInfoP.addField(values.get(1 + outcomeNum) + " was chosen as correct!", "", false); 
		    		    			
		    	                    targetInfoP.addField("Final Pot for " + values.get(2), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(4)))), true);
		    	                    targetInfoP.addField("Final Pot for " + values.get(3), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(5)))), true);
		    		    				
		    	                    targetInfoP.addField("Final odds for " + values.get(2) + " are:", Constants.dfMoney.format(odds1) + " to 1", false);
		    	                    targetInfoP.addField("Final odds for " + values.get(3) + " are:", Constants.dfMoney.format(odds2) + " to 1", true); 
		    	                    
		    	                    targetInfoP.addField("You chose " + values.get(1 + outcomeNum) + " correctly, meaning you won:","$" + Constants.dfMoney.format(payout) + "!", false); 
		                            
		    	                    targetInfoP.addField("Your Money: ", "$" + Constants.dfMoney.format(Float.parseFloat(values2.get(0))) + " --> $" + Constants.dfMoney.format((Float.parseFloat(values2.get(0)) + payout)), false); 
		    	                    
		                            target.getUser().openPrivateChannel().queue(channel -> 
		                            {
		                            	channel.sendMessage(targetInfoP.build()).queue();
		                            });
		    					}
		    				}
		    				else
		    				{
		    					System.out.println("Could not find user: " + tempTarget[0]);
		    				}
		    			}
		    			
		    			List<String[]> finalEarners = new ArrayList<String[]>();
		    			
		    			while(earners.size() > 0)
		    			{
		    				float largest = -9999;
		    				int index = 0;
		    				
		    				for(int ii = 0; ii < earners.size(); ii++)
		    				{	
		    					if(Float.parseFloat(earners.get(ii)[1]) > largest)
		    					{
		    						largest = Float.parseFloat(earners.get(ii)[1]);
		    						index = ii;
		    						
		    						System.out.println(earners.get(ii)[1]);
		    						System.out.println(earners.get(index)[1]);
		    						
		    						String[] temp = {earners.get(index)[0], earners.get(index)[1]};
				    				finalEarners.add(temp);
				    				
				    				earners.remove(index);
		    					}
		    				}
		    			}
		    			
		    			EmbedBuilder targetInfo = new EmbedBuilder();
		    			targetInfo.setTitle(event.getAuthor().getName() + " has determined the outcome for Bet#" + betID + "!")
	                    	.setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
	                    	.setThumbnail(event.getAuthor().getEffectiveAvatarUrl())
	                    	.setDescription("Bet #" + betID + ": " + values.get(1));

		    			targetInfo.addField(values.get(1 + outcomeNum) + " was chosen as correct!", "", false); 
		    			
	                    targetInfo.addField("Final Pot for " + values.get(2), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(4)))), true);
	                    targetInfo.addField("Final Pot for " + values.get(3), "$" + Constants.dfMoney.format((Float.parseFloat(values.get(5)))), true);
		    				
	                    targetInfo.addField("Final odds for " + values.get(2) + " are:", Constants.dfMoney.format(odds1) + " to 1", false);
	                    targetInfo.addField("Final odds for " + values.get(3) + " are:", Constants.dfMoney.format(odds2) + " to 1", true); 
	                    
	                    if(finalEarners.size() > 0)
	                    {
		                    targetInfo.addField("Top earners:", "", false); 
		                    
		                    for(int ii = 0; ii < finalEarners.size(); ii++)
		                	{
		                		if(ii == 3)
		                		{
		                			break;
		                		}
		                		else if(ii == 0)
		                		{
		                			targetInfo.addField("🥇1:  " + finalEarners.get(0)[0],"$" + Constants.dfMoney.format(Float.parseFloat(finalEarners.get(0)[1])), false);
		                		}
		                		else if(ii == 1)
		                		{
		                			targetInfo.addField("🥈2:  " + finalEarners.get(1)[0], "$" + Constants.dfMoney.format(Float.parseFloat(finalEarners.get(1)[1])), false);
		                		}
		                		else if(ii == 2)
		                		{
		                			targetInfo.addField("🥉3: " + finalEarners.get(2)[0], "$" + Constants.dfMoney.format(Float.parseFloat(finalEarners.get(2)[1])), false);
		                		}
		                	}
	                    }
   
	                    event.getChannel().sendMessage(targetInfo.build()).queue();
	                    
	                    File file = new File("Bets/Bet#" + betID + ".txt"); 
	                    file.delete();
		    		}
		    		else
		    		{
		    			event.getChannel().sendMessage("The betting for this bet has not been closed yet!").queue();
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
        return "payoutbet";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "declare the outcome of an existing bet!\nUsage: `" + Constants.BotPrefix + getCommand() + " <BetID>, <Outcome# that won>`";
    }
}
