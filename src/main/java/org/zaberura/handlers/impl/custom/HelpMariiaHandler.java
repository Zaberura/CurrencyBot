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

public class HelpMariiaHandler extends UserRequestHandler {
    private String command =
            "/i_need_help";
    private String responseText = ""; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING STARTER...");

        responseText = "@kiselboroda @m0tanka @moxito_s_rahitom @oleskuchyn" + "\n РЯТУУУУЙТЕ";


        userRequest.getUserSession().setState(ConversationState.MAIN_MENU);
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
        //menuButtonsTexts.add("Currency Calculator"); //TO BE EDITED
        //menuButtonsTexts.add("Currency Info"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().isCommand()
                && userRequest.getUpdate().getMessage().getText().equals(command);
    }
}
