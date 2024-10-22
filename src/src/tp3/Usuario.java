
package tp3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import static tp3.RegistroTemperatura.ANSI_GREEN;

/**
 *
 * @author gonzalez.leandro
 */
public class Usuario {
    protected int tipo_usuario;
    protected int legajo;
    protected String nom_usuario;
    protected String pass;
    protected String Email;

    public Usuario(int tipo_usuario, int legajo, String nom_usuario, String pass, String Email) {
        this.tipo_usuario = tipo_usuario;
        this.legajo = legajo;
        this.nom_usuario = nom_usuario;
        this.pass = pass;
        this.Email = Email;
    }

    public Usuario() {
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
   public static void gestionarUsuario(){
   System.out.println("Esta en la parte de gestionar usuario, en desarrollo para el tp4");
   }
}
