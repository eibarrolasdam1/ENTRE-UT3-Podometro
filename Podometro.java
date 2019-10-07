
/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Eunate Ibarrola Santesteban - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;

        double auxHombre = Math.ceil (altura * ZANCADA_HOMBRE);
        double auxMujer = Math.floor (altura * ZANCADA_MUJER);
        if (sexo == HOMBRE) {
            longitudZancada = auxHombre;
        }
        else if (sexo == MUJER) {
            longitudZancada = auxMujer;
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        switch (dia) {
            case 1: dia = 1;
            break;
            case 2: dia = 2;
            break;
            case 3: dia = 3;
            break;
            case 4: dia = 4;
            break;
            case 5: dia = 5;
            break;
            case 6: dia = SABADO;
            break;
            case 7: dia = DOMINGO;
            break;
        }

        if (dia <= 5){
            totalPasosLaborables += pasos;
        }
        else if (dia == 6){
            totalPasosSabado += pasos;
        }
        else {
            totalPasosDomingo += pasos;
        }

        if (horaInicio >= 2100){
            caminatasNoche++;
        }
        tiempo = horaFin - horaInicio;
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        double auxAltura = altura /100;
        double auxLongitudZancada = longitudZancada /100;

        System.out.println ("Configuración del Podometro" +
                            "\n***********************************" + 
                            "\nMarca:  " + marca +
                            "\nAltura:  " + auxAltura + "  mtos" +
                            "\nSexo:  " + sexo + 
                            "\nLongitud de Zancada:  " + auxLongitudZancada + "  mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        double distanciaRecorridaLaborable = (totalPasosLaborables * longitudZancada); 
        double distanciaRecorridaSabado = (totalPasosSabado * longitudZancada);
        double distanciaRecorridaDomingo = (totalPasosDomingo * longitudZancada);
        double distanciaRecorridaFinSemana = distanciaRecorridaSabado + distanciaRecorridaDomingo;
        System.out.println("\nEstadisticas del Podometro" + 
                           "\n**************************************************************************" + 
                           "\nDistancia recorrida toda la semana:  " + distanciaRecorridaLaborable / 10000 + " Km" +
                           "\nDistncia recorrida fin de semana:  " + distanciaRecorridaFinSemana / 10000 + " Km" +
                           "\n" +
                           "\nNº pasos laborables:  " + totalPasosLaborables +
                           "\nNº pasos Sabado:  " + totalPasosSabado +
                           "\nNº pasos Domingo:  " + totalPasosDomingo +
                           "\n" + 
                           "\nNº caminatas realizadas a partir de las 21h:  " + caminatasNoche +
                           "\nTiempo total caminado:  " + tiempo / 60 + " h y " + tiempo % 60 + " m" +
                           "\nDia/s con mas pasos caminados:  " + diaMayorNumeroPasos());
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String dia = "";
        if (totalPasosSabado > totalPasosLaborables && 
            totalPasosSabado > totalPasosDomingo){
                dia = "SABADO";
        }
        else if (totalPasosDomingo > totalPasosLaborables &&
                 totalPasosDomingo > totalPasosSabado){
                     dia = "DOMINGO";
        }
        else {
            dia = "LABORABLES";
        }       
        return dia;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
