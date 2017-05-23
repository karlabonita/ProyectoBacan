import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Celda extends JComponent implements Constantes
{
    public int i;
    public int j;
    public char tipo;
    
    //constructor 
    public Celda(int i, int j, char tipo)
    {
        this.i=i;
        this.j=j;
        this.tipo=tipo;
    
    }
    
    public void esPared(){
        tipo='O';
    
    }
    
    public void esFinal(){
        tipo='F';
    
    }
    
    //metodo para dibujar casilla
    @Override
    public void paintComponent(Graphics g)
    {
        switch(tipo){
            case 'T':
                EventQueue.invokeLater(new Runnable(){
                @Override
                  public void run(){
                JOptionPane.showMessageDialog(Celda.this, "Perdiste");
                //break;
                  }
            });
            case 'J':
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(Jugador.energia), i* PIXEL_CELDA + 10, j * PIXEL_CELDA - 10);
                g.setColor(COLOR_JUGADOR);break;
            case 'O':g.setColor(COLOR_OBSTACULO);break;
            case 'V':g.setColor(COLOR_CAMINO);break;
            case 'A':g.setColor(COLOR_ADVERSARIO);break;
            case 'F':g.setColor(COLOR_FINAL);break;
            case 'R':g.setColor(Color.PINK);break;
       
        }
        g.fillRect(i+(i*PIXEL_CELDA),j+(j*PIXEL_CELDA), PIXEL_CELDA, PIXEL_CELDA);
    
    }

    void esRecompensa() {
        tipo='R';
    }
}
