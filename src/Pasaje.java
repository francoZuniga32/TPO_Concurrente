public class Pasaje {
    private int nroPasaje;
    private int aerolinea;

    public Pasaje(int nroPasaje, int aerolina){
        this.nroPasaje = nroPasaje;
        this.aerolinea = aerolina;
    }

    public String toString(){
        return "Nro pasaje: #"+nroPasaje+" / aerolinea #"+aerolinea;
    }

    public int getAerolinea(){
        return this.aerolinea;
    }
}
