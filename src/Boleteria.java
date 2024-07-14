import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Boleteria {
    private Random random;
    private int aerolineas;
    private int nroPasaje;
    private final Lock mutex = new ReentrantLock();
    private final Condition pasajeros;
    private int hora;

    public Boleteria(int cantidadAerolineas){
        this.aerolineas = cantidadAerolineas;
        this.random = new Random();
        this.pasajeros = mutex.newCondition();
        this.hora = 7;
    }

    public void actualizarHora()throws InterruptedException{
        mutex.lock();
        this.hora = (this.hora + 1)%24;
        pasajeros.signalAll();
        helper.ThreadMsg("se actualizo la hora: "+hora+":00");
        mutex.unlock();
    }

    public Pasaje obtenerBoleto()throws InterruptedException{
        mutex.lock();
        while(this.hora <= 6 || this.hora >= 22){
            helper.ThreadMsg("esta esperando a que abra el aeropuero. hora: "+hora+":00");
            pasajeros.await();
        }
        int aerolineaRandom = this.random.nextInt(aerolineas);
        Pasaje pasaje = new Pasaje(nroPasaje, aerolineaRandom);
        this.nroPasaje++;
        mutex.unlock();
        return pasaje;
    }
}
