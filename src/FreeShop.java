import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 * los freeshop se encuentran en las terminales en caso de que un pasajero tenga tiempo para comprar lo hara
 * tiene una capacidad y 2 cajas para poder atender. en caso de que sean atendidos.
 */
public class FreeShop {
    private Semaphore capacidad;
    private Semaphore cajas;
    private Random random = new Random();

    public FreeShop(int capacidad){
        this.capacidad = new Semaphore(capacidad);
        this.cajas = new Semaphore(2);
    }

    public boolean entrarFreeShop(int terminal)throws Exception{
        if(this.capacidad.tryAcquire(1)){
            helper.ThreadMsg("Entrando al free shop de la terminal "+terminal+"...");
            Thread.sleep(200);
            if(random.nextInt(2) == 1){
                //compramos
                this.cajas.acquire();
                helper.ThreadMsg("Comprando ...");
                Thread.sleep(100);
                this.cajas.release();
            }
            this.capacidad.release();
            return true;
        }else{
            return false;
        }
    }
}
