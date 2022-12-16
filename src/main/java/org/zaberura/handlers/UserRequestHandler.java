package org.zaberura.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class UserRequestHandler {

    private String command;
    private static ArrayList<String> menuButtonsTexts;

    public abstract SendMessage handle(UserRequest userRequest);
    protected abstract ReplyKeyboard buildKeyboard();

    protected abstract boolean isCommand(UserRequest userRequest);

    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest);
    }

    //TO BE MOVED TO APPROPRIATE CLASS
    protected boolean isNumeric(UserRequest userRequest){
        try {
            Double.parseDouble(userRequest.getUpdate().getMessage().getText());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    protected String nameRefactor(UserRequest userRequest){

        Long userId = userRequest.getUpdate().getMessage().getFrom().getId();

        switch (userId.intValue()){
            case 341143604:
                return "Марійка";
            case 395578806:
                return "Іванко";
            case 485300860:
                return "Матвійчик";
            case 332832026:
                return "Юрко";
            case 642501963:
                return "Лесик";
            case 264643446:
                return "Іринка";
            case 407790244:
                return "Микитка";
        }
        return userRequest.getUpdate().getMessage().getFrom().getFirstName();
    }

    //TO BE MOVED TO APPROPRIATE CLASS
    protected boolean isValidDate(UserRequest userRequest){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(userRequest.getUpdate().getMessage().getText().trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /*
    public boolean isGlobal() {
        return true;
    }

    public boolean hasTextMessage(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text){
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals(text);
    }
    */

}
