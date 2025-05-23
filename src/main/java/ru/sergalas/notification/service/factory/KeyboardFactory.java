package ru.sergalas.notification.service.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class KeyboardFactory {
    public InlineKeyboardMarkup createInlineKeyboardMarkup(
        List<String> text,
        List<Integer> configuration,
        List<String> data
    ) {
        int expectedSize = configuration.stream().reduce(0, Integer::sum);

        if(text.size() != data.size() || text.size() != expectedSize) {
            log.warn("Wrong text: {}, configuration: {}, data: {}", text, configuration, data);
            return null;
        }
        List<List<InlineKeyboardButton>> rows = getInlineKeyboard(text,configuration,data);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private List<List<InlineKeyboardButton>> getInlineKeyboard(
            List<String> text,
            List<Integer> configuration,
            List<String> data) {
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        int index = 0;
        for (Integer rowNumber : configuration) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for(int i = 0 ; i < rowNumber; i++) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(text.get(index));
                button.setCallbackData(data.get(index));
                row.add(button);
                index +=1;
            }
            keyboardRows.add(row);

        }
        return keyboardRows;
    }

    public ReplyKeyboard createReplyKeyboard(
            List<String> text,
            List<Integer> configuration
    ) {
        if(text.size() != configuration.stream().reduce(0, Integer::sum)) {
            log.warn("wrong text: {}, configuration: {}", text, configuration);
            return null;
        }
        List<KeyboardRow> keyboardRows = getReplayKeyboard(text, configuration);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        return keyboardMarkup;
    }

    private List<KeyboardRow> getReplayKeyboard(List<String> text, List<Integer> configuration) {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        int index = 0;
        for (Integer rowNumber : configuration) {
            KeyboardRow row = new KeyboardRow();
            for(int i = 0 ; i < rowNumber; i++) {
                KeyboardButton button = new KeyboardButton();
                button.setText(text.get(index));
                row.add(button);
                index += 1;
            }
            keyboardRows.add(row);
        }
        return keyboardRows;
    }
}
