import java.util.Random;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Proyecto01 {
    public static final int NUMBEROFSTATES = 64;
    public static final int INTSIZE  = 1024;
    public static int intTransition;
    public static int intSizePopulation;
    public static int intGeneration;
    public static int intSizeTape;
    public static void main(String[] args) {

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
        // Ask for configuration
        //System.out.println("Dame la raíz del generador de números aleatorios:");
        //intSeed = Integer.parseInt(System.console().readLine());
        intSeed = 20161019;
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
        intSizePopulation =5;
        
        intTransition = 10000; //cuando cambias de estado, cuando l
        intSizeTape = 100;
        dblMutationProb = 0.01;
        dblPonderation = 0;
        Proyecto01 tst = new Proyecto01();
        TuringClass turingMachines[] = new TuringClass[intSizePopulation];
        
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
        double dblFitness[] = new double[intSizePopulation];

        for (j = 0; j< intSizePopulation; j++) {
            turingMachines[j] = new TuringClass();
            turingMachines[j].strMT =  new String[NUMBEROFSTATES];
            turingMachines[j].strMT = tst.tmSetStates( turingMachines[j].strMT, r);
            /*
            strResTape[j]= tst.simulationProblemMT(turingMachines[j].strMT);
            dblFitness[j] = tst.fitnessTapefromTM(strTargetText, strResTape[j]);
            System.out.println(dblFitness[j]);*/
        }
        /*
        System.out.println("====");
        double tmp;
        TuringClass turingTemp;
        // ordenación
        for ( i = 0; i < intSizePopulation; i++) {
            for (j = i + 1; j < intSizePopulation; j++) {
                tmp = 0;
                turingTemp = new TuringClass();
                if (dblFitness[i] < dblFitness[j]) {

                    tmp = dblFitness[i];
                    dblFitness[i] = dblFitness[j];
                    dblFitness[j] = tmp;

                    turingTemp  = turingMachines[i];
                    turingMachines[i] = turingMachines[j];
                    turingMachines[j] = turingTemp;
                }
            }
        }

        for (j = 0; j< intSizePopulation; j++) {
            System.out.println(dblFitness[j]); 
        }
*/

        tst.projectProblem(turingMachines,strTargetText, r);
        //projectProblem(TuringClass[] turingMachines, String strTargetText){

        



        //String arreRes[] = new String[3];
        //arreRes = tst.simulationTM(turingMachines[0].strMT, strTest, 0, strCinta.length()/2);

        //projectProblem(TuringClass[] turingMachines, String strTargetText){
        /*
        arreRes[0]=""+ intNextState;
        arreRes[1]=""+ intPointerTape;
        arreRes[2]=strCinta;
        
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

        System.out.println(strTest);
        System.out.println(tst.simulationMT(tst2[0].strMT, strCinta, 0, algo));
        System.out.println("estado \t"+ tst2[0].strMT[0]);
        System.out.println(strTest);
        // Se hace el cruzamiento
        String strParte = "01234567";
        String strParte02 = "00001111";
        String strResCGA[] = new String[2];
        strResCGA =  tst.crossGA(strParte, strParte02, .1,  r);
        System.out.println(strResCGA[0]);
        System.out.println(strResCGA[1]);
        //* /
        System.out.println(tst.mutationGA("000000000", .9, r));
        System.out.println(tst.mutationGA("000000000", .9, r));
        System.out.println(tst.mutationGA("000000000", .9, r));
        System.out.println(tst.mutationGA("000000000", .9, r));
        System.out.println(tst.mutationGA("000000000", .9, r));
        System.out.println(tst.mutationGA("000000000", .9, r));
        //*/
    }
    /*
    Pendientes
        (done) Método que simule una máquina de turing (posición, escritura, movimiento, siguiente estado)
        (done) Simulación de los estados de una máquina de Turing
        (done) Funciones de evaluación
            Distancia por byte, por letra o por pareja o por como
        
        (done)Algoritmo genético
            (done) cruzamiento
            (done) mutación
            (done) fitness
        - Complejidad de Kolmgorov
        - Integar
            genera 50 MT
            Evaluar
            Ordena
            Cruza
            muta (repet) until n iteraciones o n estados
        - Generar Salias de acuerdo a Kuri
            
    */
    /*
        Proyecto
            generar varias MT y evaluar,
    */
    public TuringClass[]  projectProblem(TuringClass[] turingMachines, String strTargetText, Random r){

        int i;
        int j;
        double tmp;
        TuringClass turingTemp;
        // Supongo que ya inicie las TM  
        String strResTape[] = new  String [intSizePopulation];
        double dblFitness[] = new double[intSizePopulation];
        String strTM[] = new String[intSizePopulation];
        //simulation

        for (i=0; i< intSizePopulation; i++) {
            strResTape[i]= simulationProblemMT(turingMachines[i].strMT);
            dblFitness[i] = fitnessTapefromTM(strTargetText, strResTape[i]);
        }
        //ordenación

        //concatenar con el abuelo
        for ( i = 0; i < intSizePopulation; i++) {
            for (j = i + 1; j < intSizePopulation; j++) {
                tmp = 0;
                turingTemp = new TuringClass();
                if (dblFitness[i] < dblFitness[j]) {

                    tmp = dblFitness[i];
                    dblFitness[i] = dblFitness[j];
                    dblFitness[j] = tmp;

                    turingTemp  = turingMachines[i];
                    turingMachines[i] = turingMachines[j];
                    turingMachines[j] = turingTemp;
                }
            }
        }
        //Seleccionar top 
        //GA
        //MT to String
        for ( i = 0; i < intSizePopulation; i++) {
            strTM[i] = tmtoString(turingMachines[i].strMT);
        }
        strTM = simulationGA(strTM,r);
        //string to MT 
        for ( i = 0; i < intSizePopulation; i++) {
            turingMachines[i].strMT = stringToTM(strTM[i]);
        }

        return turingMachines;
    }
    /*

        String strTM[] = new String[intSizePopulation];
        for ( i = 0; i < intSizePopulation; i++) {
            strTM[i] = tmtoString(turingMachines[i]);
        }

    public String[] stringToTM(String strResTM){
        String[] strTM = new String[NUMBEROFSTATES];
        int  i;
        for (i=0; i<NUMBEROFSTATES; i++) {
            strTM[i] = strResTM.substring(16*i,16*(i+1));
        }
        return strTM;
    }
    */
    public String[] simulationGA(String [] strTMs, Random r){
        int i;
        int intTMSize;
        intTMSize = strTMs[0].length();

         // Convert TM to String to cross and mutate
        String strRes[]= new String[2];
        String strTM;
        double dblCrossProb = .1;
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
        return  ""+strintState+"|  "+ strWriteTape0 +"|  "+ strLR0 +"|  "+ strNextState0 +"||"+ strWriteTape1 +"|  "+ strLR1 +"|  "+ strNextState1 ;
        
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