package org.zaberura;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.zaberura.constants.Constants;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.currency.Currency1InputHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import static java.awt.Color.RED;

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

        if(update.hasMessage() && update.getMessage().hasText()){
            executeMessage(dispatcher.dispatch(update));
        } else {
            System.out.println("Unexpected update from user");
        }
        System.out.println("Message from: " + update.getMessage().getFrom().getId()
                + " ; Name: " + update.getMessage().getFrom().getFirstName());
    }

    public void executeMessage(SendMessage message){
        try {
            this.sendApiMethod(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
