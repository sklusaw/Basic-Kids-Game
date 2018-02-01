/*    */ package ponies;
/*    */ 
/*    */ public class Trixie extends Pony
/*    */ {
/*    */   public Trixie()
/*    */   {
/*  7 */     setCharacterMovementModifier(0.9F);
/*  8 */     this.ultimateDecreaseModifier = 0.03F;
/*  9 */     this.ultimateModeAppleSpeedModifier = 0.5F;
/* 10 */     setFrameBox(6);
/* 11 */     setAnimationFrameY(256);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 15 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Trixie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */