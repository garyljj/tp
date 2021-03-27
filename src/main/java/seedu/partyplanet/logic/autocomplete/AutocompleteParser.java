package seedu.partyplanet.logic.autocomplete;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.partyplanet.logic.commands.HelpCommand;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;

public class AutocompleteParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    
    public String parse(String input, Model model) throws ParseException, CommandException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandWord.equals("edit")) {
            return new EditAutocompleteUtil().parseCommand(arguments, model);
        }

        if (commandWord.equals("eedit")) {
            return new EEditAutocompleteUtil().parseCommand(arguments, model);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
