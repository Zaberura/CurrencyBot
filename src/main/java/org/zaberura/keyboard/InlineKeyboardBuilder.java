package org.zaberura.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardBuilder {
    public InlineKeyboardBuilder () {

    }

    public InlineKeyboardMarkup build(List<String> buttons) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List <List<InlineKeyboardButton>> rowsInline = new ArrayList< >();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();

        for (String button : buttons){
            rowInline.add( new InlineKeyboardButton().builder().text(button).callbackData("/start").build());
            rowsInline.add(rowInline);
        }

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
}
