package ir.imlucky.bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import ir.imlucky.bot.telegram.handler.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotController {

    @Autowired
    private UpdateHandler handler;

    @Autowired
    private TelegramBot bot;

    @RequestMapping(value = "/poll", method = RequestMethod.GET)
    public void poll() {

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse getUpdatesResponse = bot.execute(getUpdates);

        List<Update> updates = getUpdatesResponse.updates();
        for (Update update : updates) {
            webhook(update);
        }
    }

    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public void webhook(@RequestBody Update update) {
        handler.handleUpdate(update);
    }

    @RequestMapping(value = "/mypoll", method = RequestMethod.GET)
    public String hello() {
        return "hello from myBot";
    }
}
