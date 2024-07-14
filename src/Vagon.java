import java.util.concurrent.Semaphore;

public class Vagon {
    private Semaphore lugares;
    private Semaphore[] salidas;
    private Semaphore volver;
    private Semaphore conductor;
    private int cantidadLugares;

    public Vagon(int lugares, int cantidadTerminales){
        this.lugares = new Semaphore(lugares, true);
        this.salidas = new Semaphore[cantidadTerminales];
        this.cantidadLugares = lugares;
        for (int i = 0; i < salidas.length; i++) {
            this.salidas[i] = new Semaphore(1, true);
        }

        this.volver = new Semaphore(1, true);
        this.conductor = new Semaphore(0);
    }

    public void comenzarRecorrido()throws InterruptedException{
        this.conductor.acquire();
        //comenzamos el recorrido
        helper.ThreadMsg("Saliendo de la estacion...");
        int i = 0;
        while(i < this.salidas.length){
            Thread.sleep(1000);
            liberarVagon(i);
            i++;
        }
        helper.ThreadMsg("Volviendo a la estacion ...");
        Thread.sleep(2000);
        helper.ThreadMsg("Esperando en la estacion...");
        this.lugares.release(this.cantidadLugares);
        
    }

    private void liberarVagon(int terminal)throws InterruptedException{
        helper.ThreadMsg("Parando en la terminal: "+terminal);
        //liberamos el primer hilo de la terminal
        this.salidas[terminal].release();
        //espera al que ultimo pasajero le notifique que tiene que continuar
        this.salidas[terminal].acquire();
    }
    
    public boolean abordarVagon(int terminal)throws InterruptedException{
        //pedimos el permiso para
        if(this.lugares.tryAcquire()){
            helper.ThreadMsg("Entrando al vagon...");
            this.salidas[terminal].acquire();
            //en algun momento se liberara el permiso y podra baja.
            //tiene de avisar al otro pasajero que puede bajar.
            this.salidas[terminal].release();
            return true;
        } else{
            //en caso de que el vagon este lleno, liberamos al conductor para que ponga en marcha el tren.
            this.conductor.release();
            return false;
        }
    }
}
