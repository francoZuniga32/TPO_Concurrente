
public class Pasajero implements Runnable{
    private Boleteria boleteria;
    private Pasaje pasaje;
    private PuestoAtencion[] puestosAtencion;
    public Pasajero(Boleteria unaBoleteria, PuestoAtencion[] puestosAtencion){
        this.boleteria = unaBoleteria;
        this.puestosAtencion = puestosAtencion;
    }

    public void run(){
        try {
            helper.ThreadMsg(" ingresando al aeropuerto...");
            this.pasaje = boleteria.obtenerBoleto();
            helper.ThreadMsg(this.pasaje.toString());
            boolean control = false;
            this.puestosAtencion[this.pasaje.getAerolinea()].hacerCheckIn();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}  