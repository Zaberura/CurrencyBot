package org.zaberura.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBuilder {

    public KeyboardBuilder(){

    }

    public ReplyKeyboardMarkup build (List<String> buttons){
        ArrayList<KeyboardRow> buttonRows = new ArrayList<>();

        for(String button : buttons){
            ArrayList<KeyboardButton> nextButtons = new ArrayList<>();
            nextButtons.add(new KeyboardButton(button));
            buttonRows.add(new KeyboardRow(nextButtons));
        }

        return ReplyKeyboardMarkup.builder()
                .keyboard(buttonRows)
                .selective(false)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    public ReplyKeyboardRemove hide (){
        return ReplyKeyboardRemove.builder().removeKeyboard(true).selective(false).build();
        /*return ReplyKeyboardMarkup.builder()
                .clearKeyboard()
                .oneTimeKeyboard(false)
                .build();

         */
    }
}
