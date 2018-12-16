package pe.cmacica.labs.labs03.dominio;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Cliente extends Operation {

    private int id;

    @NotNull
    @Size(min=5,max=100)
    private String nombres;

    @NotNull
    @Size(min=5,max=50)
    private String paterno;

    @NotNull
    @Size(min=5,max=50)
    private String materno;

    @NotNull
    @Min(18)
    @Max(100)
    private int edad;

    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
