package com.inventor.app.repository;

import com.inventor.app.model.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioRepo extends CrudRepository<Horario,Long> {

    @Query(value = "SELECT h.* FROM public.horario h WHERE h.id_horario NOT IN ( SELECT ci.horario_id FROM public.cita ci WHERE ci.fecha_cita = TO_DATE(?2, 'YYYY-MM-DD') AND ci.doctor_id =?1)"
            , nativeQuery = true)
    List< Horario> obtenerHorariosDisponibles(@Param("doctor") Long doctor,@Param("fecha") String fecha);
}
