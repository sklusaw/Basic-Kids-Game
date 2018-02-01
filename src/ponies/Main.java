/*     */ package ponies;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  23 */     Main test = new Main();
/*  24 */     test.go();
/*     */   }
/*     */   
/*     */   public void go() {
/*  28 */     JFrame frame = new JFrame();
/*  29 */     frame.setDefaultCloseOperation(3);
/*     */     
/*  31 */     final PonyGame ponyGame = new PonyGame(frame);
/*  32 */     frame.getContentPane().add(ponyGame);
/*  33 */     frame.setResizable(false);
/*  34 */     Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
/*  35 */     int X = screen.width / 2 - 247;
/*  36 */     int Y = screen.height / 2 - 155;
/*  37 */     frame.setBounds(X, Y, 495, 310);
/*  38 */     frame.setVisible(true);
/*  39 */     WindowListener exitListener = new WindowAdapter()
/*     */     {
/*     */       public void windowClosing(WindowEvent e)
/*     */       {
/*     */         try
/*     */         {
/*     */           try {
/*  46 */             new FileReader("highScores.txt");
/*     */           }
/*     */           catch (FileNotFoundException er)
/*     */           {
/*  50 */             FileWriter fileWriterMaker = new FileWriter("highScores.txt");
/*  51 */             BufferedWriter bufferedWriterMaker = new BufferedWriter(fileWriterMaker);
/*  52 */             for (int lines = 10; lines != 0; lines--) {
/*  53 */               bufferedWriterMaker.write("0 Pony");
/*  54 */               bufferedWriterMaker.newLine();
/*     */             }
/*  56 */             bufferedWriterMaker.close();
/*     */           }
/*     */           
/*  59 */           FileReader fileReader = new FileReader("highScores.txt");
/*     */           
/*  61 */           BufferedReader bufferedReader = new BufferedReader(fileReader);
/*  62 */           int highScore = ponyGame.getScore();
/*  63 */           int parsedHighScore = 0;
/*  64 */           String hsMessage = "";
/*  65 */           String finalMessage = "";
/*  66 */           while (bufferedReader.ready()) {
/*  67 */             finalMessage = bufferedReader.readLine() + "\n";
/*     */             
/*  69 */             parsedHighScore = Integer.valueOf(finalMessage.split(" ")[0]).intValue();
/*  70 */             if (parsedHighScore >= highScore) {
/*  71 */               hsMessage = hsMessage + finalMessage;
/*     */             } else {
/*  73 */               String str = JOptionPane.showInputDialog("New High Score!\nPlease insert your name.");
/*  74 */               hsMessage = hsMessage + highScore + " " + str + "\n" + finalMessage;
/*  75 */               highScore = 0;
/*     */             }
/*     */           }
/*     */           
/*  79 */           bufferedReader.close();
/*     */           
/*  81 */           JOptionPane.showMessageDialog(null, hsMessage);
/*  82 */           StringReader stringReader = new StringReader(hsMessage);
/*  83 */           BufferedReader bufferedReader2 = new BufferedReader(stringReader);
/*  84 */           FileWriter fileWriter = new FileWriter("highScores.txt");
/*  85 */           BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
/*  86 */           int lines = 10;
/*  87 */           for (String line = bufferedReader2.readLine(); line != null; line = bufferedReader2.readLine()) {
/*  88 */             if (lines != 0)
/*     */             {
/*  90 */               bufferedWriter.write(line);
/*  91 */               bufferedWriter.newLine();
/*  92 */               lines--;
/*     */             }
/*     */           }
/*  95 */           bufferedReader2.close();
/*  96 */           bufferedWriter.close();
/*     */         }
/*     */         catch (FileNotFoundException er)
/*     */         {
/* 100 */           er.printStackTrace();
/*     */         } catch (IOException er) {
/* 102 */           er.printStackTrace();
/*     */         }
/*     */       }
/* 105 */     };
/* 106 */     frame.addWindowListener(exitListener);
/*     */   }
/*     */ }


/* Location:              C:\Users\MajorNoob\Desktop\pwnyGame.jar!\ponies\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */