public class SalaPuestosAtencion {
    public synchronized void entrarSala()throws InterruptedException{
        this.wait();
    }    

    public synchronized void dejarPasar() throws InterruptedException{
        this.notifyAll();
    }
}
