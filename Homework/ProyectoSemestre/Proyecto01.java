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
        //System.out.println();
        tst.mtStates();
        int i;
    	for (i=0;i < 64 ;i++ ) {
    		System.out.println(i+"\t"+strTabla[i]);
    	}
    }
    public void mtStates(){
    	int intRandom;
    	int i;
    	int j;
    	i =0;
    	j=0;
		Random r = new Random();
		String strTemp="";
    	for (i=0;i < 64 ;i++ ) {
    		for (j=0;j<8 ;j++ ) {
    			strTemp=strTemp+""+r.nextInt(2);
    		}
    		strTabla[i]=strTemp; 
    		strTemp="";
    	}
	//}*/
    }

}