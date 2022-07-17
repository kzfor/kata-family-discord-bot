package family.kata.discord.bot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@Getter
@AllArgsConstructor
public class CommandOptions {
    public OptionType optionType;
    public String name;
    public String description;
    public Boolean required;
}
