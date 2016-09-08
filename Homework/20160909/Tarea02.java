import java.util.Random;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;

public class Tarea02 {
	public static double[] arreXu;
	public double intXu;
	public static double[] arreXi;
	public double intXi;
	public static void main(String[] args) throws IOException{
		Tarea02 tarea02 = new Tarea02();
		String str;
		int N, M, O;
		str = "MI";
		N=0;
		M=0;
		O=0;
		if (args[0]!=null) {
			M=Integer.parseInt(args[0]);
			N=Integer.parseInt(args[1]);
			O=Integer.parseInt(args[2]);
		}

		arreXu = new double[N];
		arreXi = new double[N];
		int i;
		i=0;
		//tarea02.fltProducciones(M,N, O);
		while (tarea02.fltProducciones(M,N, O)>3.2){
		}
		//double x =tarea02.fltProducciones(M,N, O);
		//System.out.println(x);
		//int algostr = tarea02.intRegla(N,str);
	}

	public	double fltProducciones(int M, int N, int O)  throws IOException {
		int i,j;
		double fltPromedio=0;
		i=0;

		PrintWriter writer = new PrintWriter("file.txt", "UTF-8");
		while(i < N){
			arreXu[i]=fltTeorema(N, O);
			fltPromedio+=arreXu[i];
			i++;
		}

		//std deviation

		intXu=fltPromedio/N;
		double finDes=0;
		i=0;
		while(i < N){
			finDes+=Math.pow((arreXu[i]-intXu),2);
			i++;
		}
		
		Arrays.sort(arreXu);
		
		/*
			Normalización y estandarización
		*/
		i=0;
		double arreAux[];
		double fltSum=0;
		arreAux = new double[N];

		for(j = 0; j < N; j++){
			arreAux[j] = Math.pow((arreXu[j]-intXu),2);
			fltSum+=arreAux[j];
		}
		fltSum = Math.sqrt(fltSum/(N));

		while(i < N){
			arreXu[i]=(arreXu[i]-intXu)/ fltSum;
			writer.println(""+arreXu[i]);
			i++;
		}
		writer.close();

		/*
		 * calulo de \mu
		 */ 
		double sum=0;
		double stdD=0;
		for (int k = 0; k < N; k++) {
       	 sum += arreXu[k];
        	stdD += Math.pow(arreXu[k], 2);
    	}

    	//System.out.println(sum/N);
    	double variance = stdD/N;
    	//System.out.println(variance);

    	/*
    	 * normal
    	*/
    	writer = new PrintWriter("file2.txt", "UTF-8");
    	Random r = new Random();

		double arreAuxNormal[];
		double normal=0;
		arreAuxNormal = new double[N];

    	for (int k=0; k< N; k++) {
    		arreAuxNormal[k]=r.nextGaussian();
    		writer.println(""+arreAuxNormal[k]);
    	}

		writer.close();
		
		float intArray[];
		int intDecilSize=10;
		intArray = new float[intDecilSize];
		double fltTmp;
		for (int k =0; k < intDecilSize; k++) {
			intArray[k]=N*(k/intDecilSize);
			writer.println(""+intArray[k]);
		}
		//*/

		double sumNom=0;
		double stdDNom=0;
		for (int k = 0; k < N; k++) {
        sumNom += arreAuxNormal[k];
        stdDNom += Math.pow(arreAuxNormal[k], 2);
    	}
		writer.close();
    	//System.out.println(sumNom/N);
    	double varianceNom = stdDNom/N;

    	//De acuerdo a las tablas del documento d
    	//Normality from Monte Carlo Simulation for Statistical Validation of Computer Intensive Algorithms_1.pdf
    	int arreDeci[] = new int[10];
    	for (int  k = 0; k< 10; k++) {
    		arreDeci[k]=0;
    	}
    	for (i=0;i< N ;i++ ) {
    		if (arreXu[i]<-1.2815) {
    			arreDeci[0]++;
    		}else{
    			if (arreXu[i]< -0.8416) {
    				arreDeci[1]++;	
    			}else{
    				if (arreXu[i]< -0.5243) {
    					arreDeci[2]++;
    				}else{
    					if (arreXu[i]< -0.2532) {
    						arreDeci[3]++;
    					}else{
    						if (arreXu[i]<0.0) {
    							arreDeci[4]++;
    						}else{
    							if (arreXu[i]<0.2532) {
    								arreDeci[5]++;
    							}else{
    								if (arreXu[i]<0.5243) {
    									arreDeci[6]++;
    								}else{
    									if (arreXu[i]<0.84316) {
    										arreDeci[7]++;
    									}else{
    										if (arreXu[i]<1.2815) {
    											arreDeci[8]++;
    										}else{
    											arreDeci[9]++;
    										}
    									}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	int arreDeciNom[] = new int[10];
    	for (int k=0 ; k< 10; k++) {
    		arreDeciNom[k]=0;
    	}
    	for ( i=0;i< N ;i++ ) {
    		if (arreAuxNormal[i]<-1.2815) {
    			arreDeciNom[0]++;
    		}else{
    			if (arreAuxNormal[i]< -0.8416) {
    				arreDeciNom[1]++;	
    			}else{
    				if (arreAuxNormal[i]< -0.5243) {
    					arreDeciNom[2]++;
    				}else{
    					if (arreAuxNormal[i]< -0.2532) {
    						arreDeciNom[3]++;
    					}else{
    						if (arreAuxNormal[i]<0.0) {
    							arreDeciNom[4]++;
    						}else{
    							if (arreAuxNormal[i]<0.2532) {
    								arreDeciNom[5]++;
    							}else{
    								if (arreAuxNormal[i]<0.5243) {
    									arreDeciNom[6]++;
    								}else{
    									if (arreAuxNormal[i]<0.84316) {
    										arreDeciNom[7]++;
    									}else{
    										if (arreAuxNormal[i]<1.2815) {
    											arreDeciNom[8]++;
    										}else{
    											arreDeciNom[9]++;
    										}
    									}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	double intFinal;
    	intFinal=0;
    	double quartil=.1*N;
    	for (i=0; i<10; i++) {
    		intFinal += Math.pow(arreDeci[i]-quartil,2)/(quartil);
    		//System.out.println("deciles:\t"+arreDeci[i]);
    		//System.out.println("deciles NOm:\t"+arreDeciNom[i]);
    		//System.out.println(Math.pow(arreDeci[i]-arreDeciNom[i],2)/arreDeciNom[i]);
    	}
    	if (intFinal<3.2) {

			System.out.println("Característica de los datos");
			System.out.println("===========================");
    		System.out.println("sigma:\t" +Math.sqrt(finDes/(N-1))*Math.sqrt(N));
			System.out.println("   Mu:\t"+ intXu );	
			System.out.println("Prueba Estadística");
			System.out.println("===========================");
    		System.out.println("li:\t"+intFinal);
    	}
    	
		
		return intFinal;
	}
	/*
	 *	Genera un arreglo  con teoremás y se calcula la longitud promedio
	 */
	public double fltTeorema(int N, int O){
		int i,j;
		double fltPromedio=0;
		arreXi = new double[N];
		i=0;
		while(i < N){
			arreXi[i]=intRegla(O,"MI");
			fltPromedio+=arreXi[i];
			//System.out.println("long " + arreXm[i]);
			//System.out.println("prom " + arreXm[i]/O);
			i++;
		}
		intXi=fltPromedio/N;
		return intXi;
	}
	//Aplica N axiomas a la cadena, es decir
	public int intRegla(int N, String str){
		int intLength;
		int n,i;
		n = N;
		i=0;
		int j;
		Random r = new Random();
		int intRandom;

		while(i<n){
			intRandom = r.nextInt(4);
			switch(intRandom){
				case(0):
					 if (str.substring(str.length() - 1).equals("I")) {
					 	str = str + "U";
					 	//System.out.println("soy 1");
						//System.out.println(str);
					 	i++;
					 }
					break;
				case(1):
					//System.out.println("soy 2");
					str =str+str.substring(1);
					i++;
					//System.out.println(str);
					break;
				case(2):
					if (str.indexOf("III")>0) {
						//System.out.println("soy 3");
						//System.out.println(str);
						str= str.replaceAll("III", "U");
						//System.out.println(str);
						//System.out.println(str);
						i++;
					}
					break;
				case(3):
					if (str.indexOf("UU")>0) {
						//System.out.println("soy 4");
						str= str.replaceAll("UU", "");
						//System.out.println(str);
						i++;
					}
					break;
			}

		}
	return str.length();
	}

	/** 
 	* Returns the height of the normal distribution at the specified z-score
 	*/
	double getNormalProbabilityAtZ(double z) {
	    return Math.exp(-Math.pow(z, 2) / 2) / Math.sqrt(2 * Math.PI);
	}
	/**
	  * Returns the area under the normal curve between the z-scores z1 and z2
	  */
	double getAreaUnderNormalCurve(double z1, double z2) {
	    double area = 0.0;
	    final int rectangles = 100000; // more rectangles = more precise, less rectangles = quicker execution
	    final double width = (z2 - z1) / rectangles;
	    for(int i = 0; i < rectangles; i++)
	        area += width * getNormalProbabilityAtZ(width * i + z1);
	    return area;
	}
}
