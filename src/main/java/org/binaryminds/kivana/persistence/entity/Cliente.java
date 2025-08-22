package org.binaryminds.kivana.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Clientes")
//Lombok
@Data//genera los getters and setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode//codigo de auntenticacion de la entidad
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCliente;//permite usar null en lugar de 0
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String genero;//enum(masculino, femenino, no)
    private Integer edad;
}
