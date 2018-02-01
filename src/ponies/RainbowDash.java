/*    */ package ponies;
/*    */ 
/*    */ public class RainbowDash extends Pony
/*    */ {
/*    */   public RainbowDash()
/*    */   {
/*  7 */     setCharacterMovementModifier(1.4F);
/*  8 */     this.ultimateDecreaseModifier = 0.05F;
/*  9 */     this.ultimateModePonySpeedModifier = 1.65F;
/* 10 */     setFrameBox(5);
/* 11 */     setAnimationFrameY(256);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 15 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\RainbowDash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */