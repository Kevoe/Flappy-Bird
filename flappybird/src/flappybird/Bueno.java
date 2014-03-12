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
 
public class Bueno extends Base{
    
    private final static String DESAPARECE="DESAPARECE";
    private final static String PAUSA="PAUSA";
    private boolean incrementoScore;
    

	/**
	 * Metodo constructor que hereda los atributos de la clase <code>Animal</code>.
	 * @param posX es la <code>posiscion en x</code> del objeto elefante.
	 * @param posY es el <code>posiscion en y</code> del objeto elefante.
	 * @param image es la <code>imagen</code> del objeto elefante.
	 */
	public Bueno(int posX,int posY,Image image){
		super(posX,posY,image);	
	}
        public static String getDesaparece(){
            return DESAPARECE;
        }
        
        public static String getPausa(){
            return PAUSA;
        }
        
        public void incrementarScore() {
        incrementoScore = true;
    }
    
    public boolean haIncrementadoScore() {
        return incrementoScore;
    }
}
