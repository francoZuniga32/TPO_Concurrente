public class Conductor implements Runnable{
    private Vagon vagon;
    private int cantidadTerminales;

    public Conductor(Vagon vagon, int cantidadTerminales){
        this.vagon = vagon;
        this.cantidadTerminales = cantidadTerminales;
    }

    public void run(){
        try {
            while(true){
                vagon.comenzarConduccion();
                helper.ThreadMsg("Comenzando viaje ...");
                Thread.sleep(100);
                int i = 0;
                while (i < cantidadTerminales) {
                    helper.ThreadMsg("Parando en terminal :"+i);
                    vagon.pararTerminal(i);
                    helper.ThreadMsg("Saliendo a la siguiente terminal...");
                    i++;
                    Thread.sleep(100);
                }
                helper.ThreadMsg("Finalizando viaje...");
                vagon.finalizarViaje();
                helper.ThreadMsg("Volviendo a terminal...");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
