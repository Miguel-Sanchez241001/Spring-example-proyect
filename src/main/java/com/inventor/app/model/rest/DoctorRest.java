package com.inventor.app.model.rest;

import com.inventor.app.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DoctorRest {
    @Id
    private Long docId;


    private String  docEspecialidad;

    private String docnumeroTelefono;

    private String docConsultorio;
}
