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
		double x =tarea02.fltProducciones(M,N, O);
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
			//System.out.println("long " + arreXm[i]);
			//System.out.println("prom " + arreXm[i]/O);

			//writer.println(""+arreXu[i]);
			i++;
		}
		//se aregla
		Arrays.sort(arreXu);
		/*
			Normalización y estandarización
		*/
		intXu=fltPromedio/N;
		i=0;
		double arreAux[];
		double fltSum=0;
		arreAux = new double[N];
		for(j = 0; j < N; j++){
			arreAux[j] = Math.pow((arreXu[j]-intXu),2);
			fltSum+=arreAux[j];
		}
		fltSum = Math.sqrt(fltSum/(N));
		//System.out.println(fltSum);

		
		while(i < N){
			//System.out.println("arreXu " + arreXu[i]);
			//System.out.println("fltPromedio " + intXu);
			arreXu[i]=(arreXu[i]-intXu)/ fltSum;
			//System.out.println("long " + arreXm[i]);
			//System.out.println("prom " + arreXm[i]/O);
			writer.println(""+arreXu[i]);
			i++;
		}
		writer.close();
		//\mu
		double sum=0;
		double stdD=0;
		for (int k = 0; k < N; k++) {
        sum += arreXu[k];
        stdD += Math.pow(arreXu[k], 2);
    	}

    	System.out.println(sum/N);
    	double variance = stdD/N;

    	System.out.println(variance);

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

		/*
			deciiles
		
		writer.println("===");
		float intArray[];
		int intDecilSize=10;
		intArray = new float[intDecilSize];
		double fltTmp;
		for (int k =0; k < intDecilSize; k++) {
			fltTmp = 1/10;
			//System.out.println(1/10);

			//System.out.println(String.format("%.12f", fltTmp));
			intArray[k]=N*(k/intDecilSize);
			writer.println(""+intArray[k]);
		}
		*/

		return intXu;
	}
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

}
