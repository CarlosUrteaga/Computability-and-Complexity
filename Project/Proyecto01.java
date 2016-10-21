
import java.io.PrintWriter;
import java.io.*;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;


public class Proyecto01 {
    public static final int NUMBEROFSTATES = 64;
    public static final int INTSIZE  = 1024;
    public static int intTransition;
    public static int intSizePopulation;
    public static int intGeneration;
    public static int intSizeTape;
    public static double dblCrossProb;
    public static void main(String[] args)throws IOException {

        int intSeed;
        int i;

        int intGeneration;
        String strTest;
        String strTargetText;
        String strText;
        String input;
        String[] strMT;
        String strCinta;
        double dblMutationProb;
        double dblPonderation;

        // Ask for configuration+

        System.out.println("Dame la raíz del generador de números aleatorios:");
        intSeed = Integer.parseInt(System.console().readLine());
        
        System.out.println("Deme el nombre del archivo que desee procesar:");
        input = System.console().readLine();

        strText = "";
        try {
            Scanner scanner = new Scanner( new File(input), "UTF-8" );
            strText = scanner.useDelimiter("\\A").next();
            scanner.close(); // Put this call in a finally block
        } catch(FileNotFoundException fnfe) { 
            System.out.println(fnfe.getMessage());
        }
        
        System.out.print("1) Numero de individuos:\t");
        intSizePopulation = Integer.parseInt(System.console().readLine());
        System.out.print("2) Numero de transiciones:\t");
        intTransition = Integer.parseInt(System.console().readLine());
        System.out.print("3) Longitud de la cinta:\t");
        intSizeTape = Integer.parseInt(System.console().readLine());
        System.out.print("4) Probabilidad de mutacion:\t");
        dblMutationProb = Double.parseDouble(System.console().readLine());
        System.out.print("5) Prob. de cruzamiento:    \t");
        dblCrossProb = Double.parseDouble(System.console().readLine());
        System.out.print("6) Numero de generaciones:\t");
        intGeneration = Integer.parseInt(System.console().readLine());
        System.out.print("7) Factor de Ponderación:\t");        
        dblPonderation = Double.parseDouble(System.console().readLine());
        /*
        intSizePopulation =50;
        
        intTransition = 10000; //cuando cambias de estado, cuando l
        intSizeTape = 100;
        dblMutationProb = 0.01;
        dblPonderation = 0;
        intGeneration =10;
        */
        Proyecto01 tst = new Proyecto01();
        TuringClass turingMachines[] = new TuringClass[intSizePopulation];
        TuringClass oldturingMachines[] = new TuringClass[intSizePopulation*2];
        
        Random r = new Random();
        r.setSeed(intSeed );

        int j;
        
        //Generate Random  TM
        String strCintaN="";

        //inicia cinta en blanco
        strCinta = tst.setTape(intSizeTape);
        //convierte Texto en binario
        strTargetText = tst.strToBin(strText);
        //Modifica la cinta
        strCinta = tst.modTape(strCinta, strTargetText);
        //System.out.println(tst.fitnessTapefromTM(strStB,strCintaN));
        //
        //tst.projectProblem(turingMachines, strTargetText);


        String strResTape[] = new  String [intSizePopulation];
        double olddblFitness[] = new double[intSizePopulation*2];
        double dblFitness[] = new double[intSizePopulation];
        double tmp;
        
        String strTM[] = new String[intSizePopulation];
        for (j = 0; j< intSizePopulation*2; j++) {
            olddblFitness[j]=0;
            oldturingMachines[j] = new TuringClass();
            oldturingMachines[j].strMT =  new String[NUMBEROFSTATES];
            
        }
        //inicializan los genes o las TM
        for (j = 0; j< intSizePopulation; j++) {
            turingMachines[j] = new TuringClass();
            turingMachines[j].strMT =  new String[NUMBEROFSTATES];
            turingMachines[j].strMT = tst.tmSetStates( turingMachines[j].strMT, r);   
            
            /*
            strResTape[j]= tst.simulationProblemMT(turingMachines[j].strMT);
            dblFitness[j] = tst.fitnessTapefromTM(strTargetText, strResTape[j]);
            System.out.println(dblFitness[j]);
            */
        }
        //dblFitness = tst.projectProblem(turingMachines,strTargetText, r);
        //nuevas genraciones abajo, entonces las primeras siempre son las que copio
        //oldturingMachines
        //TuringMachineabuelos
        //cada generación.
        TuringClass turingTemp;
        int k=0;
        //for (k = 0; k< intGeneration; k++)
        while(k<intGeneration) {
            System.out.println("GEN\t"+(k+1)+ "\t"+ olddblFitness[0]);
            dblFitness = tst.projectProblem(turingMachines,strTargetText, r);
            if (k==0) {
                for (j = 0; j< intSizePopulation; j++) {
                    oldturingMachines[j].strMT = turingMachines[j].strMT;
                    olddblFitness[j] = dblFitness[j];
                }
            }else{
                for (j = intSizePopulation; j< intSizePopulation*2; j++) {
                    oldturingMachines[j].strMT = turingMachines[j-intSizePopulation].strMT;
                    olddblFitness[j] = dblFitness[j-intSizePopulation];
                }
            }
            for ( i = 0; i < intSizePopulation*2; i++) {
                for (j = i + 1; j < intSizePopulation*2; j++) {
                    tmp = 0;
                    turingTemp = new TuringClass();
                    if (olddblFitness[i] < olddblFitness[j]) {

                        tmp = olddblFitness[i];
                        olddblFitness[i] = olddblFitness[j];
                        olddblFitness[j] = tmp;

                        turingTemp           = oldturingMachines[i];
                        oldturingMachines[i] = oldturingMachines[j];
                        oldturingMachines[j] = turingTemp;
                    }
                }
            }
            //getTOP50
            for (j = intSizePopulation; j< intSizePopulation; j++) {
                turingMachines[j].strMT = oldturingMachines[j].strMT;
            }

            
            for ( i = 0; i < intSizePopulation; i++) {
                strTM[i] = tst.tmtoString(turingMachines[i].strMT);
            }
            //GA
            strTM = tst.simulationGA(strTM,r);
            //string to MT 
            for ( i = 0; i < intSizePopulation; i++) {
                turingMachines[i].strMT = tst.stringToTM(strTM[i]);
            }
            if (olddblFitness[0]>.98) {
            k=intGeneration;   
            }
            k++;
        }
        PrintWriter writer;
        
        String strFnlCinta = tst.simulationProblemMT(oldturingMachines[0].strMT );
        String strFnlText;
        strFnlText = tst.getStringTape(strFnlCinta, strTargetText);

        String[] strFinalTM =tst.reduceTM(oldturingMachines[0].strMT);
        int intK = Integer.parseInt(strFinalTM[0]);
        System.out.println("La mejor MT encontrada esta en  TargetTM.txt");
        writer = new PrintWriter("TargetTM.txt", "UTF-8");
        for (i=1;i< 64; i++ ) {
            writer.println(""+oldturingMachines[0].strMT[i]);
        }  
        writer.close();
        System.out.println("La mejor cinta encontrada esta TargetTape.txt");
        writer = new PrintWriter("TargetTape.txt", "UTF-8");
        writer.println(strFnlCinta);
        writer.close();
        System.out.println("Numero de coincidencias: "+ strTargetText.length()*olddblFitness[0]);
        System.out.println("Longitud de la cinta: "+ strFnlText.length()+"\n\n");
        System.out.println("Tasas de coincidencias: "+ olddblFitness[0]+"\n\n");
        System.out.println("Estados en la maquina de Turing "+ strFinalTM[0]);
        System.out.println("Maquina de Turing compacta en PackedTM.txt");
        System.out.println("La Complejidad de Kolmogorov: "+(Integer.parseInt(strFinalTM[0])*16));
        System.out.println("Hay "+ strFinalTM[0]+" estados en la Maquina de Turing\n");
        System.out.println("EA"+"| O" +" | M" +" | SE" +" || O " +"| M " +"| SE  |");
        System.out.println("--"+"---" +"----" +"-----" +"------" +"----" +"-------");

        writer = new PrintWriter("PackedTM.txt", "UTF-8");
        

        for (i=1;i< Integer.parseInt(strFinalTM[0]) ;i++ ) {
            tst.strTMState(oldturingMachines[0].strMT,i);
            writer.println(""+oldturingMachines[0].strMT[Integer.parseInt(strFinalTM[i])]);
            System.out.println(tst.strTMState(oldturingMachines[0].strMT,Integer.parseInt(strFinalTM[i])));
        }  
        writer.close();
    }
    /*
        Proyecto
            generar varias MT y evaluar,
    */
    public double[]  projectProblem(TuringClass[] turingMachines, String strTargetText, Random r){

        int i;
        int j;
        
        // Supongo que ya inicie las TM  
        String strResTape[] = new  String [intSizePopulation];
        double dblFitness[] = new double[intSizePopulation];

        //simulation
        for (i=0; i< intSizePopulation; i++) {
            strResTape[i]= simulationProblemMT(turingMachines[i].strMT);
            dblFitness[i] = fitnessTapefromTM(strTargetText, strResTape[i]);
        }
        return dblFitness;
    }
    public String[] simulationGA(String [] strTMs, Random r){
        int i;
        int intTMSize;
        intTMSize = strTMs[0].length();

         // Convert TM to String to cross and mutate
        String strRes[]= new String[2];
        String strTM;

        double dblMutationProb = 0.01;
        for (i=0; i<intSizePopulation/2; i ++ ) {
            strRes = crossGA(strTMs[i], strTMs[intSizePopulation-1], dblCrossProb, r);
            strTMs[i] = strRes[0];
            strTMs[intSizePopulation-1] = strRes[1];
        }
        strTM = "";
        //TMs to str debería de ser 1024 * tamaño de la población
        for ( i = 0; i < intSizePopulation; i++) {
            strTM =strTM +  strTMs[i];
        }
        strTM = mutationGA(strTM, dblMutationProb, r);

        for (i=0; i<intSizePopulation; i++) {
            strTMs[i] = strTM.substring(intTMSize*i,intTMSize*(i+1));
        }
        return strTMs;
    }

