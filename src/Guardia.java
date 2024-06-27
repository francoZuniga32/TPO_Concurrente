public class Guardia implements Runnable{
    private PuestoAtencion puesto;

    public Guardia(PuestoAtencion puesto){
        this.puesto = puesto;
    }

    public void run(){
        try {
            helper.ThreadMsg("esta penzando a trabajar...");

            while(true){
                puesto.dejarEntrar();
            }
        } catch (Exception e) {
            helper.ThreadMsg("error: "+e.getMessage());
        }
    }
}
