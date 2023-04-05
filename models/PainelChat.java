package models;

//Bibliotecas
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//A classe JFrame cria a tela visível para o cliente
public class PainelChat extends JFrame implements ActionListener {

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
    private final JLabel tituloChat = new JLabel("CHAT ONLINE");    

    //Mensagem de online do usuário
    private final JLabel online = new JLabel("Envie e receba mensagens");   

    //Botões
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair   = new JButton("Sair");
    /*
    Socket - Permite a comunicação entre os clientes
    BufferedWriter - Memória que armazena um fluxo de saída de caracteres
    */
    //Classe Cliente pode lançar exceções do tipo de Entrada e Saída (IO)
    public PainelChat() throws IOException {

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* MONTAGEM DA CAIXA DO CHAT */

        nomeInicial = new JTextField("Chat ONLINE");

        //Definindo aspecto do titulo e da mensagem de online
        tituloChat.setForeground(new Color(24,245,147));
        tituloChat.setFont(new Font("Alkatra", Font.BOLD, 18));

        online.setForeground(new Color(95, 244, 245));
        online.setFont(new Font("Alkatra", Font.BOLD + Font.ITALIC, 13));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Cor de fundo chat
        chat.setBackground(new Color(32,22,45));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Cor do texto e de sua área, fonte, tamanho e margens
        textArea.setBackground(new Color(25, 22, 34));
        textArea.setForeground(new Color(24,245,147));
        textArea.setFont(new Font("Alkatra", Font.BOLD, 12));

        textArea.setEditable(false);
        textArea.setMargin(new Insets(20,20,0,0));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        textArea.setBorder(BorderFactory.createMatteBorder(4, 1, 2, 1, new Color(24,245,147)));
        //Dimensão da caixa de mensagem
        caixaMensagem.setPreferredSize(new Dimension(100, 25));  
        caixaMensagem.setBackground(new Color(25, 22, 34));
        caixaMensagem.setForeground(new Color(24,245,147)); // verde
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
        setSize(680,700);
        setTitle(nomeInicial.getName());
        setContentPane(chat);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    //Classe Cliente pode lançar exceções do tipo de Entrada e Saída (IO)
    public PainelChat(String nome) 
    {
        /* DEFINIÇÕES GERAIS DA JANELA PRINCIPAL */
        nomeInicial = new JTextField(nome);

        setVisible(true);
        setTitle(nomeInicial.getName());
        setContentPane(chat);

        setSize(680,700);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        /* DEFINIÇÕES DOS ELEMENTOS DA JANELA */

        //Definindo aspecto do titulo e da mensagem de online
        tituloChat.setForeground(new Color(24,245,147));
        tituloChat.setFont(new Font("Alkatra", Font.BOLD, 18));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        online.setForeground(new Color(95, 244, 245));
        online.setFont(new Font("Alkatra", Font.BOLD + Font.ITALIC, 13));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Cor de fundo chat
        chat.setBackground(new Color(32,22,45));
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Cor do texto e de sua área, fonte, tamanho e margens
        textArea.setBackground(new Color(25, 22, 34));
        textArea.setForeground(new Color(24,245,147));

        textArea.setMargin(new Insets(20,20,0,0));
        textArea.setFont(new Font("Alkatra", Font.BOLD, 12));
        textArea.setBorder(BorderFactory.createMatteBorder(4, 1, 2, 1, new Color(24,245,147)));

        textArea.setEditable(false);
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Dimensão da caixa de mensagem
        caixaMensagem.setBackground(new Color(25, 22, 34));
        caixaMensagem.setForeground(new Color(24,245,147)); // verde

        caixaMensagem.setPreferredSize(new Dimension(100, 25));  
        /* * * * * * * * * * * * * * * * * * * * * * * * */

        //Botões
        botaoEnviar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoSair.addActionListener(this);
        // Método faz com que o botão, sempre que for clicado, 
        // chame o método actionPerformed do ActionListener    
        
        botaoEnviar.setForeground(Color.BLACK);
        botaoLimpar.setForeground(Color.BLACK);
        botaoSair.setForeground(Color.BLACK);
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
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