    //simulation problem for each TM.
    public String simulationProblemMT(String[] strStatesTM ){
        int intState = 0;
        int i=0;
        String strCintaT;
        int intPointerTape = intSizeTape/2;
        String strArreRes[] = new String[2];
        strCintaT = this.setTape(intSizeTape);
        while (intState!=63 && i<intTransition && (intPointerTape < intSizeTape && intPointerTape>0)){

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
                            Kolmogorov
    *******************************************************************

    */
    // - función que reduzca la maquina de turing
    // - Contar el número de bits, regresar. #edos*16 bits

    //
    /*
    *******************************************************************
                            Genomic Algorithm
    *******************************************************************
    */
    //dos maquinas de turing y una probabilidad
    public  String[] crossGA(String strTMOne, String strTMTwo, double dblProbability, Random r){
        double dblPrb = r.nextDouble();
        int intPointerCut;
        String strCross01,strCross02;
        int intSize = strTMOne.length();
        intPointerCut = r.nextInt(intSize);
        if (dblPrb>dblProbability) { //si se cruza

            strCross01 = strTMOne.substring(intPointerCut,intSize)+strTMOne+strTMOne.substring(0,intPointerCut);
            strCross02 = strTMTwo.substring(intPointerCut,intSize)+strTMTwo+strTMTwo.substring(0,intPointerCut);
            strTMOne =strCross02.substring(intSize-intSize/2,intSize) + strCross01.substring(intSize,intSize+intSize/2) ;
            strTMTwo = strCross01.substring(intSize-intSize/2,intSize) + strCross02.substring(intSize,intSize+intSize/2);

        }
        String strRes[]= new String[2];
        strRes[0]=strTMOne;
        strRes[1]=strTMTwo;
        return strRes;

    }
    public String mutationGA(String strTMs, double dblMutationProb, Random r){
        int i;
        int intRandom;
        Double dblTemp = strTMs.length()*dblMutationProb;
        int intLength =dblTemp.intValue();
        char[] charText = strTMs.toCharArray();
        for (i=0; i<intLength; i++) {
            charText[r.nextInt(intLength)] =  Character.forDigit(r.nextInt(2), 10);

        }
        return String.valueOf(charText);
    }
    public double fitnessTapefromTM(String strTargetText, String strTMTape){
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
            i++;
        }
        return intCount/strTargetText.length(); 

        /*
        intTotal0s = arrayA0[i]+arrayI0[i];
  intTotal1s = TOTALDATOS -intTotal0s;
  fltn0N = intTotal0s/TOTALDATOS;
  fltn1N = intTotal1s/TOTALDATOS;

  fltP = arrayA0[i]/intTotal0s;
  fltQ = (TotalAs - arrayA0[i])/intTotal1s;
    //arrayFntUtl[i] =(1-fltP)*fltP*fltn0N+ (1-fltQ)*fltQ*fltn1N;
    fltEntropia = fltn0N*(-fltP*log2(fltP)-(1-fltP)*log2(1-fltP))+fltn1N*(-fltQ*log2(fltQ)-(1-fltQ)*log2(1-fltQ) );
    //
    arrayFntUtl[i]= (42/1909)*(1-(42/1909)) - fltEntropia;
        */

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
    public boolean contains(int[] array, int key, int j) {
    int i =0;
    while(i<j){
        if (array[i]==key) {
            return false;
        }
        i++;
    }
    return true;
}
    public String[] reduceTM(String[] strStatesTM ){
        int intState = 0;
        int intList[] = new int[64];
        int i=0;
        String strCintaT;
        int intPointerTape = intSizeTape/2;
        String strArreRes[] = new String[2];
        strCintaT = this.setTape(intSizeTape);
        int j=1;
        intList[j]=0;
        j++;
        while (intState!=63 && i<intTransition && (intPointerTape < intSizeTape && intPointerTape>0)){

            strArreRes = simulationTM(strStatesTM, strCintaT, intState, intPointerTape);
            intState = Integer.parseInt(strArreRes[0]);
            intPointerTape = Integer.parseInt(strArreRes[1]);
            strCintaT = strArreRes[2];

            if (this.contains(intList, intState,j)) {
                intList[j]=intState;
                j++;
            }
            i++;
        }
        String[] str = new String[j]; 
        i=0;
        intList[i]=j;
        //str[i]=""+j;
        //    i++;
        while(i<j) { 
            str[i] = ""+intList[i];
            i++;
        }
        return str;

    }
   
    public String[] simulationTM(String[] strStatesTM, String strCinta, int intState,  int intPointerTape){
        //get bit
        char tapeBit = strCinta.charAt(intPointerTape);//strCinta[intPointerTape];

        String strState;
        int intNextState;
        String strLR;
        if (tapeBit=='1') { //0 false,1 true
            strState = strStatesTM[intState].substring(8,16);
        }else{
            strState = strStatesTM[intState].substring(0,8);
        }
        intNextState = Integer.parseInt(strState.substring(0,6),2);

        char[] charText = strCinta.toCharArray();
        charText[intPointerTape] = strState.charAt(6);
        strCinta = String.valueOf(charText);
        strLR = strState.charAt(7)+"";
         if (strLR.equals("1")) { //0 left,1 right
            intPointerTape++;
        }else{
            intPointerTape--;
        }/*

        System.out.println("bit de cinta " + tapeBit);
        System.out.println("que escribo " + strState.charAt(6));
        System.out.println(" siguiente estado "+ intNextState);
        System.out.println("strLR " + strLR);*/
        String arreRes[] = new String[3];
        arreRes[0]=""+ intNextState;
        arreRes[1]=""+ intPointerTape;
        arreRes[2]=strCinta;
        return arreRes; //next state
    }
    public String strTMState(String[] strTMStates, int intState){
        String strState0;
        String strState1;
        strState0 = strTMStates[intState].substring(0,8);
        strState1 = strTMStates[intState].substring(8,16);
        
        int intNextState0 = Integer.parseInt(strState0.substring(0,6),2);
        int intNextState1 = Integer.parseInt(strState1.substring(0,6),2);

        String strWriteTape0 = ""+strState0.charAt(6);
        String strWriteTape1 = ""+strState1.charAt(6);

        
        String strLR0 = strState0.charAt(7)+"";;
        String strLR1 = strState1.charAt(7)+"";;
        if (strLR0.equals("0")) { //0 left,1 right
            strLR0 = "L";
         }else{
            strLR0 = "R";
         }
         if (strLR1.equals("0")) { //0 left,1 right
            strLR1 = "L";
         }else{
            strLR1 = "R";
         }
         String strintState="";
         String strNextState0="";
         String strNextState1="";
         if (intState<10) {
             strintState="0";
         }
         strintState = strintState+intState;
         if (intNextState0<10)
            strNextState0="0";
        strNextState0=strNextState0+intNextState0;
         if (intNextState1<10)
            strNextState1="0";
        strNextState1=strNextState1+intNextState1;

        // edo, escribe, movimiento, siguiente estado,  salida, movmiento SE
        return  ""+strintState+"|  "+ strWriteTape0 +"|  "+ strLR0 +"|  "+ strNextState0 +"|| "+ strWriteTape1 +" |  "+ strLR1 +"|  "+ strNextState1 +" |";
        
    }


       public void printMT(String[] strStatesTM ){
        int intState = 0;
        int i=0;
        String strCintaT;
        int intPointerTape = intSizeTape/2;
        String strArreRes[] = new String[2];
        strCintaT = this.setTape(intSizeTape);
        while (intState!=63 && i<intTransition && (intPointerTape < intSizeTape && intPointerTape>0)){
            this.strTMState(strStatesTM,intState);

            System.out.println("entre");
            strArreRes = simulationTM(strStatesTM, strCintaT, intState, intPointerTape);
            intState = Integer.parseInt(strArreRes[0]);
            intPointerTape = Integer.parseInt(strArreRes[1]);
            strCintaT = strArreRes[2];
            i++;
        
        }

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

/*
 intTotal0s = arrayA0[i]+arrayI0[i];
  intTotal1s = TOTALDATOS -intTotal0s;
  fltn0N = intTotal0s/TOTALDATOS;
  fltn1N = intTotal1s/TOTALDATOS;

  fltP = arrayA0[i]/intTotal0s;
  fltQ = (TotalAs - arrayA0[i])/intTotal1s;
    //arrayFntUtl[i] =(1-fltP)*fltP*fltn0N+ (1-fltQ)*fltQ*fltn1N;
    fltEntropia = fltn0N*(-fltP*log2(fltP)-(1-fltP)*log2(1-fltP))+fltn1N*(-fltQ*log2(fltQ)-(1-fltQ)*log2(1-fltQ) );
    //
    arrayFntUtl[i]= (42/1909)*(1-(42/1909)) - fltEntropia;
*/