/*    */ package ponies;
/*    */ 
/*    */ public class Twilight extends Pony
/*    */ {
/*    */   public Twilight()
/*    */   {
/*  7 */     setCharacterMovementModifier(1.05F);
/*  8 */     this.ultimateDecreaseModifier = 0.03F;
/*  9 */     this.ultimateModeAppleSpeedModifier = 0.6F;
/* 10 */     setFrameBox(0);
/* 11 */     setAnimationFrameY(0);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 15 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Twilight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */