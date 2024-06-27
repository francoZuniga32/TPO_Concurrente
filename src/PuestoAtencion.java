import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * este caso es similar a los productores consumidores y se tiene que diseñar de forma similar.
 */
public class PuestoAtencion {
    private int lugares;
    private int ocupados;
    private final Lock entrada = new ReentrantLock();
    private final Condition pasajeros;
    private final Condition guardia;

    public PuestoAtencion(int cantidadLugares){
        this.lugares = cantidadLugares;
        this.ocupados = 0;
        this.pasajeros = entrada.newCondition();
        this.guardia = entrada.newCondition();
    }

    public void hacerCheckIn()throws InterruptedException{
        entrada.lock();
        while(this.ocupados == this.lugares){
            //helper.ThreadMsg("Esperando a que se desocupe...");
            pasajeros.await();
        }
        helper.ThreadMsg("Realizando el check in...");

        this.ocupados ++;
        Thread.sleep(2000);
        
        this.guardia.signal();

        entrada.unlock();
    } 

    public void dejarEntrar() throws InterruptedException{
        entrada.lock();
        while(this.ocupados < lugares){
            helper.ThreadMsg("Esperando...");

            guardia.await();
        }
        helper.ThreadMsg("Esperando a desocupar ...");

        this.ocupados --;
        Thread.sleep(2000);
        this.pasajeros.signal();
        entrada.unlock();
    } 
}
