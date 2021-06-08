package DaltonChichester.HeyooSteveBot;

import java.util.Scanner;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListenerOLD implements EventListener
{
    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
        {
            System.out.println("API is ready!");
        	TextChannel textChannel = event.getJDA().getGuildById("359741409747795969").getTextChannelsByName("bot-spam", true).get(0);
        	textChannel.sendTyping().queue();
        	textChannel.sendMessage("Hey Everyone! I am now online!").queue();
        	
        	//echo(textChannel);
        }
    }
    
    public void echo(TextChannel textChannel)
    {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String message = "";
		
		while(!message.equals("QUIT"))
		{
			System.out.println("Echoer is ready!");
			System.out.print(">");
			
			message = scanner.nextLine();
			
			System.out.println("Message reads: " + message);
			
	    	textChannel.sendTyping().queue();
	        textChannel.sendMessage(message).queue();
	        System.out.println("Sent message");
		}
    }
}
