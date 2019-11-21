package bots;

import comandos.Comandos;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class FalaComAndreBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "593996380:AAG8jDv2ugMLTmwcRiHtUwOCpLrSGZsAMBc";
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message!=null && message.hasText()){



            try{
                //Se tiver o comando /start mando Ola usuario
                //E mostra a mensagem com os planos
                if(message.getText().startsWith(Comandos.start)){
                   onStartCommand(update);
                }
                if(message.getText().startsWith(Comandos.planos)){
                    onPlanos(update);
                }
                if(message.getText().startsWith(Comandos.faq)){
                    onSobre(update);
                }





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


    /***EVENTOS**/
    private void onStartCommand(Update update) throws TelegramApiException{
        enviaOlaUsuario(update);
        enviaPlanosPagamento(update);
    }

    private void onPlanos(Update update) throws TelegramApiException{
        enviaPlanosPagamento(update);
    }

    private void onSobre(Update update) throws TelegramApiException{
        enviaSobre(update);
    }


    /**** AUXILIARES **/

    private void enviaOlaUsuario(Update update) throws TelegramApiException{
        String nomeUsuario = update.getMessage().getFrom().getFirstName();
        SendMessage m = enviaMensagemPadrao(update, "Olá " + nomeUsuario);
        m.setReplyMarkup(getTeclado());
        execute(m);

    }

    private void enviaPlanosPagamento(Update update) throws TelegramApiException{
        String planos = "<b>Coisas a fazer no bot</b>:\n\n" +
                "4. Usar os botões para editar uma mensagem que já existe.\n\n" +
                "5. Falar com o pagSeguro.\n\n";

        SendMessage m = enviaMensagemPadrao(update, planos);
        m.setReplyMarkup(getBotoesMensagem());


        execute(m);

    }

    private void enviaSobre(Update update) throws TelegramApiException{
        String planos = "Mandaria uma mensagem falando do serviço.";

        SendMessage m = enviaMensagemPadrao(update, planos);


        execute(m);

    }

    private SendMessage enviaMensagemPadrao(Update update, String corpo){
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(corpo);
        message.setParseMode("HTML");



        return message;

    }

    private ReplyKeyboardMarkup getTeclado() {
        ReplyKeyboardMarkup tecladoMarkup = new ReplyKeyboardMarkup();
        tecladoMarkup.setSelective(false);
        tecladoMarkup.setResizeKeyboard(true);
        tecladoMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> teclado = new ArrayList<KeyboardRow>();
        KeyboardRow linha1 = new KeyboardRow();
        linha1.add(Comandos.faq);
        linha1.add(Comandos.planos);



        teclado.add(linha1);
        tecladoMarkup.setKeyboard(teclado);

        return tecladoMarkup;
    }

    private InlineKeyboardMarkup getBotoesMensagem(){
        InlineKeyboardMarkup tecladoMensagemMarkup = new InlineKeyboardMarkup();


        ArrayList<List<InlineKeyboardButton>> teclado = new ArrayList<List<InlineKeyboardButton>>();

        InlineKeyboardButton btn1 = new InlineKeyboardButton();
        btn1.setText("Botao 1");
        btn1.setCallbackData("Dados 1");

        InlineKeyboardButton btn2 = new InlineKeyboardButton();
        btn2.setText("Botao 2");
        btn2.setCallbackData("Dados 2");
        
        List<InlineKeyboardButton> linha1 = new ArrayList<InlineKeyboardButton>();
        linha1.add(btn1);

        List<InlineKeyboardButton> linha2 = new ArrayList<InlineKeyboardButton>();
        linha2.add(btn2);




        teclado.add(linha1);
        teclado.add(linha2);
        tecladoMensagemMarkup.setKeyboard(teclado);

        return tecladoMensagemMarkup;


    }
}
