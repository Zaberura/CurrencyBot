package org.zaberura.handlers.impl.custom;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class AlarmHandler extends UserRequestHandler {
    private String command = "/alarm";
    private String responseText = ""; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING STARTER...");
        System.out.println("User: " + userRequest.getUpdate());
        responseText = "@right_blinder @vidm0chka @zaberura @kiselboroda @m0tanka @moxito_s_rahitom @oleskuchyn";


        userRequest.getUserSession().setState(ConversationState.MAIN_MENU);
        UserSessionService.saveSession(userRequest);
        return SendMessage
                .builder()
                .chatId(userRequest.getChatId())
                .text(responseText)
                .parseMode(ParseMode.HTML)
                //.replyMarkup(buildKeyboard())
                .build();
    }

    @Override
    protected ReplyKeyboard buildKeyboard(){
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("/help"); //TO BE EDITED
        //menuButtonsTexts.add("Currency Info"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().isCommand()
                && userRequest.getUpdate().getMessage().getText().equals(command);
    }
}
