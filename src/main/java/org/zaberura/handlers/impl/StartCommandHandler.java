package org.zaberura.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class StartCommandHandler extends UserRequestHandler {

    private static String command = "start";
    private static final String responseText = "We've received your message"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts = new ArrayList<>();
    {
        menuButtonsTexts.add("Button1"); //TO BE EDITED
        menuButtonsTexts.add("Button2"); //TO BE EDITED
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(responseText)
                .replyMarkup(buildKeyboard())
                .build();
        return sendMessage;
    }
}
