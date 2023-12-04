import java.util.ArrayList;
import java.util.List;

public class Lista {

    List<Jugador> equipo;

    public Lista(){
        equipo=new ArrayList<Jugador>();
        predefinir();
    }

    public void predefinir(){
        equipo.add(new Jugador(1,"Sebas","DELANTERO",11f, 19));
        equipo.add(new Jugador(2,"Ewin","DEFENSA",15f, 22));
        equipo.add(new Jugador(3,"Javier","DEFENSA",8f, 18));
        equipo.add(new Jugador(4,"Nicolas","CENTRAL",10.2f, 32));
        equipo.add(new Jugador(5,"Mateo","PORTERO",12.5f, 25));
    }

    public boolean agregar(Jugador dato){
        if(dato.getRendimiento()>= 1 && dato.getRendimiento() <= 40 && dato.getEdad()>=18 && dato.getEdad()<= 37){
            for(Jugador j : equipo){
                if(j.getIdentificador() == dato.getIdentificador()){
                    return false;
                }
            }
            equipo.add(dato);
            return true;
        }
        return false;
    }

    public void eliminar (int indice){
        equipo.remove(indice);
    }

    public void ordenarRendimiento() {
        Jugador aux;
        for (int i = 0; i < equipo.size() - 1; i++) {
            for (int j = i + 1; j < equipo.size(); j++) {
                if (equipo.get(i).getRendimiento() > equipo.get(j).getRendimiento()) {
                    aux = equipo.get(i);
                    equipo.set(i, equipo.get(j));
                    equipo.set(j,aux);
                }
            }
        }
    }

    

    public boolean actualizar (Jugador dato){

        for(Jugador j:equipo){
            if(j.getIdentificador()==dato.getIdentificador()){
                j.setNombre(dato.getNombre());
                j.setEdad(dato.getEdad());
                j.setRendimiento(dato.getRendimiento());
                j.setPosicion(dato.getPosicion());
                return true;
            }
        }
        return false;
    }
    public Jugador obtenerMenorRendimiento(){
        Jugador aux = equipo.get(0);
        for(int i = 0; i<equipo.size(); i++){
            if(aux.getRendimiento() > equipo.get(i).getRendimiento()){
                aux = equipo.get(i);
            }
        }
        return aux;
    }



    public List<Jugador> getEquipo() {
        return equipo;
    }

    private float sumatoria(int indice){
        if(indice<equipo.size()){
            if(equipo.get(indice).getRendimiento() < 20)
               return 1+sumatoria(indice+1);
            else
                return sumatoria(indice+1);
        }else{
            return 0;
        }
    }
    public float sumatoria(){
       return sumatoria(0);
    }



}
