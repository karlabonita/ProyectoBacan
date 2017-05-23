
import javax.swing.JFrame;


public class VentanaPrincipal extends JFrame implements Constantes {
    
    public Lienzo lienzo;
     //constrcutor 
    public VentanaPrincipal(){
        lienzo = new Lienzo();
        this.getContentPane().add(lienzo);
        this.setSize(ANCHURA_ESCENARIO,LARGO_ESCENARIO);
    
    }
    
    
}
   
