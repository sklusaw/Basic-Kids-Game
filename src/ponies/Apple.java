/*    */ package ponies;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Apple
/*    */ {
/*    */   float LocationX;
/*    */   float LocationY;
/*    */   float speedMultiplier;
/*    */   
/*    */   public Apple()
/*    */   {
/* 13 */     Random random = new Random();
/* 14 */     this.LocationX = random.nextInt(475);
/* 15 */     this.LocationY = 0.0F;
/* 16 */     this.speedMultiplier = (random.nextInt(20) + 5);
/* 17 */     this.speedMultiplier /= (this.speedMultiplier + 1.0F);
/*    */   }
/*    */   
/*    */   public void update(int score, float ultimateMultiplier, float ultimateGravitate, float ultimateHover) {
/* 21 */     if (this.LocationY + (0.08F + 0.01F * score) * this.speedMultiplier * ultimateMultiplier < 264.0F) {
/* 22 */       this.LocationY += (0.08F + 0.01F * score) * this.speedMultiplier * ultimateMultiplier;
/* 23 */     } else if (ultimateHover == 0.0F) { this.LocationY = 264.0F;
/*    */     }
/* 25 */     if (ultimateGravitate != 0.0F) this.LocationX = ((float)(this.LocationX + (ultimateGravitate - this.LocationX) / (Math.abs(ultimateGravitate - this.LocationX) + 0.01D)));
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Apple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */