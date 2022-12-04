package org.zaberura.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public abstract class UserRequestHandler {

    private String command;
    private static ArrayList<String> menuButtonsTexts;

    public abstract SendMessage handle(Update update);
    protected abstract ReplyKeyboard buildKeyboard();

    public abstract boolean isCommand(Update update);

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

}
