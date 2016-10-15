import java.util.Random;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;

public class Proyecto01 {
	public static String[] strTabla;

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        int intRandom;
        strTabla = new String[64];
		Random r = new Random();
        intRandom = r.nextInt(2);
        Proyecto01 tst = new Proyecto01();
        int intSizeTape;
        //System.out.println();
        tst.mtStates();
        int i;
    	for (i=0;i < 64 ;i++ ) {
    		System.out.println(i+"\t"+strTabla[i]);
    	}
        intSizeTape=1024;
        System.out.println(tst.setTape(intSizeTape));
        
        String myName = tst.setTape(intSizeTape);
        char[] myNameChars = myName.toCharArray();
        myNameChars[4] = 'x';
        myName = String.valueOf(myNameChars);
        System.out.println(myName);
    }
    //primeros 6 bits estado siguiente, 7 bit escribe, 8 LR
    public void mtStates(){
    	int intRandom;
    	int i;
    	int j;
    	i =0;
    	j=0;
		Random r = new Random();
		String strTemp="";
    	for (i=0;i < 64 ;i++ ) {
    		for (j=0;j<16 ;j++ ) {
    			strTemp=strTemp+""+r.nextInt(2);
    		}
    		strTabla[i]=strTemp; 
    		strTemp="";
    	}
	//}*/
    }
    public String setTape(int intTamanioSCinta){
        String strCinta="";
        int i;
        for (i=0; i< intTamanioSCinta; i++) {
            strCinta=strCinta+ "0";
        }
        return  strCinta;
    }
    public char[] modTape(String strTape, String strText){
        int intSizeText;
        intSizeText = strText = 

    }

}