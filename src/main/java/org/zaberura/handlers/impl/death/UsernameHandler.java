package org.zaberura.handlers.impl.death;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class UsernameHandler extends UserRequestHandler {

    private String command = "";
    private String username = "admin";
    private String responseText = ""; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING USERNAME INPUT...");

        if(userRequest.getUpdate().getMessage().getText().equals(username)){

            userRequest.getUserSession().setState(ConversationState.WAITING_FOR_PASSWORD);
            UserSessionService.saveSession(userRequest);

            responseText = "Please enter password: ";

        } else {
            responseText = "Wrong username";

            userRequest.getUserSession().setState(ConversationState.WAITING_FOR_USERNAME);
            UserSessionService.saveSession(userRequest);

        }

        return SendMessage
                .builder()
                .chatId(userRequest.getChatId())
                .text(responseText)
                .parseMode(ParseMode.HTML)
                .replyMarkup(buildKeyboard())
                .build();
    }

    @Override
    protected ReplyKeyboard buildKeyboard(){
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("admin"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest){

        return userRequest.getUpdate().hasMessage() //&& userRequest.getUpdate().getMessage().isCommand()
                 && userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_USERNAME)
        ;


    }
}
