/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;
import ucn.Registro;
import ucn.StdOut;


/**
 *
 * @author favya
 */
public class Taller2 {

     //Funcion para la lectura del archivo Comunas.txt
     public static void leerComunas (ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Comunas.txt");
                while(!in.isEndFile()){
                Registro reg = in.getRegistro();
   
                int cod = reg.getInt();
                String nom = reg.getString();
                int numc = reg.getInt();
                int canth = reg.getInt();

                Comuna co = new Comuna(cod,nom,numc,canth);
                listaComunas.ingresarComunas(co); 
                
            }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }  
    
    //Funcion para la lectura del archivo Censistas.txt
     public static void leerCensistas (ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Censistas.txt");
                while(!in.isEndFile()){
                Registro reg = in.getRegistro();
   
                int cod = reg.getInt();
                String nom = reg.getString();
                String comu = reg.getString();

                Censista c = new Censista(cod,nom,comu);
                listaCensistas.ingresarCensistas(c);
  
            }
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }  
     

     
     //Funcion para la lectura del archivo Personas.txt 
    public static void leerPersonas(ListaPersonas listaPersonas, ListaCensistas listaCensistas, ListaComunas listaComunas) {
        try {
            ArchivoEntrada in = new ArchivoEntrada("Personas.txt");
            while(!in.isEndFile()){
                Registro reg = in.getRegistro();
                
                String nomb = reg.getString();
                int edad = reg.getInt();
                String comu = reg.getString();
                String ocup = reg.getString();
                int cantf = reg.getInt();
                String censis = reg.getString();
  
                Persona p = new Persona(nomb,edad,comu,ocup,cantf,censis);
                listaPersonas.ingresarPersonas(p);
          
                }
                
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo");
        }
    }   
    
    //RF1: Creacion del archivo Censo.txt
    public static void CensoRF1 (ListaComunas lco, ListaPersonas lp) throws IOException {
       
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
         fichero = new FileWriter("Censo.txt");
         pw = new PrintWriter(fichero);  
         

            for(int i = 0; i < lco.getCantComunas(); i++){
                
                Comuna co = lco.getComunaI(i);
                pw.println("Comuna: " + co.getNombre());
                String var = co.getNombre();
                for(int j = 0; j < lp.getCantPersonas(); j++){
                    Persona p = lp.getPersonaI(j); 
                    if(p.getComuna().equals(var)){        
                     pw.println(" Nombre: " + p.getNombre()+ " Edad: " + p.getEdad()
                            + " Ocupacion: " + p.getOcupacion()+ " Cantidad Familia: " + p.getCantidadFamilia()
                            + " Censado por: "+ p.getCensista());}
                                       
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }  
    }    

    public static void DatosCensistasRF2(ListaCensistas lc, ListaPersonas lp){
         
        int SumaTotal = 0;
        int ContMayor3 = 0;
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
         fichero = new FileWriter("DatosCensistas.txt");
         pw = new PrintWriter(fichero);  
           
         for(int i=0; i< lc.getCantCensistas(); i++){
            int SumaFamilia = 0; 
            Censista c = lc.getCensistaI(i);
            pw.println("Censista: " + c.getNombre()+" Codigo: "+c.getCodigo());                        
                  
            for(int j=0; j < lp.getCantPersonas(); j++){
                Persona p = lp.getPersonaI(j);
                if(p.getCensista().equals(c.getNombre())){
                pw.println("Persona censada: " + p.getNombre()+" Edad: "+p.getEdad());
             
                // Contador de familias
                int cont = p.getCantidadFamilia();
                SumaFamilia = SumaFamilia + cont;
                SumaTotal = SumaTotal + SumaFamilia;
             
                // Si es mayor que 3 se guarda en su respectivo contador
                if(cont > 3){
                ContMayor3++;            
                }
                }
            }
            pw.println("Total de personas de familia: " + SumaFamilia); 
            pw.println(" ");
             
             
         } pw.print("Total de Habitantes: "+SumaTotal);
           pw.print("Total familias mayor 3: " + ContMayor3);
           float Porcentaje = (ContMayor3 * 100.0f)/ SumaTotal;
           pw.println(" -> % de familias que superan los 3 integrantes: "+Porcentaje);
         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }     
    }

