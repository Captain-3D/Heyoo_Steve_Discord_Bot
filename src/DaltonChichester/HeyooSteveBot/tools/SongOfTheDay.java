package DaltonChichester.HeyooSteveBot.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;

public class SongOfTheDay
{	
	public static void SongOfTheDayChecker(ReadyEvent event)
    {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		List<String> allLines = null;
		List<String> dates = null;
		
		long waitTime = time.until(LocalTime.NOON, ChronoUnit.MINUTES);

		try 
		{
			dates = Files.readAllLines(Paths.get("SongsOfTheDayDates.txt"));
		} 
		catch (IOException e2) {
			//e2.printStackTrace();
		}
		
		if(!dates.get(dates.size() - 1).equals(date))
		{
			System.out.println(waitTime + " Minutes untill i post song of the day!");
			
			try 
			{
				allLines = Files.readAllLines(Paths.get("SongsOfTheDay.txt"));
		    }
			catch (IOException e)
			{
		        //e.printStackTrace();
		    }
			
			int randNum = (int)(Math.random()*(allLines.size()));
		
			String ChosenLine = allLines.get(randNum);
			String[] splitLine = ChosenLine.split(",");
			long ID = Long.parseLong(splitLine[0]);
			String song = splitLine[1];
			
			TextChannel textChannel = event.getJDA().getGuildById("359741409747795969").getTextChannelsByName("song-of-the-day", true).get(0);
			textChannel.sendMessage(new EmbedBuilder()
		            .setTitle("Song of the day for " + date)
		            .addField("Brought to you by " + event.getJDA().getUserById(ID).getName(), song , false)
		            .build()
		    ).queueAfter(waitTime, TimeUnit.MINUTES);
	
			FileWriter fWriter = null;
			
			allLines.remove(randNum);
			
			try 
			{
				fWriter = new FileWriter("SongsOfTheDay.txt");
			} 
			catch (IOException e1) 
			{
				//e1.printStackTrace();
			}
			
			try 
			{
				for(int i = 0; i < allLines.size(); i++)
				{
	
					fWriter.write(allLines.get(i) + "\n");
				}
				
				fWriter.close();
			} 
			catch (IOException e) 
			{
				//e.printStackTrace();
			}	
			
			try 
			{
				fWriter = new FileWriter("SongsOfTheDayDate.txt");
			} 
			catch (IOException e1) 
			{
				//e1.printStackTrace();
			}
			
			try 
			{
				fWriter.write(date.toString());
				
				fWriter.close();
			} 
			catch (IOException e) 
			{
				//e.printStackTrace();
			}	
		}
		else
		{
			System.out.println("Song of the day already posted for today!");
		}
    }
}
