package org.zaberura.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.models.UserRequest;

public abstract class UserRequestHandler {

    public abstract boolean isApplicable(UserRequest request);
    public abstract void handle(UserRequest request);
    public abstract boolean isGlobal();


    public boolean isCommand(Update update, String command) {
        //returns True if update isn't empty, and text is a Command,
        //and this command is the same as reference Command
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }

    public boolean isTextMessage(Update update){
        //Returns True if update has text
        return update.hasMessage() && update.getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text){
        //Returns True if update is the same message as reference
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals(text);
    }
}
