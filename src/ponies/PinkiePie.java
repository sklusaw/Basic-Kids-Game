/*    */ package ponies;
/*    */ 
/*    */ public class PinkiePie
/*    */   extends Pony
/*    */ {
/*    */   public PinkiePie()
/*    */   {
/*  8 */     setCharacterMovementModifier(1.4F);
/*  9 */     this.ultimateDecreaseModifier = 0.99F;
/* 10 */     this.ultimateEnergyModifier = 75.0F;
/* 11 */     setFrameBox(3);
/* 12 */     setAnimationFrameY(0);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 16 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\PinkiePie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */