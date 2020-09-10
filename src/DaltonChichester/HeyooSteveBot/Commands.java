package DaltonChichester.HeyooSteveBot;

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

	}
}
