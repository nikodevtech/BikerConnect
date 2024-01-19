function revisarContraseña() {
    const contraseña = document.getElementById('password').value;
    const confContraseña = document.getElementById('confirmarPassword').value;
    const mensajeContraseña = document.getElementById('mensajeContrasenya');
  
   if (contraseña === "" && confContraseña === "") {
        mensajeContraseña.innerHTML = '<span class="badge bg-danger fs-5">No puede dejar los campos contraseñas vacíos</span>';
        mensajeContraseña.style.color = 'red';
        document.getElementById("btnRegistro").disabled = true;
        btnRegistro.style.backgroundColor = "#959595"; 
    }else if (contraseña === confContraseña) {
        mensajeContraseña.innerHTML = '<span class="badge bg-success fs-5">Las contraseñas introducidas coinciden</span>';
        document.getElementById("btnRegistro").disabled = false;
        btnRegistro.style.backgroundColor = "#5993d3"; 
    } else {
        mensajeContraseña.innerHTML = '<span class="badge bg-danger fs-5">Las contraseñas introducidas no son iguales</span>';
        document.getElementById("btnRegistro").disabled = true;
        btnRegistro.style.backgroundColor = "#959595"; 
    } 
}


function mostrarNotificacion(titulo, mensaje, tipo) {
    Swal.fire({
        title: titulo,
        text: mensaje,
        icon: tipo,
        confirmButtonText: 'OK'
    });
}

function confirmarLogout() {
    Swal.fire({
        title: '¿Estás seguro de que deseas cerrar sesión?',
        text: 'Serás redirigido a la página de bienvenida.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, cerrar sesión'
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('logoutForm').submit();
        } else {
            console.log('Logout cancelado');
        }
    });
}
function confirmar() {
    return Swal.fire({
        title: '¿Estás seguro de que deseas eliminar?',
        text: 'Esta acción es irreversible.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar.'
    }).then((result) => {
        return result.isConfirmed;
    });
}

function confirmarEliminar(event) {
    const idUsuario = event.currentTarget.getAttribute("data-id");
    confirmar().then(function (confirmado) {
        if (confirmado) {
            window.location.href = 'http://localhost:8080/privada/eliminar/' + idUsuario;
        }
    });
}