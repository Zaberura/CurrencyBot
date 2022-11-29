package org.zaberura.services;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.zaberura.CurrencyBot;
import org.zaberura.sender.CurrencyBotSender;

public class TelegramService {

    private CurrencyBotSender currencyBotSender;

    public TelegramService(CurrencyBotSender currencyBotSender){
        this.currencyBotSender = currencyBotSender;
    }

    public void sendMessage(long chatId, String text){
        sendMessage(chatId, text, null);
    }
    public void sendMessage(long chatId, String text, ReplyKeyboard replyKeyboard){
        SendMessage sendMessage = SendMessage
                .builder()
                .text(text)
                .chatId(chatId)
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboard)
                .build();
        execute(sendMessage);
    }

    private void execute(BotApiMethod botApiMethod){
        try {
            currencyBotSender.execute(botApiMethod);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
