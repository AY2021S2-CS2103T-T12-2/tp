package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ListFoodItemCommand extends Command {
    public static final String COMMAND_WORD = "foodlist";

    public static final String MESSAGE_SUCCESS = "Here are all the food items: ";

    public static final String MESSAGE_NO_FOOD_ITEM = "The food list is empty. Start off by adding food items!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if(model.getAddressBook().getFoodList().getFoodList().size() == 0 ){
            return new CommandResult(MESSAGE_NO_FOOD_ITEM);
        }
        String result = model.listFoodItem();
        return new CommandResult(MESSAGE_SUCCESS + "\n" + result);
    }
}
