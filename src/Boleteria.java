import java.util.Random;

public class Boleteria {
    private Random random;
    private int aerolineas;
    private int nroPasaje;

    public Boleteria(int cantidadAerolineas){
        this.aerolineas = cantidadAerolineas;
        this.random = new Random();
        this.nroPasaje = 1;
    }

    public synchronized Pasaje obtenerBoleto(){
        int aerolineaRandom = this.random.nextInt(aerolineas);
        Pasaje pasaje = new Pasaje(nroPasaje, aerolineaRandom);
        this.nroPasaje++;
        return pasaje;
    }
}
