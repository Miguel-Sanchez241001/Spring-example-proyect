package com.inventor.app.model;



import com.inventor.app.config.Credenciales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

   

    @Column(name = "user_nombre") // Especifica el nombre de la columna en la base de datos
    private String userNombre;

    @Column(name = "user_apellido")
    private String userApellido;

    @Column(name = "user_correo")
    private String userCorreo;

    @Column(name = "user_edad")
    private Integer userEdad;

    @Column(name = "user_sexo")
    private String userSexo;

    @Column(name = "user_tipo")
    private String userTipo;

    @OneToOne
    private Credenciales credenciales;


}
