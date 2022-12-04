package org.zaberura.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.handlers.UserRequestHandler;

public class CurrencyInfoHandler extends UserRequestHandler {
    @Override
    public SendMessage handle(Update update) {
        return null;
    }

    @Override
    protected ReplyKeyboard buildKeyboard() {
        return null;
    }

    @Override
    public boolean isCommand(Update update) {
        return false;
    }
}
