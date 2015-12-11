package com.corpex.practicaandroid;

import java.util.ArrayList;

/**
 * Created by corpex, by the Grace of God on 11/12/2015.
 */
public class Coleccion {
    //Clase estatica que guarda lista de alumno
    private static ArrayList<Alumno> listaAlumnos = new ArrayList<>();

    public static void addAlumno(Alumno alumno){
        listaAlumnos.add(alumno);
    }

    public static ArrayList<Alumno> getListaAlumnos(){
        return listaAlumnos;
    }



    public static ArrayList<Alumno>  rellenarLista(){
        listaAlumnos.add(new Alumno("Pepe Gutierrez", "17", "Algeciras", "Avda Falsa 123", "(+34)123456789", 1));
        listaAlumnos.add(new Alumno("Maria Jimenez", "23", "La Linea", "Avda Verdadera 453", "(+34)123356789", 2));
        listaAlumnos.add(new Alumno("Gustavo Adolfo", "43", "Los Barrios", "Avda Flores 233", "(+34)123556789", 3));
        listaAlumnos.add(new Alumno("Gertrudio Benicio", "42", "Algeciras", "Avda Erquillo 654", "(+34)126456789", 4));
        listaAlumnos.add(new Alumno("Amparo Sindientes", "18", "Algeciras", "Avda Mugro 453", "(+34)123454789", 5));
        listaAlumnos.add(new Alumno("Braulio Ordoñez", "22", "Los barrios", "Avda Senda 167", "(+34)123436789", 6));
        listaAlumnos.add(new Alumno("Eurgencio Martinez", "23", "Malaga", "Avda Apache 543", "(+34)123455389", 7));
        listaAlumnos.add(new Alumno("Rotero Shurmano", "17", "Cadiz", "Avda Canguro Paquillo 153", "(+34)153456789", 8));
        listaAlumnos.add(new Alumno("Siruelo Gonzalez", "34", "Algeciras", "Avda Aquitepillo 363", "(+34)115456789", 9));
        listaAlumnos.add(new Alumno("Gertrudis Zalamera", "24", "Tarifa", "Avda Falsa 453", "(+34)123455489", 10));
        listaAlumnos.add(new Alumno("Gonzalo Quilombo", "45", "Los Barrios", "Avda Eltruco 563", "(+34)164456789", 1));
        return listaAlumnos;
    }
}
