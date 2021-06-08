package DaltonChichester.HeyooSteveBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import DaltonChichester.HeyooSteveBot.Constants;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main 
{
	public static JDA jda;
	public static String prefix = "~";
	
	//Main Method
	public static void main(String[] args) throws LoginException, FileNotFoundException, InterruptedException
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
		
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		builder.enableIntents(GatewayIntent.GUILD_PRESENCES);
		builder.enableIntents(GatewayIntent.GUILD_VOICE_STATES);
		builder.setChunkingFilter(ChunkingFilter.ALL);
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableCache(CacheFlag.ACTIVITY);
        builder.enableCache(CacheFlag.VOICE_STATE);
		
        builder.setToken(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("type " + Constants.BotPrefix + "help for help"));
        
        // Register listeners
        builder.addEventListeners(new Listener());
        
        builder.build();
	}
}
