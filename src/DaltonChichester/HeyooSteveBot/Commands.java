package DaltonChichester.HeyooSteveBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		if(event.getAuthor().isBot())
        {
            return;
        }

        System.out.println("We received a message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        String[] args = event.getMessage().getContentRaw().split("\\s");
        
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
        
        if(event.getMessage().getContentRaw().equalsIgnoreCase("heyooo"))
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "info"))
		{
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Heyooo Steve Bot Information");
			info.setDescription("The official Discord Bot for the Heyoo Thunder Channel.");
			info.addField("Commands", "~info  =  help on commands and additional information.\n"
									+ "~getID  =  gets your official Discord userID.\n" 
									+ "~spam {message} {amount}  =  spams the message with the specified amount of times.\n" 
									+ "~tracker =  Authorised users only, shows calculations for prestige token tracker.\n" 
									, false);
			info.setColor(0xf45642);
			info.setFooter("Created by Captain_3D");
			
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			
			info.clear();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "leaderboards"))
		{
			if(args.length < 2)
			{
				EmbedBuilder info = new EmbedBuilder();
				info.setTitle("Heyooo Steve Bot Leaderboards");
				info.setDescription("The official leaderboards for friend competition.");
				info.addField("Games", "Sonic: The Hedgehog\n", false);
				info.addField("Commands", "~leaderboards [Game]\tTo view Games leaderboards.\n", false);
				info.setColor(0xf45642);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(info.build()).queue();
				
				info.clear();
			}
			else if(args[1].equalsIgnoreCase("help"))
			{
				EmbedBuilder info = new EmbedBuilder();
				info.setTitle("Heyooo Steve Bot Leaderboards");
				info.setDescription("The official leaderboards for friend competition.");
				info.addField("Games", "Sonic: The Hedgehog\n", false);
				info.addField("Commands", "~leaderboards [Game]\tTo view Games leaderboards.\n", false);
				info.setColor(0xf45642);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(info.build()).queue();
				
				info.clear();
			}
			else if(args[1].equalsIgnoreCase("Sonic"))
			{
				EmbedBuilder info = new EmbedBuilder();
				info.setTitle("Sonic: The Hedgehog Leaderboards");
				info.setDescription("The official leaderboards for friend competition.");
				info.addField("Levels", "1-1", false);
				info.setColor(0xf45642);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(info.build()).queue((message) -> {
					message.addReaction("🗑").queue();
					message.addReaction("⏪").queue();
					message.addReaction("⏩").queue();
				});
				
				info.clear();
			}
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "getID"))
		{
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Your UserID is: " + event.getAuthor().getId()).queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "spam"))
		{
			try
			{
				for(int i = 0; i < Integer.parseInt(args[args.length -1]); i++)
				{
					String string = "";
					event.getChannel().sendTyping().queue();
	
		            for(int j = 1; j < args.length-1; j++)
		            {
		            	string = string + " " +  args[j];
		            }
		            
		            event.getChannel().sendMessage(string).queue();
		        }
			}
			catch(NumberFormatException e)
			{
				for(int i = 0; i < 5; i++)
				{
					String string = "";
					event.getChannel().sendTyping().queue();
	
		            for(int j = 1; j < args.length; j++)
		            {
		            	string = string + " " +  args[j];
		            }
		            
		            event.getChannel().sendMessage(string).queue();
		        }
			}
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "DecToHec"))
		{
		    int num = Integer.parseInt(args[1]);
		        
		    String str = "Decimal to Hexadecimal: ";
		    str = str + Integer.toHexString(num);
		      
			event.getChannel().sendTyping().queue();
	        event.getChannel().sendMessage(str).queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "HecToDec"))
		{
			String digits = "0123456789ABCDEF";
		    String s = args[1];
		        
		    String str = "Hexadecimal to Decimal: ";
		      
		    s = s.toUpperCase();
            int val = 0;
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                int d = digits.indexOf(c);
                val = 16*val + d;
            }
		    
            str = str + String.valueOf(val);
            
			event.getChannel().sendTyping().queue();
	        event.getChannel().sendMessage(str).queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "ping"))
		{
			event.getChannel().sendTyping().queue();
	        event.getChannel().sendMessage("pong!").queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "tracker"))
		{
			if(event.getAuthor().getId().equals("205312322527297537"))
			{
		        float tokens_required = 2000;
		        float tokens_current = 200;
		        float tokens_per_Win = 10;
		        float tokens_per_Lose = 5;
		        float mission_tokes = 1000;
		        
		        float days_left = 0;
				float days_past;
				float wins = 0;
				float losses = 0;	
				float todays_wins = 0;
				float todays_losses = 0;
		        
		        try 
		        {
					Scanner scanner = new Scanner(new File("TrackerData.txt"));
					String[] temp = {"", "", "", "", ""};
					int i = 0;
					
					while(scanner.hasNextLine()) 
					{
						temp[i] = scanner.nextLine();
				        i++;
				    }
					
					days_left = Integer.parseInt(temp[0]);
					wins = Integer.parseInt(temp[1]);
					losses = Integer.parseInt(temp[2]);
					todays_wins = Integer.parseInt(temp[3]);
					todays_losses = Integer.parseInt(temp[4]);
					days_past = 26 - days_left;
					
					tokens_current = tokens_current + ((wins * tokens_per_Win) + (losses * tokens_per_Lose));
					
					float temp1 = ((tokens_required - mission_tokes) / days_left);
					float temp2 = (((tokens_required - mission_tokes) - tokens_current) / days_left);
					float temp3 = (((tokens_required - mission_tokes) - tokens_current) / days_left) - ((todays_wins * tokens_per_Win) + (todays_losses * tokens_per_Lose));
					float temp4 = (((((tokens_required - mission_tokes) - tokens_current) / 26) * days_past) - ((wins * tokens_per_Win)  + (losses * tokens_per_Lose)));

					System.out.println("Sudo Avg Wins needed for overall day quota: " + temp1 / tokens_per_Win);
					System.out.println("Calculated Avg Wins needed for overall day quota: " + temp2 / tokens_per_Win);
					System.out.println("");
					System.out.println("Calculated Wins needed for todays quota: " + temp3 / tokens_per_Win);
					System.out.println("Calculated Wins needed for occured quota: " + temp4 / tokens_per_Win);
					
					EmbedBuilder tracker = new EmbedBuilder();
					tracker.setTitle(event.getAuthor().getName() + " Prestige Tracker");
					tracker.setDescription("Tracks and stores data for tokens required.");
					tracker.addField("Data", "tokens_current: " + tokens_current
											+ "\ntokens_left: " + ((tokens_required - tokens_current) - mission_tokes) 
											+ "\ndays_left: " + days_left + "\n"
											, false);
					tracker.addField("Calculations", "Sudo Avg Wins needed for overall day quota: " + temp1 / tokens_per_Win
											+ "\nCalculated Avg Wins needed for overall day quota: " + temp2 / tokens_per_Win
											+ "\nCalculated Wins needed for todays quota: " + temp3 / tokens_per_Win
											+ "\nCalculated Wins needed for occured quota: " + temp4 / tokens_per_Win + "\n"
											, false);	
					tracker.addField("Commands", "~Tracker_NewDay\t=\tprogress a day.\n"
											+ "~Tracker_addWin\t=\tadds Win.\n"
											+ "~Tracker_addLoss\t=\tadds Loss.\n"
											, false);
					
					tracker.setColor(0xf45642);

					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(tracker.build()).queue();
					
					tracker.clear();
				} 
		        catch (FileNotFoundException e) 
		        {
		        	event.getChannel().sendTyping().queue();
			        event.getChannel().sendMessage("ERROR: FileNotFoundEception, Cound not find \"TrackerData.txt\"").queue();
				}
			}
			else
			{
				event.getChannel().sendTyping().queue();
		        event.getChannel().sendMessage("Unauthorised user").queue();
			}
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "Tracker_NewDay"))
		{
			if(event.getAuthor().getId().equals("205312322527297537"))
			{
		        Scanner scanner;
				try 
				{
					File file = new File("TrackerData.txt");
					scanner = new Scanner(file);
					
					String[] temp = {"", "", "", "", ""};
					int i = 0;
					
					while(scanner.hasNextLine()) 
					{
						temp[i] = scanner.nextLine();
				        i++;
				    }
					
					file.delete();
					
					temp[0] = String.valueOf(Integer.parseInt(temp[0])- 1);
					temp[3] = "0";
					temp[4] = "0";
					
					FileWriter fWriter;
					try 
					{
						fWriter = new FileWriter("TrackerData.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[j] + "\n");
						}
						fWriter.close();
						
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("Successfully moved to new day").queue();
					} 
					catch (IOException e) 
					{
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("ERROR: IOException").queue();
					}
				} 
				catch (FileNotFoundException e) 
				{
					event.getChannel().sendTyping().queue();
			        event.getChannel().sendMessage("ERROR: FileNotFoundEception, Cound not find \"TrackerData.txt\"").queue();
				}
			}
			else
			{
				event.getChannel().sendTyping().queue();
		        event.getChannel().sendMessage("Unauthorised user").queue();
				
			}
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "Tracker_addWin"))
		{
			if(event.getAuthor().getId().equals("205312322527297537"))
			{
				Scanner scanner;
				try 
				{
					File file = new File("TrackerData.txt");
					scanner = new Scanner(file);
					
					String[] temp = {"", "", "", "", ""};
					int i = 0;
					
					while(scanner.hasNextLine()) 
					{
						temp[i] = scanner.nextLine();
				        i++;
				    }
					
					file.delete();
					
					temp[1] = String.valueOf(Integer.parseInt(temp[1])+ 1);
					temp[3] = String.valueOf(Integer.parseInt(temp[3])+ 1);
					
					FileWriter fWriter;
					try 
					{
						fWriter = new FileWriter("TrackerData.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[j] + "\n");
						}
						fWriter.close();
						
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("Successfully added win").queue();
					} 
					catch (IOException e) 
					{
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("ERROR: IOException").queue();
					}
				} 
				catch (FileNotFoundException e) 
				{
					event.getChannel().sendTyping().queue();
			        event.getChannel().sendMessage("ERROR: FileNotFoundEception, Cound not find \"TrackerData.txt\"").queue();
				}
			}
			else
			{
				event.getChannel().sendTyping().queue();
		        event.getChannel().sendMessage("Unauthorised user").queue();
			}
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "Tracker_addLoss"))
		{
			if(event.getAuthor().getId().equals("205312322527297537"))
			{
				Scanner scanner;
				try 
				{
					File file = new File("TrackerData.txt");
					scanner = new Scanner(file);
					
					String[] temp = {"", "", "", "", ""};
					int i = 0;
					
					while(scanner.hasNextLine()) 
					{
						temp[i] = scanner.nextLine();
				        i++;
				    }
					
					file.delete();
					
					temp[2] = String.valueOf(Integer.parseInt(temp[2])+ 1);
					temp[4] = String.valueOf(Integer.parseInt(temp[4])+ 1);
					
					FileWriter fWriter;
					try 
					{
						fWriter = new FileWriter("TrackerData.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[j] + "\n");
						}
						fWriter.close();
						
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("Successfully added loss").queue();
						
					} 
					catch (IOException e) 
					{
						event.getChannel().sendTyping().queue();
				        event.getChannel().sendMessage("ERROR: IOException").queue();
					}
				} 
				catch (FileNotFoundException e) 
				{
					event.getChannel().sendTyping().queue();
			        event.getChannel().sendMessage("ERROR: FileNotFoundEception, Cound not find \"TrackerData.txt\"").queue();
				}
			}
			else
			{
				event.getChannel().sendTyping().queue();
		        event.getChannel().sendMessage("Unauthorised user").queue();
			}
		}
	}
}
