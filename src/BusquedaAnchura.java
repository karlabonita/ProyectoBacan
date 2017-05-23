
import java.util.ArrayList;


public class BusquedaAnchura implements Constantes
{
    private final Escenario escenario;
    private ArrayList<Estado> colaEstados;
    private ArrayList<Estado> historial;    
    private boolean exito;
    private Estado inicial;
    private Estado objetivo;
    
    public BusquedaAnchura(Escenario escenario)
    {                   
        this.escenario = escenario;
    }
    
    public ArrayList<Character> buscar(int x1, int y1, int x2, int y2)
    {
        exito = false;
        colaEstados = new ArrayList<>();
        historial   = new ArrayList<>();
                
        inicial = new Estado(x1,y1,'N',null);
        objetivo=new Estado(x2,y2,'P',null);
        colaEstados.add(inicial);
        historial.add(inicial);
        
        if(inicial.equals(objetivo)) exito = true;
        
        Estado temp;
        while(!colaEstados.isEmpty() && !exito)
        {
            temp = colaEstados.get(0);
            //System.out.println(temp.toString());
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);           
        }
        
        if(exito)
        {
            ArrayList<Character> pasos = new ArrayList<>();        
            Estado predecesor=objetivo;
            do
            {
                if(predecesor.oper != 'N')
                {
                    pasos.add(predecesor.oper);
                }
                predecesor=predecesor.predecesor;                
            }
            while(predecesor !=null);
            return pasos;
        }
        else
        {            
            return null;
        }        
    }
    
    private void moverDerecha(Estado e){
        
        if(e.x< NUMERO_CELDAS_ANCHO -1)
        {
            if(escenario.celdas[e.x+1][e.y].tipo != OBSTACULO)
            {
            Estado derecha = new Estado(e.x+1, e.y,'R',e);
            if(!historial.contains(derecha)){
                colaEstados.add(derecha);
                historial.add(derecha);
                
                if(derecha.equals(objetivo)){
                    objetivo=derecha;
                    exito=true;
                
                }
            }
        }    
      }
    }
    
    private void moverIzquierda(Estado e)
    {
        if(e.x > 0)
        {
            if(escenario.celdas[e.x-1][e.y].tipo!= OBSTACULO)
            {
                Estado izquierda = new Estado(e.x-1,e.y,'L',e);
                if(!historial.contains(izquierda))
                {
                    colaEstados.add(izquierda);
                    historial.add(izquierda);
                    if(izquierda.equals(objetivo))
                    {
                        objetivo = izquierda;
                        exito=true;            
                    }            
                }
            }
        }
    }
    
    private void moverAbajo(Estado e)
    {
        if(e.y+1 < NUMERO_CELDAS_LARGO){
            if(escenario.celdas[e.x][e.y+1].tipo != OBSTACULO)
            {
            Estado abajo = new Estado(e.x,e.y+1,'D',e);
            if(!historial.contains(abajo)){
                colaEstados.add(abajo);
                historial.add(abajo);
             if(abajo.equals(objetivo)){
                 objetivo=abajo;
                 exito=true;
             
               }
             }
            
            }
        }
    }
    
    private void moverArriba(Estado e){
        if(e.y>0){
            if(escenario.celdas[e.x][e.y-1].tipo != OBSTACULO)
            {
            Estado arriba = new Estado(e.x,e.y-1,'U',e);
            if(!historial.contains(arriba)){
                colaEstados.add(arriba);
                historial.add(arriba);
            if(arriba.equals(objetivo)){
                objetivo=arriba;
                exito=true;
 
             }
            }           
           }
        }   
    }
}







