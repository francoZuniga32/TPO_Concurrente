import java.util.concurrent.locks.ReentrantLock;
/**
 * el reloj permite a los hilos poder consultar la hora actual en el aeropuerto
 * usa metodos sincronizados para evitar que se incremente cuando lo esten consultando.
 */
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
