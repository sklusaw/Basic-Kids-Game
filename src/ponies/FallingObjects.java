/*     */ package ponies;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Queue;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FallingObjects
/*     */ {
/*     */   public static final int SPRITE_WIDTH = 26;
/*     */   public static final int SPRITE_HEIGHT = 26;
/*     */   public static final int SPRITE_SIZE_OFFSET = 0;
/*     */   private ConcurrentLinkedQueue<Apple> appleQueue;
/*     */   private BufferedImage spriteSheet;
/*     */   private Image objectImage;
/*  23 */   private int missedApples = 0;
/*     */   
/*     */   public FallingObjects()
/*     */   {
/*     */     try {
/*  28 */       setSpriteSheet(ImageIO.read(getClass().getResource("/apple.png")));
/*     */     }
/*     */     catch (IOException e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*  33 */     setObjectImage(getSpriteSheet().getScaledInstance(16, 
/*  34 */       16, 1));
/*     */     
/*  36 */     this.appleQueue = new ConcurrentLinkedQueue();
/*  37 */     addApple();
/*     */   }
/*     */   
/*     */   public void updateQueue(int score, float ultimateMultiplier, float ultimateGravitate, float ultimateHover) {
/*  41 */     if (this.appleQueue.peek() != null) {
/*  42 */       for (Apple apple : this.appleQueue) {
/*  43 */         if (apple.LocationY < 264.0F) {
/*  44 */           apple.update(score, ultimateMultiplier, ultimateGravitate, ultimateHover);
/*     */         }
/*  46 */         else if (ultimateHover == 0.0F) {
/*  47 */           this.appleQueue.remove(apple);
/*  48 */           this.missedApples += 1;
/*     */         }
/*     */       }
/*     */       
/*  52 */       if ((score > 3) && (this.appleQueue.size() < score / 4)) {
/*  53 */         Random random = new Random();
/*     */         do {
/*  55 */           addApple();
/*  54 */           if (this.appleQueue.size() >= score / 4) break; } while (random.nextInt(100) == 0);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  59 */       addApple();
/*     */     }
/*     */   }
/*     */   
/*     */   public BufferedImage getSpriteSheet() {
/*  64 */     return this.spriteSheet;
/*     */   }
/*     */   
/*     */   public void setSpriteSheet(BufferedImage spriteSheet) {
/*  68 */     this.spriteSheet = spriteSheet;
/*     */   }
/*     */   
/*     */   public Queue<Apple> getAppleQueue() {
/*  72 */     return this.appleQueue;
/*     */   }
/*     */   
/*     */   public void setAppleQueue(ConcurrentLinkedQueue<Apple> appleQueue) {
/*  76 */     this.appleQueue = appleQueue;
/*     */   }
/*     */   
/*     */   public void addApple() {
/*  80 */     this.appleQueue.add(new Apple());
/*     */   }
/*     */   
/*     */   public void removeFromAppleQueue(Apple apple) {
/*  84 */     this.appleQueue.remove(apple);
/*     */   }
/*     */   
/*     */   public int getMissedApples() {
/*  88 */     return this.missedApples;
/*     */   }
/*     */   
/*     */   public void setMissedApples(int missedApples) {
/*  92 */     this.missedApples = missedApples;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Image getObjectImage()
/*     */   {
/*  99 */     return this.objectImage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setObjectImage(Image objectImage)
/*     */   {
/* 106 */     this.objectImage = objectImage;
/*     */   }
/*     */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\FallingObjects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */