package org.zaberura.handlers.impl.death;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.constants.ConversationState;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.keyboard.KeyboardBuilder;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class RecieveInfoHandler extends UserRequestHandler {

        private String command = "";
        private String responseText = "<a href='https://docs.google.com/document/d/1mhFgf0ANorYhjd41Dm18EXoF48yeZkqEGp_ON8D3ISs/edit?usp=share_link'>Link for Instructions</a>"; //TO BE EDITED
        private ArrayList<String> menuButtonsTexts;


        @Override
        public SendMessage handle(UserRequest userRequest) {
            System.out.println("HANDLING RECEIVE INFO...");


            return SendMessage
                    .builder()
                    .chatId(userRequest.getUserSession().getRequesterId())
                    .text(responseText)
                    .parseMode(ParseMode.HTML)
                    .replyMarkup(buildKeyboard())
                    .build();
        }

        @Override
        protected ReplyKeyboard buildKeyboard() {
            menuButtonsTexts = new ArrayList<>();
            //menuButtonsTexts.add("Receive Info"); //TO BE EDITED
            return new KeyboardBuilder().build(menuButtonsTexts);
        }

        @Override
        protected boolean isCommand(UserRequest userRequest) {

            /*return userRequest.getUpdate().hasMessage() && userRequest.getUpdate().getMessage().isCommand()
                    && userRequest.getUpdate().getMessage().getText().equals(command)
                    && userRequest.getUserSession().getState() == ConversationState.LOGED_IN
                    ;

             */

            return false;

        }
}
