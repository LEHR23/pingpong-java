package juego;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Pingpong extends JFrame implements KeyListener {

    private JLabel medio, rizq, rder, pelota, contizq, contder;
    private Icon m, p;
    private int y = 5, x = -5, puntosder, puntosizq;
    private AudioClip rebote;

    public Pingpong() {
        setLayout(null);
        setBounds(0, 0, 800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //medio
        ImageIcon mi = new ImageIcon(getClass().getResource("/imagenes/n.jpg"));
        m = new ImageIcon(mi.getImage().getScaledInstance(5, 500,
                Image.SCALE_DEFAULT));
        medio = new JLabel(m);
        medio.setBounds(400, 0, 5, 500);
        add(medio);

        //rizq
        rizq = new JLabel(m);
        rizq.setBounds(0, 225, 5, 50);
        add(rizq);

        //rder
        rder = new JLabel(m);
        rder.setBounds(780, 225, 5, 50);
        add(rder);

        //pelota
        ImageIcon pi = new ImageIcon(getClass().getResource(
                "/imagenes/p.png"));
        p = new ImageIcon(pi.getImage().getScaledInstance(10, 10,
                Image.SCALE_DEFAULT));
        pelota = new JLabel(p);
        pelota.setBounds(397, 250, 10, 10);
        add(pelota);

        //contador derecha
        puntosder = 0;
        contder = new JLabel(puntosder + "");
        contder.setBounds(500, 10, 50, 50);
        contder.setFont(new Font("DigifaceWide", 2, 25));
        contder.setForeground(Color.WHITE);
        add(contder);
        //contador izquierda
        puntosizq = 0;
        contizq = new JLabel("" + puntosizq);
        contizq.setBounds(300, 10, 50, 50);
        contizq.setFont(new Font("DigifaceWide", 2, 25));
        contizq.setForeground(Color.WHITE);
        add(contizq);
        
        //sonido
        rebote = java.applet.Applet.newAudioClip(getClass().getResource
        ("/imagenes/reb.wav"));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT && rizq.getY() > 0) {
            rizq.setLocation(0, rizq.getY() - 8);
        }
        if (e.getKeyCode() == e.VK_RIGHT && rizq.getY() < 420) {
            rizq.setLocation(0, rizq.getY() + 8);
        }
        if (e.getKeyCode() == e.VK_ENTER) {
            ti.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
//movimiento de la pelota
    Timer ti = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pelota.setLocation(pelota.getX() + x, pelota.getY() + y);
            //colision raqueta izquierda
            if (pelota.getX() < 7 && pelota.getY() >= rizq.getY()
                    && pelota.getY() <= rizq.getY() + 50) {
                x = 8;
                rebote.play();
            }
            //colision raqueta derecha
            if (pelota.getX() > 778 && pelota.getY() >= rder.getY()
                    && pelota.getY() <= rder.getY() + 50) {
                x = -8;
                rebote.play();
            }
            //colision parte superior e inferior del tablero
            if (pelota.getY() > 460 || pelota.getY() < 5) {
                y = y * -1;
                rebote.play();
            }
            //inicio de movimiento raqueta derecha
            if (pelota.getX() > 400) {
                if (pelota.getY() - 40 > rder.getY()&& x > 0) {
                    rder.setLocation(rder.getX(), rder.getY() + 5);
                }
                if (pelota.getY() - 20 < rder.getY() && x > 0) {
                    rder.setLocation(rder.getX(), rder.getY() - 5);
                }
            }
            if(rder.getY() > 230 && x < 0 || rder.getY() < 220 && x < 0){
                if(rder.getY() > 230 ){
                    rder.setLocation(rder.getX(), rder.getY() - 5);
                }
                if(rder.getY() < 220 ){
                    rder.setLocation(rder.getX(), rder.getY() + 5);
                }
            }
            //puntaje
            if (pelota.getX() > 790 || pelota.getX() < 0) {
                ti.stop();
                if (pelota.getX() < 0) {
                    puntosder++;
                    contder.setText("" + puntosder);
                    rder.setLocation(780, 250);
                    rizq.setLocation(0, 250);
                } else {
                    puntosizq++;
                    contizq.setText("" + puntosizq);
                    rder.setLocation(780, 250);
                    rizq.setLocation(0, 250);
                }
                pelota.setLocation(397, 250);
                //gano?
                if (puntosder == 5) {
                    JOptionPane.showMessageDialog(null, "HAS PERDDO :(");
                    puntosder = puntosizq = 0;
                    contder.setText("0");
                    contizq.setText("0");
                    rder.setLocation(780, 250);
                    rizq.setLocation(0, 250);
                }
                if (puntosizq == 5) {
                    JOptionPane.showMessageDialog(null, "FELICIDADES\n"
                            + "HAS GANADO!!! XD");
                    puntosder = puntosizq = 0;
                    contder.setText("0");
                    contizq.setText("0");
                    rder.setLocation(780, 250);
                    rizq.setLocation(0, 250);
                }
            }
        }
    });

}
