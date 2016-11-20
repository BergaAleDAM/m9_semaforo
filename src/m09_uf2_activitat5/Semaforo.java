package m09_uf2_activitat5;

import java.util.concurrent.Semaphore;

public class Semaforo {

    public static float saldoTotal = 120;

    public static void main(String[] args) {
        /**
         * Se delcara el semaforo para interrumpir cualquier proceso y que no entren
         * en conflicto una posible variable que se puede modificar
         */
        final Semaphore semaph = new Semaphore(1, true);


        final Runnable rIngreso = new Runnable() {
            /**
             * EL primer run cortara el paso con el acquire, se ingresara en la cuenta
             * se imprime por pantalla y cierra el semáforo
             */
            public void run() {
                try {
                    semaph.acquire();
                    ingresar(10);
                    System.out.println("Saldo Compte " + llegirSaldo());
                    semaph.release();
                } catch (Exception e) {

                }
            }

        };

        final Runnable rTreu = new Runnable() {
            /**
             * Este es igual al anterior pero sacando dinero
             */
            public void run() {
                try {
                    semaph.acquire();
                    sacar(8);
                    System.out.println("Saldo Compte" + llegirSaldo());
                    semaph.release();
                } catch (Exception e) {

                }
            }

        };

        /**
         * Recorremos un bucle que hemos creado para ejecutar los Thread que
         * ejecutaran los ingresos y extraeran el dinero.
         */

            new Thread(rIngreso).start();
            new Thread(rTreu).start();
            new Thread(rIngreso).start();
            new Thread(rTreu).start();
        

    }

    /**
     * Este metodo actualiza la variable de saldo y la guarda sumando lo que se 
     * pasa por parametro
     *
     * @param diners
     */
    public static void ingresar(float diners) {
        float aux, saldo;
        aux = llegirSaldo();
        aux += diners;
        saldo = aux;
        guardarSaldo(saldo);
    }

    /**
     * Hace exactamente lo contrario que el anterior
     *
     * @param diners
     */
    public static void sacar(float diners) {
        float aux, saldo;
        aux = llegirSaldo();
        aux -= diners;
        saldo = aux;
        guardarSaldo(saldo);
    }


    public static float guardarSaldo(float saldo) {
        saldoTotal = saldo;
        return saldoTotal;
    }

    public static float llegirSaldo() {
        return saldoTotal;
    }

}
