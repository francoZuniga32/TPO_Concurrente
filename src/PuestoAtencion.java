import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * los puestos de atencion es donde los pasajeros hacer check-in de los pasajes
 * tiene una capacidad maxima de personas y un guardar es el encargado de dejar pasar a los pasajeros
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

    public void entrarPuestoAtencion()throws InterruptedException{
        entrada.lock();
        while(this.ocupados == this.lugares){
            //helper.ThreadMsg("Esperando a que se desocupe...");
            pasajeros.await();
        }
        this.ocupados ++;
        helper.ThreadMsg(" Esta esperando en la fila...");
        entrada.unlock();
    } 

    public void hacerCheckIn()throws InterruptedException{
        entrada.lock();
        helper.ThreadMsg("Realizando el check in...");

        
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
