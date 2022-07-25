import java.awt.FlowLayout;
import javax.swing.JFrame;
/**
 * Clase autónoma para visualizar la ventana
 * 
 * @author mmirabete
 *
 */
public class Principal {
   public static void main(String[] args) {
      JFrame frame=new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
      frame.getContentPane().add(new PersonaUI());
      frame.setSize(670, 250);
      frame.setVisible(true);
      frame.setTitle("CRUD Persona");
   }
}