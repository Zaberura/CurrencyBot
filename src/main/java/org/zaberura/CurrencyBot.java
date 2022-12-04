package org.zaberura;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.zaberura.constants.Constants;
import org.zaberura.handlers.impl.StartCommandHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;

import java.util.ArrayList;

public class CurrencyBot extends TelegramLongPollingBot {
    Dispatcher dispatcher = new Dispatcher();


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


            executeMessage(dispatcher.dispatch(update));

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
