package org.zaberura.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.constants.ConversationState;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;

import java.util.HashMap;
import java.util.Map;

//ABSTRACT Modifier TO BE ADDED
public class UserSessionService {

    /*private static UserSessionService userSessionService = new UserSessionService();
    private UserSessionService(){
    }
    public static UserSessionService getInstance(){
        return userSessionService;
    }

     */

    private static Map<Long, UserSession> userSessionMap = new HashMap<>();

    public static UserSession getSession(Update update) {
         UserSession userSession = userSessionMap.getOrDefault(update.getMessage().getChatId(), UserSession
                .builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getFrom().getFirstName())
                //.state(ConversationState.CONVERSATION_STARTED)
                .build());
        userSessionMap.putIfAbsent(update.getMessage().getChatId(), userSession);
        return userSession;
    }

    public static UserSession saveSession(UserRequest userRequest) {
        return userSessionMap.replace(userRequest.getUpdate().getMessage().getChatId(), userRequest.getUserSession());
    }

    public static void display() {
        for (Map.Entry<Long, UserSession> entry : userSessionMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            //ME
            //-1001331174838:UserSession(chatId=-1001331174838, firstName=Yurriy,
            //-1001331174838:UserSession(chatId=-1001331174838, firstName=Іван 🇺🇦, state=MAIN_MENU, currency1=null, currency2=null, timeFrame=null, amount=null)
        }
    }
}
