public class Pasaje {
    private int nroPasaje;
    private int aerolinea;
    private int hora;
    private Reloj reloj;
    private int puertaEmbarque;
    private int terminal;

    public Pasaje(int nroPasaje, int aerolina, int hora, int puertaEmbarque, int terminal){
        this.nroPasaje = nroPasaje;
        this.aerolinea = aerolina;
        this.hora = hora;
        this.puertaEmbarque = puertaEmbarque;
        this.terminal = terminal;
    }

    public String toString(){
        return "Nro pasaje: #"+nroPasaje+" \t / aerolinea #"+aerolinea+" \t / terminal "+this.terminal+" \t / puerta embarque "+this.puertaEmbarque;
    }

    public int getAerolinea(){
        return this.aerolinea;
    }

    public int getHora(){
        return hora;
    }

    public int getTerminal(){
        return terminal;
    }

    public int getPuerta(){
        return puertaEmbarque;
    }
}
