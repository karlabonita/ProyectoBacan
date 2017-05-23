
import java.awt.Color;
import java.util.Random;


public interface Constantes {
    //constantes relacionadas con el tamaño del escenarios
    public final int PIXEL_CELDA =32;
    public final int NUMERO_CELDAS_ANCHO=15;
    public final int NUMERO_CELDAS_LARGO=15;
    public final int ANCHO_BORDE_VENTANA=30;
    public final int LARGO_BORDE_VENTANA=50;
    public final int ANCHURA_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_ANCHO)+
                                        ANCHO_BORDE_VENTANA;
    public final int LARGO_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_LARGO)+
                                       LARGO_BORDE_VENTANA;
    
    
    //constantes relacionadas con la implementacion de las celdas
    public final char JUGADOR='J';
    public final char CAMINO='V';
    public final char OBSTACULO='O';
    public final char ADVERSARIO ='A';
    public final char FINAL ='F';
    public final char RECOMPENSA = 'R';
    
    public final Color COLOR_JUGADOR=Color.BLUE;
    public final Color COLOR_CAMINO= Color.GREEN;
    public final Color COLOR_OBSTACULO=Color.BLACK;
    public final Color COLOR_ADVERSARIO= Color.RED;
    public final Color COLOR_FINAL = Color.MAGENTA;
    
    //funcion para generar un aleatorio
    default int numeroAleatorio(int minimo, int maximo){
        Random random = new Random();
        int numero_aleatorio = random.nextInt((maximo-minimo)+1)+minimo;
        return numero_aleatorio;
    
    }
}
