/*     */ package ponies;

/*     */
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Queue;
/*     */ import javax.imageio.ImageIO;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public abstract class Pony
/*     */ {
	/*     */ public static final int SPRITE_WIDTH = 64;
	/*     */ public static final int SPRITE_HEIGHT = 64;
	/*     */ public static final int SPRITE_SIZE_OFFSET = 20;
	/*     */ private BufferedImage spriteSheet;
	/*     */ private Image spriteImage;
	/*     */ private Image helperSprite;
	/* 29 */ private int directionOfMovement = 0;
	/* 30 */ private float locationOnXAxis = 5.0F;
	/* 31 */ private float locationOnYAxis = 200.0F;
	/* 32 */ private float characterMovementModifier = 1.0F;
	/*     */
	/*     */
	/*     */
	/*     */
	/* 37 */ protected float ultimatePercentage = 0.0F;
	/* 38 */ private int ultimatePercentageModifier = 1;
	/* 39 */ protected float ultimateDecreaseModifier = 1.0F;
	/* 40 */ protected boolean ultimateMode = false;
	/*     */
	/*     */
	/*     */
	/*     */
	/* 45 */ protected float ultimateModeAppleSpeedModifier = 1.0F;
	/*     */
	/* 47 */ protected float ultimateModePonySpeedModifier = 1.0F;
	/*     */
	/* 49 */ protected float ultimateModeAppleGravitateModifier = 0.0F;
	/*     */
	/* 51 */ protected float ultimateModeStayOnGroundModifier = 0.0F;
	/*     */
	/* 53 */ protected float ultimateEnergyModifier = 1.0F;
	/* 54 */ protected int inJump = 0;
	/*     */
	/* 56 */ protected float ultimateHelperModifier = 0.0F;
	/* 57 */ private float helperLocX = -30.0F;
	/* 58 */ private float helperLocY = 245.0F;
	/*     */
	/*     */
	/*     */ private int frameBox;
	/*     */
	/*     */
	/* 64 */ private int stageOfAnimation = 0;
	/* 65 */ private int clockToChangeAnimationFrame = 0;
	/* 66 */ private int animationFrameX = 0;
	/* 67 */ private int animationFrameY = 0;

	/*     */
	/*     */
	/*     */ public Pony()
	/*     */ {
		/*     */ try
		/*     */ {
			/* 74 */ setSpriteSheet(ImageIO.read(getClass().getResource("/134101095082.png")));
			/*     */ }
			/*     */ catch (IOException e) {
			/* 77 */ e.printStackTrace();
			/*     */ }
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public void update(FallingObjects fallingObjects, boolean b)
	/*     */ {
		/* 88 */ determineAnimationFrame();
		/* 89 */ getAnimationFrame();
		/* 90 */ handleAnimationClock();
		/* 91 */ handleMovement(b);
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ private void determineAnimationFrame()
	/*     */ {
		/* 98 */ switch (this.stageOfAnimation) {
		/*     */ case 0:
			/*     */ case 2:
			/* 101 */ this.animationFrameX = 64;
			/* 102 */ break;
		/*     */ case 1:
			/* 104 */ this.animationFrameX = 0;
			/* 105 */ break;
		/*     */ case 3:
			/* 107 */ this.animationFrameX = 128;
			/*     */ }
		/*     */
		/* 110 */ this.animationFrameX = (this.animationFrameX + this.frameBox % 4 * 192);
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ private void getAnimationFrame()
	/*     */ {
		/* 117 */ setSpriteImage(getSpriteSheet()
				.getSubimage(this.animationFrameX, this.directionOfMovement * 64 + this.animationFrameY, 64, 64)
				/* 118 */ .getScaledInstance(76, 76, 1));
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ private void handleAnimationClock()
	/*     */ {
		/* 126 */ if (this.clockToChangeAnimationFrame < 300) {
			/* 127 */ this.clockToChangeAnimationFrame += 1;
			/*     */ } else {
			/* 129 */ if (this.stageOfAnimation != 3) {
				/* 130 */ this.stageOfAnimation += 1;
				/*     */ } else/* 132 */ this.stageOfAnimation = 0;
			/* 133 */ this.clockToChangeAnimationFrame = 0;
			/*     */ }
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public void handleMovement(boolean moving)
	/*     */ {
		/* 143 */ xAxisMovement(moving);
		/* 144 */ handleJump();
		/*     */
		/* 146 */ if (!this.ultimateMode) {
			/* 147 */ if (this.ultimatePercentage
					+ this.ultimatePercentageModifier * 0.005D * this.ultimateEnergyModifier <= 100.0D) {
				/* 148 */ this.ultimatePercentage = ((float) (this.ultimatePercentage
						+ this.ultimatePercentageModifier * 0.005D * this.ultimateEnergyModifier));
				/*     */ } else/* 150 */ this.ultimatePercentage = 100.0F;
			/*     */ } else {
			/* 152 */ if (this.ultimateEnergyModifier > 1.0F)
				this.inJump = 1;
			/* 153 */ handleUltimate();
			/*     */ }
		/*     */ }

	/*     */
	/*     */ private void xAxisMovement(boolean moving) {
		/* 158 */ if (moving) {
			/* 159 */ if (this.characterMovementModifier > 0.0F) {
				/* 160 */ normalMovement();
				/*     */ } else {
				/* 162 */ reversedMovement();
				/*     */ }
			/*     */ }
		/*     */ }

	/*     */
	/*     */ private void reversedMovement() {
		/* 168 */ if (this.directionOfMovement == 1) {
			/* 169 */ if (this.locationOnXAxis < 411.0F) {
				/* 170 */ if ((this.ultimateModePonySpeedModifier != 1.0F) && (this.ultimateMode)) {
					/* 171 */ this.locationOnXAxis = ((float) (this.locationOnXAxis - this.directionOfMovement * 1.0D
							* this.characterMovementModifier * this.ultimateModePonySpeedModifier));
					/*     */ } else {
					/* 173 */ this.locationOnXAxis = ((float) (this.locationOnXAxis
							- this.directionOfMovement * 1.0D * this.characterMovementModifier));
					/*     */ }
				/*     */ }
			/*     */ }
			/* 177 */ else if (this.locationOnXAxis > -20.0F) {
			/* 178 */ if ((this.ultimateModePonySpeedModifier != 1.0F) && (this.ultimateMode)) {
				/* 179 */ this.locationOnXAxis = ((float) (this.locationOnXAxis + this.directionOfMovement * 0.5D
						* this.characterMovementModifier * this.ultimateModePonySpeedModifier));
				/*     */ } else {
				/* 181 */ this.locationOnXAxis = ((float) (this.locationOnXAxis
						+ this.directionOfMovement * 0.5D * this.characterMovementModifier));
				/*     */ }
			/*     */ }
		/*     */ }

	/*     */
	/*     */ private void normalMovement()
	/*     */ {
		/* 188 */ if (this.directionOfMovement == 1) {
			/* 189 */ if (this.locationOnXAxis > -20.0F) {
				/* 190 */ if ((this.ultimateModePonySpeedModifier != 1.0F) && (this.ultimateMode)) {
					/* 191 */ this.locationOnXAxis = ((float) (this.locationOnXAxis - this.directionOfMovement * 1.0D
							* this.characterMovementModifier * this.ultimateModePonySpeedModifier));
					/*     */ } else {
					/* 193 */ this.locationOnXAxis = ((float) (this.locationOnXAxis
							- this.directionOfMovement * 1.0D * this.characterMovementModifier));
					/*     */ }
				/*     */ }
			/*     */ }
			/* 197 */ else if (this.locationOnXAxis < 411.0F) {
			/* 198 */ if ((this.ultimateModePonySpeedModifier != 1.0F) && (this.ultimateMode)) {
				/* 199 */ this.locationOnXAxis = ((float) (this.locationOnXAxis + this.directionOfMovement * 0.5D
						* this.characterMovementModifier * this.ultimateModePonySpeedModifier));
				/*     */ } else {
				/* 201 */ this.locationOnXAxis = ((float) (this.locationOnXAxis
						+ this.directionOfMovement * 0.5D * this.characterMovementModifier));
				/*     */ }
			/*     */ }
		/*     */ }

	/*     */
	/*     */ private void handleJump()
	/*     */ {
		/* 208 */ if (this.inJump != 0) {
			/* 209 */ if (this.inJump == 1)
			/*     */ {
				/* 211 */ if (this.locationOnYAxis > 151.0F) {
					/* 212 */ this.locationOnYAxis -= (this.locationOnYAxis - 150.0F) * 0.08F;
					/*     */ } else {
					/* 214 */ this.inJump = 2;
					/*     */ }
				/*     */ }
				/* 217 */ else if (this.inJump == 2)
			/*     */ {
				/* 219 */ if (this.locationOnYAxis > 199.6D) {
					/* 220 */ this.inJump = 0;
					/* 221 */ this.locationOnYAxis = 200.0F;
					/*     */ } else {
					/* 223 */ this.locationOnYAxis += (50.0F - (200.0F - this.locationOnYAxis)) * 0.3F;
					/*     */ }
				/*     */ }
			/*     */ }
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ protected void handleUltimate() {
	}

	/*     */
	/*     */
	/*     */
	/*     */ public void startUltimate()
	/*     */ {
		/* 238 */ this.ultimateMode = true;
		/* 239 */ if (this.ultimateModeAppleGravitateModifier == 1.0F) {
			/* 240 */ this.ultimateModeAppleGravitateModifier = (this.locationOnXAxis + 32.0F);
			/*     */ }
		/* 242 */ if (this.ultimateHelperModifier != 0.0F) {
			this.helperLocX = (this.locationOnXAxis + 32.0F);
			/*     */ }
		/*     */ }

	/*     */
	/*     */
	/*     */ protected void ultimateEndCheck()
	/*     */ {
		/* 249 */ if (this.ultimatePercentage <= 0.0F) {
			/* 250 */ this.ultimateMode = false;
			/* 251 */ if (this.ultimateModeAppleGravitateModifier > 0.0F)
				this.ultimateModeAppleGravitateModifier = 1.0F;
			/*     */ }
			/* 253 */ else if (this.ultimatePercentage - 100.0F * this.ultimateDecreaseModifier > 0.0F) {
			/* 254 */ this.ultimatePercentage -= 100.0F * this.ultimateDecreaseModifier * 0.01F;
			/* 255 */ if (this.ultimateModeAppleGravitateModifier > 0.0F)
				this.ultimateModeAppleGravitateModifier = (this.locationOnXAxis + 32.0F);
			/*     */ } else {
			/* 257 */ this.ultimatePercentage = 0.0F;
			/*     */ }
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public void updateHelper(FallingObjects fallingObjects)
	/*     */ {
		/* 267 */ if (this.helperLocX < ((Apple) fallingObjects.getAppleQueue().peek()).LocationX - 10.0F) {
			/* 268 */ this.helperLocX += 1.0F;
			/*     */ }
			/* 270 */ else if (this.helperLocX > ((Apple) fallingObjects.getAppleQueue().peek()).LocationX) {
			this.helperLocX -= 1.0F;
			/*     */ }
		/*     */ }

	/*     */
	/*     */ public int getFrameBox()
	/*     */ {
		/* 276 */ return this.frameBox;
		/*     */ }

	/*     */
	/*     */ public void setFrameBox(int characterChosen) {
		/* 280 */ this.frameBox = characterChosen;
		/*     */ }

	/*     */
	/*     */ public int getDirectionOfMovement() {
		/* 284 */ return this.directionOfMovement;
		/*     */ }

	/*     */
	/*     */ public void setDirectionOfMovement(int directionOfMovement) {
		/* 288 */ this.directionOfMovement = directionOfMovement;
		/*     */ }

	/*     */
	/*     */ public float getLocationOnXAxis() {
		/* 292 */ return this.locationOnXAxis;
		/*     */ }

	/*     */
	/*     */ public void setLocationOnXAxis(float locationOnXAxis) {
		/* 296 */ this.locationOnXAxis = locationOnXAxis;
		/*     */ }

	/*     */
	/*     */ public float getCharacterMovementModifier() {
		/* 300 */ return this.characterMovementModifier;
		/*     */ }

	/*     */
	/*     */ public void setCharacterMovementModifier(float characterMovementModifier) {
		/* 304 */ this.characterMovementModifier = characterMovementModifier;
		/*     */ }

	/*     */
	/*     */ public Image getSpriteImage() {
		/* 308 */ return this.spriteImage;
		/*     */ }

	/*     */
	/*     */ public void setSpriteImage(Image spriteImage) {
		/* 312 */ this.spriteImage = spriteImage;
		/*     */ }

	/*     */
	/*     */ public int getAnimationFrameY() {
		/* 316 */ return this.animationFrameY;
		/*     */ }

	/*     */
	/*     */ public void setAnimationFrameY(int animationFrameY) {
		/* 320 */ this.animationFrameY = animationFrameY;
		/*     */ }

	/*     */
	/*     */ public float getUltimatePercentage() {
		/* 324 */ return this.ultimatePercentage;
		/*     */ }

	/*     */
	/*     */ public void setUltimatePercentage(float ultimatePercentage)
	/*     */ {
		/* 329 */ if (this.ultimatePercentage <= 98.0F) {
			/* 330 */ this.ultimatePercentage = ultimatePercentage;
			/*     */ } else/* 332 */ this.ultimatePercentage = 100.0F;
		/*     */ }

	/*     */
	/*     */ public float getLocationOnYAxis() {
		/* 336 */ return this.locationOnYAxis;
		/*     */ }

	/*     */
	/*     */ public void setLocationOnYAxis(float locationOnYAxis) {
		/* 340 */ this.locationOnYAxis = locationOnYAxis;
		/*     */ }

	/*     */
	/*     */ public Image getHelperSprite() {
		/* 344 */ return this.helperSprite;
		/*     */ }

	/*     */
	/*     */ public void setHelperSprite(Image helperSprite) {
		/* 348 */ this.helperSprite = helperSprite;
		/*     */ }

	/*     */
	/*     */ public float getHelperLocX() {
		/* 352 */ return this.helperLocX;
		/*     */ }

	/*     */
	/*     */ public void setHelperLocX(float helperLocX) {
		/* 356 */ this.helperLocX = helperLocX;
		/*     */ }

	/*     */
	/*     */ public float getHelperLocY() {
		/* 360 */ return this.helperLocY;
		/*     */ }

	/*     */
	/*     */ public void setHelperLocY(float helperLocY) {
		/* 364 */ this.helperLocY = helperLocY;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public BufferedImage getSpriteSheet()
	/*     */ {
		/* 371 */ return this.spriteSheet;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public void setSpriteSheet(BufferedImage spriteSheet)
	/*     */ {
		/* 379 */ this.spriteSheet = spriteSheet;
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Pony.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */