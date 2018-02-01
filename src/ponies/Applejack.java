/*    */ package ponies;
/*    */ 
/*    */ public class Applejack extends Pony
/*    */ {
/*    */   public Applejack()
/*    */   {
/*  7 */     setCharacterMovementModifier(1.3F);
/*  8 */     this.ultimateDecreaseModifier = 0.02F;
/*  9 */     this.ultimateModeStayOnGroundModifier = 1.0F;
/* 10 */     setFrameBox(1);
/* 11 */     setAnimationFrameY(0);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 15 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Applejack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */