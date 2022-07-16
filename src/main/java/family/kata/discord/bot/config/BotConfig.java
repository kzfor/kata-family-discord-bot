package family.kata.discord.bot.config;

import family.kata.discord.bot.config.chain.BotConfigurationChain;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@Order
public class BotConfig {
    private final String botToken;
    private final List<EventListener> listeners;

    private final List<BotConfigurationChain> botConfigurationChain;

    public BotConfig(@Value("${bot.token}") String botToken,
                     List<EventListener> listeners,
                     List<BotConfigurationChain> botConfigurationChain) {
        this.botToken = botToken;
        this.listeners = listeners;
        this.botConfigurationChain = botConfigurationChain;
    }

    @Bean
    public void botBuilder() {
        JDABuilder jdaBuilder = JDABuilder.createDefault(botToken);
        listeners.forEach(jdaBuilder::addEventListeners);
        try {
            JDA jda = jdaBuilder.build().awaitReady();
            botConfigurationChain.forEach((it) -> it.performStage(jda));
        } catch (LoginException | InterruptedException ignored) {
            // TODO add retrying
        }
    }
}
