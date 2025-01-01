/**
 * el reloj permite a los hilos poder consultar la hora actual en el aeropuerto
 * usa metodos sincronizados para evitar que se incremente cuando lo esten consultando.
 */
public class Reloj{
    private int hora;
    public static int MINUTO = 1;
    public static int HORA = MINUTO*60;

    public synchronized void actualizarHora(){
        try{
            this.hora = (this.hora + 1)%24;
            helper.ThreadMsg(" hora: "+hora);
        }catch(Exception ex){
            helper.ThreadMsg(ex.getMessage());
        }
    }

    public synchronized int getHora(){
        return this.hora;
    }
}
