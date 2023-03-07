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

public class DeathHappenedHandler extends UserRequestHandler {

    private String command = "Somebody's gone";
    private final String responseText = "As an author of this bot, I'm truly sorry for your loss. How can I help you?"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING DEATH HAPPENED...");

        userRequest.getUserSession().setState(ConversationState.DEATH_HAPPENED);
        UserSessionService.saveSession(userRequest);

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
        menuButtonsTexts.add("Receive Info"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().getText().equals(command);
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest) && userRequest.getUserSession().getState().equals(ConversationState.MAIN_MENU);
    }


}
