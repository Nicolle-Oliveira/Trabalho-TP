//Bibliotecas
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.*;



//A classe JFrame cria a tela visível para o cliente
public class Cliente extends JFrame implements ActionListener {
    //Variáveis com dados do usuário
    /*O modificador "final" determina que depois de incializada
    o valor da variável não pode ser modificado*/
    private final JTextField ipInicial;
    private final JTextField portaInicial;
    private final JTextField nomeInicial;
   
    private final JPanel chat = new JPanel();   // Criação de um Container genérico
    private final JTextArea textArea = new JTextArea(30, 53);   //Permite a edição do texto(Fonte, cor...)
    private final JTextField caixaMensagem = new JTextField(50);   //Criação de formulários com inserção de texto
    private final JLabel tituloChat = new JLabel("CHAT ONLINE");    //Exibe uma String curta ou um icone de imagem
    private final JLabel online = new JLabel("Envie e receba mensagens");   //Mensagem de online do usuário
    //Botões
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");
    /*
    Socket - Permite a comunicação entre os clientes
    BufferedWriter - Memória que armazena um fluxo de saída de caracteres
    */
    private Socket socket;
    private BufferedWriter bufferWriter;
    //Classe Cliente pode lançar exceções do tipo de Entrada e Saída (IO)
    public Cliente() throws IOException{
        
        JPanel ipUsuario = new JPanel();
        JPanel portaUsuario = new JPanel();
        JPanel nomeUsuario = new JPanel();
        JPanel inicio = new JPanel();

        //Borda e Background
        ipUsuario.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(112,128,144)));
        portaUsuario.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(112,128,144)));                            
        nomeUsuario.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(112,128,144)));

        ipUsuario.setBackground(new Color(176,196,222));
        portaUsuario.setBackground(new Color(176,196,222));
        nomeUsuario.setBackground(new Color(176,196,222));
        //Inicializa dados do usuário
        ipInicial = new JTextField("127.0.0.1");
        portaInicial = new JTextField("9000");
        nomeInicial = new JTextField("Visitante");
        //Fonte dados iniciais
        ipInicial.setFont(new Font("Alkatra", Font.BOLD, 15));
        portaInicial.setFont(new Font("Alkatra", Font.BOLD, 15));
        nomeInicial.setFont(new Font("Alkatra", Font.BOLD, 15));
        //Grava novos dados do usuário 
        JLabel enderecoIpLabel = new JLabel("ENDEREÇO DE IP:    ");
        enderecoIpLabel.setFont(new Font("Alkatra", Font.BOLD, 15));
        ipUsuario.add(enderecoIpLabel); 
        ipUsuario.add(ipInicial);
       
        JLabel portaLabel = new JLabel("PORTA:                 ");
        portaLabel.setFont(new Font("Alkatra", Font.BOLD, 15));
        portaUsuario.add(portaLabel); 
        portaUsuario.add(portaInicial);

        JLabel nomeLabel = new JLabel("NOME DE USUÁRIO: ");
        nomeLabel.setFont(new Font("Alkatra", Font.BOLD, 15));
        nomeUsuario.add(nomeLabel); 
        nomeUsuario.add(nomeInicial);
        /*
        JOptionPane - Cria caixa de diálogos
        Array Object permite a gravação de qualquer objeto
        */
        Object[] dadosUsuario = {ipUsuario, portaUsuario, nomeUsuario, inicio};
        JOptionPane.showMessageDialog(null, dadosUsuario);
        //Definindo aspecto do titulo e da mensagem de online
        tituloChat.setForeground(Color.BLACK);
        tituloChat.setFont(new Font("Alkatra", Font.BOLD, 18));
        online.setForeground(Color.BLACK);
        online.setFont(new Font("Alkatra", Font.BOLD, 13));
        //Cor de fundo chat
        chat.setBackground(new Color(176,196,222));
        chat.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3, new Color(112,128,144)));
        //Cor do texto e de sua área, fonte, tamanho e margens
        textArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(112,128,144)));
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Alkatra", Font.BOLD, 12));
        textArea.setMargin(new Insets(20,20,0,0));
        textArea.setEditable(false);
        //Dimensão da caixa de mensagem
        caixaMensagem.setPreferredSize(new Dimension(100, 25));  
        //Método faz com que o botão, sempre que for clicado, chame o método actionPerformed do ActionListener    
        botaoEnviar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoSair.addActionListener(this);
        
        botaoEnviar.setForeground(Color.BLACK);
        botaoLimpar.setForeground(Color.BLACK);
        botaoSair.setForeground(Color.BLACK);
        //Container flexível que se adapta conforme um componente adicionado a ele
        JScrollPane rolavel = new JScrollPane(textArea);
        //Adicionando elementos
        chat.add(tituloChat);
        chat.add(rolavel);
        chat.add(online);
        chat.add(caixaMensagem);
        chat.add(botaoEnviar);
        chat.add(botaoLimpar);
        chat.add(botaoSair);
        //Definições interface
        setSize(620,670);
        setTitle(nomeInicial.getText());
        setContentPane(chat);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }
    //Metodo que conecta o cliente
    public void ConectandoCliente() throws IOException{
        socket = new Socket(ipInicial.getText(),Integer.parseInt(portaInicial.getText()));
        bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferWriter.write(nomeInicial.getText() + "\r\n");
        bufferWriter.flush();
    }
    //Mensagens de aviso para o usuário
    public void MensagensAvisos(String message) throws IOException{
        if(message.length() < 1){
            textArea.append("Não há nada escrito \r\n");
        }else if(message.equals("/sair")){
          bufferWriter.write("Desconectado");
          textArea.append("Usuário desconectado do chat\r\n");
        }else if(message.equals("/limparchat")){
          textArea.selectAll();
          textArea.replaceSelection("Chat limpo. \r\n");  
        }else{
          bufferWriter.write(message +"\r\n");
          textArea.append(nomeInicial.getText() + " [" + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()) + "] " + "\n" + caixaMensagem.getText()+ "\n");
        }
        bufferWriter.flush();
        caixaMensagem.setText("");
    }
   
    public void UpdateCliente() throws IOException{
        String message = "";
        BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(!"/sair".equalsIgnoreCase(message)){
            if(bfr.ready()){
                message = bfr.readLine();
                if(message.equals("/sair")){
                    textArea.append("Você desconectou do chat! \r\n");
                }    
                else{
                    textArea.append(message + "\r\n");
                }
            }
        }    
    }
    //Desconecta cliente
    public void desconectandoCliente() throws IOException{
        MensagensAvisos("/sair");
        bufferWriter.close();
        socket.close();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getActionCommand().equals(botaoEnviar.getActionCommand())){
                MensagensAvisos(caixaMensagem.getText());
            }else if(e.getActionCommand().equals(botaoLimpar.getActionCommand())){
                textArea.selectAll();
                textArea.replaceSelection("");
            }else if(e.getActionCommand().equals(botaoSair.getActionCommand())){
                desconectandoCliente();
            }
        }catch (IOException error){
            System.out.println(error.toString());
        }
    }
   
    public static void main(String []args) throws IOException{
        Cliente Cliente = new Cliente();
        Cliente.ConectandoCliente();
        Cliente.UpdateCliente();
    }
}


