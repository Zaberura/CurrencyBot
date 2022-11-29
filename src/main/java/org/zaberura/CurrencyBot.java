package org.zaberura;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.zaberura.constants.Constants;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class CurrencyBot extends TelegramLongPollingBot {

    private UserSessionService userSessionService;
    public ArrayList<String> userRequestHandlers = new ArrayList<>();

    Dispatcher dispatcher;
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
            System.out.println("has text");
            //String textFromUser = update.getMessage().getText();
            //Long userID = update.getMessage().getFrom().getId();
            //String userFirstName = update.getMessage().getFrom().getFirstName();
            Long chatId = update.getMessage().getChatId();
            System.out.println(update.getMessage().getChatId());
            //UserSession userSession = userSessionService.getSession(chatId);

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
            //        .userSession(userSession)
                    .chatId(chatId)
                    .build();
            System.out.println("point 1" + userRequest.getChatId());

            boolean dispatched = dispatcher.dispatch(userRequest);
            System.out.println("problem point 4 passed");
            if (!dispatched) {
                System.out.println("Unexpected update from user 1");
            }
        } else {
            System.out.println("Unexpected update from user");
        }
    }




}
