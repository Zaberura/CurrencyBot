package org.zaberura.handlers.impl;

import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.TelegramService;

import java.util.ArrayList;

public class StartCommandHandler extends UserRequestHandler {

    private static String command = "start";
    private static final String responseText = "We've received your message"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts = new ArrayList<>();
    {
        menuButtonsTexts.add("Button1"); //TO BE EDITED
        menuButtonsTexts.add("Button2"); //TO BE EDITED
    }

    TelegramService telegramService;
    KeyboardBuilder keyboardBuilder = new KeyboardBuilder();

    //TO BE IMPLEMENTED
    @Override
    public boolean isApplicable(UserRequest request) {
        System.out.println(isCommand(request.getUpdate(), command));
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request) {
        telegramService.sendMessage(request.getChatId(), responseText, keyboardBuilder.build(menuButtonsTexts));
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
