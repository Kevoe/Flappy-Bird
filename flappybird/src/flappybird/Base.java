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
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Base {
	
	private int posX;    //posicion en x.       
	private int posY;	//posicion en y.
	private ImageIcon icono;    //icono.
        private ArrayList frame;
        private int cuadroActual;
        private long tiempoAnima;
        private long duraciontota;
        
	
	/**
	 * Metodo constructor usado para crear el objeto
	 * @param posX es la <code>posicion en x</code> del objeto.
	 * @param posY es la <code>posicion en y</code> del objeto.
	 * @param image es la <code>imagen</code> del objeto.
	 */
	public Base(int posX, int posY ,Image image) {
		this.posX=posX;
		this.posY=posY;
		icono = new ImageIcon(image);
                frame=new ArrayList();
                duraciontota=0;
                iniciar();
	}
        public synchronized void iniciar(){
            tiempoAnima=0;
            cuadroActual=0;
        }
	
        public synchronized void sumaCuadro(Image imagen, long duracion) {
        //se suma la duración del cuadro nuevo a la duración total de la animación
        duraciontota += duracion; 
        //se añade el nuevo cuadro de animación
        frame.add(new frameAnimado(imagen, duraciontota));
                                                                
    }
        public synchronized void actualiza(long tiempoTranscurrido) {
        if (frame.size() > 1) {  // verifica que haya frame de animación
           tiempoAnima += tiempoTranscurrido;  // se actualiza el tiempo de la animación
            
            //Se verifica el índice según el tiempo transcurrido
            if (tiempoAnima >= duraciontota) {
                tiempoAnima = tiempoAnima % duraciontota;
                cuadroActual = 0; 
            }
            
            while (tiempoAnima > getCuadro(cuadroActual).tiempoFinal) {
                cuadroActual++;
            }
        }
    }
    
    /**
     * Método <I>getImagen</I> que regresa la imagen del cuadro actual
     * 
     * @return imagen del cuadro actual del tipo <code>Image</code>
     */
    public synchronized Image getImagen(){
        if (frame.isEmpty()) {  //Si no hay frame de animación, regresa nulo
            return null;
        }
        
        else {
            //Regresa la imagen del cuadro actual
            return getCuadro(cuadroActual).imagen; 
        }
    }
    
    /**
     * Método <I>getCuadro</I> que regresa un cuadro de animación de la animación
     * @param i es el índice del cuadro de animación del tipo <code>int</code>
     * 
     * @return un cuadro de animación del tipo <code>cuadroDeAnimacion</code>
     */
    private frameAnimado getCuadro(int i) {
        return (frameAnimado)frame.get(i);
    }
    
    /** Se crea la clase <I>frameAnimado</I>
     * 
     */
    public class frameAnimado {
        Image imagen;  // Contiene una imagen del tipo Image
        long tiempoFinal;  // Contiene un tiempo final del tipo long
        
        /**
         * Método constructor sin parámetros de la clase 
         * <code>frameAnimado</code>
         */
        public frameAnimado() {
            this.imagen = null;  //se define con imagen vacía
            this.tiempoFinal = 0;  // se define con tiempo 0
        }
        
        /**
         * Métodos constructor con parámetros de la calse
         * <code>frameAnimado</code>
         * 
         * @param imagen es la imagen del cuadro de animaciónd el tipo
         * <code>Image</code>
         * @param tiempoFinal es el tiempo de la animación del tipo
         * <code>long</code>
         */
        public frameAnimado(Image imagen, long tiempoFinal) {
            this.imagen = imagen;  //se guarda la imagen del cuadro
            this.tiempoFinal = tiempoFinal;  //se guarda el tiempo de la animación
        }
        
        /**
         * Método <I>getImagen</I> que regresa la imagen del cuadro de animación
         * 
         * @return una imagen del tipo <code>Image</code>
         */
        public Image getImagen() {
            return imagen;  //regresa la imagen del cuadro de animación
        }
        
        /**
         * Método <I>getTiempoFinal</I> que regresa el tiempo de la animación
         * 
         * @return el tiempo de la animación de tipo <code>long</code>
         */
        public long getTiempoFinal() {
            return tiempoFinal;  //regresa el tiempo final de la animación
        }
        
        /**
         * Método <I>setImagen</I> que define la imagen del cuadro de animación
         * 
         * @param imagen del tipo <code>Image</code>
         */
        public void setImagen (Image imagen) {
            this.imagen = imagen;  //define la imagen del cuadro de animación
        }
        
        /**
         * Método <I>setTiempoFinal que define el timepo de la animación
         * 
         * @param tiempoFinal del tipo <code>long</code>
         */
        public void setTiempoFinal(long tiempoFinal) {
            this.tiempoFinal = tiempoFinal;  //define el tiempo de la animación
        }
    }
    
    /**
     * Metodo modificador usado para cambiar la posicion en x del objeto 
     * @param posX es la <code>posicion en x</code> del objeto.
     */
    public void setPosX(int posX) {
        this.posX = posX;  // Se almacena la posición en x
    }
    
    /**
     * Metodo de acceso que regresa la posicion en x del objeto 
     * @return posX es la <code>posicion en x</code> del objeto.
     */
    public int getPosX() {
        return posX; // Regresa la posición en x
    }
    
    /**
     * Metodo modificador usado para cambiar la posicion en y del objeto 
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public void setPosY(int posY) {
        this.posY = posY;  // Se almacena la posición en y
    }
    
    /**
     * Metodo de acceso que regresa la posicion en y del objeto 
     * @return posY es la <code>posicion en y</code> del objeto.
     */
    public int getPosY() {
        return posY;  // Regresa la posición en y
    }
    
    /**
     * Metodo modificador usado para cambiar el icono del objeto 
     * @param icono es el <code>icono</code> del objeto.
     */
    public void setImageIcon(ImageIcon icono) {
        this.icono = icono;  // Define el ícono del objeto
    }
    
    /**
     * Metodo de acceso que regresa el icono del objeto 
     * @return icono es el <code>icono</code> del objeto.
     */
    public ImageIcon getImageIcon() {
        return icono;  // Regresa el ícono del objeto
    }
    
    /**
     * Metodo de acceso que regresa el ancho del icono 
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del icono.
     */
    public int getAncho() {
        return icono.getIconWidth();  // Regresa el ancho de la imagen
    }
    
    /**
     * Metodo de acceso que regresa el alto del icono 
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del icono.
     */
    public int getAlto() {
        return icono.getIconHeight();  //Regresa el alto de la imagen
    }
    
    /**
     * Metodo de acceso que regresa la imagen del icono 
     * @return un objeto de la clase <code>Image</code> que es la imagen del icono.
     */
    public Image getImagenI() {
        return icono.getImage();  //Regresa la imagen
    }
    
    /**
     * Metodo de acceso que regresa un nuevo rectangulo
     * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
     * del rectangulo
     */
    public Rectangle getPerimetro() {
        // Crea un nuevo rectángulo
        return new Rectangle(getPosX(), getPosY(), getAncho(), getAlto());
    }
    
    /**
     * Checa si el objeto <code>Cuerpo</code> intersecta a otro <code>Cuerpo</code>
     *
     * @param obj es el objeto Cuerpo recibido a comparar
     * 
     * @return un valor boleano <code>true</code> si lo intersecta <code>false</code>
     * en caso contrario
     */
    public boolean intersecta(Base obj) {
        //Verifica que el perímetro intersecte el perímetro del otro objeto
        return getPerimetro().intersects(obj.getPerimetro());
    }
    
    /**
     * Checa si las coordenadas del <code>Click</code> están dentro del <code>Base</code>
     *
     * @param x es la coordenada en X del click
     * @param y es la coordenada en Y del click
     * 
     * @return un valor boleano <code>true</code> si están dentro <code>false</code>
     * en caso contrario
     */ 
    public boolean clickDentro(int x, int y) {
         // Verifica que las coordenadas estén dentro del perímetro
        return getPerimetro().contains(x, y); 
    }
}
        
        
	
