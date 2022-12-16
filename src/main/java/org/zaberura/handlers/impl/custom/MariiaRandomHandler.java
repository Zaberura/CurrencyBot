package org.zaberura.handlers.impl.custom;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class MariiaRandomHandler extends UserRequestHandler {
    private String command = "/mariiaR";
    private String responseText = "";
    private ArrayList<String> mariiaStatusList = new ArrayList();
    private final String HEART = "\uD83E\uDEC0: ";


    @Override
    public SendMessage handle(UserRequest userRequest) {
        System.out.println("HANDLING RANDOM MARIAA REQUEST...");
        responseText = yourMariiaRandom(userRequest);

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
    protected ReplyKeyboard buildKeyboard() {
        return null;
    }

    @Override
    protected boolean isCommand(UserRequest userRequest) {
        return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().isCommand()
                && userRequest.getUpdate().getMessage().getText().equals(command);
    }

    private String yourMariiaRandom(UserRequest userRequest){

        String firstName = nameRefactor(userRequest);
        String introText = HEART + firstName + ", сьогодні ви\n<i> Марія ";
        String closingText = "</i>";

        mariiaStatusList.add("Миті та Життя");
        mariiaStatusList.add("Бази й Каламбуру");
        mariiaStatusList.add("Космосу та Хаосу");
        mariiaStatusList.add("Справедливості та Спалаху");
        mariiaStatusList.add("Блискавки й Наслідку");
        mariiaStatusList.add("Посіпак й Володарів");
        mariiaStatusList.add("Спокою та Гармонії");
        mariiaStatusList.add("Ночі та Пітьми");
        mariiaStatusList.add("");

        int rand = (int) (Math.random() * mariiaStatusList.size());

        return introText + mariiaStatusList.get(rand) + closingText;
    }
}
