package org.zaberura.handlers.impl.custom;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class GayHandler extends UserRequestHandler {
    private String command = "/gay";
    private String responseText = "";
    private ArrayList<String> menuButtonsTexts;

    private final String GAY_FLAG = "\uD83C\uDFF3️\u200D\uD83C\uDF08";
    private final String HEART = "\uD83E\uDEC0: ";

    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING GAY REQUEST...");
        responseText = gayRandomizeUkr(userRequest);

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
        menuButtonsTexts.add("/gay");
        return new KeyboardBuilder().hide();
        //return new KeyboardBuilder().build(menuButtonsTexts);
    }

    @Override
    protected boolean isCommand(UserRequest userRequest){
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().isCommand()
                && userRequest.getUpdate().getMessage().getText().equals(command);
    }

    private String gayRandomizeEng(UserRequest userRequest){
        int rand = (int)(Math.random() * 100) + 1;
        String firstName = HEART + userRequest.getUpdate().getMessage().getFrom().getFirstName();
        if(rand <= 10){
            rand += (int)(Math.random() * 5);
            return firstName + ", you're " + rand + "% gay\nShame, barely gay";
        } else if (rand>10 && rand <= 50) {
            rand += (int)(Math.random() * 20);
            return firstName + ", you're " + rand + "% gay\nThere's a place to grow";

        } else if (rand > 50 && rand <90) {
            if (rand <= 80){
                rand += (int)(Math.random() * 10) + 1;
            }
            return firstName + ", you're " + rand + "% gay,\nImpressive " + GAY_FLAG + " !";
        } else if (rand >= 90 && rand <= 99) {
            rand += (int)Math.round((Math.abs(rand-99))/2);
            return firstName + ", you're " + rand + "% gay, Literally my " + GAY_FLAG + " Crush!"
                    + "\nYou've deserved it";
        } else if (rand == 100){
            return firstName + ", YOU ARE TRULY GAY " + GAY_FLAG +
                    " GOD \n Honest 100% " + GAY_FLAG + GAY_FLAG + GAY_FLAG;
        }
        return firstName + ", you're " + rand + "% gay\nThere's a place to grow";
    }

    private String gayRandomizeUkr(UserRequest userRequest){

        int rand = (int)(Math.random() * 100) + 1;
        String firstName = HEART + nameRefactor(userRequest);

        //MARY SPECIAL CONDITION
        //if (userRequest.getUpdate().getMessage().getFrom().getId() == 341143604){rand = 101;}

        if(rand <= 10){
            rand += (int)(Math.random() * 5);
            return firstName + " на " + rand + "% гей " + GAY_FLAG
                    + "\n<i> - Ще потрібна практика... </i>";

        } else if (rand>10 && rand <= 50) {
            rand += (int)(Math.random() * 20);
            return firstName + " на " + rand + "% гей" + GAY_FLAG +
                    "\n<i> - Це непоганий результат </i>";

        } else if (rand > 50 && rand <90) {
            if (rand <= 80){
                rand += (int)(Math.random() * 10) + 1;
            }
            //IVAN SPECIAL CONDITION
            if (userRequest.getUpdate().getMessage().getFrom().getId() == 395578806){
                return firstName + " на " + rand + "% гей " + GAY_FLAG
                        + "\n<i> - Дай по сраці Юрі!</i>";
            }

            return firstName + " на " + rand + "% гей " + GAY_FLAG
                    + "\n<i> - Дай сраці сіна!</i>";

        } else if (rand >= 90 && rand <= 99) {
            rand += (int)Math.round((Math.abs(rand-99))/2);
            return firstName + " на " + rand + "% гей " + GAY_FLAG
                    + "\n<i> - Поклін Отаману! </i>";

        } else if (rand >= 100){
            return firstName + " на " + rand + "% гей " + GAY_FLAG
                    + "\n<i> -Справжнісінький гей-бог </i>\n"
                    + GAY_FLAG + GAY_FLAG + GAY_FLAG + GAY_FLAG + GAY_FLAG + GAY_FLAG;

        }
        return firstName + ", ти на цілих " + rand + "% Гей\nЦе непоганий результат";    }


}
