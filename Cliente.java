//Package
import models.*;

//Bibliotecas
import java.io.*;
import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;

//A classe JFrame cria a tela visível para o cliente
public class Cliente extends JFrame implements ActionListener {

    //Variáveis com dados do usuário
    private final JTextField ipInicial;
    private final JTextField portaInicial;
    private final JTextField nomeInicial;
    /* O modificador "final" determina que depois de 
    inicializada o valor da variável não pode ser modificado */ 

    // Criação de um Container genérico
    private final JPanel chat = new JPanel();   

    //Permite a edição do texto(Fonte, cor...)
    private final JTextArea textArea = new JTextArea(30, 53);   

    //Criação de formulários com inserção de texto
    private final JTextField caixaMensagem = new JTextField(50);   

    //Exibe uma String curta ou um icone de imagem
    private final JLabel tituloChat = new JLabel("CHAT ONLINE", SwingConstants.CENTER);    

    //Mensagem de online do usuário
    private final JLabel online = new JLabel("Envie e receba mensagens ≧◠‿◠≦✌",  SwingConstants.CENTER);   

    //Botões
    private final JButton botaoEnviar = new JButton("ENVIAR");
    private final JButton botaoLimpar = new JButton("LIMPAR");
    private final JButton botaoSair   = new JButton("SAIR");
    /*
    Socket - Permite a comunicação entre os clientes
    BufferedWriter - Memória que armazena um fluxo de saída de caracteres
    */
    private Socket socket;
    private BufferedWriter bufferWriter;
    //Classe Cliente pode lançar exceções do tipo de Entrada e Saída (IO)
    public Cliente() throws IOException {

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* CAIXA DE TEXTO INICIAL*/ 

        //Boas vindas
        JPanel inicio       = new JPanel();
        JLabel inicioLabel  = new JLabel("BEM VINDO AO CHAT ONLINE");
        inicio.add(inicioLabel);

        //Inicializa dados do usuário
        ipInicial    = new JTextField("127.0.0.1",16);
        portaInicial = new JTextField("9000", 16);
        nomeInicial  = new JTextField("Visitante", 16);

        //PainelTitulo ipUsuario = new PainelTitulo();
        PainelInicial ipUsuario      = new PainelInicial("IP",ipInicial);
        PainelInicial portaUsuario   = new PainelInicial("Porta",portaInicial);
        PainelInicial nomeUsuario    = new PainelInicial("Nome",nomeInicial);

        //Array Object permite a gravação de qualquer objeto
        Object[] dadosUsuario = { inicio, ipUsuario, portaUsuario, nomeUsuario};
        //JOptionPane - Cria caixa de diálogos
        JOptionPane.showMessageDialog(null, dadosUsuario);

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* DEFINIÇÕES GERAIS DA JANELA PRINCIPAL */
        chat.setBackground(new Color(47,44,46));   //Cor principal
        chat.setForeground(new Color(89, 124, 168));    //Cor secundaria
        //chat.setForeground(new Color(1, 171, 149));    //Cor secundaria
        //chat.setForeground(new Color(164,216,219));    //Cor secundaria

        /* Save 
        chat.setBackground(new Color(242,39,76));   //Cor principal
        chat.setForeground(new Color(42,39,76));    //Cor secundaria
        */

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* DEFINIÇÕES DOS ELEMENTOS DA JANELA */

        //Definindo aspecto do titulo e da mensagem de online
        tituloChat.setOpaque(true);
        tituloChat.setForeground(chat.getBackground());
        tituloChat.setBackground(chat.getForeground());

        tituloChat.setFont(new Font("Alkatra", Font.BOLD, 18));
        tituloChat.setPreferredSize(new Dimension(600, 50));
        //tituloChat.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, chat.getForeground()));

        /* * * * * * * * * * * * * * * * * * * * * * * * */

        // Texto introdutor a caixa de mensagem
        online.setOpaque(true);
        online.setForeground(chat.getBackground());
        online.setBackground(chat.getForeground());

        online.setFont(new Font("Alkatra", Font.BOLD, 13));
        online.setPreferredSize(new Dimension(400, 20));

        /* * * * * * * * * * * * * * * * * * * * * * * * */

        // Tela do Chat, onde aparece as mensagens de todas os clientes
        //Cor do texto e de sua área, fonte, tamanho e margens
        textArea.setBackground(new Color(242, 235, 220));
        textArea.setForeground(new Color(76, 74, 72));

        textArea.setMargin(new Insets(0,20,0,0));
        textArea.setBorder(null);
        textArea.setFont(new Font("Alkatra", Font.BOLD, 12));

        textArea.setEditable(false);
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Caixa de mensagem, onde o usuario digita sua mensagem
        caixaMensagem.setBackground(textArea.getBackground());
        caixaMensagem.setForeground(textArea.getForeground());

        caixaMensagem.setPreferredSize(new Dimension(100, 25));  
        caixaMensagem.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, chat.getForeground()));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //botaoEnviar
        botaoEnviar.addActionListener(this);

        botaoEnviar.setBackground(chat.getForeground());
        botaoEnviar.setForeground(chat.getBackground());

        botaoEnviar.setBorder(null);
        botaoEnviar.setFont(new Font("Alkatra", Font.BOLD, 14));
        botaoEnviar.setPreferredSize(new Dimension(90, 25));

        //botaoLimpar
        botaoLimpar.addActionListener(this);

        botaoLimpar.setBackground(chat.getForeground());
        botaoLimpar.setForeground(chat.getBackground());

        botaoLimpar.setBorder(null);
        botaoLimpar.setFont(new Font("Alkatra", Font.BOLD, 14));
        botaoLimpar.setPreferredSize(new Dimension(90, 25));

        //botaoSair
        botaoSair.addActionListener(this);

        botaoSair.setBackground(chat.getForeground());
        botaoSair.setForeground(chat.getBackground());

        botaoSair.setBorder(null);
        botaoSair.setFont(new Font("Alkatra", Font.BOLD, 14));
        botaoSair.setPreferredSize(new Dimension(90, 25));

        // Método faz com que o botão, sempre que for clicado, 
        // chame o método actionPerformed do ActionListener    
        
        /* * * * * * * * * * * * * * * * * * * * * * * * */
        /* INCLUSÕES */

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

        setVisible(true);
        setTitle(nomeInicial.getName());
        setContentPane(chat);

        setSize(630,700);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //Metodo que conecta o cliente
    public void ConectandoCliente() throws IOException{
        socket = new Socket(ipInicial.getText(),Integer.parseInt(portaInicial.getText()));
        bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferWriter.write(nomeInicial.getText() + "\r\n");
        bufferWriter.flush();
        MensagensAvisos("/conectado");
    }
    //Mensagens de aviso para o usuário
    public void MensagensAvisos(String mensagem) throws IOException{
        if(mensagem.length() < 1){
            textArea.append("Não há nada escrito \r\n");

        }else if(mensagem.equals("/sair")){
            bufferWriter.write("Desconectado");
            textArea.append("Usuário desconectado do chat\r\n");

        }else if(mensagem.equals("/limparchat")){  
            textArea.selectAll();
            textArea.replaceSelection("Chat limpo. \r\n");  

        }else if(mensagem.equals("/conectado")){
            textArea.append("  Você se conectou ao chat\r\n\n");
        
        }else{
            bufferWriter.write(mensagem +"\r\n");
            textArea.append(" [" + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()) + "] Você - " + caixaMensagem.getText() + "\n");
        }

        bufferWriter.flush();
        caixaMensagem.setText("");
    }
   
    public void UpdateCliente() throws IOException{
        String mensagem = "";
        BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(!"/sair".equalsIgnoreCase(mensagem)){
            if(buffer.ready()){
                mensagem = buffer.readLine();
                if(mensagem.equals("/sair")){
                    textArea.append("Você desconectou do chat \r\n");
                } else{
                    textArea.append(mensagem + "\r\n");
                }
            }
        }    
    }
    //Desconecta cliente
    public void desconectandoCliente() throws IOException{
        MensagensAvisos("/sair");
        bufferWriter.close();
        socket.close();
        System.exit(0);
    }
   //Botões
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
   //Main
    public static void main(String []args) throws IOException{
        Cliente cliente = new Cliente();
        cliente.ConectandoCliente();
        cliente.UpdateCliente();
    }
}
