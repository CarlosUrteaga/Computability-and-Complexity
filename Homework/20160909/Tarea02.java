import java.util.Random;
import java.io.*;

public class Tarea02 {
	public static float[] arreXu;
	public float intXu;
	public static float[] arreXi;
	public float intXi;
	public static void main(String[] args) {
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

		arreXu = new float[N];
		arreXi = new float[N];
		float x =tarea02.fltProducciones(M,N, O);
		System.out.println(x);
		//int algostr = tarea02.intRegla(N,str);
	}

	public	float fltProducciones(int M, int N, int O){
		int i,j;
		float fltPromedio=0;
		i=0;
		//PrintWriter writer; = new PrintWriter("the-file-name.txt", "UTF-8");
		try {
		File file = new File("filename.txt");
		if (!file.exists()) {
				file.createNewFile();
			}
			while(i < N){
			arreXu[i]=fltTeorema(N, O);
			fltPromedio+=arreXu[i];
			writer.println(""+arreXu[i]);
			//System.out.println("long " + arreXm[i]);
			//System.out.println("prom " + arreXm[i]/O);

			//writer.println(""+arreXu[i]);
			
			i++;
		}
		//writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			// if file doesnt exists, then create it
			
		
		intXu=fltPromedio/N;
		return intXu;
	}
	public float fltTeorema(int N, int O){
		int i,j;
		float fltPromedio=0;
		arreXi = new float[N];
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
