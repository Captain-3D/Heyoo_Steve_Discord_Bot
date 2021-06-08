package DaltonChichester.HeyooSteveBot;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import DaltonChichester.HeyooSteveBot.tools.SongOfTheDay;
import DaltonChichester.HeyooSteveBot.tools.Money;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

public class Listener extends ListenerAdapter {

    public final Manager m = new Manager();

    @Override
    public void onReady(@Nonnull ReadyEvent event)
    {
        System.out.println(event.getJDA().getSelfUser().getName() + " is online!");
        TextChannel textChannel = event.getJDA().getGuildById("359741409747795969").getTextChannelsByName("bot-spam", true).get(0);
    	//textChannel.sendTyping().queue();
    	//textChannel.sendMessage(event.getJDA().getSelfUser().getName() + " is online!").queue();
    	
    	//SongOfTheDay.SongOfTheDayChecker(event);
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) 
    {
        if(event.getMessage().getContentRaw().equalsIgnoreCase(Constants.BotPrefix+"shutdown") && event.getAuthor().getIdLong()==Constants.OWNERID) 
        {
        	TextChannel textChannel = event.getJDA().getGuildById("359741409747795969").getTextChannelsByName("bot-spam", true).get(0);
        	textChannel.sendTyping().queue();
        	textChannel.sendMessage(event.getJDA().getSelfUser().getName() + " is shutting down!").queue();
        	
            event.getJDA().shutdown();
            System.exit(0);
        }
        
        Money.ChangeMoney(event.getMember(), 1, 0);
        
        m.run(event);
    }
    
    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event)
    {
    	if(event.getAuthor().isBot())
        {
            return;
        }
  
    	System.out.println("Received private message from " + event.getAuthor() + ", saying: " + event.getMessage().getContentRaw());
    	m.runP(event);
    }
}