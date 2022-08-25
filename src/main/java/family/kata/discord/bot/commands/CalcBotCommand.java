package family.kata.discord.bot.commands;

import family.kata.discord.bot.commands.abs.AbstractBotCommand;
import family.kata.discord.bot.utils.calc.abs.Calculator;
import family.kata.discord.bot.utils.calc.exceptions.ConverterException;
import family.kata.discord.bot.utils.calc.exceptions.InvalidInputException;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CalcBotCommand extends AbstractBotCommand {

    private final Calculator calculator;

    CalcBotCommand(Calculator calculator) {
        this.calculator = calculator;
        this.name = "calc";
        this.description = "Великий и ужасный калькулятор";
        this.commandOptionsList = Arrays.asList(
                new CommandOptions(OptionType.STRING, "x1", "Первый аргумент", true),
                new CommandOptions(OptionType.STRING, "operator", "Оператор", true),
                new CommandOptions(OptionType.STRING, "x2", "Первый аргумент", true)
        );
    }

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        List<OptionMapping> options = event.getOptions();
        String x1 = options.get(0).getAsString();
        String operator = options.get(1).getAsString();
        String x2 = options.get(2).getAsString();
        String replyMessage;
        try {
            replyMessage = calculator.calc(x1, operator, x2);
        } catch (ConverterException | InvalidInputException exception) {
            replyMessage = exception.getMessage();
        }
        if (replyMessage.equals("300")) {
            replyMessage = ":tractor:";
        }
        event.reply(replyMessage).queue();
    }
}
