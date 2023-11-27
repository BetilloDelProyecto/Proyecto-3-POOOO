
package GUI;

import Bala.*;
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
    ArrayList<ThreadTanqueEnemigo> enemigos = new ArrayList<>();
    ArrayList<ThreadBala> balas = new ArrayList<>();
    
    public CampoJuego() {
        initComponents();
        setFocusable(true);
        gestorETank = new GestorTankEnemigo();
        this.nivel = 3;
        this.setBackground(Color.black);
        this.setLayout(null);
        pnlCampo.setSize(780,780);
        generaMatriz();
        cargarMapas();
        mapaActual = mapas.get(nivel);
        setFriendlyTank();
        buildMapaAux();
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
        mapaActual.getETanks().add(fTank);
    }
    
    public int[] getPosLibreMapa(){
        int[] pos = new int[2];
        for (int i = 24; i >= 0; i--) 
            for (int j = 0; j < 25; j++) 
                if(!puntoUsado(i, j)&&!puntoUsado(i, j+1)&&!puntoUsado(i+1, j)&&!puntoUsado(i+1, j+1)){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
        pos[0] = -1;
        pos[1] = -1;                
        return pos;
    }

    public JPanel getPnlCampo() {
        return pnlCampo;
    }

    public Mapa getMapaActual() {
        return mapaActual;
    }
    
    public void moverTanqueAux(String command,int posX,int posY){
        if(validarMovimiento(posX,posY,fTank)){
            limpiarMapa(fTank.getPosX(), fTank.getPosY());
            ICommand iCommand = fTank.getCommand(command);
            iCommand.execute(fTank);
            pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
        }else{
            fTank.setOrientacion(command);
            pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
        }
    }
    
    public static void setCenteredImage(JLabel label, String imagePath) {
        try {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();

            // Redimensionar la imagen a 15x15
            int targetWidth = 15;
            int targetHeight = 15;
            Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen redimensionada
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            // Establecer el nuevo ImageIcon en el JLabel
            label.setIcon(resizedIcon);

            // Centrar la imagen en el JLabel
            int x = (label.getWidth() - resizedIcon.getIconWidth()) / 2;
            int y = (label.getHeight() - resizedIcon.getIconHeight()) / 2;
            label.setIcon(new ImageIcon(resizedImage));  // Establecer de nuevo para asegurar el tamaño correcto
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBounds(x, y, resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void moverTanque(){
        CampoJuego c = this;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && !started){
                    started = true;
                    thGenerarTanques.start();
                }else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A'){
                    moverTanqueAux("LEFT",fTank.getPosX(),fTank.getPosY()-1);
                }else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D'){
                    moverTanqueAux("RIGHT",fTank.getPosX(),fTank.getPosY()+1);
                }else if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W'){
                    moverTanqueAux("UP",fTank.getPosX()-1,fTank.getPosY());
                }else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'){
                    moverTanqueAux("DOWN",fTank.getPosX()+1,fTank.getPosY());
                }else if(e.getKeyChar() == KeyEvent.VK_SPACE){
                    new ThreadBala(BulletFactory.createBullet((Tanque)fTank, fTank.getOrientacion(), true), c).start();
                }  
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public boolean validarConTanques(int posX,int posY, Tanque tanqueRecibido){
        for(Tanque eTank : mapaActual.getETanks()){
            if(eTank != tanqueRecibido){
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
            
        }
        return true;
    }
    
    public boolean validarMovimiento(int posX, int posY, Tanque tanqueRecibido){
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
        if(!(validarConAgulia(posX, posY)) || !(validarConAgulia(posX+1, posY)) || !(validarConAgulia(posX, posY+1)) || !validarConAgulia(posX+1, posY+1))
            return false;
        return validarConTanques(posX, posY, tanqueRecibido) && validarConTanques(posX, posY+1, tanqueRecibido) && validarConTanques(posX+1, posY, tanqueRecibido) && validarConTanques(posX+1, posY+1, tanqueRecibido);
    }
    
    public BufferedImage toBufferedImage(Image img) {
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
        }else {
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
    
    public void buildMapaAux(){
        Bloque aguila = mapaActual.getAguila();
        pintarTanque(aguila.getImagen(), aguila.getPosX(), aguila.getPosY());
        for(Bloque b : mapaActual.getBloques()){
            if(b.getPosX() != -1 && b.getPosY() != -1 ){
                
                JLabel lb = new JLabel("");
                lb.setForeground(Color.red);
                cambiarImagenDeLabel(b.getImagen(), lb);
                lb.setLocation(b.getPosY()*30,b.getPosX()*30);
                lb.setSize(30,30);
                b.setLbl(lb);
                pnlCampo.add(lb);
                pnlCampo.revalidate();
                pnlCampo.repaint();
                
            }
        }
    }
    
    public void buildMapa(){
        pintarTanque(fTank.getImages()[fTank.getPosImagen(fTank.getOrientacion())], fTank.getPosX(), fTank.getPosY());
        for(Tanque eTank : mapaActual.getETanks())
            pintarTanque(eTank.getImages()[eTank.getPosImagen(eTank.getOrientacion())], eTank.getPosX(), eTank.getPosY());
        
        
    }
    
    public void limpiarMapa(int posX, int posY){
        matriz[posX][posY].setIcon(null);
        matriz[posX+1][posY].setIcon(null);
        matriz[posX][posY+1].setIcon(null);
        matriz[posX+1][posY+1].setIcon(null);
    }
    
    public boolean puntoUsado(int posX,int posY){
        for(Bloque b:mapaActual.getBloques())
            if(b.getPosX() == posX && b.getPosY() == posY)
                return true;
        for(Tanque t:mapaActual.getETanks())
            if(dentroTanque(posX, posY, t))
                return true;
        return false;
    }
     
    public int[] getPosLibreEnemigo(){
        int[] pos = new int[2];
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) 
                if(!puntoUsado(i, j)&&!puntoUsado(i, j+1)&&!puntoUsado(i+1, j)&&!puntoUsado(i+1, j+1)){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
        pos[0] = -1;
        pos[1] = -1;                
        return pos;
    }

    public FriendlyTank getfTank() {
        return fTank;
    }
    
    public void generarEnemigo(String tipo){
        EnemyTank eTank = gestorETank.getClon(tipo);
        int[] pos = getPosLibreEnemigo();
        eTank.setPosX(pos[0]);
        eTank.setPosY(pos[1]);
        eTank.setVelocidadDisparo(configuracion.getVelDips(eTank.getTipo()));
        eTank.setVelocidadMovimiento(configuracion.getVelMov(eTank.getTipo()));
        eTank.setOrientacion("DOWN");
        pintarTanque(eTank.getImages()[eTank.getPosImagen(eTank.getOrientacion())], eTank.getPosX(), eTank.getPosY());
        mapaActual.addTanque((Tanque)eTank);
        ThreadTanqueEnemigo tte = new ThreadTanqueEnemigo(this, eTank, configuracion);
        tte.start();
        enemigos.add(tte);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CampoJuego().setVisible(true);
            }
        });
    }
    
    public Bloque estaBloque(int px, int py){
        for (int i = 0; i < mapaActual.getBloques().size(); i++) {
            Bloque get = mapaActual.getBloques().get(i);
            if (px == get.getPosX() && py == get.getPosY()) 
                return get;
        }
        return null;
    }
    
    public ThreadTanqueEnemigo estaTanque(int px, int py){
        for (int i = 0; i < enemigos.size(); i++) {
            ThreadTanqueEnemigo get = enemigos.get(i);
            if (dentroTanque(px, py, get.getTanque())) {
                return get;
            }
        }
        return null;
    }
    
    public boolean estaAguila(int px, int py){
        return dentroAguila(px, py, mapaActual.getAguila());
    }
    
    public boolean estaFTanque(int px, int py){
        return dentroTanque(px,py,fTank);
    }
    
    public boolean dentroTanque(int posX,int posY, Tanque t){
        return (t.getPosX() == posX && t.getPosY() == posY) || (t.getPosX()+1 == posX && t.getPosY() == posY) || (t.getPosX() == posX && t.getPosY()+1 == posY) || (t.getPosX()+1 == posX && t.getPosY()+1 == posY);
    }
    
    public boolean dentroAguila(int posX,int posY, Bloque a){
        return (a.getPosX() == posX && a.getPosY() == posY) || (a.getPosX()+1 == posX && a.getPosY() == posY) || (a.getPosX() == posX && a.getPosY()+1 == posY) || (a.getPosX()+1 == posX && a.getPosY()+1 == posY);
    }
    
    public ArrayList<ThreadTanqueEnemigo> getEnemigos() {
        return enemigos;
    }
    
    public boolean validarConAgulia(int posX,int posY){
        Bloque eagle = mapaActual.getAguila();
        if(eagle.getPosX() == posX && eagle.getPosY() == posY){
            return false;
        }else if(eagle.getPosX() == posX && eagle.getPosY()+1 == posY){
            return false;
        }else if(eagle.getPosX()+1 == posX && eagle.getPosY() == posY){
            return false;
        }else if(eagle.getPosX()+1 == posX && eagle.getPosY()+1 == posY){
            return false;
        }else
            return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlCampo;
    // End of variables declaration//GEN-END:variables
}
