/**
 * los puestos de atencion es donde los pasajeros hacer check-in de los pasajes
 * tiene una capacidad maxima de personas y un guardar es el encargado de dejar pasar a los pasajeros
 */
public class PuestoAtencion {
    private int lugares;
    private int ocupados;

    public PuestoAtencion(int cantidadLugares){
        this.lugares = cantidadLugares;
        this.ocupados = 0;
    }

    public synchronized void entrarPuestoAtencion()throws InterruptedException{
        
        while(this.ocupados == this.lugares){
            //helper.ThreadMsg("Esperando a que se desocupe...");
            this.wait();
        }
        this.ocupados ++;
    } 


    public synchronized void hacerCheckIn()throws InterruptedException{
        this.ocupados --;
    }

    public synchronized void dejarEntrar() throws InterruptedException{
        if(this.ocupados < lugares){
            this.notifyAll();
        }
    } 
}
