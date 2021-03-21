package com.touristBot;

import com.touristBot.domain.CityBookEntity;
import com.touristBot.service.CityBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private CityBookService cityBookService;

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if ("/start".equals(message.getText())) {
                sendMessage(message, "Введите город: ");
            } else {
                if (isAvailable(message.getText())) {
                    try {
                        CityBookEntity cityBookEntity = cityBookService.takeCityByName(message.getText());
                        sendMessage(message, takeFeedback(cityBookEntity));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    sendMessage(message, "Информация по данному городу отсутствует.");
                }
            }
        }
    }

    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean isAvailable(String cityName) {
        CityBookEntity cityBookEntity = cityBookService.takeCityByName(cityName);
        return cityBookEntity != null;
    }

    public String takeFeedback(CityBookEntity cityBookEntity) {
        return cityBookEntity.getCity() + "\n" + "\n" + cityBookEntity.getDescription();
    }

}
