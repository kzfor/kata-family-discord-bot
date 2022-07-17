package family.kata.discord.bot.commands.abs;

import family.kata.discord.bot.commands.CommandOptions;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public interface BotCommand {
    String getName();
    String getDescription();
    List<CommandOptions> getCommandOptions();

    void onCommand(SlashCommandInteractionEvent event);
}
