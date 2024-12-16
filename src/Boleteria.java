import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * la boleteria se encarga de asignar los pasajes de forma aleatoria entre los pasajeros
 */
public class Boleteria {
    private Random random;
    private int aerolineas;
    private int nroPasaje;
    private final Lock mutex = new ReentrantLock();
    private final Condition pasajeros;
    private int horaAtencion;
    private int cantidadTerminales;
    private Reloj reloj;

    public Boleteria(int cantidadAerolineas, int cantidadTerminales, Reloj reloj){
        this.aerolineas = cantidadAerolineas;
        this.cantidadTerminales = cantidadTerminales;
        this.random = new Random();
        this.pasajeros = mutex.newCondition();
        this.horaAtencion = 7;
        this.reloj = reloj;
    }

    public Pasaje obtenerBoleto()throws InterruptedException{
        mutex.lock();
        while(this.reloj.getHora()  <= 6 || this.reloj.getHora()  >= 22){
            helper.ThreadMsg("esta esperando a que abra el aeropuero. hora: "+this.reloj.getHora()+":00");
            pasajeros.await();
        }
        int aerolineaRandom = this.random.nextInt(aerolineas);
        int terminal = this.random.nextInt(0,this.cantidadTerminales);
        int puertaEmbarque = this.random.nextInt(1, 10)*cantidadTerminales;
        int hora = this.random.nextInt(12, 20);
        Pasaje pasaje = new Pasaje(nroPasaje, aerolineaRandom, hora, puertaEmbarque, terminal);
        this.nroPasaje++;
        mutex.unlock();
        return pasaje;
    }

    public void notificarPasajeros(){
        mutex.lock();
        this.pasajeros.signalAll();
        mutex.unlock();
    }
}
