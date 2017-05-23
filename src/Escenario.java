
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;


public class Escenario extends JComponent implements Constantes {
    
    public Celda[][]celdas;
    public Jugador jugador;
    public Lienzo dondeSeDibuja;
    public Adversario[] adversarios;
        
    public Escenario(Lienzo lienzo){
    
        dondeSeDibuja=lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        //inicializar array
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
                 for(int j=0; j< NUMERO_CELDAS_LARGO;j++)
                celdas[i][j] = new Celda(i, j,'V');
        celdas[2][2].esRecompensa();
        celdas[3][2].esRecompensa();
        celdas[10][7].esRecompensa();
        celdas[6][2].esRecompensa();
        //celdas[6][2].esRecompensa();  //LINEA PARA DETENER 
        celdas[5][3].esPared();
        celdas[5][4].esPared();
        celdas[5][5].esPared();
        celdas[4][4].esPared();
        celdas[3][3].esPared();
        celdas[3][4].esPared();
        celdas[3][5].esPared();
        celdas[3][6].esPared();
        celdas[3][7].esPared();
        celdas[3][8].esPared();
        //celdas[3][9].esPared();
        
        //destino
        
        celdas[14][14].esFinal();
        
        jugador = new Jugador(this);
        
        adversarios = new Adversario[3];
        
        adversarios[0] = new Adversario(this,9,9); 
        
        adversarios[1] = new Adversario(this,11,6); 
        
        adversarios[2] = new Adversario(this,13,5); 
        
    }   

    @Override
    public void paintComponent(Graphics g)
    {
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
        {
            for(int j=0; j<NUMERO_CELDAS_LARGO;j++)
            {
                celdas[i][j].paintComponent(g);                
            }
        }
    
    }

    public boolean esRecompensa(int x, int y) 
    {
        return celdas[x][y].tipo == RECOMPENSA;        
    }
    
    public ArrayList<Celda> obtenerRecompensas()
    {
        ArrayList<Celda> recompensas = new ArrayList<Celda>();
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
        {
            for(int j=0; j<NUMERO_CELDAS_LARGO;j++)
            {
               if(esRecompensa(i,j)) recompensas.add(celdas[i][j]);
            }
        }        
        return recompensas;
    }
    
}
