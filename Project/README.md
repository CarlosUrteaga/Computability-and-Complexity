# Complejidad de Kolmogorv
## Manual del usuario
### Jorge Carlos Urteaga Reyesvera

El programa se desarrollo en el lenguaje JAVA. La versión utilizada del JDK (Java Development Kit) es 1.8.0_92 fue probada bajo un entorno Unix. Por cuestiones de eficiencias sólo se desarrollaron dos Clases. La notación utilizada para las variables son los tres primeros letras para el tipo de variable seguido del nombre.

Nombre del archivo *Proyecto01.java* donde se enceuntran dos clases

#### Clases
Clase **Proyecto** lógica principal del programa
Clase **TuringClass** Clase auxiliar.
La estructura de las variables utilizadas

```java
String[]	Máquinas de turing, caracteres de 0,1)
String		Estados de máquinas de turing, caracteres de 0,1)
String		Manejo del gen par la mutaicón
double[] 	Arreglo de función de ajuste
	
```

La lógica del programa es la siguiente.

1. Generación aleatoria de máquinas de Turing.
2. Evaluación de las Máquinas de Turing
3. Mezcla con los antecedores
4. Ordenación de acuerdo a la función de ajuste.
5. Aplicación de los métodos de Algoritmos genéticos.
6. Evaluación de mejor máquina
7. Mostrar resultados


1. función main utilizada para obtener los parámetros, inicializar las variables y donde se ejecuta la lógica del programa
2. Simulacion general del problema de MT ```projectProblem ```
3. Cálculo de función fitness para ordenar ancestros e hijos
4. Obtención de los $n$ mejores genes
5. Simulación de AG ```	public String[] simulationGA```
6. Extracción de la mejor máquina de Turing.
7. Minimización de la máquina de Turin
8. Muesra de resultados.

```java
public class Proyecto01 
	//Funciones para el proyecto
	public static void main(String[] args)throws IOException 

	public double[]  projectProblem(TuringClass[] turingMachines, String strTargetText, Random r)
	
	// Funciones del AG
	public String[] simulationGA(String [] strTMs, Random r)
	public String simulationProblemMT(String[] strStatesTM )
pu	blic  String[] crossGA(String strTMOne, String strTMTwo, double 	dblProbability, Random r)
	public String mutationGA(String strTMs, double dblMutationProb, Random r)
	public double fitnessTapefromTM(String strTargetText, String strTMTape)
	
	//Funciones para la manipulación de TM
		public String[] simulationTM(String[] strStatesTM, String strCinta, int intState,  int intPointerTape)
	public String tmtoString(String[] strTM)
	public String[] stringToTM(String strResTM)
	public String[] tmSetStates(String [] strMT, Random r)
	public String[] reduceTM(String[] strStatesTM )

	public String strTMState(String[] strTMStates, int intState)
	public void printMT(String[] strStatesTM )public String setTape(int intTamanioSCinta)
	
	//Funciones para la manipulación de la cinta
	public String  modTape(String strTape, String strText)
	public String getStringTape(String strTape, String strText)
	public String strToBin(String strText)
	public String binToStr(String strBin)
		public boolean contains(int[] array, int key, int j) 
		
public static class TuringClass //clase auxiliar para el manejo de cadenas de caracteres.
```
#Repositiorio del programa
https://github.com/CarlosUrteaga/Computability-and-Complexity/tree/master/Project
