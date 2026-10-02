// src/main/resources/static/js/heroes.js
// Conecta las vistas (index, nuevo, edita) con HeroeController usando AJAX (jQuery)
// Requiere jQuery cargado ANTES que este archivo.

const API_URL = '/api/v1/heroes';

/* ---------- Llamadas AJAX a la API ---------- */

const HeroesAPI = {
    listar() {
        return $.ajax({ url: API_URL, method: 'GET' });
    },
    buscar(id) {
        return $.ajax({ url: `${API_URL}/${id}`, method: 'GET' });
    },
    porEpoca(epoca) {
        return $.ajax({ url: `${API_URL}/epoca/${encodeURIComponent(epoca)}`, method: 'GET' });
    },
    porMovimiento(movimiento) {
        return $.ajax({ url: `${API_URL}/movimiento/${encodeURIComponent(movimiento)}`, method: 'GET' });
    },
    porEstado(estado) {
        return $.ajax({ url: `${API_URL}/estado/${encodeURIComponent(estado)}`, method: 'GET' });
    },
    crear(heroe) {
        return $.ajax({
            url: API_URL,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(heroe)
        });
    },
    actualizar(id, heroe) {
        return $.ajax({
            url: `${API_URL}/${id}`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(heroe)
        });
    },
    eliminar(id) {
        return $.ajax({ url: `${API_URL}/${id}`, method: 'DELETE' });
    }
};

/* ---------- Vista: index (tabla) ---------- */

function pintarTabla(heroes) {
    const $tbody = $('#tabla-heroes').empty();

    if (!heroes || heroes.length === 0) {
        $tbody.append('<tr><td colspan="9" class="text-center">No hay héroes registrados</td></tr>');
        return;
    }

    heroes.forEach(h => {
        // .text() escapa el contenido, evita inyección de HTML
        const $fila = $('<tr>');
        [h.id, h.nombre, h.apellido, h.fechaNacimiento, h.estadoNacimiento,
            h.epoca, h.movimiento, h.descripcion].forEach(valor => {
            $('<td>').text(valor ?? '').appendTo($fila);
        });

        const $acciones = $('<td class="text-nowrap">');
        $('<a class="btn btn-warning btn-sm me-1">')
            .text('Editar')
            .attr('href', `/edita?id=${encodeURIComponent(h.id)}`)
            .appendTo($acciones);
        $('<button type="button" class="btn btn-danger btn-sm">')
            .text('Eliminar')
            .attr({ 'data-bs-toggle': 'modal', 'data-bs-target': '#eliminaModal', 'data-bs-id': h.id })
            .appendTo($acciones);

        $fila.append($acciones).appendTo($tbody);
    });
}

function cargarHeroes() {
    HeroesAPI.listar()
        .done(pintarTabla)
        .fail(() => alert('No se pudo cargar la lista de héroes'));
}

function configurarFiltros() {
    $('#btn-filtrar').on('click', () => {
        const tipo = $('#filtro-tipo').val();
        const valor = $('#filtro-valor').val().trim();
        if (!valor) return cargarHeroes();

        const busquedas = {
            epoca: HeroesAPI.porEpoca,
            movimiento: HeroesAPI.porMovimiento,
            estado: HeroesAPI.porEstado
        };
        busquedas[tipo](valor)
            .done(pintarTabla)
            .fail(() => alert('Error al filtrar'));
    });

    $('#btn-limpiar').on('click', cargarHeroes);
}

// Modal de eliminación
function configurarModalEliminar() {
    let idAEliminar = null;
    const modalEl = document.getElementById('eliminaModal');
    if (!modalEl) return;

    $(modalEl).on('show.bs.modal', event => {
        idAEliminar = event.relatedTarget.getAttribute('data-bs-id');
    });

    $('#form-elimina').on('submit', event => {
        event.preventDefault();
        HeroesAPI.eliminar(idAEliminar)
            .done(() => {
                bootstrap.Modal.getInstance(modalEl).hide();
                cargarHeroes();
            })
            .fail(() => alert('No se pudo eliminar el héroe'));
    });
}

const CAMPOS = ['nombre', 'apellido', 'fechaNacimiento', 'estadoNacimiento', 'epoca', 'movimiento', 'descripcion'];

function configurarFormulario() {
    const $form = $('#form-heroe');
    if ($form.length === 0) return;

    const id = new URLSearchParams(window.location.search).get('id');

    // Modo edición: cargar los datos del héroe en el formulario
    if (id) {
        HeroesAPI.buscar(id)
            .done(heroe => {
                CAMPOS.forEach(c => $form.find(`[name="${c}"]`).val(heroe[c] ?? ''));
            })
            .fail(() => alert('No se pudo cargar el héroe'));
    }

    $form.on('submit', event => {
        event.preventDefault();

        const heroe = {};
        CAMPOS.forEach(c => {
            const $campo = $form.find(`[name="${c}"]`);
            if ($campo.length) heroe[c] = $campo.val();
        });

        const peticion = id ? HeroesAPI.actualizar(id, heroe) : HeroesAPI.crear(heroe);
        peticion
            .done(() => { window.location.href = '/'; })
            .fail(() => alert('No se pudo guardar el héroe'));
    });
}


$(function () {
    if ($('#tabla-heroes').length) {
        cargarHeroes();
        configurarFiltros();
        configurarModalEliminar();
    }
    configurarFormulario();
});