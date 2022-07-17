package family.kata.discord.bot.config.chain;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public interface BotConfigurationChain {
    void performStage(JDA jda);
}
