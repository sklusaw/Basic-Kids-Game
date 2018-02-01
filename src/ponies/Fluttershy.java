/*    */ package ponies;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ public class Fluttershy
/*    */   extends Pony
/*    */ {
/*    */   public Fluttershy()
/*    */   {
/* 13 */     setCharacterMovementModifier(0.95F);
/* 14 */     this.ultimateDecreaseModifier = 0.03F;
/* 15 */     this.ultimateHelperModifier = 1.0F;
/* 16 */     setFrameBox(2);
/* 17 */     setAnimationFrameY(0);
/*    */     try {
/* 19 */       setHelperSprite(ImageIO.read(getClass().getResource("/angel_500x500.png")));
/* 20 */       setHelperSprite(getHelperSprite().getScaledInstance(30, 
/* 21 */         30, 1));
/*    */     }
/*    */     catch (IOException e) {
/* 24 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 29 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Fluttershy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */