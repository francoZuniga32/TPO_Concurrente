import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Cuantas Aerolines operan en este aeropuerto:");
        int cantidadAerolineas = TecladoIn.readInt();

        Boleteria boleteria = new Boleteria(cantidadAerolineas);

        //creamos los puestos de atencion
        PuestoAtencion[] puestosAtencion = new PuestoAtencion[cantidadAerolineas];
        for(int i = 0; i < cantidadAerolineas; i++){
            puestosAtencion[i] = new PuestoAtencion(3);
        }

        //creamos los hilos pasajeros, guardias
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        ArrayList<Thread> guardias = new ArrayList<Thread>();

        int cantidadPasajeros = 10;

        for(int i=0; i < cantidadPasajeros; i++) {
            hilos.add(new Thread(new Pasajero(boleteria, puestosAtencion), "pasajero #"+i));
        }

        for(int j=0; j < puestosAtencion.length; j++){
            guardias.add(new Thread(new Guardia(puestosAtencion[j])));
        }
        
        hilos.forEach(e -> e.start());
        guardias.forEach(g -> g.start());
    }
}
