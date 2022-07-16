package family.kata.discord.bot.utils.calc;


import family.kata.discord.bot.utils.calc.abs.Calculator;
import family.kata.discord.bot.utils.calc.abs.Converter;
import family.kata.discord.bot.utils.calc.exceptions.ConverterException;
import family.kata.discord.bot.utils.calc.exceptions.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class Calc implements Calculator {

    private final static String INVALID_ARGUMENTS_MESSAGE = "Аргументы введены неверно.";
    private final static String UNKNOWN_OPERATOR_MESSAGE = "Введен неизвестный оператор.";
    private final Converter converter;

    public Calc(Converter converter) {
        this.converter = converter;
    }
    @Override
    public String calc(String x1, String operator, String x2) {
        InputType inputType = determineInputType(x1);
        InputType secondArgumentInputType = determineInputType(x2);
        if (inputType != secondArgumentInputType || inputType == InputType.UNKNOWN) {
            throw new InvalidInputException(INVALID_ARGUMENTS_MESSAGE);
        }
        Integer firstArg = inputType == InputType.ROMAN ? converter.convertFromRomanToInt(x1)
                                                         : Integer.parseInt(x1);
        Integer secondArg = inputType == InputType.ROMAN ? converter.convertFromRomanToInt(x2)
                                                         : Integer.parseInt(x2);
        CalcOperator op = determineOperator(operator);
        int calculationResult = 0;
        switch (op) {
            case ADD:
                calculationResult = firstArg + secondArg;
                break;
            case SUB:
                calculationResult = firstArg - secondArg;
                break;
            case MUL:
                calculationResult = firstArg * secondArg;
                break;
            case DIV:
                calculationResult = firstArg / secondArg;
                break;
        }
        return inputType == InputType.ARABIC ? String.valueOf(calculationResult)
                                                      : converter.convertFromIntToRoman(calculationResult);
    }

    private InputType determineInputType(String x) {
        try {
            Integer.parseInt(x);
            return InputType.ARABIC;
        } catch (NumberFormatException numberFormatException) {
            try {
                converter.convertFromRomanToInt(x);
                return InputType.ROMAN;
            } catch (ConverterException converterException) {
                return InputType.UNKNOWN;
            }
        }
    }

    private CalcOperator determineOperator(String operator) {
        switch (operator) {
            case ("+"):
                return CalcOperator.ADD;
            case ("-"):
                return CalcOperator.SUB;
            case ("*"):
                return CalcOperator.MUL;
            case ("/"):
                return CalcOperator.DIV;
        }
        throw new InvalidInputException(UNKNOWN_OPERATOR_MESSAGE);
    }
}
