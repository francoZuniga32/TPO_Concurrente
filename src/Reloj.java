import java.util.concurrent.locks.ReentrantLock;

public class Reloj{
    private int hora;

    synchronized public void actualizarHora(){
        try{
            this.hora = (this.hora + 1)%24;
            helper.ThreadMsg(" hora: "+hora);
        }catch(Exception ex){
            helper.ThreadMsg(ex.getMessage());
        }
    }

    synchronized public int getHora(){
        return this.hora;
    }
}
