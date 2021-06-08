package DaltonChichester.HeyooSteveBot.commands;

import DaltonChichester.HeyooSteveBot.Command;
import DaltonChichester.HeyooSteveBot.Constants;
import DaltonChichester.HeyooSteveBot.Main;
import DaltonChichester.HeyooSteveBot.tools.Money;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MoneyLeaderboards implements Command 
{
    @Override
    public void run(List<String> args, GuildMessageReceivedEvent event) 
    {
    	List<String> leaderboards = Money.MoneyLeaderboards(event.getGuild());
    	
    	EmbedBuilder targetInfo = new EmbedBuilder()
    			.setTitle("Dollarydoos Leaderboards:");
    	
    	for(int i = 0; i < leaderboards.size(); i++)
    	{
    		if(i == 10)
    		{
    			break;
    		}
    		else if(i == 0)
    		{
    			List<Member> targetTemp = new ArrayList<Member>();
				targetTemp = event.getGuild().getMembersByName(leaderboards.get(0), false);
				Member target = targetTemp.get(0);
				float targetM = Float.parseFloat(Money.GetMoney(target).get(0));
				
    			targetInfo.addField("🥇1:  " + leaderboards.get(0),"$" + Constants.dfMoney.format(targetM), false);
    		}
    		else if(i == 1)
    		{
    			List<Member> targetTemp = new ArrayList<Member>();
				targetTemp = event.getGuild().getMembersByName(leaderboards.get(1), false);
				Member target = targetTemp.get(0);
				float targetM = Float.parseFloat(Money.GetMoney(target).get(0));
				
    			targetInfo.addField("🥈2:  " + leaderboards.get(1), "$" + Constants.dfMoney.format(targetM), false);
    		}
    		else if(i == 2)
    		{
    			List<Member> targetTemp = new ArrayList<Member>();
				targetTemp = event.getGuild().getMembersByName(leaderboards.get(2), false);
				Member target = targetTemp.get(0);
				float targetM = Float.parseFloat(Money.GetMoney(target).get(0));
				
    			targetInfo.addField("🥉3: " + leaderboards.get(2), "$" + Constants.dfMoney.format(targetM), false);
    		}
    		else
    		{
    			List<Member> targetTemp = new ArrayList<Member>();
				targetTemp = event.getGuild().getMembersByName(leaderboards.get(i), false);
				Member target = targetTemp.get(0);
				float targetM = Float.parseFloat(Money.GetMoney(target).get(0));
				
    			targetInfo.addField((i+1) + ": " + leaderboards.get(i), "$" + Constants.dfMoney.format(targetM), false);
    		}
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
        return "moneytop";
    }
    
    @Override
    public String getPCommand() 
    {
        return "";
    }

    @Override
    public String getHelp() 
    {
        return "Check to see the top 10 money holders!\nUsage: `" + Constants.BotPrefix + getCommand()  + " <@User>";
    }
}
