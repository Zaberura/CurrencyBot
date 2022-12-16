package org.zaberura.handlers.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class Currency1InputHandler extends UserRequestHandler {

    private final String responseText = "Choose Currency"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;

    @Override
    public SendMessage handle(UserRequest userRequest) {
        return null;
    }

    @Override
    protected ReplyKeyboard buildKeyboard() {
        return null;
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {
        return true;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest){
        return true;
        /*
        return isCommand(userRequest) && isNumeric(userRequest)
                && userRequest.getUserSession().getState().equals(ConversationState.CURRENCY_INFO_WAITING_FOR_CURRENCY1);

         */
    }

}
