/*    */ package ponies;
/*    */ 
/*    */ public class PonyFactory {
	public static Pony createPony(int characterChosen2) {
		Pony pony;
			
				switch (characterChosen2) {
/*    */     case 0: 
/* 11 */       pony = new Twilight();
/* 12 */       break;
/*    */     case 1: 
/* 14 */       pony = new Applejack();
/* 15 */       break;
/*    */     case 2: 
/* 17 */       pony = new Fluttershy();
/* 18 */       break;
/*    */     case 3: 
/* 20 */       pony = new PinkiePie();
/* 21 */       break;
/*    */     case 4: 
/* 23 */       pony = new Rarity();
/* 24 */       break;
/*    */     case 5: 
/* 26 */       pony = new RainbowDash();
/* 27 */       break;
/*    */     case 6: 
/* 29 */       pony = new Trixie();
/* 30 */       break;
/*    */     case 7: 
/* 32 */       pony = new Derpy();
/* 33 */       break;
/*    */     default: 
/* 35 */       System.exit(0);
/* 36 */       return new Derpy();
/*    */     }
/*    */     
/* 39 */     return pony;
/*    */   }
/*    */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\PonyFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */