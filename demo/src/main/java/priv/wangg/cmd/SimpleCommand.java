package priv.wangg.cmd;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.stream.IntStream;

/**
 * Simple Command
 *
 * @author wangg
 */
@org.springframework.shell.command.annotation.Command
public class SimpleCommand {

    /**
     * command: hello-world
     * say hello world
     */
    @Command(command = "hello-world", group = "Simple command")
    public String helloWorld() {
        return "你好 世界";
    }

    /**
     * command: cpw
     * The password must be between 6 and 12 characters long
     */
    @Command(command = "cpw", description = "change password", group = "Simple command")
    @NotEmpty
    public String changePassword(
            @Size(min = 6, max = 12) String password) {
        return "Password changed!";
    }

    /**
     * command: sum
     * numbers to sum
     */
    @Command(command = "sum", description = "numbers to sum", group = "Simple command")
    public int sum(
            @Option(arity = CommandRegistration.OptionArity.ONE_OR_MORE)
            @NotEmpty int... nums
    ) {
        return IntStream.of(nums).sum();
    }

    /**
     * command: concat
     * strings to concat
     */
    @Command(command = "concat", description = "strings to concat", group = "Simple command")
    public String concat(
            @Option(arity = CommandRegistration.OptionArity.ONE_OR_MORE)
            @NotEmpty String... strings
    ) {
        return String.join("", strings);
    }
}
