package com.tarassen;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        Properties botProps = new Properties();
        try (InputStream internalInput = Bot.class.getClassLoader().getResourceAsStream("bot.properties")) {
            if (internalInput == null) {
                throw new RuntimeException("Internal properties file not found!");
            }
            botProps.load(internalInput);
        } catch (IOException e) {
            throw new RuntimeException("Error loading internal properties file: bot.properties", e);
        }
        Bot bot = new Bot();
        bot.init(botProps);

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);

        System.out.println("Count |        Time        |         Chat         |    name    |     username     | message ");


    }
}