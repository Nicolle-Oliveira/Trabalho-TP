import java.awt.HeadlessException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.io.InputStreamReader;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.Socket;


public class Servidor extends Thread{

    private String nomeUsuario;
    final static String[] opcoes = {"Ok", "Cancelar"};
    final static String iniciadoServidor = "Servidor iniciando na porta: ";
    final static String porta = "9000";
    /*
    Socket - Permite a comunicação entre os clientes
    ServerSocket - Espera a conexão do cliente, possui um construtor onde passamos a porta que desejamos usar para escutar as conexões
    BufferedReader - Lê o texto de um fluxo de entrada de caracteres, armazenando caracteres em um buffer
    */
    private final Socket socket;
    private static ServerSocket servidor;
    private BufferedReader bufferReader;
    //ArayList do tipo BufferedWriter que adiciona objetos em clientes
    private static ArrayList<BufferedWriter>clientes;
    /* 
    Metodo servidor que recebe o socket como parametro
    InputStreamReader - Lê os bytes do fluxo de entrada como caracteres, converte dados em bytes em dados em caracteres
    toString - É herdado de todo objeto descendente de Object, é chamado quando o objeto precisa ser representado como um valor em texto (nexte caso em uma exceção)
    */
    public Servidor(Socket socket){
        this.socket = socket;
        try {
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    /*
    Run() - Executa a tarefa da thread
    OutputStreamWriter - Ponte de streams de caracteres para streams de bytes
    GetOutputStream -  Retorna um fluxo de saída para o socket fornecido
    EqualsIgnoreCase - Compara uma String, sem fazer distinção entre letras maiusculas e minusculas
    */
    @Override
    public void run(){
        try{
          String mensagem;
          
          BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
          clientes.add(bufferWriter);
          nomeUsuario = mensagem = bufferReader.readLine();

          while(!"Sair".equalsIgnoreCase(mensagem) && mensagem != null){
            mensagem = bufferReader.readLine();
            mandaMensagemUsuario(bufferWriter, mensagem);
            System.out.println(mensagem);
          }
        }catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public static void main(String []args) throws IOException{
        try{
            //JTextField - criação de formulários para a inserção dos dados 
            JTextField portaServidor = new JTextField(porta);
            
            JPanel janela = new JPanel();
            janela.add(new JLabel("Porta do servidor: "));
            janela.add(portaServidor);
            /*
            As opções são herdadas da classe javax.swing.JOptionPane
            JOptionPane possui métodos estáticos que criam caixas de diálogos simples e objetivas
            */
            int opcaoEscolhida = JOptionPane.showOptionDialog(null, janela, "Socket chat", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, 0);
            
            if(opcaoEscolhida == JOptionPane.NO_OPTION){
               System.out.println("O servidor foi finalizado.");
               System.exit(0);
            }
            /*
            showMessageDialog - Exibe uma caixa de mensagens com um botão
            JOptionPane.showMessageDialog(centraliza a caixa na tela, determina a posição da caixa);
            */
            JOptionPane.showMessageDialog(null, iniciadoServidor + portaServidor.getText());
            
            clientes = new ArrayList<>();
            servidor = new ServerSocket(Integer.parseInt(portaServidor.getText()));
            
            while(true){
                System.out.println("SERVIDOR ONLINE");
                Socket socket = servidor.accept();
                System.out.println("Usuário conectou ao servidor");
                Thread thread = new Servidor(socket);
                thread.start();
            }
            
        }catch(HeadlessException e){
            System.out.println(e.toString());
        }
    }
    //Metodo que envia mensagem a todos
    public void mandaMensagemUsuario(BufferedWriter bufferWriter, String mensagemUsuario) throws IOException{
        BufferedWriter bufferWriterUsuario;
        for(BufferedWriter buffer: clientes){
            bufferWriterUsuario = (BufferedWriter)buffer;
            if(!(bufferWriter == bufferWriterUsuario)){
                if(mensagemUsuario.equals("Desconectado")){
                    buffer.write(nomeUsuario + " desconectou do chat" + "\r\n");
                
                }else{
                    //Imprimindo a mensagem dos outros usuários 
                    buffer.write(" [" + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()) + "] " + nomeUsuario + " - " + mensagemUsuario + "\r\n");
                }
                //O flush() esvazia o conteúdo do Buffer
                buffer.flush();
            }
        }
    }
}
