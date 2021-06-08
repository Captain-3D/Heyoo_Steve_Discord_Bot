package DaltonChichester.HeyooSteveBot;

import java.util.Scanner;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class Echo
{
	public void echo(JDA jda)
    {
		Scanner scanner = new Scanner(System.in);
		String message = "";
		
		while(!message.equals("QUIT"))
		{
			System.out.println("Echoer is ready!");
			System.out.print(">");
			
			message = scanner.nextLine();
			
			System.out.println("Message reads: " + message);
			
			TextChannel textChannel = jda.getGuildById("359741409747795969").getTextChannelsByName("bot-spam", true).get(0);
	    	textChannel.sendTyping().queue();
	        textChannel.sendMessage(message).queue();
	        System.out.println("Sent message");
		}
    }
}
