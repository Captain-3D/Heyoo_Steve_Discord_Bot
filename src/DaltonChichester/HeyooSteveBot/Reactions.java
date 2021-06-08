package DaltonChichester.HeyooSteveBot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Reactions extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		if(!event.getAuthor().isBot())
        {
            return;
        }
		
		event.getMessage().addReaction("🗑").queue();
	}
}
