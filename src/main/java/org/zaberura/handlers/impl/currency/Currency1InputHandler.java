package org.zaberura.handlers.impl.currency;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.InlineKeyboardBuilder;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class Currency1InputHandler extends UserRequestHandler {
    private String command = "Currency Info";
    private final String responseText = "Choose Currency 2"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;

    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING CURRENCY 1 Input REQUEST");

        userRequest.getUserSession().setState(ConversationState.CURRENCY_INFO_WAITING_FOR_CURRENCY2);
        UserSessionService.saveSession(userRequest);
        return SendMessage
                .builder()
                .chatId(userRequest.getChatId())
                .text(responseText)
                .parseMode(ParseMode.HTML)
                .replyMarkup(buildInlineKeyboard())
                .build();
    }

    protected InlineKeyboardMarkup buildInlineKeyboard(){
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("Hryvnia"); //TO BE EDITED
        menuButtonsTexts.add("Zloti"); //TO BE EDITED
        menuButtonsTexts.add("Other"); //TO BE EDITED
        return new InlineKeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected ReplyKeyboard buildKeyboard() {
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("Hryvnia"); //TO BE EDITED
        menuButtonsTexts.add("Zloti"); //TO BE EDITED
        menuButtonsTexts.add("Other"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {
        return userRequest.getUpdate().hasCallbackQuery();
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        System.out.println("HEELLOOOO");
        return userRequest.getUpdate().hasCallbackQuery();
    }

}
