package org.zaberura.handlers.impl.death.isAlive;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class IsAliveHandler extends UserRequestHandler {

    private String command = "";
    private String responseText = "Someones worried if u r alive, r u?";
    private ArrayList<String> menuButtonsTexts;
    private long myId = 332832026;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("SENDING MESSAGE TO OWNER...");

        UserSession yuriiUserSession = UserSessionService.getById(myId);
        yuriiUserSession.setState(ConversationState.WAITING_FOR_CONFIRMATION_2);
        yuriiUserSession.setRequesterId(userRequest.getChatId());

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
        menuButtonsTexts.add("Yes");
        menuButtonsTexts.add("No");
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {
        return false;
    }
}
