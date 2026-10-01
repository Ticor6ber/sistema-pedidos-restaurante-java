
package piaiis;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class PiaIIS {


    public static void main(String[] args) 
    {
        try {
            // Aplicar el estilo Cupertino Light
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new Interfaz().setVisible(true));
    }
}
