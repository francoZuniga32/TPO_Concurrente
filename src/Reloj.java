public class Reloj implements Runnable {
    private Boleteria boleteria;
    public Reloj(Boleteria boleteria){
        this.boleteria = boleteria;
    }

    public void run(){
        try{
            while(true){
                boleteria.actualizarHora();   
                Thread.sleep(10000);
            }
        }catch(Exception ex){
            helper.ThreadMsg(ex.getMessage());
        }
    }
}
