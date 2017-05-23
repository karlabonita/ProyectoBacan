import java.util.ArrayList;
import java.util.TimerTask;


public class Adversario extends TimerTask implements Constantes 
{
    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public BusquedaAnchuraAdversario inteligencia;
    public Celda adversario;
    public Adversario(Escenario escenario, int x, int y)
    {        
        posicionX=x;
        posicionY=y;
        this.escenario=escenario;
        adversario = new Celda(x,y,escenario.celdas[posicionX][posicionY].tipo);
        
        
        inteligencia = new BusquedaAnchuraAdversario(escenario);    
    }
    
    public boolean puedeMoverse(int posicionX, int posicionY)
    {
       return (posicionX < NUMERO_CELDAS_ANCHO && posicionX > - 1 && posicionY <NUMERO_CELDAS_LARGO && posicionY>-1
               && escenario.celdas[posicionX][posicionY].tipo != OBSTACULO);
    }
    
    public void moverArriba()
    {        
        if(posicionY>-1 && puedeMoverse (posicionX, posicionY-1 ))
        {   
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[posicionX][--posicionY].tipo='A';
        }
    }
    
    public void moverAbajo()
    {   
        if(posicionY<NUMERO_CELDAS_LARGO && puedeMoverse(posicionX,posicionY +1))
        {              
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[posicionX][++posicionY].tipo='A';
    
        }
    }
    
    public void moverIzquierda()
    {     
        if(posicionX>0 && puedeMoverse(posicionX -1,posicionY))
        {          
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[--posicionX][posicionY].tipo='A';
            
        }
    }
    
    public void moverDerecha()
    {     
        if(posicionX< NUMERO_CELDAS_ANCHO && puedeMoverse(posicionX +1,posicionY))
        {
            escenario.celdas[posicionX][posicionY].tipo   = 'V';
            escenario.celdas[++posicionX][posicionY].tipo = 'A';
        }
    }
    
    public void run()
    {
        ArrayList<Character> ruta = inteligencia.buscar(posicionX, posicionY, escenario.jugador.posicionX, escenario.jugador.posicionY);
        
        if(ruta != null && ruta.size() > 0)
        {
//            if(escenario.jugador.energia <= 0)
//            {
//                this.cancel();
//            }
            switch(ruta.get(ruta.size()-1))
            {
                 case 'D': moverAbajo(); break;
                 case 'U': moverArriba(); break;
                 case 'R': moverDerecha(); break;
                 case 'L': moverIzquierda(); break;
            }
            
         
            System.out.println("Adversario en : "+ posicionX + ","+ posicionY);
            if(escenario.jugador.posicionX==this.posicionX && escenario.jugador.posicionY==this.posicionY){
                escenario.celdas[posicionX][posicionY].tipo='T';
                System.out.println("Tipo: "+ escenario.celdas[posicionX][posicionY].tipo);
                escenario.jugador.cancel();
                escenario.adversarios[0].cancel();
                escenario.adversarios[1].cancel();
                escenario.adversarios[2].cancel();
                
            }
               escenario.dondeSeDibuja.repaint();
        }
    }
    
}
