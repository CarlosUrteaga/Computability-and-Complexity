import java.util.Random;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Proyecto01 {
    public static final int NUMBEROFSTATES = 64;

    public static int intTransition;
    public static int intSizePopulation;
    public static int intGeneration;
    public static int intSizeTape;
    public static void main(String[] args) {

        int intSeed;
        int i;

        int intGeneration;
        String strTest;
        String strStB;
        String strText;
        String input;
        String[] strMT;
        String strCinta;
        double dblMutationProb;
        double dblPonderation;
        // Ask for configuration
        //System.out.println("Dame la raíz del generador de números aleatorios:");
        //intSeed = Integer.parseInt(System.console().readLine());
        intSeed = 109152;
        //System.out.println("Deme el nombre del archivo que desee procesar:");
        //input = System.console().readLine();
        input = "12345.txt";
        strText = "";
        try {
            Scanner scanner = new Scanner( new File(input), "UTF-8" );
            strText = scanner.useDelimiter("\\A").next();
            scanner.close(); // Put this call in a finally block
        } catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        } 
        /*
        System.out.print("1) Numero de individuos:\t");
        intSizePopulation = Integer.parseInt(System.console().readLine());
        System.out.print("2) Numero de transiciones:\t");
        intTransition = Integer.parseInt(System.console().readLine());
        System.out.print("3) Longitud de la cinta:\t");
        intSizeTape = Integer.parseInt(System.console().readLine());
        System.out.print("4) Probabilidad de mutacion:\t");
        dblMutationProb = Double.parseDouble(System.console().readLine());
        System.out.print("5) Numero de generaciones:\t");
        intGeneration = Integer.parseInt(System.console().readLine());
        System.out.print("6) Factor de Ponderación:\t");        
        dblPonderation = Double.parseDouble(System.console().readLine());
        */
        intSizePopulation =50;
        intTransition = 50; //cuando cambias de estado, cuando l
        intSizeTape = 10000;
        dblMutationProb = 0.01;
        dblPonderation = 0;
        Proyecto01 tst = new Proyecto01();
        TuringClass turingMachines[] = new TuringClass[intSizePopulation];
        
        Random r = new Random();
        r.setSeed(intSeed );

        int j;
        //genera n maquinas de turing
        for (j = 0; j< intSizePopulation; j++) {
            turingMachines[j] = new TuringClass();
            turingMachines[j].strMT =  new String[NUMBEROFSTATES];
            turingMachines[j].strMT = tst.tmSetStates( turingMachines[j].strMT, r);
        }
        for (i=0; i<64; i++) {
            System.out.println(turingMachines[0].strMT[i]);
        }

        strMT = new String[64];
        strMT = turingMachines[0].strMT;
        System.out.println("=======");
        String strTesttm;
        strTesttm = tst.tmtoString(strMT);
        System.out.println(strTesttm);
        //Imprime estdos
        //for (i=0;i < 64 ;i++ ) {
        //  System.out.println(i+"\t"+strMT[i]);
        //}
        
        //inicia cinta en blanco
        strCinta = tst.setTape(intSizeTape);
        //convierte Texto en binario
        strStB = tst.strToBin(strText);
        //Crea una nueva cinta con el texto
        strTest = tst.modTape(strCinta, strStB);
        System.out.println("=======");
        strMT = new String[64];
        strMT = tst.stringToTM(strTesttm);
        for (i=0; i<64; i++) {
            System.out.println(turingMachines[0].strMT[i]);
        }
        //System.out.println(strStB);
        /*
        System.out.println(strTest);
        System.out.println(tst.simulationMT(tst2[0].strMT, strCinta, 0, algo));
        System.out.println("estado \t"+ tst2[0].strMT[0]);
        System.out.println(strTest);
        //*/
        
    }
    /*
    Pendientes
        (done) Método que simule una máquina de turing (posición, escritura, movimiento, siguiente estado)
        (done) Simulación de los estados de una máquina de Turing
        (done) Funciones de evaluación
            Distancia por byte, por letra o por pareja o por como

        -Algoritmo genético


            - cruzamiento
            - mutación
            
    */
    public void projectProblem(TuringClass[] turingMachines, String strTargetText){

        int i;
        int j;
        String strResTape[] = new  String [intSizePopulation];
        double dblFitness[] = new double[intSizePopulation];
        for (i=0; i< intSizePopulation; i++) {
            strResTape[i]= simulationProblem(turingMachines[i].strMT);
            dblFitness[i] = evalTapefromTM(strTargetText, strResTape[i]);
        }
        //sort Turing machine by dblFitness
        for ( i = 0; i < intSizePopulation; i++) {
            for (j = i + 1; j < intSizePopulation; j++) {
                double tmp = 0;
                TuringClass turingTemp = new TuringClass();
                if (dblFitness[i] > dblFitness[j]) {

                    tmp = dblFitness[i];
                    dblFitness[i] = dblFitness[j];
                    dblFitness[j] = tmp;
            
                    turingTemp  = turingMachines[i];
                    turingMachines[i] = turingMachines[j];
                    turingMachines[j] = turingTemp;
                }
            }
        }
       //transform turing machine in string binary string to make the GA.

    }
    public double evalTapefromTM(String strTargetText, String strTMTape){
        //se extrae el texto de la cinta
        strTMTape  = getStringTape(strTMTape, strTargetText);
        // idea de reforzamiento, lo que buscamos es que obtenga puntos cuando un byte esta bien.
        char[] charTape = strTMTape.toCharArray();
        char[] charText = strTargetText.toCharArray();
        int i =0;
        double intCount=0;
        while (i< strTargetText.length()){
            if (charTape[i]==charText[i]) {
                intCount++;
            }
        }
        return intCount/strTargetText.length(); 

    }
    public String simulationProblem(String[] strStatesTM ){
        int intState = 0;
        int i=0;
        String strCintaT;
        int intPointerTape = intSizeTape/2;
        String strArreRes[] = new String[2];
        strCintaT = this.setTape(intSizeTape);
        while (intState!=63 && i<intTransition && (intPointerTape < intSizeTape || intPointerTape>0)){
            strArreRes = simulationTM(strStatesTM, strCintaT, intState, intPointerTape);
            intState = Integer.parseInt(strArreRes[0]);
            intPointerTape = Integer.parseInt(strArreRes[1]);
            strCintaT = strArreRes[2];
            i++;
        }
        return strCintaT;
    }
    /*
    *******************************************************************
                            Genomic Algorithm
    *******************************************************************
    */
    public void cruzamiento(TuringClass[] turingMachines){
        String strTM;
        strTM = this.tmtoString(turingMachines);

    }
    /*
    *******************************************************************
                            Turing Machine
    *******************************************************************
    */
    
    public String tmtoString(String[] strTM){
        String strResTM = "";
        int i;
        for (i=0; i< NUMBEROFSTATES; i++) {
            strResTM= strResTM+ strTM[i];
        }
        return strResTM;
    }
    public String[] stringToTM(String strResTM){
        String[] strTM = new String[NUMBEROFSTATES];
        int  i;
        for (i=0; i<NUMBEROFSTATES; i++) {
            strTM[i] = strResTM.substring(16*i,16*(i+1));
        }
        return strTM;
    }
    //primeros 6 bits estado siguiente, 7 bit escribe, 8 LR
    public String[] tmSetStates(String [] strMT, Random r){
        int intRandom;
        int i;
        int j;
        i =0;
        j=0;
        String strTemp="";
        for (i=0;i < 64 ;i++ ) {
            for (j=0;j<16 ;j++ ) {
                strTemp=strTemp+""+r.nextInt(2);
            }
            strMT[i]=strTemp; 
            strTemp="";
        }
        return strMT;
    }
   
    public String[] simulationTM(String[] strStatesTM, String strCinta, int intState,  int intPointerTape){
        //get bit
        int tapeBit = strCinta.charAt(intPointerTape)- '0';//strCinta[intPointerTape];

        String strState;
        int intNextState;
        String strWriteTape;
        String strLR;
        if (tapeBit==1) { //0 false,1 true
            strState = strStatesTM[intState].substring(8,16);
        }else{
            strState = strStatesTM[intState].substring(0,8);
        }
        intNextState = Integer.parseInt(strState.substring(0,6),2);
        strWriteTape = ""+strState.charAt(6);
        strLR = strState.charAt(7)+"";
         if (strLR.equals("1")) { //0 left,1 right
            intPointerTape++;
        }else{
            intPointerTape--;
        }
        String arreRes[] = new String[3];
        arreRes[0]=""+ intNextState;
        arreRes[1]=""+ intPointerTape;
        arreRes[2]=strCinta;
        return arreRes; //next state
    }

    /*
    *******************************************************************
                            Tape Manipulation
    *******************************************************************
    */
    //Create a tape of size intTamanioSCinta 
    public String setTape(int intTamanioSCinta){
        String strCinta="";
        int i;
        for (i=0; i< intTamanioSCinta; i++) {
            strCinta=strCinta+ "0";
        }
        return strCinta;

    }/*char[]*/
    //Add strText (must to be in binary) to Tape
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
    //get the "text" from tape
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
    /*
    *******************************************************************
                    String         -> Binary String (ASCII)
                    Binary String  -> String
    *******************************************************************
    */
    //convert a String to binary string
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
    //binary string to char string
    public String binToStr(String strBin){
        String str = "";
        int i;
        for (i = 0; i < strBin.length()/8; i++) {
            int a = Integer.parseInt(strBin.substring(8*i,(i+1)*8),2);
            str += (char)(a);
        }
        return str;
    }

public static class TuringClass {
    
        String[] strMT;
}

}