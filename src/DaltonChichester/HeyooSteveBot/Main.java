package DaltonChichester.HeyooSteveBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main 
{
	public static JDA jda;
	public static String prefix = "~";
	
	//Main Method
	public static void main(String[] args) throws LoginException, FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("Token.txt"));
		
		String data = null;
        String token = null;
		
		while (scanner.hasNextLine()) 
		{
	    	data = scanner.nextLine();
	        token = data.replace(" ", "");
	    }

		JDABuilder builder = new JDABuilder();
        builder.setToken(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("type ~info for help"));
        
        // Register listeners
        builder.addEventListeners(new Commands());
        //builder.addEventListeners(new Reactions());
        builder.build();
	}
}
