
import java.util.ArrayList;
import java.util.TimerTask;


public class Jugador extends TimerTask implements Constantes
{
    public int posicionX;
    public int posicionY;
    
    public static int energia;
    public Escenario escenario;
    public BusquedaAnchura inteligencia;
    public TimerTask perdidaEnergia;
    
    public Jugador(Escenario escenario){
        energia=10;
        posicionX=0;
        posicionY=0;
        this.escenario=escenario;        
        inteligencia = new BusquedaAnchura(escenario);
        perdidaEnergia = new PerdidaEnergia(this);
    }
    
     public boolean puedeMoverse(int posicionX, int posicionY)
     {
        return (posicionX<NUMERO_CELDAS_ANCHO && posicionX>-1 && posicionY<NUMERO_CELDAS_LARGO && posicionY>-1 && escenario.celdas[posicionX][posicionY].tipo!= OBSTACULO);
   }
    
    public void moverArriba()
    {
        if(posicionY>-1 && puedeMoverse (posicionX, posicionY-1 ))
        {   
            if(escenario.esRecompensa(posicionX,posicionY-1)){
                 energia+=10;
             }
            escenario.celdas[posicionX][posicionY].tipo = 'V';
            escenario.celdas[posicionX][--posicionY].tipo = JUGADOR;
        }
    }
    
    public void moverAbajo(){
        if(posicionY<NUMERO_CELDAS_LARGO && puedeMoverse(posicionX,posicionY +1)){  
            if(escenario.esRecompensa(posicionX,posicionY+1)){
                 energia+=10;
             }
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[posicionX][++posicionY].tipo = JUGADOR;
    
        }
    }
    
    public void moverIzquierda(){
        if(posicionX>0 && puedeMoverse(posicionX -1,posicionY)){
            if(escenario.esRecompensa(posicionX-1,posicionY)){
                 energia+=10;
             }
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[--posicionX][posicionY].tipo = JUGADOR;
            
        }
    }
    
    public void moverDerecha(){
        if(posicionX< NUMERO_CELDAS_ANCHO && puedeMoverse(posicionX +1,posicionY)){
            if(escenario.esRecompensa(posicionX+1,posicionY)){
                 energia+=10;
             }
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[++posicionX][posicionY].tipo = JUGADOR;
   
        }
    }
    
    private ArrayList<Character> buscarRutaCorta(ArrayList<Character> ruta1, ArrayList<Character> ruta2)
    {
        ArrayList<Character> menor = ruta1;
        if(menor == null) menor = ruta2;
        if(ruta2 != null && menor.size() > ruta2.size())
        {
            menor = ruta2;
        }        
        return menor;
    }
    
    @Override
    public void run()
    {
        ArrayList<Celda> recompensas = escenario.obtenerRecompensas();
        ArrayList<Character> ruta = null;
        if(recompensas.size() > 0)
        {
            for(Celda celda: recompensas)
            {
                ArrayList<Character> aux = inteligencia.buscar(posicionX, posicionY, celda.i, celda.j);
                //System.out.println("celda"+celda.i+" "+celda.j);
                //System.out.println(aux);
                ruta = buscarRutaCorta(ruta, aux);                  
            }
        }
        else
        {           
            ruta = inteligencia.buscar(posicionX, posicionY, 14, 14);            
        }
        System.out.println(ruta);        
        
        if(ruta != null && ruta.size() > 0)
        {
            if(escenario.jugador.energia <= 0)
            {
               this.cancel();
            }
            switch(ruta.get(ruta.size()-1))
            {
                 case 'D': escenario.jugador.moverAbajo(); break;
                 case 'U': escenario.jugador.moverArriba(); break;
                 case 'R': escenario.jugador.moverDerecha(); break;
                 case 'L': escenario.jugador.moverIzquierda(); break;
            }
            escenario.dondeSeDibuja.repaint();
        }
    }
    
}
