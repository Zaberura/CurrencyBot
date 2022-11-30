package org.zaberura.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public abstract class UserRequestHandler {

    private String command;
    private ArrayList<String> menuButtonsTexts;

    public abstract SendMessage handle(Update update);

    public boolean isCommand(Update update) {
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }

    public boolean hasTextMessage(Update update){
        return update.hasMessage() && update.getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text){
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals(text);
    }

    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate());
    }

    public boolean isGlobal() {
        return true;
    }

    protected ReplyKeyboard buildKeyboard(){
        return new KeyboardBuilder().build(menuButtonsTexts);
    }
}
