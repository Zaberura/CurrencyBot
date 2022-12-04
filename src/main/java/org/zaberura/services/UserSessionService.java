package org.zaberura.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.models.UserSession;

import java.util.HashMap;
import java.util.Map;

public class UserSessionService {

    private Map<Long, UserSession> userSessionMap = new HashMap<>();


    public UserSession getSession(Update update) {
         UserSession userSession = userSessionMap.getOrDefault(update.getMessage().getChatId(), UserSession
                .builder()
                .chatId(update.getMessage().getChatId())
                .build());
        userSessionMap.putIfAbsent(update.getMessage().getChatId(), userSession);
        return userSession;
    }

    public UserSession saveSession(Update update, UserSession session) {
        return userSessionMap.replace(update.getMessage().getChatId(), session);
    }
}