     public static void CensistaRF3(ListaCensistas lc, ListaPersonas lp, ListaComunas lco,int codigo){
         
         Censista c = lc.buscarPorCodigo(codigo);
         StdOut.println("Nombre: " + c.getNombre());
         StdOut.println("Codigo: " + c.getCodigo());
         StdOut.println("Comuna: "+ c.getComuna());
         int CantHab = 0;
         for(int i=0; i < lco.getCantComunas();i++){
             Comuna comun = lco.getComunaI(i);
             String nombrec = comun.getNombre();
                if(c.getComuna().equals(nombrec)){
                    CantHab = comun.getCanthabitantes();
                    StdOut.println("Cantidad Habitantes: "+CantHab);
                    }
         }
         //int TotalCensadosCen=
            String cen = c.getNombre();
            StdOut.println("");
            StdOut.println("Personas Censadas por "+ c.getNombre()+":");
            int sumatotalf =0;
            for(int j=0; j < lp.getCantPersonas(); j++){
                Persona p = lp.getPersonaI(j);
                if(p.getCensista().equals(cen)){
                StdOut.println("Nombre: " + p.getNombre()+" Edad: "+p.getEdad()
                        +" Comuna: "+ p.getComuna()+" Ocupacion: "+ p.getOcupacion()+
                        " Cantidad Familia: "+ p.getCantidadFamilia());
                sumatotalf = sumatotalf + p.getCantidadFamilia();
                    }  
         }
           float Porcentaje = (sumatotalf * 100.0f)/ CantHab;
           StdOut.println("%: "+Porcentaje);
     }
    public static void DatosComunaRF4(ListaComunas lco, ListaPersonas lp, int CodigoComuna) {
        
        int Cont = 0;
        String comunanombre = null;
        int canthab = 0;
        for(int i=0; i < lco.getCantComunas();i++){
             Comuna comun = lco.getComunaI(i);
             int codigo = comun.getCodigo();
             if(codigo == CodigoComuna){
                    comunanombre = comun.getNombre();
                    StdOut.println("Nombre: " + comun.getNombre());
                    StdOut.println("Codigo: " + comun.getCodigo());
                    canthab = comun.getCanthabitantes();
                    StdOut.println("Cantidad Total Habitantes: " + comun.getCanthabitantes());
                    }
        }
                
        for(int i=0; i < lp.getCantPersonas();i++){
            Persona pe = lp.getPersonaI(i);
                if(pe.getComuna().equals(comunanombre)){
                    int cantpersonas = pe.getCantidadFamilia();
                    Cont = Cont + cantpersonas;
                    }  
        }
        if (Cont == canthab){
            StdOut.println("El censo fue realizado exitosamente");

        }else{           
            StdOut.println("El censo NO fue realizado satisfactoriamente");
        
        }
    }
        
       
    public static void main(String[] args) throws IOException {
 
    ListaCensistas lc = new ListaCensistas(1000);
    ListaPersonas lp = new ListaPersonas(1000);
    ListaComunas lco = new ListaComunas(1000);
    
    leerComunas(lco);
    leerCensistas(lp, lc, lco);
    leerPersonas(lp, lc, lco);
    
    
    CensoRF1(lco, lp);
    DatosCensistasRF2(lc,lp);
    CensistaRF3(lc, lp, lco, 1);
    DatosComunaRF4(lco,lp, 4);
   // StdOut.println(lp.getPersonaI(0).getComuna().getNombre());

  
  int op;
  
      /*   do{  
            StdOut.println("\nIngrese una opción del menú: " );
            op = Integer.parseInt(bf.readLine()); 
            switch(op){
                
                case 1: 
                  //ReservarHabitacionRF1(lp,lr,lh);
                    break;
                case 2:
                    ReservacionesRF2(lp);
                    break;
                case 3:
                    SueldosRF3 (lp);
                    break;
  StdOut.print("Ingrese patente: ") ;
String patente = StdIn.readString();
                case 4:
                    StdOut.println("Usted está saliendo del menú..." );
                    break;
                default:
                    StdOut.println("OPCION NO VALIDA" );
                    break;
               
            }
            }while( op != 4 );*/
 }
       
    
}
