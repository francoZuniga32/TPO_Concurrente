
public class Pasajero implements Runnable{
    private Boleteria boleteria;
    private Pasaje pasaje;
    private PuestoAtencion[] puestosAtencion;
    private Vagon vagon;

    public Pasajero(Boleteria unaBoleteria, PuestoAtencion[] puestosAtencion, Vagon vagon){
        this.boleteria = unaBoleteria;
        this.puestosAtencion = puestosAtencion;
        this.vagon = vagon;
    }

    public void run(){
        try {
            /*helper.ThreadMsg(" ingresando al aeropuerto...");
            this.pasaje = boleteria.obtenerBoleto();
            helper.ThreadMsg(this.pasaje.toString());
            boolean control = false;
            this.puestosAtencion[this.pasaje.getAerolinea()].entrarPuestoAtencion();
            Thread.sleep(4000);
            this.puestosAtencion[this.pasaje.getAerolinea()].hacerCheckIn();
            */
            //vamos al vagon
            while(!this.vagon.abordarVagon(0)){
                helper.ThreadMsg("Esperando al vagon...");
                Thread.sleep(1000);
            }

            helper.ThreadMsg("Bajando del vagon...");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}  