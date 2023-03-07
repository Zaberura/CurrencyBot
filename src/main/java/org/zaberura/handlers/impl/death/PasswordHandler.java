package org.zaberura.handlers.impl.death;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.CurrencyBot;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.death.isAlive.IsAliveHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class PasswordHandler extends UserRequestHandler {

        private String command = "";
        private String password = "admin";
        private String responseText = "Please wait till person confirms or not"; //TO BE EDITED
        private ArrayList<String> menuButtonsTexts;

        @Override
        public SendMessage handle(UserRequest userRequest) {
            System.out.println("HANDLING PASSWORD INPUT...");

            if (userRequest.getUpdate().getMessage().getText().equals(password)) {

                responseText = "Please wait till person confirms or not";

                userRequest.getUserSession().setState(ConversationState.WAITING_FOR_CONFIRMATION);
                UserSessionService.saveSession(userRequest);

                CurrencyBot currencyBot = new CurrencyBot();
                IsAliveHandler isAliveHandler = new IsAliveHandler();
                currencyBot.executeMessage(isAliveHandler.handle(userRequest));

            } else {
                userRequest.getUserSession().setState(ConversationState.WAITING_FOR_PASSWORD);
                UserSessionService.saveSession(userRequest);

                responseText = "Wrong password";
            }

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
            menuButtonsTexts = new ArrayList<>();
            //menuButtonsTexts.add("/continue"); //TO BE EDITED
            return new KeyboardBuilder().build(menuButtonsTexts);
        }

        @Override
        protected boolean isCommand(UserRequest userRequest) {

            return userRequest.getUpdate().hasMessage() //&& userRequest.getUpdate().getMessage().isCommand()
                    && userRequest.getUserSession().getState().equals(ConversationState.WAITING_FOR_PASSWORD)
                    ;


        }
}

