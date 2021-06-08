package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public class EightBall implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
    	if(!args.isEmpty()) 
        {
	        String[] replies = {"Yes!", "No!", "Maybe...", "Probably", "Probably not...", "Absolutely!", "Sure!", "Definitely not!", "false", "true"};
	        event.getChannel().sendMessage(new EmbedBuilder()
	                .setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
	                .addField(String.join(" ", args), replies[(int)(Math.random()*replies.length)], true)
	                .build()
	        ).queue();
        }
    	else
    	{
    		Tools.wrongUsage(event.getChannel(), this);
    	}
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {
    	if(!args.isEmpty()) 
        {
	        String[] replies = {"Yes!", "No!", "Maybe...", "Probably", "Probably not...", "Absolutely!", "Sure!", "Definitely not!", "false", "true"};
	        event.getChannel().sendMessage(new EmbedBuilder()
	                .setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
	                .addField(String.join(" ", args), replies[(int)(Math.random()*replies.length)], true)
	                .build()
	        ).queue();
	    }
		else
		{
			Tools.wrongUsage(event.getChannel(), this);
		}
    }

    @Override
    public String getCommand() 
    {
        return "8ball";
    }
    
    @Override
    public String getPCommand() 
    {
        return "8ball";
    }

    @Override
    public String getHelp() 
    {
        return "Ask a question and receive the answer!\nUsage: `" + Constants.BotPrefix + getCommand() + " <question>`";
    }
}
