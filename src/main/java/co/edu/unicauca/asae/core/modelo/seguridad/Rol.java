package co.edu.unicauca.asae.core.modelo.seguridad;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private ERol nombre;
    
    // Constructor, getters y setters
    public Rol() {}
    
    public Rol(ERol nombre) {
        this.nombre = nombre;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public ERol getNombre() { return nombre; }
    public void setNombre(ERol nombre) { this.nombre = nombre; }
}