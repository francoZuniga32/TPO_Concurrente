import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Cuantas Aerolines operan en este aeropuerto:");
        int cantidadAerolineas = TecladoIn.readInt();

        System.out.println("Cuantas terminales tienen el aeropuerto?:");
        int cantidadTerminales = TecladoIn.readInt();

        //creamos la boleteria
        Boleteria boleteria = new Boleteria(cantidadAerolineas);

        //creamos los puestos de atencion
        PuestoAtencion[] puestosAtencion = new PuestoAtencion[cantidadAerolineas];
        for(int i = 0; i < cantidadAerolineas; i++){
            puestosAtencion[i] = new PuestoAtencion(3);
        }

        //creamos el vagon
        Vagon vagon = new Vagon(5, cantidadTerminales);

        //creamos los hilos pasajeros, guardias
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        ArrayList<Thread> guardias = new ArrayList<Thread>();
        Thread reloj = new Thread(new Reloj(boleteria),"Reloj");
        Thread conductor = new Thread(new Conductor(vagon), "Conductor");

        int cantidadPasajeros = 10;

        //iniciamos los hilos de pasajeros y de guardias en los puestos de atencion
        for(int i=0; i < cantidadPasajeros; i++) {
            hilos.add(new Thread(new Pasajero(boleteria, puestosAtencion, vagon), "pasajero #"+i));
        }

        for(int j=0; j < puestosAtencion.length; j++){
            guardias.add(new Thread(new Guardia(puestosAtencion[j]),"Guardia #"+j));
        }

        
        hilos.forEach(e -> e.start());
        guardias.forEach(g -> g.start());
        reloj.start();
        conductor.start();
    }
}
