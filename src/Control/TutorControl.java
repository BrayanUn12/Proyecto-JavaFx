package Control;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.events.Event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import Model.Tutor;
import Util.Archivos;

public class TutorControl {

    public void guardarTutor(String json){
        
        Archivos files = new Archivos();
        files.writeFile("Tutores", json);
    }
    public void resetGuardarTutor(String json){
        
        Archivos files = new Archivos();
        files.resetWriteFile("Tutores", json);
    }
    public String verHorario( Tutor obejcto,int index){
        return obejcto.getCalendarios().get(index).showEvents(index);
    }
    public String serializeObecjtoCollectiontoJson( 
    ArrayList<?> list){
        JsonSerializer<LocalTime> localTimeSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME));
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeSerializer).setPrettyPrinting()
        .create();
        return gson.toJson(list);
    }
    public Object deserializeObecjtoCollectionfromJson( Type listType)
    {
        JsonDeserializer<LocalTime> localTimeDeserializer = (json, typeOfT, context) -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);
        Archivos files = new Archivos();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeDeserializer).setPrettyPrinting()
        .create();
        return gson.fromJson(files.readJsonFile("src//Persistence//Tutores.json//"),listType);
    }
    public void borrarTutoria (Tutor tutor, Evento evento){
        List<Dia> eventos = tutor.getCalendarios();
        eventos.remove(evento);
        tutor.setCalendarios(eventos);
        
    }
}
