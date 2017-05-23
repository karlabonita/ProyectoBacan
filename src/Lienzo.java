
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Timer;


public class Lienzo extends Canvas implements Constantes {
    public Escenario escenario;
    public Timer lanzadorTareas;
    public Timer cronometro;
    public Lienzo(){
    
        
            
            escenario = new Escenario(this);
            cronometro = new Timer();
            cronometro.scheduleAtFixedRate(escenario.jugador.perdidaEnergia,0,5000);
            
            lanzadorTareas = new Timer();
            lanzadorTareas.scheduleAtFixedRate(escenario.jugador, 0, 1000);
            
            for(int i=0; i < escenario.adversarios.length; i++)
            {
                if(escenario.adversarios[i] != null)
                {
                    lanzadorTareas.scheduleAtFixedRate(escenario.adversarios[i], 0, 1000);
                }
            }
    }
    
    @Override
    public void paint(Graphics g){
        escenario.paintComponent(g);
    
    }
    
    
    
}
