import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Cuantas Aerolines operan en este aeropuerto:");
        int cantidadAerolineas = TecladoIn.readInt();

        System.out.println("Cuantas terminales tienen el aeropuerto?:");
        int cantidadTerminales = TecladoIn.readInt();

        //creamos el reloj
        Reloj reloj = new Reloj();

        //creamos la boleteria
        Boleteria boleteria = new Boleteria(cantidadAerolineas, cantidadTerminales, reloj);
        

        //hilo reloj
        Thread relojHilo = new Thread(new RelojHilo(reloj, boleteria),"Reloj");
        
        //creamos los puestos de atencion
        PuestoAtencion[] puestosAtencion = new PuestoAtencion[cantidadAerolineas];
        for(int i = 0; i < cantidadAerolineas; i++){
            puestosAtencion[i] = new PuestoAtencion(3);
        }

        //creamos los freeshops
        FreeShop[] freeshops = new FreeShop[cantidadTerminales];
        for(int j = 0; j <cantidadTerminales; j++){
            freeshops[j] = new FreeShop(2);
        }

        //creamos el vagon
        Vagon vagon = new Vagon(5, cantidadTerminales);

        //creamos los hilos pasajeros, guardias
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        Thread guardia = new Thread();
        
        Thread conductor = new Thread(new Conductor(vagon, cantidadTerminales), "Conductor");

        int cantidadPasajeros = 10;

        //iniciamos los hilos de pasajeros y de guardias en los puestos de atencion
        for(int i=0; i < cantidadPasajeros; i++) {
            hilos.add(new Thread(new Pasajero(boleteria, puestosAtencion, vagon, reloj, freeshops ), "pasajero #"+i));
        }

        guardia = new Thread(new Guardia(puestosAtencion),"Guardia ");

        
        hilos.forEach(e -> e.start());
        guardia.start();
        relojHilo.start();
        conductor.start();
    }
}
