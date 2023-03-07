package org.zaberura;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.currency.Currency1InputHandler;
import org.zaberura.handlers.impl.currency.CurrencyInfoHandler;
import org.zaberura.handlers.impl.StartCommandHandler;
import org.zaberura.handlers.impl.custom.*;
import org.zaberura.handlers.impl.death.*;
import org.zaberura.handlers.impl.death.isAlive.IsAliveAnswerHandler;
import org.zaberura.handlers.impl.death.isAlive.IsAliveDoubleCheckHandler;
import org.zaberura.models.UserRequest;
import org.zaberura.services.UserSessionService;

import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<UserRequestHandler> handlers;

    public Dispatcher(){
        setHandlers();
    }

    public SendMessage dispatch(Update update){
        UserRequest userRequest = UserRequest.builder()
                .update(update)
                .chatId(update.getMessage().getChatId())
                .userSession(UserSessionService.getSession(update))
                .build();

        //HANDLERS
        for (UserRequestHandler handler : handlers){
            if (handler.isApplicable(userRequest)){
                UserSessionService.saveSession(userRequest);
                return handler.handle(userRequest);
            }
        }
        return null;
    }

    private void setHandlers(){
        handlers = new ArrayList<>();

        handlers.add(new StartCommandHandler());

        handlers.add(new CurrencyInfoHandler());
        //handlers.add(new Currency1InputHandler());

        handlers.add(new GayHandler());
        handlers.add(new AlarmHandler());
        handlers.add(new HelpMariiaHandler());
        handlers.add(new MariiaRandomHandler());
        handlers.add(new YuriiRandomHandler());


        handlers.add(new DeathHappenedHandler());
        handlers.add(new RequestInstructionsHandler());
        handlers.add(new UsernameHandler());
        handlers.add(new PasswordHandler());
        //handlers.add(new RecieveInfoHandler());
        handlers.add(new IsAliveDoubleCheckHandler());
        handlers.add(new IsAliveAnswerHandler());
    }
}
