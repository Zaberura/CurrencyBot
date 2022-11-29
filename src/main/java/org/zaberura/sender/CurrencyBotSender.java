package org.zaberura.sender;

import lombok.Value;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.zaberura.constants.Constants;

import javax.validation.Valid;

public class CurrencyBotSender extends DefaultAbsSender {

    private String botToken = Constants.token;

    protected CurrencyBotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
