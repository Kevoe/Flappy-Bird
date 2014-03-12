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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Vector;
import java.lang.Math;

public class Tiro extends JFrame implements Runnable, KeyListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	// Se declaran las variables.
	private int direccion;    // Direccion del elefante
	private int vidas;    // vidas del elefante
	private int puntaje;   // el puntaje de usuario.
	private final int MIN = -5;    //Limite minimo al generar el numero random. 
	private final int MAX = 6;    //Limite maximo al generar el numero random.
	private static final int WIDTH = 800;    //Ancho del JFrame
	private static final int HEIGHT = 640;    //Alto del JFrame
	private Image fondo;
        private boolean info;
        private int score;
        private int nivel;
        private Image pausaImagen;
        private Image infoImagen;
        private Image creditos;
        private Image dbImage;	// Imagen a proyectar
	private Image gameover;	// Imagen al finalizar el juego.
        private Image pokebar0, pika0, pika1,pika2,pika3,pika4,pika5,pika6,pika7;
	private Graphics dbg;	// Objeto grafico

//	private SoundClip explosion; //sonido explosion
//        private SoundClip moneda; //sonido explosion
//        private SoundClip fondoM;
	private int gravity;
        private boolean semueve;
        private int perdidos;
        private int i;
        private int vel;
        private int velX,velY;
        private int posOriginalX;
        private int posOriginalY;
        private int random;
        
	private Bueno pokebar[];    // Objeto de la clase Elefante
	
	private Malo pika;    //Objeto de la clase Raton
	
	
	private String nombreArchivo;    //Nombre del archivo.
	private String[] arr;    //Arreglo del archivo divido.
	private long tiempoActual;	//Tiempo de control de la animación
        private boolean pausa;
        private boolean click;
        private boolean sonidillo;
        private int clickX; 
        private int clickY; 
        
        public Tiro() {
        init();
        start();
    }
        public void init() {
        direccion = 0; //inicia estático
        click = false; //inicia sin click
        score=0;
        nivel=1;
        pausa = false;  //se inicia sin pausa
        vidas = 0; // cantidad inicial de vidas
        puntaje = 0; // socre inicial
        nombreArchivo = "puntajes.txt"; // nombre del archivo a modificar donde se guardara la informacion del juego
        sonidillo = true; // boooleana apra prender sonido
        gravity = 1; //gravity del jeugo
        semueve = true; //booleana para ver si ya empezo a moverse
        info = false; //booleana para desplegar instrucciones
        perdidos = 0;// cantidad de perdidos
        velX = (int)(0); // posiciones de velocidad x
        velY = (int)(10); //posiciones de velocidad y
        posOriginalX = WIDTH/2;
        posOriginalY = HEIGHT/2;
        setSize(1024, 640);  //se redimenciona el applet
        setBackground(Color.white);  //fondo blanco del applet
        addKeyListener(this);  //se añade el keyListener al applet
        addMouseListener(this);  //se añade el mouseListeenr al applet
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar aplicación al cerrar ventana
        
        //URL's de las imágenes de ambas animaciones y los sonidos
        fondo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/flappyfondo.jpg"));
        pausaImagen = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pause.png"));
        infoImagen = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/info.png"));
        creditos = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/Creditos.png"));

        pokebar0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pokebar.png"));
	
        pika0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_000.gif"));
	pika1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_001.gif"));
        pika2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_002.gif"));
        pika3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_003.gif"));
        pika4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_004.gif"));
        pika5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_005.gif"));
        pika6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_006.gif"));
        pika7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/frame_007.gif"));
      
//        explosion = new SoundClip("sounds/monkey.wav"); //sonido de explosion
//        moneda = new SoundClip("sounds/money.wav");  //sonido de explosion
//        fondoM = new SoundClip("sounds/flappyfondo.wav");  //sonido de explosion
        
        //Se crea un nuevo objeto bueno y se añaden los cuadros de animación
        pokebar = new Bueno[4];
        pokebar[0] = new Bueno(getWidth(), getHeight()-300 , pokebar0);
        pokebar[1] = new Bueno(getWidth(), 0 - 250 , pokebar0);
        pokebar[2] = new Bueno(3* getWidth()/2, getHeight()-300 , pokebar0);
        pokebar[3] = new Bueno(3* getWidth()/2, 0 - 250 , pokebar0);
        pokebar[0].sumaCuadro(pokebar0, 1000);
        pokebar[1].sumaCuadro(pokebar0, 1000);
        pokebar[2].sumaCuadro(pokebar0, 1000);
        pokebar[3].sumaCuadro(pokebar0, 1000);
      
        // del objeto malo se crea la pika y se anima.
        pika = new Malo(posOriginalX, posOriginalY, pika0, velX, velY);
        
        pika.sumaCuadro(pika0, 20);
        pika.sumaCuadro(pika1, 20);
        pika.sumaCuadro(pika2, 20);
        pika.sumaCuadro(pika3, 20);
        pika.sumaCuadro(pika4, 20);
        pika.sumaCuadro(pika5, 20);
        pika.sumaCuadro(pika6, 20);
        pika.sumaCuadro(pika7, 20);         
        }
        
         public void start() {
        //Crea el thread
        Thread hilo = new Thread(this);
	//Inicializa el thread
        hilo.start();
    }
         
         @Override
    public void run () {
        tiempoActual = System.currentTimeMillis();
        
        while (vidas > 0) {
            
//            if (sonidillo) {
//                if (!fondoM.getLooping()) {
//                    fondoM.setLooping(true);
//                    fondoM.play(); 
//                }
//            }
//            else {
//                fondoM.setLooping(false);
//                fondoM.stop();
//            }

            // Se ejecutará siempre
            //Verifica si el juego está en pausa, de ser así, no actualizará
            //de lo contrario, actualizará
            if (!pausa) {
                actualiza();
                checaColision();
            }
            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
            
            try	{
                // El thread se duerme.
                Thread.sleep (20);
            }
            
            catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }
    
    /**
    
      * Metodo <I>actualiza</I> sobrescrito de la clase 
    * En este metodo se actualizan las posciciones de la pokebar
    *  y de la pika. asi como actualizar las booleanas
    * 
    */
    public void actualiza() {
         //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
         long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
         
         //Guarda el tiempo actual
       	 tiempoActual += tiempoTranscurrido;
         
         //Actualiza la animación con base en el tiempo transcurrido
         if (direccion != 0) {
             pokebar[0].actualiza(tiempoTranscurrido);
             pokebar[1].actualiza(tiempoTranscurrido);
             pokebar[2].actualiza(tiempoTranscurrido);
             pokebar[3].actualiza(tiempoTranscurrido);
             
         }
         
         
         //Actualiza la animación con base en el tiempo transcurrido para cada malo
//         if (click) {
             pika.actualiza(tiempoTranscurrido);
//         }
         if(!pokebar[0].haIncrementadoScore() && pokebar[0].getPosX() + pokebar[0].getAncho()/2 <= pika.getPosX() + pika.getAncho()/2) {
                    pokebar[0].incrementarScore();
                    score++;
                   
         }
         if(!pokebar[0].haIncrementadoScore() && pokebar[2].getPosX() + pokebar[2].getAncho()/2 <= pika.getPosX() + pika.getAncho()/2) {
                    pokebar[0].incrementarScore();
                    score++;
         }
         
         
         //Actualiza la posición de cada malo con base en su velocidad
         //pika.setPosY(pika.getPosY() + pika.getVel());
         
         
         
         if (pika.getPosX() != posOriginalX || pika.getPosY() != posOriginalY) {
             semueve = false;
         }
         
         if (click) { // si click es true hara movimiento parabolico
             pika.setVelY(15);
             click=false;
         } else{
         }
         
        if(score==3){
             nivel=2;
         }
         if(score==20){
             nivel=3;
             
         }
         if(score==100){
             nivel=4;
         }
         
         pika.setPosY(pika.getPosY() - pika.getVelY());
         pika.setVelY(pika.getVelY() - gravity);
         pokebar[0].setPosX(pokebar[0].getPosX() - 4*nivel);
         pokebar[1].setPosX(pokebar[1].getPosX() - 4*nivel);
         pokebar[2].setPosX(pokebar[2].getPosX() - 4*nivel);
         pokebar[3].setPosX(pokebar[3].getPosX() - 4*nivel);
         
         
    }
    
    /**
     * Metodo usado para checar las colisiones del objeto tierra y asteroide
     * con las orillas del <code>JFrame</code> y entre sí.
     */
    public void checaColision() {
        //Verifica que la pokebar no choque con el applet por la izq
        if (pokebar[0].getPosX() + pokebar[0].getAncho() < 0) {
            pokebar[0].setPosX(getWidth() );
            pokebar[1].setPosX(getWidth() );
            random = (int) (Math.random() * 200 + 50);
            pokebar[0].setPosY(getHeight() - random);
            pokebar[1].setPosY(0 - random + 50);
            pokebar[0].noIncrementarScore();
        }
        
        if (pokebar[2].getPosX() + pokebar[0].getAncho() < 0) {
            pokebar[2].setPosX(getWidth() );
            pokebar[3].setPosX(getWidth() );
            random = (int) (Math.random() * 200 + 50);
            pokebar[2].setPosY(getHeight() - random);
            pokebar[3].setPosY(0 - random + 50);
            pokebar[0].noIncrementarScore();
        }
        
        
        //Verifica que cada objeto malo no choque con el caballo
        if (pokebar[0].intersecta(pika) || pokebar[1].intersecta(pika) || pokebar[2].intersecta(pika) || pokebar[3].intersecta(pika) ) {
            if (sonidillo) {
//                moneda.play();  //reproducre sonidillo de choque corecto           
            }
            pika.setContador(pika.getContador() + 1);
            pika.setPosX(posOriginalX);// pone la espera en la posicion original
            pika.setPosY(posOriginalY); // pone la pika en la posicion original
            pika.setVelY(velY);//valor de velocidad
//            puntaje += 2; // aumenta el score si intersecta
            click = false;
            semueve = true;
            vidas--;
        }
        
        //Verifica que cada objeto malo choque con el applet
        if (pika.getPosY() + pika.getAlto() > getHeight()) {
            if (sonidillo) {
//                explosion.play();  //reproducre sonidillo de bala           
            }
//            velX = (int)(Math.random() * 5 + 13);
//            velY = (int)(Math.random() * 12 + 15);
            pika.setPosX(posOriginalX);
            pika.setPosY(posOriginalY);
            pika.setVelY(velY);
            perdidos++;
            click = false;
            semueve = true;
            vidas--;

        }
        
        if (perdidos == 3) {
            vidas--;
            perdidos = 0;
        }
    }
    
    public void leeArchivo() throws IOException {
        BufferedReader fileIn;
                try {
                    fileIn = new BufferedReader(new FileReader(nombreArchivo));
                } catch (FileNotFoundException e){
                    File puntos = new File(nombreArchivo);
                    PrintWriter fileOut = new PrintWriter(puntos);
                    fileOut.println("512,0,512,100,0,0");
                    fileOut.close();
                    fileIn = new BufferedReader(new FileReader(nombreArchivo));
                }
                String dato = fileIn.readLine();
                while(dato != null) {
                    
                      arr = dato.split(",");
                      pokebar[0].setPosX(Integer.parseInt(arr[0])); //pos x de pokebar
                      pokebar[0].setPosY(Integer.parseInt(arr[1])); // pos y de pokebar
                      pika.setPosX(Integer.parseInt(arr[2])); // pos x de pika
                      pika.setPosY(Integer.parseInt(arr[3])); // posy de pika
                      pika.setVelX(Integer.parseInt(arr[4])); //vel de pika en x
                      pika.setVelY(Integer.parseInt(arr[5]));// vel de pika en y
                      vidas = (Integer.parseInt(arr[6])); // vidas
                      puntaje = (Integer.parseInt(arr[7])); //score actual
                      perdidos = (Integer.parseInt(arr[8])); //perdidos acomulados
                      if ((Integer.parseInt(arr[9])) == 1) { //checar respectivas booleanas
                          click = true;
                      }
                      else {
                          click = false;
                      }
                      if ((Integer.parseInt(arr[10])) == 1) {
                          sonidillo = true;
                      }
                      else {
                          sonidillo = false;
                      }
                      
                      dato = fileIn.readLine();
                }
                fileIn.close();
        }
    public void grabaArchivo() throws IOException {
            PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
            String graba; //graba el archivo de (pokebarposx,pokebarposy,pikaposx,pikaposy,velocidadx,veloidady,vidas, score , choque,click,sonidillo)
            graba = Integer.toString(pokebar[0].getPosX()) + "," + Integer.toString(pokebar[0].getPosY()) + "," + Integer.toString(pika.getPosX()) + "," + Integer.toString(pika.getPosY()) + "," + Integer.toString(pika.getVelX()) + "," + Integer.toString(pika.getVelY()) + "," + Integer.toString(vidas)+ "," + Integer.toString(puntaje) + "," + Integer.toString(perdidos);
            if (click) {
                graba += ",1";
            }
            else {
                graba += ",0";
            }
            
            if (sonidillo) {
                graba += ",1";
            }
            else {
                graba += ",0";
            }
            fileOut.println(graba);
            fileOut.close();
    }
    
    /**
     * Método <I>keyPressed<I/> de la clase <code>KeyListener</code>
     * @param e es el <code>evento</code> del teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Verifica si se oprime la tecla P para pausar o reanudar el juego
        if (e.getKeyCode() == KeyEvent.VK_P) {
            //Se cambia el estado de la variable pausa dependiendo de su
            //valor actual y desaparece el letrero de desaparece
            pausa = !pausa;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_I) {
            //Se cambia el estado de la variable pausa dependiendo de su
            //valor actual y desaparece el letrero de desaparece
            info = !info;
            pausa = !pausa;
        }

        if (e.getKeyCode() == KeyEvent.VK_G) { //tecla para grabar el juego
            if (!info) {
                try {
                    grabaArchivo();
            } catch(IOException f) {
                    System.out.println("");
              }
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_C) { // tecla para cargar el juego anterior
            try {
                leeArchivo();
            } catch(IOException f) {
                System.out.println("");
            }
        }

        //Se cambia la dirección del bueno con base en la tecla oprimida
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direccion = 1;
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direccion = 2;
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_R){
        pokebar = new Bueno[4];
        pokebar[0] = new Bueno(getWidth(), getHeight()-300 , pokebar0);
        pokebar[1] = new Bueno(getWidth(), 0 - 250 , pokebar0);
        pokebar[0].sumaCuadro(pokebar0, 1000);
        pokebar[1].sumaCuadro(pokebar0, 1000);
        
        pokebar[2] = new Bueno(3* getWidth()/ 2, getHeight()-300 , pokebar0);
        pokebar[3] = new Bueno(3* getWidth()/ 2, 0 - 250 , pokebar0);
        pokebar[2].sumaCuadro(pokebar0, 1000);
        pokebar[3].sumaCuadro(pokebar0, 1000);
        
        vidas=1;
        score=0;
        nivel=1;

        // del objeto malo se crea la pika y se anima.
        pika = new Malo(posOriginalX, posOriginalY, pika0, velX, velY);
        pika.sumaCuadro(pika0, 20);
        pika.sumaCuadro(pika1, 20);
        pika.sumaCuadro(pika2, 20);
        pika.sumaCuadro(pika3, 20);
        pika.sumaCuadro(pika4, 20);
        pika.sumaCuadro(pika5, 20);
        pika.sumaCuadro(pika6, 20);
        pika.sumaCuadro(pika7, 20);
        
        start();
        
        
       }
    }
    
    /**
     * Método <I>keyReleased<I/> de la clase <code>KeyListener</code>
     * @param e es el <code>evento</code> del teclado
     */
    @Override
    public void keyReleased(KeyEvent e) { //detener direcciones
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
            direccion = 0;
        }
        
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direccion = 0;
        }
        
                if (e.getKeyCode() == KeyEvent.VK_S) {//encender apagar musica y sonidillos.
            sonidillo = !sonidillo;
        }
    }
    
    /**
     * Método <I>KeyTyped<I/> de la clase <code>KeyListener</code>
     * @param e es el <code>evento</code> del teclado
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    /**
     * Método <I>mouseClicked<I/> de la clase <code>MouseListener</code>
     * @param e es el <code>evento</code> del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
//        click=true;
//        click = true;
        //verifica que el click haya sido dentro del objeto caballo
//        if (pika.clickDentro(e.getX(), e.getY())) {
//            //cambia el estado de la variable click
//            if (semueve) {
//                click = true;
//            }
//        }
    }
    
    /**
     * Método <I>mouseEntered<I/> de la clase <code>MouseListener</code>
     * @param e es el <code>evento</code> del mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
   
    /**
     * Método <I>mouseExited<I/> de la clase <code>MouseListener</code>
     * @param e es el <code>evento</code> del mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    /**
     * Método <I>mousePressed<I/> de la clase <code>MouseListener</code>
     * @param e es el <code>evento</code> del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
                click = true;

    }

    /**
     * Método <I>mouseReleased<I/> de la clase <code>MouseListener</code>
     * @param e es el <code>evento</code> del mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    //            click = false;

    }
    
    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>,
     * En este metodo lo que hace es actualizar el contenedor
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    @Override
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null){
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

	// Actualiza la imagen de fondo.
	dbg.setColor(getBackground ());
	dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

	// Actualiza el Foreground.
	dbg.setColor(getForeground());
	paint1(dbg);
        
	// Dibuja la imagen actualizada
	g.drawImage(dbImage, 0, 0, this);
    }
    
    /**
     * El método <I>paint1</I> muestra en pantalla la animación
     * @param g
    */
    public void paint1(Graphics g) {
        g.setColor(Color.white);
        //Verifica que los objetos existan
        if (pokebar[0] != null && pika != null ) { 
            
            if (info) {
                g.drawImage(infoImagen, 0, 0, getWidth(), getHeight(), this);
            }

            else {

                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                
                // Dibuja la barra
                g.drawImage(pokebar[0].getImagen(), pokebar[0].getPosX(), pokebar[0].getPosY(), this);
                g.drawImage(pokebar[1].getImagen(), pokebar[1].getPosX(), pokebar[1].getPosY(), this);
                g.drawImage(pokebar[2].getImagen(), pokebar[2].getPosX(), pokebar[2].getPosY(), this);
                g.drawImage(pokebar[3].getImagen(), pokebar[3].getPosX(), pokebar[3].getPosY(), this);
                
                
                //Dibuja los objetos malos
                g.drawImage(pika.getImagen(), pika.getPosX(), pika.getPosY(), this);
                //Verifica que haya desaparecido un objeto malo y dibuja el mensaje desaparece
                g.drawString("Nivel: " + nivel, getWidth() - 200, 50);
                g.drawString(String.valueOf(score), getWidth()/2 - 20, getHeight()/3);
                if (pausa) {
                     //Dibuja el mensaje de pausado
                    g.drawImage(pausaImagen, getWidth() / 2 - 202, getHeight() / 2 - 197, 405, 392, this);
                }  
            }
            
            if (vidas <= 0) {
                 g.drawString("FLAPPY PIKA!!! Presiona R para iniciar ", getWidth() - 500, 100);
                 
                //g.drawImage(creditos, 0, 0, getWidth(), getHeight() , this);
            }
        }
        
        else {
            //Da un mensaje mientras se carga el dibujo
            g.drawString("No se cargo la imagen..", 20, 20);
        }
    }
        
    
}