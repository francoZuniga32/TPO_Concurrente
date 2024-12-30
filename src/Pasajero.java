import java.util.Random;

public class Pasajero implements Runnable{
    private Boleteria boleteria;
    private Pasaje pasaje;
    private PuestoAtencion[] puestosAtencion;
    private Vagon vagon;
    private Reloj reloj;
    private FreeShop[] freeshop;
    private Random random;

    public Pasajero(Boleteria unaBoleteria, PuestoAtencion[] puestosAtencion, Vagon vagon, Reloj reloj, FreeShop[] freeshop){
        this.boleteria = unaBoleteria;
        this.puestosAtencion = puestosAtencion;
        this.vagon = vagon;
        this.reloj = reloj;
        this.freeshop = freeshop;
        this.random = new Random();
    }

    public void run(){
        try {
            helper.ThreadMsg(" ingresando al aeropuerto...");
            
            this.pasaje = boleteria.obtenerBoleto();
            helper.ThreadMsg(this.pasaje.toString());

            //check in del pasajero
            helper.ThreadMsg("Esperando a hacer check in...");
            this.puestosAtencion[this.pasaje.getAerolinea()].entrarPuestoAtencion();
            helper.ThreadMsg("Esperando en la fila del puesto de atencion ...");
            Thread.sleep(4000);
            this.puestosAtencion[this.pasaje.getAerolinea()].hacerCheckIn();
            helper.ThreadMsg("Realizo el chek in...");
            
            //entrada al vagon
            helper.ThreadMsg("Esperando a entrar al vagon...");
            vagon.entradaPasajero(this.pasaje.getTerminal());
            
            //helper.ThreadMsg("Bajando del vagon...");
            while(this.reloj.getHora() <= this.pasaje.getHora() - 1 ){
                // si tiene almenos una hora para embarcar entra a comprar de forma aleatoria a la tienda
                if(random.nextInt(2) == 1){
                    if(!this.freeshop[this.pasaje.getTerminal()].entrarFreeShop(this.pasaje.getTerminal()) && this.reloj.getHora() <= this.pasaje.getHora() - 1 ){
                        helper.ThreadMsg("esperando para entrar al freeshop...");
                        Thread.sleep(10);
                    }
                }

                helper.ThreadMsg("Esperando en la terminal");
                Thread.sleep(100);
            }
            //embarcamos (esperamos a embarcar)
            helper.ThreadMsg("esta embarcando en la terminal: "+pasaje.getTerminal()+" puerta de embarque: "+pasaje.getPuerta());
        } catch (Exception e) {
            // TODO: handle exception
            helper.ThreadMsg(e.toString());
        }
    }
}  