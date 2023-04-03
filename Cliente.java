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
        
        CustomPanel ipUsuario = new CustomPanel();
        CustomPanel portaUsuario = new CustomPanel();
        CustomPanel nomeUsuario = new CustomPanel();
        JPanel inicio = new JPanel();
        JLabel inicioLabel = new JLabel("BEM VINDO AO CHAT ONLINE");
        inicio.add(inicioLabel);

        //Inicializa dados do usuário
        ipInicial = new JTextField("127.0.0.1");
        portaInicial = new JTextField("9000");
        nomeInicial = new JTextField("Visitante");

        //Grava novos dados do usuário 
        CustomLabel enderecoIpLabel = new CustomLabel();
        //CustomLabel enderecoIpLabel = new CustomLabel("ENDEREÇO DE IP:    ");
        ipUsuario.add(enderecoIpLabel); 
        ipUsuario.add(ipInicial);
       
        CustomLabel portaLabel = new CustomLabel("PORTA:                 ");
        portaUsuario.add(portaLabel); 
        portaUsuario.add(portaInicial);

        CustomLabel nomeLabel = new CustomLabel("NOME DE USUÁRIO: ");
        nomeUsuario.add(nomeLabel); 
        nomeUsuario.add(nomeInicial);
        /*
        JOptionPane - Cria caixa de diálogos
        Array Object permite a gravação de qualquer objeto
        */
        Object[] dadosUsuario = {ipUsuario, portaUsuario, nomeUsuario, inicio};
        JOptionPane.showMessageDialog(null, dadosUsuario);

        //Definindo aspecto do titulo e da mensagem de online
        //tituloChat.setForeground(new Color(245,111,63));
        tituloChat.setForeground(new Color(24,245,147));
        tituloChat.setFont(new Font("Alkatra", Font.BOLD, 18));

        online.setForeground(new Color(95, 244, 245));
        online.setFont(new Font("Alkatra", Font.BOLD + Font.ITALIC, 13));
        //Cor de fundo chat
        chat.setBackground(new Color(32,22,45));
        //chat.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3,Color.CYAN));

        //Cor do texto e de sua área, fonte, tamanho e margens
        //textArea.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, new Color(245,111,63)));

        textArea.setBorder(null);
        textArea.setBorder(BorderFactory.createMatteBorder(4, 1, 2, 1, new Color(24,245,147)));
        textArea.setBackground(new Color(25, 22, 34));
        textArea.setForeground(new Color(24,245,147));
        textArea.setFont(new Font("Alkatra", Font.BOLD, 12));
        textArea.setMargin(new Insets(20,20,0,0));
        textArea.setEditable(false);
        //Dimensão da caixa de mensagem
        caixaMensagem.setPreferredSize(new Dimension(100, 25));  
        caixaMensagem.setBackground(new Color(25, 22, 34));
        caixaMensagem.setForeground(new Color(24,245,147));
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
        setSize(650,700);
        setTitle(nomeInicial.getText());
        setContentPane(chat);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
        Cliente cliente = new Cliente();
        cliente.ConectandoCliente();
        cliente.UpdateCliente();
    }
}


