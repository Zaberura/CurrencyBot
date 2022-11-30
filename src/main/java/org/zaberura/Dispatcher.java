package org.zaberura;

import org.checkerframework.checker.units.qual.A;
import org.zaberura.handlers.UserRequestHandler;
import org.zaberura.handlers.impl.StartCommandHandler;
import org.zaberura.models.UserRequest;

import java.util.ArrayList;

public class Dispatcher {

    private ArrayList<UserRequestHandler> handlers;
    public Dispatcher(){

    }

    private void setHandlers(){
        handlers.add(new StartCommandHandler());
    }
}
