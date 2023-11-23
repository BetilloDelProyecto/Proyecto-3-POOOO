
package GUI;

import Bloque.*;
import Commands.*;
import ConfigurationManager.*;
import GestorEnemyTank.*;
import Mapa.*;
import Tanque.*;
import filemanager.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;


public class CampoJuego extends javax.swing.JFrame {
    JLabel[][] matriz = new JLabel[26][26];
    ArrayList<Mapa> mapas = new ArrayList<>();
    int nivel;
    ConfigurationManager configuracion = ConfigurationManager.getInstance();
    boolean started;
    FriendlyTank fTank = new FriendlyTank(3);
    Mapa mapaActual;
    GestorTankEnemigo gestorETank;
    ThreadGenerarTanques thGenerarTanques = new ThreadGenerarTanques(this);
    
    public CampoJuego() {
        initComponents();
        setFocusable(true);
        gestorETank = new GestorTankEnemigo();
        this.nivel = 0;
        this.setBackground(Color.black);
        this.setLayout(null);
        pnlCampo.setSize(780,780);
        generaMatriz();
        cargarMapas();
        mapaActual = mapas.get(nivel);
        buildMapa();
        setFriendlyTank();
        moverTanque();
        started = false;
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCampo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        pnlCampo.setBackground(new java.awt.Color(0, 0, 0));
        pnlCampo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCampo.setPreferredSize(new java.awt.Dimension(780, 780));

        javax.swing.GroupLayout pnlCampoLayout = new javax.swing.GroupLayout(pnlCampo);
        pnlCampo.setLayout(pnlCampoLayout);
        pnlCampoLayout.setHorizontalGroup(
            pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        pnlCampoLayout.setVerticalGroup(
            pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void generaMatriz (){
        int posX, posY = 0;
        for (int i = 0; i < 26; i++){
            posX = 0;
            for (int j = 0; j < 26; j++){
                matriz[i][j] = new JLabel("");
                matriz[i][j].setLayout(null);
                matriz[i][j].setSize(30,30);
                //matriz[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                pnlCampo.add(matriz[i][j]);
                matriz[i][j].setLocation(posX, posY);
                posX += 30;
            }
            posY += 30;
        }
    }
    
    public void setFriendlyTank(){
        fTank = new FriendlyTank(3);
        fTank.addImage("Imgs\\tankPu.png");//index 0 hacia arriba
        fTank.addImage("Imgs\\tankPr.png");//index 1 hacia derecha
        fTank.addImage("Imgs\\tankPi.png");//index 2 hacia izq
        fTank.addImage("Imgs\\tankPd.png");//index 3 hacia abajo
        int[] pos = getPosLibreMapa();
        fTank.setPosX(pos[0]);
        fTank.setPosY(pos[1]);
        fTank.setOrientacion("UP");
        pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
    }
    
    public int[] getPosLibreMapa(){
        int[] pos = new int[2];
        for (int i = 24; i >= 0; i--) {
            for (int j = 0; j < 25; j++) {
                if(matriz[i][j].getIcon() == null && matriz[i+1][j].getIcon() == null && matriz[i][j+1].getIcon() == null && matriz[i+1][j+1].getIcon() == null){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        pos[0] = -1;
        pos[1] = -1;                
        return pos;
    }
    
    public void moverTanqueAux(String command,int posX,int posY){
        if(validarMovimiento(posX,posY)){
            ICommand iCommand = fTank.getCommand(command);
            iCommand.execute(fTank);
            buildMapa();
            pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
        }else{
            buildMapa();
            fTank.setOrientacion(command);
            pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
        }
    }
     
    public void moverTanque(){
        this.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && !started){
                    System.out.println("LE DI ENTER");
                    started = true;
                    thGenerarTanques.start();
                }else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
                    moverTanqueAux("LEFT",fTank.getPosX(),fTank.getPosY()-1);
                }else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D'){
                    moverTanqueAux("RIGHT",fTank.getPosX(),fTank.getPosY()+1);
                }else if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W'){
                    moverTanqueAux("UP",fTank.getPosX()-1,fTank.getPosY());
                }else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'){
                    moverTanqueAux("DOWN",fTank.getPosX()+1,fTank.getPosY());
                } 
            }

            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public boolean validarConTanques(int posX,int posY){
        for(Tanque eTank : mapaActual.getETanks()){
            if(eTank.getPosX() == posX && eTank.getPosY() == posY){
                return false;
            }else if(eTank.getPosX() == posX && eTank.getPosY()+1 == posY){
                return false;
            }else if( eTank.getPosX()+1 == posX && eTank.getPosY() == posY){
                return false;
            }else if(eTank.getPosX()+1 == posX && eTank.getPosY()+1 == posY ){
                return false;
            }
        }
        return true;
    }
    
    private boolean validarMovimiento(int posX, int posY){
        if(posX >= 25 || posX < 0 || posY < 0 || posY >= 25)
            return false;
        for(Bloque b : mapaActual.getBloques())
            if(!b.isTraspasable())
                if(b.getPosX() == posX && b.getPosY() == posY){
                    return false;
                }else if(b.getPosX() == posX && b.getPosY() == posY+1){
                    return false;
                }else if( b.getPosX() == posX+1 && b.getPosY() == posY){
                    return false;
                }else if(b.getPosX() == posX+1 && b.getPosY() == posY+1 ){
                    return false;
                }
        return validarConTanques(posX, posY) && validarConTanques(posX, posY+1) && validarConTanques(posX+1, posY) && validarConTanques(posX+1, posY+1);
        
    }
    
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
            return (BufferedImage) img;
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bufferedImage;
    }
    
