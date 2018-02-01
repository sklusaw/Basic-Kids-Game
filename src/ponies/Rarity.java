/*    */ package ponies;
/*    */ 
/*    */ public class Rarity extends Pony
/*    */ {
/*    */   public Rarity()
/*    */   {
/*  7 */     setCharacterMovementModifier(0.95F);
/*  8 */     this.ultimateDecreaseModifier = 0.05F;
/*  9 */     this.ultimateModeAppleGravitateModifier = 1.0F;
/* 10 */     setFrameBox(4);
/* 11 */     setAnimationFrameY(256);
/*    */   }
/*    */   
/*    */   protected void handleUltimate() {
/* 15 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Rarity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */