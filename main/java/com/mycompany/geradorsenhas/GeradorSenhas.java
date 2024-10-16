package com.mycompany.geradorsenhas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class GeradorSenhas extends JFrame {
    
    private JTextField senhaGeradaField;
    private JSpinner comprimentoSpinner;
    private JCheckBox maiusculasCheckBox;
    private JCheckBox minusculasCheckBox;
    private JCheckBox numerosCheckBox;
    private JCheckBox simbolosCheckBox;
    
    public GeradorSenhas() {
        setTitle("Gerador de Senhas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 3));
        
        JPanel painelComprimento = new JPanel();
        painelComprimento.add(new JLabel("Comprimento da Senha:"));
        comprimentoSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 20, 1));
        painelComprimento.add(comprimentoSpinner);
        add(painelComprimento);

        // Opções de caracteres
        maiusculasCheckBox = new JCheckBox("Incluir Maiúsculas");
        minusculasCheckBox = new JCheckBox("Incluir Minúsculas");
        numerosCheckBox = new JCheckBox("Incluir Números");
        simbolosCheckBox = new JCheckBox("Incluir Símbolos");

        add(maiusculasCheckBox);
        add(minusculasCheckBox);
        add(numerosCheckBox);
        add(simbolosCheckBox);

        // Campo de senha gerada
        senhaGeradaField = new JTextField();
        senhaGeradaField.setEditable(false);
        add(senhaGeradaField);

        // Botão de gerar senha
        JButton gerarSenhaButton = new JButton("Gerar Senha");
        gerarSenhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });
        add(gerarSenhaButton);
    }

    private void gerarSenha() {
        int comprimento = (int) comprimentoSpinner.getValue();
        boolean incluirMaiusculas = maiusculasCheckBox.isSelected();
        boolean incluirMinusculas = minusculasCheckBox.isSelected();
        boolean incluirNumeros = numerosCheckBox.isSelected();
        boolean incluirSimbolos = simbolosCheckBox.isSelected();

        String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String simbolos = "!@#$%^&*()-_=+[]{};:,.<>?";

        StringBuilder caracteres = new StringBuilder();
        if (incluirMaiusculas) caracteres.append(maiusculas);
        if (incluirMinusculas) caracteres.append(minusculas);
        if (incluirNumeros) caracteres.append(numeros);
        if (incluirSimbolos) caracteres.append(simbolos);

        if (caracteres.length() == 0) {
            senhaGeradaField.setText("Selecione pelo menos uma opção!");
            return;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < comprimento; i++) {
            int index = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(index));
        }
        
        senhaGeradaField.setText(senha.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeradorSenhas gerador = new GeradorSenhas();
            gerador.setVisible(true);
        });
    }
}
