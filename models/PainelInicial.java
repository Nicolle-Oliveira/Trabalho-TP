// Package
package models;

// Bibliotecas
import java.awt.*;
import javax.swing.*;

public class PainelInicial extends JPanel
{
    public PainelInicial()
    {
        //Fonte dados iniciais
        setFont(new Font("Alkatra", Font.BOLD, 15));
        setForeground(Color.BLACK);
        //Borda e Background
        setBackground(new Color(243, 230, 229));
        setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, new Color(162, 63, 68)));

        JLabel nomeLabel = new JLabel(" ");
        JTextField nomeInicial = new JTextField(" ");
        JTextArea testArea = new JTextArea();
        add (testArea);
        add(nomeLabel); 
        add(nomeInicial);
    }
    public PainelInicial(String mensagem)
    {

        JPanel inicio       = new JPanel();
        JLabel inicioLabel  = new JLabel(mensagem, SwingConstants.CENTER);
        inicio.add(inicioLabel);
        inicioLabel.setForeground(new Color(76, 74, 72));

        //Borda e Background
        setBackground(new Color(242, 235, 220));
        setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(89, 124, 168)));

    }
    public PainelInicial(String defaultL,JTextField defaultF)
    {
        //Borda e Background
        setBackground(new Color(242, 235, 220));
        setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(89, 124, 168)));

        JLabel nomeLabel = new JLabel(defaultL);
        nomeLabel.setFont(new Font("Alkatra", Font.BOLD, 18));
        nomeLabel.setForeground(new Color(76, 74, 72));
        nomeLabel.setPreferredSize(new Dimension(60,40));

        /* 
            (new Color(47,44,46));   //Cor principal (cinza)
            (new Color(89, 124, 168));    //Cor secundaria(azul)

            (new Color(242, 235, 220)); //branco
            (new Color(76, 74, 72));//cinza preto
        */
        defaultF.setBackground(null);
        defaultF.setForeground(new Color(76, 74, 72));
        defaultF.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(89, 124, 168)));
        defaultF.setFont(new Font("Alkatra", Font.BOLD, 15));
        
        add(nomeLabel); 
        add(defaultF);
    }
}