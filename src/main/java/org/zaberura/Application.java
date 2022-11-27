package org.zaberura;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Application {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            System.out.println("Registering bot...");
            telegramBotsApi.registerBot(new CurrencyBot());
        } catch (TelegramApiRequestException e) {
            System.out.println("Failed to register bot(check internet connection / bot token or make sure only one instance of bot is running).");
        }
        System.out.println("Telegram bot is ready to accept updates from user......");
    }
}
