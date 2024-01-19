package priv.wangg.cmd;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * particular data type as option value
 */
@Command(command = "types")
class TypesCommand {

    /**
     * String Type
     * String is a most simplest type as there’s no conversion involved
     */
    @Command(command = "string", description = "string type", group = "Types commands")
    public String stringTypeCommand(
            @NotBlank String arg
    ) {
        return "hello " + arg;
    }

    /**
     * Boolean Type：boolean/Boolean
     * Boolean can be null
     */
    @Command(command = "boolean", description = "boolean type", group = "Types commands")
    public String booleanTypeCommand(
            @Option(defaultValue = "true") boolean arg1,
            @Option(defaultValue = "false") boolean arg2,
            @Option() Boolean arg3,
            @Option(defaultValue = "true") Boolean arg4
    ) {
        return String.format("arg1:%s, arg2:%s, arg3:%s, arg4:%s",
                arg1, arg2, arg3, arg4);
    }

    /**
     * Number Type
     */
    @Command(command = "number", description = "number type", group = "Types commands")
    public String NumberTypeCommand(int arg1) {
        return "hello " + arg1;
    }

    /**
     * Enum type
     * Conversion to enums is possible if given value is exactly matching enum itself
     */
    @Command(command = "enum", description = "enum type", group = "Types commands")
    public String enumTypeCommand(@NotNull OptionTypeEnum arg) {
        return "hello " + arg;
    }

    /**
     * Array type
     */
    @Command(command = "array", description = "array type", group = "Types commands")
    public void arrayTypeCommand(String[] arg) {
        Arrays.stream(arg)
                .forEach(System.out::println);
    }

    /**
     * Shows conversion using Spring converters
     * According to the converter definition, properties are separated by commas
     */
    @Command(command = "customType", description = "conversion type", group = "Types commands")
    public Object customTypeCommand(CustomType arg) {
        return arg;
    }

    enum OptionTypeEnum {
        ONE, TWO, THREE
    }
}

class CustomType {
    private final String value;
    private final int number;
    private final boolean flag;

    CustomType(String value, int number, boolean flag) {
        this.value = value;
        this.number = number;
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "CustomType{" +
                "value='" + value + '\'' +
                ", number=" + number +
                ", flag=" + flag +
                '}';
    }
}

@Component
class CustomTypeConverter implements Converter<String, CustomType> {

    @Override
    public CustomType convert(String source) {
        String[] split = source.split(",");
        return new CustomType(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
    }
}