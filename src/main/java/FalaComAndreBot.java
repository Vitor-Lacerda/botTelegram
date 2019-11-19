import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class FalaComAndreBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "593996380:AAG8jDv2ugMLTmwcRiHtUwOCpLrSGZsAMBc";
    }

    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText("Oi André. Consegui publicar e fazer o bot responder. É um começo. Vou atualizar esse bot pra te dar notícias");

            try{
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }


        }
    }

    public String getBotUsername() {
        return "OiAndreBot";
    }

    public void onUpdatesReceived(List<Update> updates) {
        for(Update u: updates){
            onUpdateReceived(u);
        }
    }
}
