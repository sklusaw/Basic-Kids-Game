/*    */ package ponies;
/*    */ 
/*    */ 
/*    */ public class Derpy
/*    */   extends Pony
/*    */ {
/*    */   public Derpy()
/*    */   {
/*  9 */     setCharacterMovementModifier(-1.0F);
/* 10 */     this.ultimateDecreaseModifier = 0.02F;
/* 11 */     this.ultimateModeStayOnGroundModifier = 1.0F;
/* 12 */     this.ultimateModeAppleGravitateModifier = 0.5F;
/* 13 */     this.ultimateModePonySpeedModifier = 1.75F;
/* 14 */     this.ultimateModeAppleSpeedModifier = 0.66F;
/* 15 */     this.ultimateEnergyModifier = 0.9F;
/* 16 */     setFrameBox(7);
/* 17 */     setAnimationFrameY(256);
/*    */   }
/*    */   
/*    */   protected void handleUltimate()
/*    */   {
/* 22 */     ultimateEndCheck();
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Derpy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */