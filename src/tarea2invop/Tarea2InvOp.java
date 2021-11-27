package tarea2invop;

/* Tarea2InvOp class
 * Clase que ejecuta la Metaherustica y realiza las 10 ejecuciones, finalmente
   entrega los 10 resultados junto a su Promedio y Desviacion Estandar
 * @author Yerko Gonzalez
 */
public class Tarea2InvOp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        double TempI, TempF, alpha;
        double[] resultados = new double [10];
        int ejecuciones = 1;
        double valor, suma;
        double Promedio, DesEstandar, var;
        
        var = 0;
        valor = 0;
        suma = 0;
        
        /**
     * Instancia del problema a resolver
     */
        
        SRFLP ejercicio = new SRFLP("QAP_sko56_04_n");
        
        /**
     * Asignacion de valores a los parametros Temperatura Inicial, Temperatura Final 
     * y Alpha
     */
        
        TempI = 10000;
        TempF = 0.2;
        alpha = 0.9;
        
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("  PARAMETROS DEL ARCHIVO  ");
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("");
        
        SimulatedAnnealing go = new SimulatedAnnealing();
        
         /**
     * Imprimr los parametros del problema a resolver
     */
        ejercicio.printInstance();
        
        System.out.println("");
        System.out.println("--------------------------------------------------------------");
        
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("  COMENZANDO EJECUCIONES  ");
        System.out.println("||||||||||||||||||||||||||||");
        
       
        //Ciclo que ejecuta el programa 10 veces
        //Codgo sacado de: https://tpec05.blogspot.com/2013/11/programando-media-varianza-y-desviacion.html
        for(int i = 0; i < 10; i++){
            System.out.println("");
            System.out.println("Ejecucion numero: " + ejecuciones);
            System.out.println("");
            valor = go.SA_exe(TempI, TempF, alpha, ejercicio);
            suma = suma + valor;
            resultados[i] = valor;
            ejecuciones++;
            System.out.println("--------------------------------------------------------------");
        }
        
        //Calculo del Promedio de las 10 ejecuciones realizadas
        Promedio = suma / 10;
       
        //Ciclo que calcula la varianza de las 10 ejecuciones realizadas
        for(int i = 0 ; i<10; i++){
            double rango;
            rango = Math.pow(resultados[i] - Promedio,2f);
            var = var + rango;
        }
        var = var / 10f;//suma de diferencias sobre "n" o "n - 1"
        
        //Calculo de la Desviacion Estandar
        DesEstandar = Math.sqrt(var);
        
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("  RESULTADOS OBTENIDOS  ");
        System.out.println("||||||||||||||||||||||||||||");
        
        System.out.println("");
        System.out.println("Datos obtenidos: ");
        int j = 1;
       
    
     //Ciclo que imprime las mejores distancias de las 10 ejecuciones realizadas
     
        for(int i = 0; i < 10; i++){
            System.out.println(j + ": " + resultados[i]);
            j++;
        }
        
        System.out.println("");
        System.out.println("El Promedio de las 10 ejecuciones es: " + Promedio);
        System.out.println("La Desviacion Estandar de las 10 ejecuciones es : " + DesEstandar);
    }   
    
}
