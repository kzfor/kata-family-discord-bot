package family.kata.discord.bot.listeners;

import family.kata.discord.bot.commands.abs.BotCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlashCommandListener extends ListenerAdapter {

    private final List<BotCommand> botCommands;

    public SlashCommandListener(List<BotCommand> botCommands) {
        this.botCommands = botCommands;
    }
    private final Logger logger
            = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        logger.info("Got slash command \"{}\" in channel {}", event.getName(), event.getGuild().getName());
        botCommands.forEach((it) -> {
            if (it.getName().equals(event.getName())) {
                it.onCommand(event);
            }
        });
    }
}
