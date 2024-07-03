package com.tarassen;

import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.reactions.SetMessageReaction;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.reactions.ReactionTypeEmoji;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.util.List;
import java.util.Properties;

@Setter
public class Bot extends TelegramLongPollingBot {
    private String token;

    /**
     * This method is called when receiving updates via GetUpdates method
     *
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasText()){
                String text = message.getText().toLowerCase();
                if (text.contains("русня хуйня")){
                    try {
                        System.out.println(Instant.now() + " sending reaction");
                        execute(SetMessageReaction.builder()
                                .chatId(message.getChatId())
                                .messageId(message.getMessageId())
                                .reactionTypes(List.of(new ReactionTypeEmoji("emoji", "🤬")))
                                .build());
                    } catch (TelegramApiException e) {
                        System.out.println("Can't send reaction: " + e);
                    }
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    /**
     * Return username of this bot
     */
    @Override
    public String getBotUsername() {
        return "zhmeka_bot";
    }

    public void init(Properties botProps) {
        this.setToken(botProps.getProperty("token"));
    }
}
