package Control;

import java.util.ArrayList;

public class Dia {
    private String nombre;
    private ArrayList <Evento> eventos;

    public Dia(String nombre) {
        this.nombre = nombre;
        this.eventos = new ArrayList<>();
    }

    

    public Dia(String nombre, ArrayList<Evento> eventos) {
        this.nombre = nombre;
        this.eventos = eventos;
    }



    public boolean addEvento (Evento evento){
        if (eventos.isEmpty()) {
            eventos.add(evento);
            return true; 
        }
        for (Evento e: eventos) {
            if (e.equals(evento)) {
                return false;
            }
        }
        eventos.add(evento);
        return true; 
    }
    
    public String showEvents (int index){
        if (eventos.isEmpty()) {
            return null;
        }else {
                return "Events: " + nombre + "." + eventos.get(index).toString();
        }

    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }



    @Override
    public String toString() {
        return "Dia [nombre=" + nombre + ", eventos=" + eventos + "]";
    }
    
}
