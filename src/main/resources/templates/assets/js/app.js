$(function() {
    // Al hacer clic en el botón "Crear cita", muestra el formulario
    $("#crear-cita").click(function() {
        $("#formulario-cita").show();
        $("#tabla-citas").hide();
    });

    // Al hacer clic en el botón "Ver citas", muestra la tabla
    $("#ver-citas").click(function() {
        $("#formulario-cita").hide();
        $("#tabla-citas").show();
    });
});