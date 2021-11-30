package miPresentacion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI_Presentacion extends JFrame
{
    //Atributos
    private JButton miFotoButton, miAficionButton, espectativasButton;
    static private JPanel panelBotones, panelDatos;
    private Header header;
    private JLabel labelImage;
    private JTextArea espectativasTexto;
    private ClickListener firstListener;
    private DoubleClickListener secondListener;
    private KeyboardListener thirdListener;
    private MouseMovementListener backgroundListener;

    //Metodos

    public GUI_Presentacion(){
        initGUI();
        //Configuracion base de la ventana
        this.setTitle("Mi Presentacion");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI(){
        // definir container y layout del jframe
        // crear objetos escuchas y control y agregarle el escucha a los botones
        firstListener = new ClickListener();
        secondListener = new DoubleClickListener();
        thirdListener = new KeyboardListener();
        backgroundListener = new MouseMovementListener();
        // configurar Jcomponents
        header = new Header("ESTA ES MI PRESENTACION", new Color(86, 200, 209));
        this.add(header, BorderLayout.NORTH);

        panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(800,600));
        panelDatos.addMouseMotionListener(backgroundListener);
        panelDatos.setBorder(BorderFactory.createTitledBorder(
                null,
                "dale click a los botones para elegir que quieres ver",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.PLAIN, 20),
                Color.BLACK));
        this.add(panelDatos, BorderLayout.CENTER);

        miFotoButton = new JButton("mi Foto");
        miFotoButton.addMouseListener(firstListener);
        miAficionButton = new JButton("mis Aficiones");
        miAficionButton.addMouseListener(secondListener);
        espectativasButton = new JButton("mis Espectativas");
        espectativasButton.addKeyListener(thirdListener);
        espectativasButton.setFocusable(true);

        panelBotones = new JPanel();
        panelBotones.add(miFotoButton);
        panelBotones.add(miAficionButton);
        panelBotones.add(espectativasButton);

        this.add(panelBotones, BorderLayout.SOUTH);

        labelImage = new JLabel();
        espectativasTexto = new JTextArea(10, 12);
        espectativasTexto.setText(
                "Comienzo este curso con conocimientos de programacion orientada a objetos \n " +
                        "y un poquito de experiencia en GameDev. \n \n" +
                        "En cuanto a mis espectativas para el curso, espero poder aprender a implementar \n" +
                        "interfaces de usuario, y adquirir conocimientos que puedan ser aplicados en distintos \n" +
                        "ambitos de la programacion, como el desarrollo de videojuegos, aplicaciones o paginas web\n");
        espectativasTexto.setFont(new Font("Calibri", Font.PLAIN, 20));
        espectativasTexto.setFocusable(false);
        espectativasTexto.setBackground(new Color(251, 183, 106));
    }

    public void UpdatePanelDatos(JComponent Datos)
    {
        panelDatos.removeAll();
        panelDatos.add(Datos);
        panelDatos.revalidate();
        panelDatos.repaint();
    }

    private class ClickListener extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            ImageIcon image;
            image = new ImageIcon(getClass().getResource("/recursos/ipoe1.jpg"));
            labelImage.setIcon(image);
            UpdatePanelDatos(labelImage);
        }

    }

    private class DoubleClickListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount()% 2 == 0  && !e.isConsumed()) {
                e.consume();
                ImageIcon image;
                image = new ImageIcon(getClass().getResource("/recursos/IPOE 2.jpg"));
                labelImage.setIcon(image);
                UpdatePanelDatos(labelImage);
            }

        }
    }

    private class KeyboardListener extends KeyAdapter
    {
        char Key = KeyEvent.VK_M;
        @Override
        public void keyTyped(KeyEvent e) {
            boolean isKeyPressed = (Key == e.getKeyChar()) || (Character.toLowerCase(Key) == e.getKeyChar());
            if (isKeyPressed)
            {
                UpdatePanelDatos(espectativasTexto);
            }

        }
    }

    private class MouseMovementListener extends MouseMotionAdapter
    {
        @Override
        public void mouseMoved(MouseEvent e) {
            int green = Math.abs(255 - (e.getX() % 510));
            int blue = Math.abs(255 - (e.getY() % 510));
            int red = Math.abs(255 - ((e.getX()+e.getY()) % 510));
            panelDatos.setBackground(new Color(red, green, blue));
        }
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                miPresentacion.GUI_Presentacion mainGUI = new miPresentacion.GUI_Presentacion();
            }
        });
    }
}
