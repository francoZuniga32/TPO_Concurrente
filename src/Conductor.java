public class Conductor implements Runnable{
    private Vagon vagon;

    public Conductor(Vagon vagon){
        this.vagon = vagon;
    }

    public void run(){
        try {
            while(true){
                vagon.conductor();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
