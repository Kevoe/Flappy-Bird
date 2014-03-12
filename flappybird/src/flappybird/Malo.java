/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package flappybird;

/**
 *
 * @author alexgarza
 */
import java.awt.Image;

public class Malo extends Base {
    private static int contador;  
    private int veloX;  
    private int veloY;

	/**
	 * Metodo constructor que hereda los atributos de la clase <code>Animal</code>.
	 * @param posX es la <code>posiscion en x</code> del objeto raton.
	 * @param posY es el <code>posiscion en y</code> del objeto raton.
	 * @param image es la <code>imagen</code> del objeto raton.
	 */
	public Malo(int posX,int posY,Image image,int vX,int vY){
		super(posX,posY,image);
                veloX=vX;
                veloY=vY;
	}
        
        public static void setContador(int cont) {
        contador = cont;  
    }
        
        public static int getContador() {
        return contador;  //regresa el valor de conteo
    }  
        
        public void setVelX(int v) {
        veloX = v;  //define el valor de la velocidad
    }
    
    /**
     * Método <I>getVelocidad</I> que regresa el valor de la velocidad del objeto
     * 
     * @return valor de la velocidad del objeto de tipo <code>int</code>
     */
        public int getVelX() {
        return veloX;  //regresa el valor de la velocidad
    }
    
        public void setVelY(int v) {
        veloY = v;  //define el valor de la velocidad
    }
    
        public int getVelY() {
        return veloY;  //regresa el valor de la velocidad
    }
        
        
}

