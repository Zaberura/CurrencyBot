package org.zaberura.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class StartCommandHandler extends UserRequestHandler {

    private String command = "/start";
    private final String responseText = "Welcome to Currency bot. \nChoose options from menu below"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;


    @Override
    public SendMessage handle(Update update) {
        System.out.println("HANDLING STARTER...");
        return SendMessage
                .builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(responseText)
                .parseMode(ParseMode.HTML)
                .replyMarkup(buildKeyboard())
                .build();
    }

    @Override
    protected ReplyKeyboard buildKeyboard(){
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("Currency Calculator"); //TO BE EDITED
        menuButtonsTexts.add("Currency Info"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    public boolean isCommand(Update update){
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }
}
