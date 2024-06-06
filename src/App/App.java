    package App;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.reflect.TypeToken;

import Control.Calendario;
    import Control.Dia;
    import Control.Evento;
    import Control.StudentControl;
import Control.TutorControl;
import Model.Estudent;
import Model.Tutor;

    public class App {
        public static void main(String[] args) throws Exception {
            
            Scanner sc = new Scanner(System.in);
            StudentControl s1 = new StudentControl();
            TutorControl t1= new TutorControl();

            Calendario calendario = new Calendario();
            String op;
            int opcionEstu=0;
            boolean menuET=true;
            Type listTypeTutor = new TypeToken<ArrayList<Tutor>>() {}.getType();
            ArrayList <Tutor> tutors = (ArrayList<Tutor>) t1.deserializeObecjtoCollectionfromJson(listTypeTutor);
            Type listType = new TypeToken<ArrayList<Estudent>>() {}.getType();
            ArrayList<Estudent> estudiantes = (ArrayList<Estudent>) s1.deserializeObecjtoCollectionfromJson(listType);
            while (menuET) {

                System.out.println("Menu de entrada");
                System.out.println("1.Estudiante");
                System.out.println("2.Tutor");
                System.out.println("3.salir");
                op=sc.next();
                sc.next();
                switch (op) {
                    case "1":
                    int opcionTutorias=0;
                    boolean opcionEst=true;
                    while (opcionEst) {
                    System.out.println("ingreso de estudiante");
                    System.out.println("elia segun el numero que se muestra antes de la informacion");
                    System.out.println("1"+" "+estudiantes.get(0).toString());
                    System.out.println("2"+" "+estudiantes.get(1).toString());
                        try {
                            opcionEstu=sc.nextInt()-1;
                            estudiantes.get(opcionEstu);
                            opcionEst=false;
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.err.println("opcion no valida o que no es un numero "+e.getMessage());
                            sc.next();
                        }
                    }
                    
                    boolean menuEstudianteIn=true;
                    String opMenuEstud="";
                    while (menuEstudianteIn) {
                        System.out.println("1.Inscribir una tutoria");
                        System.out.println("2.Cancelar una tutoria");
                        System.out.println("3.Buscar tutoria por Profesor");
                        System.out.println("4.Mostrar tutorias");
                        System.out.println("5.Salir");
                        opMenuEstud =sc.next();
                        switch (opMenuEstud) {
                            case "1":
                           
                                System.out.println("tutorias disponibles");
                                for(int indexofCalendarios=0;indexofCalendarios<estudiantes.get(opcionEstu).getCalendarios().size();indexofCalendarios++)
                                {
                                    for(int i=0;i<estudiantes.get(opcionEstu).getCalendarios().
                                    get(indexofCalendarios).getEventos().size();i++){
                                        System.out.println((i+1));
                                        System.out.println(estudiantes.get(opcionEstu).getCalendarios().
                                        get(indexofCalendarios).showEvents(i));
                                    }
                                    try {
                                        opcionTutorias=sc.nextInt()-1;
                                        sc.nextLine();
                                        estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios).setEventos(
                                            s1.inscribir(opcionTutorias,  estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios).getEventos()));
                                        if (estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios)
                                        .getEventos().get(opcionTutorias).isInscrito()) {
                                            System.out.println("tutoria agregada");
                                            String json= s1.serializeObecjtoCollectiontoJson(estudiantes);
                                            s1.resetGuardarEstudiante(json);
                                        }
                                        
                                    } catch (Exception e) {
                                        
                                        System.err.println("opcion invalida " +e.getMessage());
                                        sc.next();
                                    }
                                
                                }
                                
                                break;
                            case "2":
                            
                                 System.out.println("tutorias disponibles");
                                 for(int indexofCalendarios=0;indexofCalendarios<estudiantes.get(opcionEstu).getCalendarios().size();indexofCalendarios++)
                                 {
                                     for(int i=0;i<estudiantes.get(opcionEstu).getCalendarios().
                                     get(indexofCalendarios).getEventos().size();i++){
                                         System.out.println((i+1));
                                         System.out.println(estudiantes.get(opcionEstu).getCalendarios().
                                         get(indexofCalendarios).showEvents(i));
                                         if (estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios).getEventos().get(i)
                                        .isInscrito()) {
                                            try {
                                            opcionTutorias=sc.nextInt()-1;
                                            estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios).
                                            setEventos(s1.Cancelar(opcionTutorias,  estudiantes.get(opcionEstu)
                                            .getCalendarios().get(indexofCalendarios).getEventos()));
                                            if (estudiantes.get(opcionEstu).getCalendarios().get(indexofCalendarios)
                                            .getEventos().get(opcionTutorias).isInscrito()) {
                                                
                                            }else{
                                                System.out.println("tutoria cancelada");
                                                String json= s1.serializeObecjtoCollectiontoJson(estudiantes);
                                                s1.resetGuardarEstudiante(json);
                                            }
                                            
                                        } catch (Exception e) {
                                            // TODO: handle exception
                                            System.err.println("opcion invalida " +e.getMessage());
                                            sc.next();
                                        } 
                                    }else{
                                        System.out.println("no hay tutorias inscritas");
                                    }
                            }          
                                }
                                break;
                            case "3":
                                System.out.println("profesores actuales");
                                for(int i=0;i<tutors.size();i++){
                                    System.out.println((i+1)+" Nombre: "+
                                    tutors.get(i).getFirstName()+" Apellido: "+tutors.get(i).getLastName());
                                }
                                System.out.println("Ingrese el nombre del prodesor");
                                String selectProfesor;
                                int cont=0;
                                selectProfesor=sc.next();
                                for(int index=0;index<tutors.size();index++){
                                    if (s1.buscarProfesor(selectProfesor, tutors, index)!=null) {
                                        System.out.println("tutoria del profesor "+selectProfesor);
                                        for(int tutorindex=0;tutorindex<
                                        s1.buscarProfesor(selectProfesor, tutors, index).size();tutorindex++)
                                        {
                                            System.out.println(s1.buscarProfesor(selectProfesor, tutors, index).
                                            get(tutorindex).showEvents(tutorindex));
                                        }
                                        cont++;
                                    }
                                }
                                if (cont==0) {
                                    System.out.println("profesor no encontrado o mal digitado");
                                }
                                break;
                                case "4":
                                for(int indexofCalendarios=0;indexofCalendarios<estudiantes.get(opcionEstu).getCalendarios().size();indexofCalendarios++)
                                {
                                    for(int i=0;i<estudiantes.get(opcionEstu).getCalendarios().
                                    get(indexofCalendarios).getEventos().size();i++){
                                        System.out.println((i+1));
                                        System.out.println(estudiantes.get(opcionEstu).getCalendarios().
                                        get(indexofCalendarios).showEvents(i));
                                    }
                                }
                                case "5":
                                    menuEstudianteIn=false;
                                    break;
                            default:
                                System.out.println("ingreso invalido elija una de las opciones disponibles");
                                break;
                        }
                    }
                    
                      break;
                    case "2":
                        boolean menuTu=true;
                        System.out.println("ingreso de tutor");
                        boolean opcionTutor=true;
                        int opTut=0;

                        while (opcionTutor) {
                            System.out.println("elija el tutor con el que va a ingresar");
                            System.out.println("1 "+tutors.get(0).toString());
                            System.out.println("2 "+tutors.get(1).toString());
                            try {
                                opTut=sc.nextInt()-1;
                                opcionTutor=false;
                            } catch (Exception e) {
                                System.err.println("Invalida opcion" + e.getMessage());
                                sc.next();
                            }
                        }                        
                        while (menuTu) {
                            System.out.println("1. Agregar evento.\n2. Ver eventos\n 3.modificar Horario\n 4.Borrar una tutoria \n" + //
                                                                " 5. Salir");
                            int opcion = sc.nextInt();
                            sc.next();
                            switch (opcion) {
                                case 1:
                                    System.out.print("Ingrese el nombre del evento: ");
                                    String nombre = sc.next();
                                    sc.nextLine();
                                    System.out.print("Ingrese la descripción del evento: ");
                                    String descripcion = sc.nextLine();
                                    System.out.print("Ingrese el día del evento (Lunes a Viernes): ");
                                    String dia = sc.next();
                                    sc.nextLine();
                                    boolean validHour=true;
                                    int hora=0;
                                    int minuto=0;
                                    while (validHour) {
                                        System.out.print("Ingrese la hora incial del evento (HH:MM): ");
                                    try {
                                        hora = sc.nextInt();
                                        validHour=false;

                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        sc.nextLine();
                                    }
                                    }
                                    boolean validMinuto=true;
                                    while (validMinuto) {
                                        System.out.print("Ingrese el minuto incial del evento (HH:MM): ");
                                        try {
                                            minuto = sc.nextInt();
                                            validMinuto=false;
    
                                        } catch (Exception e) {
                                            // TODO: handle exception
                                            sc.nextLine();
                                        }
                                    }
                                    LocalTime horaInicial= LocalTime.of(hora, minuto);
                                    LocalTime horaFinal=LocalTime.of(0, 0);
                                    boolean horaFinalMenu=true;
                                    while (horaFinalMenu) {
                                        validHour=true;
                                        while (validHour) {
                                            System.out.print("Ingrese la hora final del evento (HH:MM): ");
                                            System.out.println("Recuerde que la duracion final debe de ser de 30 minutos despues de la hora incial");
                                        try {
                                            hora = sc.nextInt();
                                            validHour=false;

                                        } catch (Exception e) {
                                            // TODO: handle exception
                                            sc.nextLine();
                                        }
                                        }
                                        validMinuto=true;
                                        while (validMinuto) {
                                            System.out.print("Ingrese el minuto final del evento (HH:MM): ");
                                            try {
                                                minuto = sc.nextInt();
                                                validMinuto=false;
        
                                            } catch (Exception e) {
                                                // TODO: handle exception
                                                sc.nextLine();
                                            }
                                        }
                                        horaFinal=LocalTime.of(hora, minuto);
                                        LocalTime horaPrueba=horaInicial.plusHours(0).plusMinutes(30);
                                        if (horaFinal.equals(horaPrueba)) {
                                            horaFinalMenu=false;
                                        }
                                    }
                                    
                                    Evento evento = new Evento(nombre, descripcion, horaInicial,horaFinal, false);
                                    if (calendario.addEventoCalendar(dia, evento)) {
                                        System.out.println("Evento agregado exitosamente.");
                                        ArrayList<Evento> evs= new ArrayList<>();
                                        evs.add(evento);
                                        Dia dias= new Dia(nombre,evs);
                                        estudiantes.get(0).getCalendarios().add(dias);
                                        tutors.get(opTut).getCalendarios().add(dias);
                                        String jsonst= s1.serializeObecjtoCollectiontoJson(estudiantes);
                                        s1.resetGuardarEstudiante(jsonst);
                                        String jsonTutor = t1.serializeObecjtoCollectiontoJson(tutors);
                                        t1.resetGuardarTutor(jsonTutor);
                                    } else {
                                        System.out.println("Ya hay un evento programado en esa hora.");
                                    }
                                    break;
                                case 2:
                                    for(int i=0;i<tutors.get(opTut).getCalendarios().size();i++){
                                        System.out.println(t1.verHorario(tutors.get(opTut), i));
                                    }
                                    break;
                                case 3:
                                    for(int i=0;i<tutors.get(opTut).getCalendarios().size();i++){
                                        System.out.println((i));
                                        System.out.println(t1.verHorario(tutors.get(opTut), i));
                                        try {
                                            System.out.println("ingrese el  numero de la tutoria");
                                            opcionTutorias=sc.nextInt();
                                            tutors.get(opTut).getCalendarios().get(i).getEventos().get(opcionTutorias);
                                            System.out.print("Ingrese el nombre del evento: ");
                                            String nombreEdit = sc.next();
                                            sc.nextLine();
                                            System.out.print("Ingrese la descripción del evento: ");
                                            String descripcionEdit = sc.nextLine();
                                            System.out.print("Ingrese el día del evento (Lunes a Viernes): ");
                                            String diaEdit = sc.next();
                                            sc.nextLine();
                                            boolean validHourEdit=true;
                                            int horaEdit=0;
                                            int minutoEdit=0;
                                            while (validHourEdit) {
                                                System.out.print("Ingrese la hora incial del evento (HH:MM): ");
                                            try {
                                                horaEdit = sc.nextInt();
                                                validHourEdit=false;
        
                                            } catch (Exception e) {
                                                // TODO: handle exception
                                                sc.nextLine();
                                            }
                                            }
                                            boolean validMinutoEdit=true;
                                            while (validMinutoEdit) {
                                                System.out.print("Ingrese el minuto incial del evento (HH:MM): ");
                                                try {
                                                    minutoEdit= sc.nextInt();
                                                    validMinutoEdit=false;
            
                                                } catch (Exception e) {
                                                    // TODO: handle exception
                                                    sc.nextLine();
                                                }
                                            }
                                            LocalTime horaInicialEdit= LocalTime.of(horaEdit, minutoEdit);
                                            LocalTime horaFinalEdit=LocalTime.of(0, 0);
                                            boolean horaFinalMenuEdit=true;
                                            while (horaFinalMenuEdit) {
                                                validHourEdit=true;
                                                while (validHourEdit) {
                                                    System.out.print("Ingrese la hora final del evento (HH:MM): ");
                                                    System.out.println("Recuerde que la duracion final debe de ser de 30 minutos despues de la hora incial");
                                                try {
                                                    horaEdit = sc.nextInt();
                                                    validHourEdit=false;
        
                                                } catch (Exception e) {
                                                    // TODO: handle exception
                                                    sc.nextLine();
                                                }
                                                }
                                                validMinutoEdit=true;
                                                while (validMinutoEdit) {
                                                    System.out.print("Ingrese el minuto final del evento (HH:MM): ");
                                                    try {
                                                        minutoEdit = sc.nextInt();
                                                        validMinutoEdit=false;
                
                                                    } catch (Exception e) {
                                                        // TODO: handle exception
                                                        sc.nextLine();
                                                    }
                                                }
                                                horaFinalEdit=LocalTime.of(horaEdit, minutoEdit);
                                                LocalTime horaPrueba=horaInicialEdit.plusHours(0).plusMinutes(30);
                                                if (horaFinalEdit.equals(horaPrueba)) {
                                                    horaFinalMenuEdit=false;
                                                }
                                            }
                                            
                                            Evento events = new Evento(nombreEdit, descripcionEdit,
                                             horaInicialEdit,horaFinalEdit, false);
                                            if (calendario.addEventoCalendar(diaEdit, events)) {
                                                System.out.println("Evento agregado exitosamente.");
                                                tutors.get(opTut).getCalendarios().get(i).getEventos().set(opcionTutorias, events);
                                                System.out.println( tutors.get(opTut).getCalendarios().get(i).getEventos().get(opcionTutorias));
                                                String jsonTutor=t1.serializeObecjtoCollectiontoJson(tutors);
                                                t1.resetGuardarTutor(jsonTutor);
                                                for(int indexofCalendarios=0;indexofCalendarios<estudiantes.get(opcionEstu).getCalendarios().size();indexofCalendarios++)
                                                {
                                                    for(int ind=0;ind<estudiantes.get(0).getCalendarios().
                                                    get(indexofCalendarios).getEventos().size();ind++){
                                                        if(estudiantes.get(0).getCalendarios().get(indexofCalendarios)
                                                        .getEventos().get(ind).equals(tutors.get(opTut).
                                                        getCalendarios().get(i).getEventos().get(opcionTutorias))){
                                                            estudiantes.get(0).getCalendarios().get(indexofCalendarios).getEventos()
                                                            .set(i, events);
                                                            String jsonEst=s1.serializeObecjtoCollectiontoJson(estudiantes);
                                                            s1.resetGuardarEstudiante(jsonEst);
                                                        }
                                                    }
                                                    for(int ind=0;ind<estudiantes.get(1).getCalendarios().
                                                    get(indexofCalendarios).getEventos().size();ind++){
                                                        if(estudiantes.get(1).getCalendarios().get(indexofCalendarios)
                                                        .getEventos().get(ind).equals(tutors.get(opTut).
                                                        getCalendarios().get(i).getEventos().get(opcionTutorias))){
                                                            estudiantes.get(1).getCalendarios().get(indexofCalendarios).getEventos()
                                                            .set(i, events);
                                                            String jsonEst=s1.serializeObecjtoCollectiontoJson(estudiantes);
                                                            s1.resetGuardarEstudiante(jsonEst);
                                                        }
                                                    }
                                                }
                                            } else {
                                                System.out.println("Ya hay un evento programado en esa hora.");
                                            }
                                            
                                        } catch (Exception e) {
                                            // TODO: handle exception
                                            System.err.println("opcion invalida " +e.getMessage());
                                            sc.next();
                                        }
                                    }
                                    
                                    break;
                                case 4:
                                System.out.println("Ingrese el indice del evento que desea eliminar");
                                for (int i=0;i<tutors.get(opTut).getCalendarios().size(); i++) {
                                    System.out.println((i + 1) + ". " + tutors.get(opTut).getCalendarios().get(i).getNombre());
                                }
                                int eventoABorrar = sc.nextInt() -1;
                                if (eventoABorrar >= 0 && eventoABorrar< tutors.get(opTut).getCalendarios().size()) {
                                    Dia disa= tutors.get(opTut).getCalendarios().get(eventoABorrar);
                                    int eventoInde = sc.nextInt()-1;
                                    if (eventoInde >= 0 && eventoInde < disa.getEventos().size()) {
                                        Evento evento3= disa.getEventos().get(eventoInde);
                                        t1.borrarTutoria(tutors.get(opTut), evento3);
                                        String jsonTut=s1.serializeObecjtoCollectiontoJson(tutors);
                                        t1.resetGuardarTutor(jsonTut);
                                        System.out.println("Evento eliminado correctamente");
                                    }else {
                                        System.out.println("Opcion invalida");
                                    }
                                    
                                }else {
                                    System.out.println("Opcion invalida");
                                }
                                break;
                                case 5:
                                    System.out.println("Saliendo...");
                                    menuTu=false;
                                    break;
                                default:
                                System.out.println("Opción inválida. Intente nuevamente.");
                            }
                        }
                            break;
                        case "3":
                            menuET=false;
                        break;
                    default:
                    System.out.println("ingrese una opcion correcta");
                        break;
                }
            }            
}
}        

