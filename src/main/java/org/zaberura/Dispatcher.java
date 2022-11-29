package org.zaberura;

import org.checkerframework.checker.units.qual.A;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.StartCommandHandler;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class Dispatcher {
    private ArrayList<UserRequestHandler> handlers;

    public Dispatcher(ArrayList<UserRequestHandler> handlers){
        this.handlers = handlers;
    }

    public boolean dispatch(UserRequest userRequest) {

        handlers = setHandlers();
        for (UserRequestHandler userRequestHandler : handlers) {
            if(userRequestHandler.isApplicable(userRequest)){
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }

    private ArrayList<UserRequestHandler> setHandlers (){
        ArrayList<UserRequestHandler> handlers = new ArrayList<>();
        handlers.add(new StartCommandHandler());

        return handlers;
    }
}
