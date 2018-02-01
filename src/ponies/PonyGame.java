/*     */ package ponies;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ 

/*     */ class PonyGame
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private BufferedImage background;
/*     */   private Pony character;
/*     */   private FallingObjects fallingObjects;
/*     */   private JFrame frame;
/*  47 */   private int[] levelAlg = { 1, 1 };
/*  48 */   private int level = 1;
/*  49 */   private boolean pause = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private TAdapter tAdapter;
/*     */   
/*     */ 
/*     */ 
/*  58 */   private int score = 0;
/*     */   
/*     */   public int getScore() {
/*  61 */     return this.score;
/*     */   }
/*     */   
/*     */   public void setScore(int score) {
/*  65 */     this.score = score;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PonyGame(JFrame frame)
/*     */   {
/*  76 */     this.tAdapter = new TAdapter();
/*  77 */     addKeyListener(this.tAdapter);
/*  78 */     setFocusable(true);
/*  79 */     setDoubleBuffered(true);
/*     */     try {
/*  81 */       this.character = PonyFactory.createPony(getUserCharacter());
/*  82 */       this.background = ImageIO.read(getClass().getResource(
/*  83 */         "/untitled2.png"));
/*  84 */       this.fallingObjects = new FallingObjects();
/*     */     } catch (IOException e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*  88 */     this.frame = frame;
/*  89 */     if (this.character.getCharacterMovementModifier() > 0.0F) {
/*  90 */       this.character.setCharacterMovementModifier(this.character
/*  91 */         .getCharacterMovementModifier() - 0.7F);
/*     */     } else
/*  93 */       this.character.setCharacterMovementModifier(this.character
/*  94 */         .getCharacterMovementModifier() + 0.7F);
/*  95 */     this.character.ultimateDecreaseModifier /= 4.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getUserCharacter()
/*     */   {
/* 105 */     JOptionPane.showMessageDialog(
/* 106 */       this.frame, 
/* 107 */       "Oh no! Derpy's shipment of apples is falling all over Ponyville!\nTry to collect them before they hit the ground!\n\nLeft/Right Arrow - Movement \nSpacebar              - Special Power");
/* 108 */     String str = "";
/*     */     try
/*     */     {
/* 111 */       while ((str != null) && (str.equals(""))) {
/* 112 */         str = 
/* 113 */           JOptionPane.showInputDialog("Choose a Pony:\n1 - Twilight Sparkle - Slow Down the Apples\n2 - Applejack             - Pick Up Fallen Apples\n3 - Fluttershy             - Angel Bunny Helper\n4 - Pinkie Pie             - Jump (Fast Recharge)\n5 - Rarity                     - Attract Apples\n6 - Rainbow Dash     - Sonic Speed\n7 - The GnP Trixie     - Slower Apples\n8 - Derpy Hooves      - Derp");
/*     */       }
/*     */       
/* 116 */       if ((Integer.valueOf(str).intValue() > 0) && (Integer.valueOf(str).intValue() < 9)) {
/* 117 */         return Integer.valueOf(str).intValue() - 1;
/*     */       }
/* 119 */       return 8;
/*     */     } catch (NumberFormatException e) {}
/* 121 */     return 8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintComponent(Graphics g)
/*     */   {
/* 130 */     super.paintComponent(g);
/* 131 */     drawAll(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawAll(Graphics g)
/*     */   {
/* 144 */     checkScore();
/* 145 */     drawGUI();
/* 146 */     drawScreen(g);
/* 147 */     if (this.fallingObjects.getMissedApples() < 3)
/* 148 */       drawObjects(g);
/* 149 */     drawCharacter(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkScore()
/*     */   {
/* 161 */     if (this.fallingObjects.getMissedApples() < 3) {
/* 162 */       for (Apple apple : this.fallingObjects.getAppleQueue()) {
/* 163 */         if (apple.LocationX >= this.character.getLocationOnXAxis())
/*     */         {
/* 165 */           if ((apple.LocationX <= this.character.getLocationOnXAxis() + 64.0F) && 
/* 166 */             (apple.LocationY >= this.character.getLocationOnYAxis()))
/*     */           {
/* 168 */             if (apple.LocationY <= this.character.getLocationOnYAxis() + 64.0F) {
/* 169 */               this.fallingObjects.removeFromAppleQueue(apple);
/* 170 */               this.score += 1;
/* 171 */               this.fallingObjects.setMissedApples(0);
/* 172 */               if (!this.character.ultimateMode)
/* 173 */                 this.character.setUltimatePercentage(this.character
/* 174 */                   .getUltimatePercentage() + this.level);
/*     */             } }
/*     */         }
/* 177 */         if ((this.character.ultimateMode) && 
/* 178 */           (this.character.ultimateHelperModifier != 0.0F) && 
/* 179 */           (apple.LocationX >= this.character.getHelperLocX()) && 
/* 180 */           (apple.LocationX <= this.character.getHelperLocX() + 30.0F) && 
/* 181 */           (apple.LocationY >= this.character.getHelperLocY() - 10.0F) && 
/* 182 */           (apple.LocationY <= this.character.getHelperLocY() + 30.0F)) {
/* 183 */           this.fallingObjects.setMissedApples(0);
/* 184 */           this.fallingObjects.removeFromAppleQueue(apple);
/* 185 */           this.score += 1;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 190 */       if (this.score > this.levelAlg[0] + this.levelAlg[1]) {
/* 191 */         int combined = this.levelAlg[0] + this.levelAlg[1];
/* 192 */         this.levelAlg[0] = this.levelAlg[1];
/* 193 */         this.levelAlg[1] = combined;
/* 194 */         this.level += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawGUI()
/*     */   {
/* 203 */     if (this.fallingObjects.getMissedApples() < 3) {
/* 204 */       this.frame.setTitle("Level " + this.level + " | Score - " + this.score + 
/* 205 */         " | Special - " + (int)this.character.getUltimatePercentage() + 
/* 206 */         "% | Missed - " + this.fallingObjects.getMissedApples());
/*     */     } else {
/* 208 */       this.frame.setTitle("Score - " + this.score + " | Game Over");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawScreen(Graphics g)
/*     */   {
/* 217 */     g.drawImage(this.background, 0, 0, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawObjects(Graphics g)
/*     */   {
/* 226 */     if (!this.pause) {
/* 227 */       if (!this.character.ultimateMode) {
/* 228 */         this.fallingObjects.updateQueue(this.level, 1.0F, 0.0F, 0.0F);
/*     */       } else
/* 230 */         this.fallingObjects.updateQueue(this.level, 
/* 231 */           this.character.ultimateModeAppleSpeedModifier, 
/* 232 */           this.character.ultimateModeAppleGravitateModifier, 
/* 233 */           this.character.ultimateModeStayOnGroundModifier);
/*     */     }
/* 235 */     for (Apple apple : this.fallingObjects.getAppleQueue()) {
/* 236 */       g.drawImage(this.fallingObjects.getObjectImage(), (int)apple.LocationX, 
/* 237 */         (int)apple.LocationY, this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawCharacter(Graphics g)
/*     */   {
/* 248 */     if (!this.pause) {
/* 249 */       this.character.update(this.fallingObjects, 
/* 250 */         this.tAdapter.leftDown ^ this.tAdapter.rightDown);
/*     */     }
/* 252 */     g.drawImage(this.character.getSpriteImage(), 
/* 253 */       (int)this.character.getLocationOnXAxis(), 
/* 254 */       (int)this.character.getLocationOnYAxis(), this);
/*     */     
/* 256 */     if ((this.character.ultimateMode) && (this.character.ultimateHelperModifier != 0.0F)) {
/* 257 */       if (!this.pause)
/* 258 */         this.character.updateHelper(this.fallingObjects);
/* 259 */       if (((this.character.ultimatePercentage >= 40.0F) || (this.character.ultimatePercentage % 5.0F >= 1.0F)) && (
/* 260 */         (this.character.ultimatePercentage >= 20.0F) || (this.character.ultimatePercentage % 2.0F >= 1.0F))) {
/* 261 */         g.drawImage(this.character.getHelperSprite(), 
/* 262 */           (int)this.character.getHelperLocX(), 
/* 263 */           (int)this.character.getHelperLocY(), this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private class TAdapter
/*     */     extends KeyAdapter
/*     */   {
/* 284 */     boolean leftDown = false;
/* 285 */     boolean rightDown = false;
/*     */     
/*     */     private TAdapter() {}
/*     */     
/*     */     public void keyPressed(KeyEvent e) {
/* 290 */       int key = e.getKeyCode();
/*     */       
/*     */ 
/* 293 */       if (key == 37) {
/* 294 */         this.leftDown = true;
/* 295 */         if (this.rightDown) {
/* 296 */           PonyGame.this.character.setDirectionOfMovement(2);
/*     */         } else {
/* 298 */           PonyGame.this.character.setDirectionOfMovement(1);
/*     */         }
/*     */       }
/*     */       
/* 302 */       if (key == 39) {
/* 303 */         this.rightDown = true;
/* 304 */         if (this.leftDown) {
/* 305 */           PonyGame.this.character.setDirectionOfMovement(1);
/*     */         } else {
/* 307 */           PonyGame.this.character.setDirectionOfMovement(2);
/*     */         }
/*     */       }
/* 310 */       if ((key == 32) && 
/* 311 */         (PonyGame.this.character.getUltimatePercentage() >= 100.0F)) {
/* 312 */         PonyGame.this.character.startUltimate();
/*     */       }
/*     */       
/*     */ 
/* 316 */       if (key == 10)
/*     */       {
/* 318 */         if (!PonyGame.this.pause)
/*     */         {
/* 320 */           PonyGame.this.pause = false;
/*     */         } else {
/* 322 */           PonyGame.this.pause = false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void keyReleased(KeyEvent e)
/*     */     {
/* 329 */       int key = e.getKeyCode();
/*     */       
/*     */ 
/* 332 */       if (key == 37) {
/* 333 */         this.leftDown = false;
/* 334 */         if (this.rightDown) {
/* 335 */           PonyGame.this.character.setDirectionOfMovement(2);
/*     */         } else {
/* 337 */           PonyGame.this.character.setDirectionOfMovement(1);
/*     */         }
/*     */       }
/*     */       
/* 341 */       if (key == 39) {
/* 342 */         this.rightDown = false;
/* 343 */         if (this.leftDown) {
/* 344 */           PonyGame.this.character.setDirectionOfMovement(1);
/*     */         } else {
/* 346 */           PonyGame.this.character.setDirectionOfMovement(2);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\PonyGame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */