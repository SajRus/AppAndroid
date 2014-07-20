
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Frame extends javax.swing.JFrame {

    private List<Record> lista = new ArrayList<Record>();
    private String path = "record.txt";
    private PrintWriter fout;
    private BufferedReader fin;
    private StringTokenizer st;
    private JFileChooser chooser = null;

    public Frame() {
        initComponents();
        
        try {

                fout = new PrintWriter(new FileWriter(path,true));

            fout.close();
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }


       
        Toolkit mioToolkit = Toolkit.getDefaultToolkit();
        Dimension dimensioniSchermo = mioToolkit.getScreenSize();
        this.setBounds((int) (dimensioniSchermo.getWidth() / 2) - (this.getWidth() / 2), (int) (dimensioniSchermo.getHeight() / 2) - (this.getHeight() / 2), this.getWidth(), this.getHeight());
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

    
         init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox();
        txtuser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpsw = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        psw = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        back = new javax.swing.JButton();
        delet = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ricorda Password");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Visualizza Password");

        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });

        txtuser.setBackground(new java.awt.Color(255, 255, 255));
        txtuser.setEditable(false);
        txtuser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Username");

        txtpsw.setBackground(new java.awt.Color(255, 255, 255));
        txtpsw.setEditable(false);
        txtpsw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Aggiungi un nuovo elemento");

        user.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel5.setText("Username");

        psw.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("Password");

        nome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Nome");

        save.setText("Salva");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });

        delete.setText("Pulisci");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        back.setText("Esegui backup");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        delet.setText("Elimina elemento");
        delet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Seleziona");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtpsw, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2))))
                        .addContainerGap(50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                    .addComponent(psw, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(delete)
                                .addGap(202, 202, 202)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(save)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(47, 47, 47))))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(delet)
                .addContainerGap(262, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(back)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpsw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addComponent(delet)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(psw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(save))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        user.setText("");
        nome.setText("");
        psw.setText("");
        nome.requestFocus();
    }//GEN-LAST:event_deleteMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked

        chooser = new JFileChooser();
        int ret = chooser.showDialog(null, "Salva");
        if (ret == JFileChooser.APPROVE_OPTION) {
            String pathnew = chooser.getSelectedFile().toString() + ".txt";
            try {
                fin = new BufferedReader(new FileReader(path));
                fout = new PrintWriter(new FileWriter(pathnew));

                String s = null;
                s = fin.readLine();
                fout.println("Denominazione  |  Username  |  Password");
                fout.println("***************************************");

                while (s != null) {
                    st = new StringTokenizer(s, ";");
                    fout.println(st.nextToken() + "    " + st.nextToken() + "    " + st.nextToken());
                    fout.println("***************************************");
                    s = fin.readLine();
                }
                fin.close();
                fout.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }//GEN-LAST:event_backMouseClicked

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        if (nome.getText().equals("") || user.getText().equals("") || psw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Devi riempire tutti i campi banana:) !!", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
        	}
        else //mancante
        	{

            try {
                lista.add(new Record(nome.getText(), user.getText(), psw.getText()));
                fout = new PrintWriter(new FileWriter(path));
                for (Record x : lista) {
                    fout.println(x.toRecord());
                }
                fout.close();
                init();
                user.setText("");
                nome.setText("");
                psw.setText("");
                nome.requestFocus();
                JOptionPane.showMessageDialog(null, "Dati salvati con successo  :)", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }//GEN-LAST:event_saveMouseClicked

    private void deletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletMouseClicked
        if (!lista.isEmpty()) {

            int p = combo.getSelectedIndex();
            int scielta = JOptionPane.showConfirmDialog(null, "Sei sicura di voler eliminare:\n"
                    + "Nome: " + lista.get(p).getNome()
                    + "\nUser: " + lista.get(p).getUser()
                    + "\nPassword: " + lista.get(p).getPsw()
                    + "", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (scielta == 0) {
                try {
                    fout = new PrintWriter(new FileWriter(path));
                    lista.remove(p);	//istruzione mancante
                    for (Record x : lista) {
                        fout.println(x.toRecord());
                    }
                    fout.close();
                    init();
                    JOptionPane.showMessageDialog(null, "Record eliminato !!  :)", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                }
            }

        }

    }//GEN-LAST:event_deletMouseClicked

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged
        int punt = combo.getSelectedIndex();
         System.out.println(punt);
        if (punt >= 0) {
            txtuser.setText(lista.get(punt).getUser());
            txtpsw.setText(lista.get(punt).getPsw());
        }
    }//GEN-LAST:event_comboItemStateChanged

    private void init() {

        try {
            lista.clear();

            fin = new BufferedReader(new FileReader(path));

            String s = null;
            s = fin.readLine();

            while (s != null) {
                st = new StringTokenizer(s, ";");

                lista.add(new Record(st.nextToken(), st.nextToken(), st.nextToken()));
                s = fin.readLine();
            }
            fin.close();
            // System.out.println("*****************************************************");
            // for (Record x : lista) { System.out.println(x.toRecord()); }

            combo.removeAllItems();

            if (!lista.isEmpty()) {
                Collections.sort(lista);

                for (Record x : lista) {
                    // System.out.println(x.toRecord());
                    combo.addItem(x.getNome());
                    //System.out.println(x.getNome());
                }
            }
            txtuser.setText("");
            txtpsw.setText("");

        } catch (Exception ex) {
            System.out.println("errore durante apertura del file " + ex.getMessage());
        }

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception e) {
                }
                new Frame().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox combo;
    private javax.swing.JButton delet;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField psw;
    private javax.swing.JButton save;
    private javax.swing.JTextField txtpsw;
    private javax.swing.JTextField txtuser;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

    class Record implements Comparable {

        private String user = "", psw = "", nome = "";

        Record(String n, String u, String p) {
            this.nome = n;
            this.user = u;
            this.psw = p;
        }

        private String getNome() {
            return this.nome;
        }

        private String getUser() {
            return this.user;
        }

        private String getPsw() {
            return this.psw;
        }

        private String toRecord() {
            return (this.nome + ";" + this.user + ";" + this.psw);
        }

        public int compareTo(Object o) {
            Record R = (Record) o;
            return (this.nome.compareTo(R.nome));
        }
    }
}

