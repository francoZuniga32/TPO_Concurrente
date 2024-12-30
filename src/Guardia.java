/**
 * es el encargado de dejar entrar a las pasajeros a los puetos de atencion.
 */
public class Guardia implements Runnable{
    private PuestoAtencion[] puestos;

    public Guardia(PuestoAtencion[] puestos){
        this.puestos = puestos;
    }

    public void run(){
        try {
            helper.ThreadMsg("esta penzando a trabajar...");
            int i = 0;
            while(true){
                puestos[i].dejarEntrar();
                //helper.ThreadMsg("Revisando el puesto:"+i);
                i++;
                i = i%puestos.length;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            helper.ThreadMsg("error: "+e.getMessage());
        }
    }
}
