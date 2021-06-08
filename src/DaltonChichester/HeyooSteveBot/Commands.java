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
        
        if(event.getMessage().getContentRaw().contains("wack"))
        {
        	event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("*slams the table*").queue();
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
									+ "~getID  =  gets your official Discord userID.\n", false);
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
		

	}
}
