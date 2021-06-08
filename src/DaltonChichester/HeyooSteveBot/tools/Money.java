package DaltonChichester.HeyooSteveBot.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class Money 
{
	public static List<String> GetMoney(Member target)
    {
		LocalDateTime now = LocalDateTime.now();
		
		if(target.getUser().isBot())
        {
            return null;
        }
		
		List<String> returner = new ArrayList<String>();
		
		try 
		{
			returner = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
		} 
		catch (IOException e) 
		{
			FileWriter fWriter;
			try 
			{
				fWriter = new FileWriter("Users_Money/" + target.getUser().getName() + "_Money.txt", true);
			
				fWriter.write( 0 + "\n");
				fWriter.write( 0 + "\n");
				fWriter.write(now + "\n");

				fWriter.close();
			} 
			catch (Exception e1) 
			{
			}
			
			try 
			{
				returner = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return returner;
    }
	
	public static void ChangeMoney(Member target, float f, int menthod)
    {
		if(target.getUser().isBot())
        {
            return;
        }
		
		if(menthod == 0)
		{
			List<String> values = new ArrayList<String>();
			float newVal1 = 0;
			float newVal2 = 0;
			LocalDateTime now = LocalDateTime.now();
			
			try 
			{
				values = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
			} 
			catch (IOException e) 
			{
				FileWriter fWriter;
				try 
				{
					fWriter = new FileWriter("Users_Money/" + target.getUser().getName() + "_Money.txt", true);
				
					fWriter.write(0 + "\n");
					fWriter.write(0 + "\n");
					fWriter.write(now + "\n");
	
					fWriter.close();
				} 
				catch (Exception e1) 
				{
				}
				
				try 
				{
					values = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(now.isAfter(LocalDateTime.parse(values.get(2)).plusMinutes(1)))
			{
				FileWriter fWriter;
				try 
				{
					fWriter = new FileWriter("Users_Money/" + target.getUser().getName() + "_Money.txt", false);
						
					newVal1 = (f + Float.parseFloat(values.get(0)));
					
					if(f > 0.0)
					{
						newVal2 = (f + Float.parseFloat(values.get(1)));
					}
						
					fWriter.write(newVal1 + "\n");
					fWriter.write(newVal2 + "\n");
					fWriter.write(now + "\n");
			
					fWriter.close();
				}
				catch (Exception e) 
				{
				}
			}
		}
		else
		{
			List<String> values = new ArrayList<String>();
			float newVal1 = 0;
			float newVal2 = 0;
			LocalDateTime now = LocalDateTime.now();
			
			try 
			{
				values = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
			} 
			catch (IOException e) 
			{
				FileWriter fWriter;
				try 
				{
					fWriter = new FileWriter("Users_Money/" + target.getUser().getName() + "_Money.txt", true);
				
					fWriter.write(0 + "\n");
					fWriter.write(0 + "\n");
					fWriter.write(now.minusMinutes(1) + "\n");
	
					fWriter.close();
				} 
				catch (Exception e1) 
				{
				}
				
				try 
				{
					values = Files.readAllLines(Paths.get("Users_Money/" + target.getUser().getName() + "_Money.txt"));
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			FileWriter fWriter;
			try 
			{
				fWriter = new FileWriter("Users_Money/" + target.getUser().getName() + "_Money.txt", false);
					
				newVal1 = (f + Float.parseFloat(values.get(0)));
					
				if(f > 0.0)
				{
					newVal2 = (f + Float.parseFloat(values.get(1)));
				}
						
				fWriter.write(newVal1 + "\n");
				fWriter.write(newVal2 + "\n");
				fWriter.write(now.minusMinutes(1) + "\n");
			
				fWriter.close();
			}
			catch (Exception e) 
			{
			}
		}
    }
	
	public static List<String> MoneyLeaderboards(Guild guild)
    {
		List<String> returner = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		Set<String> files = listFilesUsingJavaIO("Users_Money/");
		
		Iterator<String> filesI = files.iterator();
		
		while(filesI.hasNext())
		{
			String username = filesI.next();
			username = username.replace("_Money.txt","");
			temp.add(username);
			
			//System.out.println(filesI.next());
		}
		
		while(temp.size() > 0)
		{
			float largest = -9999;
			int index = 0;
			
			for(int ii = 0; ii < temp.size(); ii++)
			{
				List<Member> targetTemp = new ArrayList<Member>();
				targetTemp = guild.getMembersByName(temp.get(ii), false);	
				
				Member target = targetTemp.get(0);
				float targetM = Float.parseFloat(GetMoney(target).get(0));
				
				if(targetM > largest)
				{
					largest = targetM;
					index = ii;
				}
			}
			
			returner.add(temp.get(index));
			temp.remove(index);
		}
		
		return returner;
    }
	
	public static Set<String> listFilesUsingJavaIO(String dir) 
	{
	    return Stream.of(new File(dir).listFiles())
	      .filter(file -> !file.isDirectory())
	      .map(File::getName)
	      .collect(Collectors.toSet());
	}
}
