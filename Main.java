package AQUI4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.WindowConstants;

public class Main extends JFrame{
    
    private Font mainFont = new Font("Texto aqui", Font.BOLD, 18);
    JTextField tfFirstName, tfLastName;
    JLabel lbWelcome;

    public void inicio(){
 
        /***************** Form Panel *****************/
        JLabel lbFirstName = new JLabel("Primeiro nome");
        lbFirstName.setFont(mainFont);
        
        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        JLabel lbLastName = new JLabel("Ultimo nome");
        lbFirstName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);


        /***************** Welcome Panel *****************/
        lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        /***************** Button Panel *****************/
        JButton btnOK = new JButton("MEU OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                lbWelcome.setText("Eae " + firstName + " " + lastName + ", bom? ");

            }
            
        });

        JButton btnCLEAR = new JButton("MEU CLEAR");
        btnCLEAR.setFont(mainFont);
        btnCLEAR.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                tfFirstName.setText("");
                tfLastName.setText("");
                lbWelcome.setText("");
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1 ,2, 5, 5));
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnCLEAR);


        JPanel meuPanel = new JPanel();
        meuPanel.setLayout(new BorderLayout());
        meuPanel.setBackground(new Color(128, 128, 200));

        meuPanel.add(formPanel, BorderLayout.NORTH);
        meuPanel.add(lbWelcome, BorderLayout.CENTER);
        meuPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(meuPanel);

        //titulo da janela
        setTitle("Testes Testados");
        
        //tamanho/tamanho minimo
        setSize(400, 500);
        setMinimumSize(new Dimension(200, 300));
        
        //para fechar a pagina
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Tornar janela visivel
        setVisible(true);
    }

    public static void main(String[] args){
        Main myFrame = new Main();
        myFrame.inicio();
    }
}
