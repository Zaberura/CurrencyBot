package org.zaberura.handlers.impl.death.isAlive;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.CurrencyBot;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.death.RecieveInfoHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class IsAliveAnswerHandler extends UserRequestHandler {

    private String command = "I Confirm";
    private String responseText = "";
    private ArrayList<String> menuButtonsTexts;
    private long myId = 332832026;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("SENDING MESSAGE TO REQUESTER...");

        UserSession yuriiUserSession = UserSessionService.getById(myId);
        yuriiUserSession.setState(ConversationState.LOGED_IN);

        //if (userRequest.getUpdate().getMessage().getText() == command){

            CurrencyBot currencyBot = new CurrencyBot();
            RecieveInfoHandler recieveInfoHandler = new RecieveInfoHandler();
            currencyBot.executeMessage(recieveInfoHandler.handle(userRequest));

        //}

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
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {

        return userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_CONFIRMATION_3)
                && userRequest.getUpdate().getMessage().getFrom().getId().equals(myId);


    }


}
