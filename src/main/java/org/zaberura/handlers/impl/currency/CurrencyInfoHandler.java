package org.zaberura.handlers.impl.currency;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.InlineKeyboardBuilder;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;
import java.util.List;

public class CurrencyInfoHandler extends UserRequestHandler {

    private String command = "Currency Info";
    private final String responseText = "Choose Currency"; //TO BE EDITED
    private ArrayList<String> menuButtonsTexts;

    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING CURRENCY INFO REQUEST");

        userRequest.getUserSession().setState(ConversationState.CURRENCY_INFO_WAITING_FOR_CURRENCY1);
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
        menuButtonsTexts.add("Euro"); //TO BE EDITED
        menuButtonsTexts.add("US Dollar"); //TO BE EDITED
        menuButtonsTexts.add("Canada Dollar"); //TO BE EDITED
        return new InlineKeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected ReplyKeyboard buildKeyboard() {
        menuButtonsTexts = new ArrayList<>();
        menuButtonsTexts.add("Euro"); //TO BE EDITED
        menuButtonsTexts.add("US Dollar"); //TO BE EDITED
        menuButtonsTexts.add("Canada Dollar"); //TO BE EDITED
        return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().getText().equals(command);
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest) && userRequest.getUserSession().getState().equals(ConversationState.MAIN_MENU);
    }
}
