/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t6_helpgui;

import javax.help.*;
import java.net.URL;
import javax.help.HelpSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

/**
 *
 * @author Marcos Gonzalez Leon
 */
public class DI_T6_HelpGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Vars.

        // Set Frame Items.
        JFrame frame = new JFrame("Aplicacion Swing con Ayuda");        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        
        JMenu filemenu = new JMenu("Ayuda");        
            JMenuItem fileItem1 = new JMenuItem("Contenido de Ayuda");
            JMenuItem fileItem2 = new JMenuItem("About");        
        filemenu.add(fileItem1);
        filemenu.add(fileItem2);
        menubar.add(filemenu);
            
        frame.setJMenuBar(menubar);
        frame.setSize(400,400);
        frame.setVisible(true);
        // END Framing Items.
        
        // JItems Adicionales (?)        
        
        HelpSet hs = obtenFicAyuda();
        HelpBroker hb = hs.createHelpBroker();
        
        hb.enableHelpOnButton(fileItem1,"t_index",hs);

        // Se abre dicho Item al pulsar F1.
        fileItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        
        //hb.enableHelpKey(fileItem1, "top", hs);
        
    }
    
    public static HelpSet obtenFicAyuda(){
        try {
            //ClassLoader cl = LanzaAyuda.class.getClassLoader();
            File file = new 
            File(DI_T6_HelpGUI.class.getResource("help/helpset.hs").getFile());
            URL url = file.toURI().toURL();
            //URL url = new URL("jar:file:DI_T6_HelpGUI.jar!/di_t6_helpgui/help/helpset.hs");
            
            // crea un objeto Helpset
            HelpSet hs = new HelpSet(null,url);
            return hs;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Fichero HelpSet no encontrado");
            return null;
        }
    }

}
