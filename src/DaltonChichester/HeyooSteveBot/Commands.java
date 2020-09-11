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

        System.out.println("We received a chat message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
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
	            event.getChannel().sendMessage(":regional_indicator_s: :regional_indicator_l: :regional_indicator_a: :regional_indicator_m: :regional_indicator_s:  :regional_indicator_t: :regional_indicator_a: :regional_indicator_b: :regional_indicator_l: :regional_indicator_e:").queue();
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "info"))
		{
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Heyooo Steve Bot Information");
			info.setDescription("The official Discord Bot for the Heyoo Thunder Channel.");
			info.addField("Commands", "~info  =  help on commands and additional information.\n"
									+ "~getID  =  gets your official Discord userID.\n" 
									+ "~spam {@user} {amount}  =  pings user specified with the specified amount of times.\n" 
									, false);
			info.setColor(0xf45642);
			info.setFooter("Created by Captain_3D");
			
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			
			info.clear();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "getID"))
		{
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Your UserID is: " + event.getAuthor().getId()).queue();
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "spam"))
		{
			for(int i = 0; i < Integer.parseInt(args[2]); i++)
			{
				event.getChannel().sendTyping().queue();
	            event.getChannel().sendMessage(args[1]).queue();
			}
			
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "ping"))
		{
			event.getChannel().sendTyping().queue();
	        event.getChannel().sendMessage("pong").queue();
			
		}
		
		if(args[0].equalsIgnoreCase(Main.prefix + "tracker"))
		{
			if(event.getAuthor().getName() == "Captain_3D")
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
					float temp4 = ((((tokens_required - mission_tokes) - tokens_current) / days_left) * days_past) - ((wins * tokens_per_Win)  + (losses * tokens_per_Lose));

					System.out.println("Sudo Avg Wins needed for overall day quota: " + temp1 / tokens_per_Win);
					System.out.println("Calculated Avg Wins needed for overall day quota: " + temp2 / tokens_per_Win);
					System.out.println("");
					System.out.println("Calculated Wins needed for todays quota: " + temp3 / tokens_per_Win);
					System.out.println("Calculated Wins needed for occured quota: " + temp4 / tokens_per_Win);
					
					EmbedBuilder tracker = new EmbedBuilder();
					tracker.setTitle(event.getAuthor().getName() + " Prestige Tracker");
					tracker.setDescription("Tracks and stores data for tokens required.");
					tracker.addField("Data", "tokens_current: " + tokens_current
											+ "\ntokens_left: " + (tokens_required - tokens_current) 
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "~Tracker_NewDay"))
		{
			if(event.getAuthor().getName() == "Captain_3D")
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
						fWriter = new FileWriter("filename.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[i] + "\n");
						}
						fWriter.close();
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "~Tracker_addWin"))
		{
			if(event.getAuthor().getName() == "Captain_3D")
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
						fWriter = new FileWriter("filename.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[i] + "\n");
						}
						fWriter.close();
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
		
		if(args[0].equalsIgnoreCase(Main.prefix + "~Tracker_addLoss"))
		{
			if(event.getAuthor().getName() == "Captain_3D")
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
						fWriter = new FileWriter("filename.txt");
						
						for(int j = 0; j < 5; j++)
						{
							fWriter.write(temp[i] + "\n");
						}
						fWriter.close();
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
