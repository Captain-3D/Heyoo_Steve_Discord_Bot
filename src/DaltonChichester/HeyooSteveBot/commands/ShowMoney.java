package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.tools.Money;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public class ShowMoney implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
    	String id;
    	Member target;
    	
    	if(args.size() == 1) 
        {
    		id = args.get(0).replaceAll("<@", "").replaceAll("!", "").replaceAll(">", "");
            target = event.getGuild().getMemberById(id);
        } 
        else 
        {
        		target = (Member) event.getMember();
        }
    	
    	List<String> values = Money.GetMoney(target);
    	List<String> leaderboards = Money.MoneyLeaderboards(event.getGuild());
    	
    	float currentM = Float.parseFloat(values.get(0));
    	float totalM = Float.parseFloat(values.get(1));
    	int rank = 1 + leaderboards.indexOf(target.getUser().getName());
    	int outOfRank = leaderboards.size();
    	
    	EmbedBuilder targetInfo = new EmbedBuilder()
    			.setTitle("Money profile for " + target.getUser().getName())
                .setAuthor(target.getUser().getName(), target.getUser().getAvatarUrl(), target.getUser().getEffectiveAvatarUrl())
                .setThumbnail(target.getUser().getEffectiveAvatarUrl())
                .addField("Curent Money","$" + Constants.dfMoney.format(currentM), true)
                .addField("Total Money Cumulated","$" + Constants.dfMoney.format(totalM), false);
    	
    	if(rank == 1)
    	{
    		targetInfo.addField("Server Rank", "🥇#" + rank + " out of " + outOfRank, false);
    	}
    	else if(rank == 2)
    	{
    		targetInfo.addField("Server Rank", "🥈#" + rank + " out of " + outOfRank, false);
    	}
    	else if(rank == 3)
    	{
    		targetInfo.addField("Server Rank", "🥉#" + rank + " out of " + outOfRank, false);
    	}
    	else
    	{
    		targetInfo.addField("Server Rank", "#" + rank + " out of " + outOfRank, false);
    	}
    			
        event.getChannel().sendMessage(targetInfo.build()).queue();
    	
    }
    
    @Override
    public void runP(List<String> args, PrivateMessageReceivedEvent event) 
    {

    }

    @Override
    public String getCommand() 
    {
        return "money";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "Check to see how much money you, or someone else, currently have!\nUsage: `" + Constants.BotPrefix + getCommand()  + " <@User>";
    }
}
