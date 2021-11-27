    package tarea2invop;
    
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/* SimulatedAnneling Class
 * Clase que realiza la metaheuristica Simulated Anneling, junto a realizar las funciones
    de Crear la Poblacion Random, el Metodo Swap y el Criterio de Metropolis
 * @author Yerko Gonzalez
 */
public class SimulatedAnnealing {
    
     
     public double SA_exe(double TempI, double TempF, double alpha, SRFLP Problem){
        /**
         * Funcion que ejecuta la metaheuristica Simulated Anheling
         * @param TempI es la Temperatura inicial
         * @param TempF es la Temperatura final
         * @param alpha es el alpha
         * @param Problem es el problema que se desea resolver 
         * @return Retorna la mejor distancia encontrada al ejecutar la metaheuristica
         */
    
         int[] solution_act = new int[Problem.getProblemSize()]; 
         solution_act = crearPoblacionRandom(Problem.getProblemSize());
         int[] solution_new = Swap_Method(solution_act);
         double Temp = TempI;
         boolean Crit_Acep;
         
         //Impresion de la primera solucion con su respectiva deistancia
         System.out.println("La Solución Inicial es:");
         System.out.println(Arrays.toString(solution_act));
         double distance1 = Problem.getTotalDistance(solution_act);
         System.out.println("La Distancia Inicial es:" + distance1);
         System.out.println("--------------------------------------------------------------");
         
         int i = 1;
         
         //Buclé que genera 1 ejecucion de la mmetaheuristica
         while(Temp > TempF){
             double distance2 = Problem.getTotalDistance(solution_new);
             if(distance2 < distance1){
                 solution_act = solution_new;
                 distance1 = distance2;
                 Crit_Acep = true;
             }else if(Criterio_Problem_Metrop(distance1, distance2, Temp) == true){
                 solution_act = solution_new;
                 distance1 = distance2;
                 Crit_Acep = true;
             } else{
                 Crit_Acep = false;
             }
             
             //Diminucion de la temperatura
             Temp = Temp * alpha;
             solution_new = Swap_Method(solution_act);
             
             //Impresion de los resultados obtenidos
             if(Crit_Acep == true){
             System.out.println("Iteracion Numero: " + i++);
             System.out.println("Criterio de Metropolis: Aceptado");
             System.out.println("La Nueva Solucion es: ");
             System.out.println(Arrays.toString(solution_act));
             System.out.println("La Nueva Distancia es:" + distance1); 
             System.out.println("--------------------------------------------------------------");
             }else{
             System.out.println("Iteracion numero: " + i++);
             System.out.println("Criterio de Metropolis: Rechazado");
             System.out.println("La Solucion Seguira siendo:");
             System.out.println(Arrays.toString(solution_act));
             System.out.println("La Distancia Seguira siendo: " + distance1); 
             System.out.println("--------------------------------------------------------------");   
             }
        }
         
         //Impresion de los resultados de las 10 ejecuciones
         System.out.println("||||||||||||||||||||||||||");
         System.out.println("  EJECUCION FINALIZADA  ");
         System.out.println("||||||||||||||||||||||||||");
         System.out.println("");
         System.out.println("La Mejor Solucion encontrada es: ");
         System.out.println((Arrays.toString(solution_act)));
         System.out.println("La Distancia de la mejor Solucion es: " + distance1);
         System.out.println("--------------------------------------------------------------");
         return distance1;
     }
     
     /**
      * Esta funcion fue sacada de:
      * @author Justin
      * https://stackoverflow.com/questions/20039025/java-array-of-unique-randomly-generated-integers
      */
     public int[] crearPoblacionRandom(int tamaño){
        /** 
         * Esta que crea una poblacion de un tamaño "tamaño"
         * @param tamaño es el Tamaño de la poblacion deseada
         * @return Retorna la poblacion random
         */ 
        
        int[] poblacion = new int[tamaño];
        ArrayList<Integer> auxPoblacion = new ArrayList<>();
        for(int i=0;i<tamaño;i++){
            auxPoblacion.add(i); //Añade numeros desde el 0 hasta el tamañno a una Array 
        }
        Collections.shuffle(auxPoblacion); //Desordena el Array 
        for(int i=0;i<tamaño;i++){
            poblacion[i] = auxPoblacion.get(i); //Copia el Array desordenado a la Poblacion
        }
        return poblacion;
    }
     
     /**
      * Esta funcion fue basada en el codigo de:
      * @author Dyend
      * https://github.com/Dyend/Simulated-annealing-SRFLP/blob/master/src/SimulatedAnnealing.java
      */
     public int[] Swap_Method(int[] solution){
         /**
      * Funcion que realiza el metodo Swap a un Array
      * @param solution es el Array al que se le hará Swap
      * @return Retorna el Array con un Swap
      */
         
        int rand1 = 0;
        int rand2 = 0;
        Random random = new Random();
        int [] SwapSolution = new int[solution.length];
        
        //Verifica que rand1 y rand2 sean diferentes
        while(rand1 == rand2){
            rand1 = random.nextInt(((solution.length-1) - 0) + 1) + 0;
            rand2 = random.nextInt(((solution.length-1) - 0) + 1) + 0;
        }
        
        //ciclo que copia los valores de la solucion a la solucion con Swap
        for (int i=0;i<solution.length;i++){
            if (i != rand1 && i!= rand2){
                SwapSolution[i] = solution[i];
            }
        }
        
        //Realiza el Swap
        SwapSolution[rand1] = solution[rand2];
        SwapSolution[rand2] = solution[rand1];
        
        return SwapSolution;
     }
     
     
     public boolean Criterio_Problem_Metrop(double distance1, double distance2, double Temp){         
        /**
       * Funcion que realiza el criterio de metropolis entre 2 distancias
       * @param distance1 es la Distancia 1
       * @param distance2 es la Distancia 2
       * @param Temp es la Temperatura que hay al momento de realizar el criterio
       * @return Retorna un True si el cirterio se acepta y un False en el caso contrario
       */
        
         double distance_diff = distance2 - distance1;
         double random = Math.random();
         return Math.exp(-1*(distance_diff/Temp)) > random;
     }
} 
