const API = '/api/v1/usuarios';
const id = new URLSearchParams(window.location.search).get('id');
const esEdicion = id !== null;

$(function () {
    const $form =$('#form-usuario');

    // La fecha de nacimiento no puede ser posterior a hoy
    const hoy = new Date();
    const hoyIso = `${hoy.getFullYear()}-${String(hoy.getMonth() + 1).padStart(2, '0')}-${String(hoy.getDate()).padStart(2, '0')}`;
    $('#fechaNacimiento').attr('max', hoyIso);

    function alerta(texto, tipo = 'danger') {
        // Alerta nativa del navegador para asegurar que el usuario la vea inmediatamente
        alert(texto);

        // Mensaje dentro del DOM (#alerta)
        $('#alerta').empty().append(
            $('<div class="alert" role="alert">').addClass('alert-' + tipo).text(texto)
        );
    }

    function leerFormulario() {
        return {
            nombre: $('#nombre').val().trim(),
            apellido_paterno: $('#apellido_p').val().trim(),
            apellido_materno: $('#apellido_m').val().trim(),
            correo: $('#correo').val().trim(),
            usuario: $('#usuario').val().trim(),
            password: $('#password').val(),
            fecha_nacimiento: $('#fechaNacimiento').val()
        };
    }

    function llenarFormulario(u) {
        $('#nombre').val(u.nombre);
        $('#apellido_p').val(u.apellido_paterno);
        $('#apellido_m').val(u.apellido_materno);
        $('#correo').val(u.correo);
        $('#usuario').val(u.usuario);
        $('#password').val(u.password || '');
        $('#fechaNacimiento').val(u.fecha_nacimiento);
    }

    // Mismas reglas que el servicio, para avisar antes de enviar
    function validar(d) {
        if (d.usuario.length < 5) return 'El usuario debe tener al menos 5 caracteres.';
        if (d.password.trim().length < 8) return 'La contraseña debe tener al menos 8 caracteres.';
        if (d.fecha_nacimiento > hoyIso) return 'La fecha de nacimiento no puede ser posterior a hoy.';
        return null;
    }

    if (esEdicion) {
        $.getJSON(`${API}/${id}`)
            .done(llenarFormulario)
            .fail(() => alerta('No se pudo cargar el usuario.'));
    }

    $form.on('submit', function (e) {         e.preventDefault();$('#alerta').empty();

        const datos = leerFormulario();
        const error = validar(datos);
        if (error) {
            alerta(error);
            return;
        }

        const $btn =$form.find('button[type=submit]').prop('disabled', true);

        $.ajax({
            url: esEdicion ? `${API}/${id}` : API,
            type: esEdicion ? 'PUT' : 'POST',
            contentType: 'application/json',
            data: JSON.stringify(datos)
        })
            .done(() => { window.location.href = '/'; })
            .fail(xhr => {
                const mensajeError = xhr.responseJSON?.mensaje || 'No se pudo guardar el usuario.';
                alerta(mensajeError);
            })
            .always(() => $btn.prop('disabled', false));
    });
});