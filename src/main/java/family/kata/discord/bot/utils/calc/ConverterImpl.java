package family.kata.discord.bot.utils.calc;

import family.kata.discord.bot.utils.calc.abs.Converter;
import family.kata.discord.bot.utils.calc.exceptions.ConverterException;
import org.springframework.stereotype.Component;

@Component
public class ConverterImpl implements Converter {
    private final String[] romanOnes = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"
    };

    private final String[] romanTens = {
            "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"
    };

    @Override
    public String convertFromIntToRoman(Integer integer) {
        if (integer <= 0 || integer > 100) {
            throw new ConverterException(String.format("Калькулятор римских цифр не поддерживает число %d", integer));
        }
        String result = "";
        int ones = integer % 10;
        int tens = 0;
        if (integer > 10) {
            tens = integer / 10;
        }
        if (tens > 0) {
            result = romanTens[--tens];
        }
        if (ones > 0) {
            result = result.concat(romanOnes[Math.floorMod(--ones, 10)]);
        }
        return result;
    }

    @Override
    public Integer convertFromRomanToInt(String roman) {
        for (int i = 0; i < romanOnes.length; i++) {
            if (romanOnes[i].equals(roman)) {
                return ++i;
            }
        }
        throw new ConverterException(String.format("На вход дана неподдерживаемая римская цифра %s", roman));
    }
}
