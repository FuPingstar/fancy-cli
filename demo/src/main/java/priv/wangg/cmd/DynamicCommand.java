package priv.wangg.cmd;

import org.springframework.context.annotation.Bean;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;

/**
 * dynamic command test
 *
 * @author wangg
 */
@Command
public class DynamicCommand {

    /**
     * connect status
     */
    private boolean connected;

    /**
     * authentication status
     */
    private boolean authenticated;

    /**
     * command: connect
     * connect to server
     */
    @Command(command = "connect", description = "connect to server", group = "Dynamic command")
    public void connect() {
        connected = true;
    }

    /**
     * command: disconnect
     * disconnect from server
     */
    @Command(command = "disconnect", description = "disconnect from server", group = "Dynamic command")
    public void disconnect() {
        connected = false;
        authenticated = false;
    }

    /**
     * command: auth
     * authenticate
     * @param credentials credential var
     */
    @Command(command = "auth", description = "authenticate", group = "Dynamic command")
    public void auth(String credentials) {
        authenticated = "passwd".equals(credentials);
    }

    /**
     * command: boom
     * dangerous command
     * This command is invalid before authentication.
     * @return
     */
    @Command(command = "boom", description = "boom", group = "Dynamic command")
    @CommandAvailability(provider = "authCheckAvailability")
    public String blowUp() {
        return "Boom";
    }

    /**
     * Verify command availability
     */
    @Bean
    public AvailabilityProvider authCheckAvailability() {
        return () ->
            connected && authenticated
                    ? Availability.available() : Availability.unavailable("You failed to authenticate");
    }

}