    public void pintarTanque(String ruta,int posX,int posY){
        String rutaImagen = ruta; 
        ImageIcon imagenIcono = new ImageIcon(rutaImagen);
        imagenIcono = resizeGifIcon(imagenIcono, 60, 60);
        Image imagenOriginal = imagenIcono.getImage();
        BufferedImage bufferedImage = toBufferedImage(imagenOriginal);
        int anchoParte = bufferedImage.getWidth() / 2;
        int altoParte = bufferedImage.getHeight() / 2;
        for (int i = 0; i < 2; i++) 
            for (int j = 0; j < 2; j++) {
                int x = j * anchoParte;
                int y = i * altoParte;
                Image parte = bufferedImage.getSubimage(x, y, anchoParte, altoParte);
                matriz[posX+i][posY+j].setIcon(new ImageIcon(parte));
            }
    }
    
    public void cargarMapas(){
        File carpeta = new File("Mapas");
        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        Mapa mapa = (Mapa)FileManager.readObject("Mapas//" + archivo.getName());
                        if (mapa != null){
                            mapas.add(mapa);
                        }else{
                            System.out.println("no se cargo el mapa");
                        }
                            
                    }
                }
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta.");
        }
    }
    
    public void cambiarImagenDeLabel(String ruta,JLabel label){
        ImageIcon icon = new ImageIcon(ruta);
        icon = resizeGifIcon(icon, 30, 30);
        label.setIcon(icon);
        label.setHorizontalAlignment(JLabel.LEADING);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setLayout(null);
    }

    private static ImageIcon resizeGifIcon(ImageIcon originalIcon, int width, int height) {
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
    public void buildMapa(){
        limpiarMapa();
        for(Bloque b : mapaActual.getBloques())
            if(b.getPosX() != -1 && b.getPosY() != -1 ){
                JLabel lbl = matriz[b.getPosX()][b.getPosY()];
                cambiarImagenDeLabel(b.getImagen(), lbl);
            }
        for(Tanque eTank : mapaActual.getETanks())
            pintarTanque(eTank.getImages()[eTank.getPosImagen(eTank.getOrientacion())], eTank.getPosX(), eTank.getPosY());
    }
    
    public void limpiarMapa(){
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                matriz[i][j].setIcon(null);
            }
        }
    }
     
    public int[] getPosLibreEnemigo(){
        int[] pos = new int[2];
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) 
                if(matriz[i][j].getIcon() == null && matriz[i+1][j].getIcon() == null && matriz[i][j+1].getIcon() == null && matriz[i+1][j+1].getIcon() == null){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
        pos[0] = -1;
        pos[1] = -1;                
        return pos;
    }
    
    public void generarEnemigo(String tipo){
        EnemyTank eTank = gestorETank.getClon(tipo);
        mapaActual.addTanque(eTank);
        int[] pos = getPosLibreEnemigo();
        eTank.setPosX(pos[0]);
        eTank.setPosY(pos[1]);
        eTank.setVelocidadDisparo(configuracion.getVelocidadDisparo());
        eTank.setVelocidadMovimiento(configuracion.getVelocidadMovimiento());
        eTank.setOrientacion("DOWN");
        System.out.println("IMAGEN DEL TANQUE: " +eTank.getImages()[eTank.getPosImagen(eTank.getOrientacion())] );
        pintarTanque(eTank.getImages()[eTank.getPosImagen(eTank.getOrientacion())], eTank.getPosX(), eTank.getPosY());
        mapaActual.addTanque((Tanque)eTank);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampoJuego().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlCampo;
    // End of variables declaration//GEN-END:variables
}
