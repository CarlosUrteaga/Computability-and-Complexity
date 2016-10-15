import java.util.Random;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;

public class Proyecto01 {
	public static String[] strTabla;
    public static void main(String[] args) {
        
        int intSeed;
        int intSizeTape;
        int i;
        String strTest;
        String strStB;
        String strText;
        Proyecto01 tst = new Proyecto01();
        
        intSeed = 109152;
        strText = "hola";
        intSizeTape=1024;

        strTabla = new String[64];
        tst.mtStates(intSeed);
    	//Imprime estdos
        //for (i=0;i < 64 ;i++ ) {
    	//	System.out.println(i+"\t"+strTabla[i]);
    	//}
            
        strTest = tst.setTape(intSizeTape);
        strStB = tst.strToBin(strText);
        strTest = tst.modTape(strTest, strStB);
        System.out.println(strTest);
        strTest = tst.getStringTape(strTest, strStB);
        System.out.println(strTest);
        //System.out.println(strStB);
        strStB = tst.binToStr(strTest);
        System.out.println(strStB);


    }
    //primeros 6 bits estado siguiente, 7 bit escribe, 8 LR
    public void mtStates(int intSeed){
    	int intRandom;
    	int i;
    	int j;
    	i =0;
    	j=0;
		Random r = new Random();
        r.setSeed(intSeed );
		String strTemp="";
    	for (i=0;i < 64 ;i++ ) {
    		for (j=0;j<16 ;j++ ) {
    			strTemp=strTemp+""+r.nextInt(2);
    		}
    		strTabla[i]=strTemp; 
    		strTemp="";
    	}

    }
    public String setTape(int intTamanioSCinta){
        String strCinta="";
        int i;
        for (i=0; i< intTamanioSCinta; i++) {
            strCinta=strCinta+ "0";
        }
        return  strCinta;
    }/*char[]*/

    public String  modTape(String strTape, String strText){
        int intLengthTape;
        int intLengthText;
        int i;
        char[] charTape = strTape.toCharArray();
        char[] charText = strText.toCharArray();
        intLengthTape = strTape.length();
        intLengthText = strText.length();
        

        for (i=0; i<intLengthText; i++) {
            charTape[intLengthTape/2 + i] = charText[i];
        }
        strTape= String.valueOf(charTape);
        return strTape;
    }
    public String strToBin(String strText){
        byte[] bytes = strText.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes){
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }

        }
        return binary.toString();
    }
    public String binToStr(String strBin){
        String str = "";
        int i;
        for (i = 0; i < strBin.length()/8; i++) {
            int a = Integer.parseInt(strBin.substring(8*i,(i+1)*8),2);
            str += (char)(a);
        }
        return str;
    }
    public String getStringTape(String strTape, String strText){
        int intLengthTape;
        int intLengthText;
        int i;
        char[] charTape = strTape.toCharArray();
        char[] charText = strText.toCharArray();
        intLengthTape = strTape.length();
        intLengthText = strText.length();
        

        for (i=0; i<intLengthText; i++) {
             charText[i] = charTape[intLengthTape/2 + i];
        }

        strText = String.valueOf(charText);  
        return strText;
    }
}