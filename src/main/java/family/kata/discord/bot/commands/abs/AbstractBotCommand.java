package family.kata.discord.bot.commands.abs;

import family.kata.discord.bot.commands.CommandOptions;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public abstract class AbstractBotCommand implements BotCommand {
    protected String name;
    protected String description;
    protected List<CommandOptions> commandOptionsList;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<CommandOptions> getCommandOptions() {
        return this.commandOptionsList;
    }

    @Override
    abstract public void onCommand(SlashCommandInteractionEvent event);
}
