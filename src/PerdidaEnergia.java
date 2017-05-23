import java.util.TimerTask;

public class PerdidaEnergia extends TimerTask 
{
    private Jugador jugador;    
    public PerdidaEnergia(Jugador jugador) 
    {    
        this.jugador = jugador;
    }

    @Override
    public void run() 
    {
        if(jugador.energia > 0)
        {
            jugador.energia = jugador.energia - 5;
        }
    }
}
