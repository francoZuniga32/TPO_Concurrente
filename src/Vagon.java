import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vagon {
    private Lock mutex = new ReentrantLock();
    private Condition pasajeros;
    private Condition conductor;
    private int cantidadLugares;
    private int cantidadLugaresLibres;
    private boolean[] paradas;
    private int[] pasajerosPorParada;

    public Vagon(int cantidadLugares, int terminales){
        this.cantidadLugaresLibres = 0;
        this.cantidadLugares = cantidadLugares;

        this.pasajeros = mutex.newCondition();
        this.conductor = mutex.newCondition();

        this.paradas = new boolean[terminales];
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = false;
        }

        this.pasajerosPorParada = new int[terminales];
        for (int i = 0; i < pasajerosPorParada.length; i++) {
            pasajerosPorParada[i] = 0;
        }
    }

    public void entradaPasajero(int terminal)throws Exception{
        this.mutex.lock();

        //no hay lugares libre
        while (cantidadLugaresLibres == cantidadLugares) {
            helper.ThreadMsg("Esperando a entrar al vagon...");
            this.pasajeros.await();
        }
        this.cantidadLugaresLibres ++;
        //incrementamos la cantidad de pasajeros para la parada
        this.pasajerosPorParada[terminal] ++;
        //evaluamos si tenemos que bajar en una terminal
        //notificamos al chofer
        this.conductor.signal();
        while(!this.paradas[terminal] && this.pasajerosPorParada[terminal] > 0){
            helper.ThreadMsg("Esperando a bajar. Pasajero restantes para la parada de la terminal " +terminal+ " "+this.pasajerosPorParada[terminal]+" | cantidad de pasajeros restantes: "+this.cantidadLugares);
            this.pasajeros.await();
        }
        //bajamos en la terminal indicada
        this.pasajerosPorParada[terminal]--;
        helper.ThreadMsg("Bajando en la estacion "+terminal+". pasajeros restantes"+this.pasajerosPorParada[terminal]);
        this.conductor.signal();

        this.mutex.unlock();
    }

    public void conductor()throws Exception{
        this.mutex.lock();

        while(this.cantidadLugaresLibres < this.cantidadLugares){
            this.pasajeros.signalAll();
            this.conductor.await();
        }
        helper.ThreadMsg("Empenzando el recorrido...");
        //comenzamos el recorrido
        int i = 0;
        //si no terminamos de recorrer las paradas o estan bajando pasajeros esperamos
        while(i < this.paradas.length ){
            //recorremos cada terminal
            helper.ThreadMsg("Saliendo a la estacion "+i+" ... ");
            Thread.sleep(2000);
            this.paradas[i] = true;
            //mientras tengamos pasajeros que tienen que bajar en esta parada esperamos
            while(this.pasajerosPorParada[i] > 0){
                    helper.ThreadMsg("Esperando a que los pasajeros de la terminal "+i+" bajen en su parada...");
                    this.pasajeros.signalAll();
                    this.conductor.await();
            }
            //avanzamos a la siguiente terminal
            i++;
        }
        helper.ThreadMsg("Regresando a la estacion...");
        //reseteamos la cantidad de lugares.
        this.cantidadLugaresLibres = 0;
        for (int j = 0; j < paradas.length; j++) {
            paradas[j] = false;
        }
        Thread.sleep(2000);
        helper.ThreadMsg("paradas: "+this.paradas.toString()+" cantidad por paradas: "+this.pasajerosPorParada.toString());
        this.pasajeros.signalAll();
        this.mutex.unlock();
    }
}
