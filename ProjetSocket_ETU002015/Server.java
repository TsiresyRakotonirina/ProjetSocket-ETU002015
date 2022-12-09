import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.io.InputStreamReader;

import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.awt.Color;




public class Server extends JFrame{
    //SOCKET    
        
    static ServerSocket serverSocket;
    static Socket clientSocket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    
    //SWING 
    static JTextArea jta;
    static JTextField jtf;

                            //FONCTIONS
        
    private static void sendMsg(ActionEvent evt) {
        try {
            // TODO add your handling code here:
            // Socket clientSocket = new Socket("localhost",5000);
            // DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            // DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            String msgout = "";
            msgout = jtf.getText().trim();
            dataOutputStream.writeUTF(msgout);
        } catch (IOException ex) { }
    }
    
        // private void receiveMsg(ActionEvent evt) {
        //     // TODO add your handling code here:
        // }
    
    private static void jtf_ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_msg_txtActionPerformed
        // TODO add your handling code here:
    }


    private void fenetre(){
        
        //JFrame fen = new JFrame();
        //JPanel contenu = new JPanel();
        JScrollPane jScrollpane = new JScrollPane();
        jta = new JTextArea();
        JLabel lb = new JLabel();
        JButton bouton = new JButton("ENVOYER");
        bouton.setBackground(Color.YELLOW);
        //bouton.setSize(100, 50);
        jtf = new JTextField();

        jta.setColumns(20);
        jta.setRows(5);
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setBackground(Color.LIGHT_GRAY);
        jScrollpane.setViewportView(jta);

        jtf.setText("Ecrire");
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtf_ActionPerformed(evt);
            }
        });

        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sendMsg(evt);
                jtf.setText(null);
            }
        });

        lb.setText("SERVER");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtf, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bouton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollpane, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(lb, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(lb)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollpane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(bouton))
                .addGap(26, 26, 26))
        );

        /////////////////////////////////////////////////////////////////////
    
                
        pack();
        //fen.setSize(500, 500);
        //fen.setLayout(new BoxLayout(fen.getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);
        setLocation(0, 0);
        getContentPane().setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fen.getContentPane().add(contenu);
        setVisible(true);
    }


        
    public static void main(String[] args) {
        new Server().fenetre();
        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            String msg = "";
            while(!msg.equals("exit")){
                msg = dataInputStream.readUTF();
                jta.setText(jta.getText().trim() + "\n" + "CLIENT : "+ msg);
                //System.out.println("huhu");
            }
               
        } catch (IOException ex) {}
    }
}