package org.zaberura;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.StartCommandHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.models.UserSession;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<UserRequestHandler> handlers;
    //private UserSessionService userSessionService;

    public Dispatcher(){
        setHandlers();
    }

    public SendMessage dispatch(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            String text = update.getMessage().getText();
            for (UserRequestHandler handler : handlers){
                if (handler.isCommand(update)){

                    return handler.handle(update);
                }
            }
        }
        return null;
    }

    private void setHandlers(){
        handlers = new ArrayList<>();
        handlers.add(new StartCommandHandler());
    }
}
