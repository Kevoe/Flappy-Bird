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
public class Guardar {
    private int posXbarril;
    private int posYbarril;
    private int posXbanana;
    private int posYbanana;
    private int puntaje;
    private int vidas;

	/**
	 * Constructor vacio con darle valores iniciales al momento de
	 * crear el objeto Puntaje
	 */
	public Guardar() {
		posXbarril=0;
                posYbarril=0;
                posXbanana=0;
                posYbanana=0;
		puntaje = 0;
                vidas=5;
                
	}

	/**
	 * Metodo constructor usado para crear el objeto
	 * @param nombre es el <code>nombre</code> del objeto.
	 * @param puntaje es el <code>puntaje</code> del objeto.
	 */
	public Guardar(int xbarril, int ybarril, int xbanana, int ybanana, int p, int v) {
		this.posXbarril=xbarril;
                this.posYbarril=ybarril;
                this.posXbanana=xbanana;
                this.posYbanana=ybanana;
                this.vidas=v;
		this.puntaje =p;
	}

	
          public void setXbarril(int xbarril)
    {
        this.posXbarril=xbarril;
    }
  /**
     * Metodo set de posYnave
     * @param ynave es la <code>posicion en x</code> del objetonave.
     */
  public void setYbarril(int ybarril)
  {
      this.posYbarril=ybarril;
  }
  /**
     * Metodo set de posXbola
     * @param xbola es la <code>posicion en x</code> del objetonave.
     */
  public void setXbanana(int xbanana)
  {
      this.posXbanana=xbanana;
  }
   /**
     * Metodo set de posYbola
     * @param ybola es la <code>posicion en x</code> del objetonave.
     */
  public void setYbanana(int ybanana)
  {
      this.posYbanana=ybanana;
  }
	

  public void setPuntaje(int s) 
  {
        this.puntaje = s;
  }
  
  public void setVidas(int d)
  {
      this.vidas=d;
  }

  public int getXbarril()
  {
      return posXbarril;
  }
   /**
     * Metodo get de posYnave
     */
  public int getYbarril()
  {
      return posYbarril;
  }
   /**
     * Metodo get de posXbola
     */
  public int getXbanana()
  {
      return posXbanana;
  }
   /**
     * Metodo get de posYbola.
     */
  public int getYbanana()
  {
      return posYbanana;
  }
  /**
     * Metodo get de score.
     */
  public int getPuntaje()
  {
      return puntaje;
  }
  
  /**
     * Metodo get de vidas.
     */
   public int getVidas()
  {
      return vidas;
  }
	
	
        
        

	
	
}