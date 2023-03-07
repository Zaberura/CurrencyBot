package org.zaberura.handlers.impl.death.isAlive;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class IsAliveDoubleCheckHandler extends UserRequestHandler {

    private String command = "Yes";
    private String responseText = "If you confirm, please type \"I Confirm\"";
    private ArrayList<String> menuButtonsTexts;
    private long myId = 332832026;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("SENDING MESSAGE TO OWNER...");

        userRequest.getUserSession().setState(ConversationState.WAITING_FOR_CONFIRMATION_3);

        return SendMessage
                .builder()
                .chatId(myId)
                .text(responseText)
                .parseMode(ParseMode.HTML)
                .replyMarkup(buildKeyboard())
                .build();
    }

    @Override
    protected ReplyKeyboard buildKeyboard() {
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("I Confirm");
        return new KeyboardBuilder().build(menuButtonsTexts);
    }


    @Override
    protected boolean isCommand(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().getText().equals(command)
                && userRequest.getUpdate().getMessage().getFrom().getId().equals(myId)
                ;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest) && userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_CONFIRMATION_2);
    }
}
