package priv.wangg.cmd;

import org.springframework.context.annotation.Bean;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.OptionValues;
import org.springframework.shell.completion.CompletionResolver;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * completion proposals
 *
 * @author wangg
 */
@Command
public class CompleteCommand {

    /**
     * annotation model
     * compeltion command 1
     */
    @Command(command = "complete-cmd1", description = "complete sample1", group = "Complete Commands")
    public String completeCommand1(
            @OptionValues(provider = "someValuesProvider") String arg1,
            @OptionValues(provider = "someValuesProvider") String arg2
    ) {
        return "You said: " + arg1 + " and " + arg2;
    }

    @Bean
    CompletionResolver someValuesProvider() {
        String[] VALUES = {
                "'hello world'",
                "'I am quoting \"The Daily Mail\"'",
                "'10 \\ 3 = 3'"
        };

        return ctx -> Arrays.stream(VALUES)
                            .map(CompletionProposal::new)
                            .collect(Collectors.toList());
    }

    /**
     * programmatic model
     * compeltion command 2
     */
    @Bean
    CommandRegistration completeCommandRegistration() {
        return CommandRegistration.builder()
              .command("complete-cmd2")
              .description("complete sample2")
              .group("Complete Commands")
              .withOption()
              .longNames("arg1")
              .completion(ctx -> {
                  CompletionProposal p1 = new CompletionProposal("arg1hi1");
                  CompletionProposal p2 = new CompletionProposal("arg1hi2");
                  return Arrays.asList(p1, p2);
              })
              .and()
              .withOption()
              .longNames("arg2")
              .completion(ctx -> {
                  CompletionProposal p1 = new CompletionProposal("arg2hi1");
                  CompletionProposal p2 = new CompletionProposal("arg2hi2");
                  return Arrays.asList(p1, p2);
              })
              .and()
              .withTarget()
              .function(ctx -> {
                  String arg1 = ctx.getOptionValue("arg1");
                  String arg2 = ctx.getOptionValue("arg2");
                  return String.format("hi, arg1 value is '%s'\n arg2 value is '%s'\n", arg1, arg2);
              })
              .and()
              .build();
    }
}
