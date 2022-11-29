package org.zaberura;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CurrencyBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "SponsoredByFilmBot";
    }

    @Override
    public String getBotToken() {
        return "5931948095:AAGiyeFswIkoAsi4iv2jAYBUG6DZsMMgZyQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("UPDATE RECEIVED");
    }


}
