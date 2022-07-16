package family.kata.discord.bot.config.chain;

import family.kata.discord.bot.commands.abs.BotCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCommandsStage implements BotConfigurationChain {
    private final List<BotCommand> botCommands;

    public AddCommandsStage(List<BotCommand> botCommands) {
        this.botCommands = botCommands;
    }

    @Override
    public void performStage(JDA jda) {
        botCommands.forEach((command) -> {
            SlashCommandData commandData = Commands.slash(
                    command.getName(),
                    command.getDescription()
            );
            if (command.getCommandOptions() != null) {
                command.getCommandOptions().forEach((option) -> {
                    commandData.addOption(
                            option.getOptionType(),
                            option.getName(),
                            option.getDescription(),
                            option.getRequired()
                    );
                });
            }
            jda.updateCommands().addCommands(commandData).queue();
        });
    }
}
