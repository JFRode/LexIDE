package br.univali.lexide.principal;

import br.univali.lexide.exception.BusinessException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class LexIDE extends javax.swing.JFrame {

    public LexIDE() {
        initComponents();
        principal.setLayout(new BorderLayout());
        principal.add(toolBar, BorderLayout.NORTH);
        principal.add(scrollPane_codigo, BorderLayout.CENTER);
        principal.add(scrollPane_saida, BorderLayout.SOUTH);
    }

    public void selecionarArquivo() {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Código fonte LexIDE", "lexIDE");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = new File(String.valueOf(fileChooser.getSelectedFile()));
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                String data = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((data = reader.readLine()) != null) {
                    stringBuilder.append(data);
                    stringBuilder.append("\n");
                }
                textPane_codigo.setText(stringBuilder.toString());
                fileReader.close();
                reader.close();
                textPane_saida.setText("");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Arquivo não encontrado.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo.");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane_codigo = new javax.swing.JScrollPane();
        textPane_codigo = new javax.swing.JTextPane();
        scrollPane_saida = new javax.swing.JScrollPane();
        textPane_saida = new javax.swing.JTextPane();
        toolBar = new javax.swing.JToolBar();
        button_novo = new javax.swing.JButton();
        button_abrir = new javax.swing.JButton();
        button_salvar = new javax.swing.JButton();
        button_compilar = new javax.swing.JButton();
        principal = new javax.swing.JPanel();

        scrollPane_codigo.setViewportView(textPane_codigo);

        textPane_saida.setEditable(false);
        textPane_saida.setBackground(new java.awt.Color(220, 219, 219));
        textPane_saida.setMinimumSize(new java.awt.Dimension(50, 120));
        textPane_saida.setPreferredSize(new java.awt.Dimension(50, 130));
        scrollPane_saida.setViewportView(textPane_saida);

        toolBar.setRollover(true);

        button_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/lexide/imagens/novo.png"))); // NOI18N
        button_novo.setFocusable(false);
        button_novo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_novo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_novoActionPerformed(evt);
            }
        });
        toolBar.add(button_novo);

        button_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/lexide/imagens/abrir.png"))); // NOI18N
        button_abrir.setFocusable(false);
        button_abrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_abrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_abrirActionPerformed(evt);
            }
        });
        toolBar.add(button_abrir);

        button_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/lexide/imagens/salvar.png"))); // NOI18N
        button_salvar.setFocusable(false);
        button_salvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_salvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_salvarActionPerformed(evt);
            }
        });
        toolBar.add(button_salvar);

        button_compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/univali/lexide/imagens/compilar.png"))); // NOI18N
        button_compilar.setFocusable(false);
        button_compilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_compilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_compilarActionPerformed(evt);
            }
        });
        toolBar.add(button_compilar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LexIDE");
        setSize(new java.awt.Dimension(998, 673));

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_compilarActionPerformed
        if (!textPane_codigo.getText().equals("")) {
            textPane_saida.setText("");
            Lexico lexico = new Lexico();
            Sintatico sintatico = new Sintatico();
            Semantico semantico = new Semantico();

            lexico.setInput(textPane_codigo.getText());
            
            try {
                sintatico.parse(lexico, semantico);
                textPane_saida.setText("CONSTRUÍDO COM SUCESSO.");
                textPane_saida.setForeground(new Color(34, 139, 34));
            } catch (LexicalError e) {
                textPane_saida.setText("Erro léxico na posição: " + e.getPosition());
                textPane_saida.setForeground(Color.RED);
            } catch (SyntaticError e) {
                textPane_saida.setText("Erro Sintático na posição: " + e.getPosition());
                textPane_saida.setForeground(Color.RED);
            } catch (SemanticError e) {
                textPane_saida.setText("Erro Semantico na posição: " + e.getPosition());
                textPane_saida.setForeground(Color.RED);
            } catch (BusinessException ex) {
                textPane_saida.setText(ex.getMessage());
                textPane_saida.setForeground(Color.RED);
            }
        } else {
            textPane_saida.setText("É necessário escrever um codigo para compilar.");
            textPane_saida.setForeground(new Color(205, 92, 92));
        }
    }//GEN-LAST:event_button_compilarActionPerformed

    private void button_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_salvarActionPerformed
        String codigoGravacao = textPane_codigo.getText();
        if (!codigoGravacao.equals("")) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Código fonte LexIDE", "lexIDE");
                fileChooser.setFileFilter(filter);

                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".lexIDE");
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(codigoGravacao);

                    bw.close();
                    fw.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "É necessário escrever um codigo para salvar.");
        }
    }//GEN-LAST:event_button_salvarActionPerformed

    private void button_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_novoActionPerformed
        if (!textPane_codigo.getText().equals("")) {
            int resp = JOptionPane.showConfirmDialog(this, "Se não foi salvo o codigo será perdido. Deseja realmente criar um novo?");
            if (resp == 0) {
                textPane_codigo.setText("");
            }
        }
    }//GEN-LAST:event_button_novoActionPerformed

    private void button_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_abrirActionPerformed
        if (!textPane_codigo.getText().equals("")) {
            int resp = JOptionPane.showConfirmDialog(this, "Se não foi salvo o codigo será perdido. Deseja realmente abrir um arquivo?");
            if (resp == 0) {
                selecionarArquivo();
            }
        } else {
            selecionarArquivo();
        }
    }//GEN-LAST:event_button_abrirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LexIDE.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LexIDE.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LexIDE.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LexIDE.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LexIDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_abrir;
    private javax.swing.JButton button_compilar;
    private javax.swing.JButton button_novo;
    private javax.swing.JButton button_salvar;
    private javax.swing.JPanel principal;
    private javax.swing.JScrollPane scrollPane_codigo;
    private javax.swing.JScrollPane scrollPane_saida;
    private javax.swing.JTextPane textPane_codigo;
    private javax.swing.JTextPane textPane_saida;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
