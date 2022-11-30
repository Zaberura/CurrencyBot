package org.zaberura;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.zaberura.constants.Constants;
import org.zaberura.handlers.impl.StartCommandHandler;

public class CurrencyBot extends TelegramLongPollingBot {
    StartCommandHandler startCommandHandler = new StartCommandHandler();

    @Override
    public String getBotUsername() {
        return Constants.botName;
    }

    @Override
    public String getBotToken() {
        return Constants.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("UPDATE RECEIVED");

        if(update.hasMessage() && update.getMessage().hasText()){
            executeMessage(startCommandHandler.handle(update));
        } else {
            System.out.println("Unexpected update from user");
        }
    }

    private void executeMessage(SendMessage message){
        try {
            this.sendApiMethod(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
