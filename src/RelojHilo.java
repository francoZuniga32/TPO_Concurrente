public class RelojHilo implements Runnable{
    private Reloj reloj;
    private Boleteria boleteria;
    
    public RelojHilo(Reloj reloj, Boleteria boleteria){
        this.reloj = reloj;
        this.boleteria = boleteria;
    }
    
    public void run(){
        try {
            while(true){
                this.reloj.actualizarHora();
                this.boleteria.notificarPasajeros();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
