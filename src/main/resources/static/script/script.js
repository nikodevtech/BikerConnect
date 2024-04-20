function revisarContraseña() {
	const contraseña = document.getElementById('password').value;
	const confContraseña = document.getElementById('confirmarPassword').value;
	const mensajeContraseña = document.getElementById('mensajeContrasenya');
	const contraseñaRegex = /^(?=.*\d).{8,}$/;

	if (contraseña === "" && confContraseña === "") {
		mensajeContraseña.innerHTML = '<span class="badge bg-danger">No puede dejar los campos contraseñas vacíos</span>';
		mensajeContraseña.style.color = 'red';
		document.getElementById("btnRegistro").disabled = true;
		btnRegistro.style.backgroundColor = "#959595";
	} else if (contraseña === confContraseña) {
		if (contraseñaRegex.test(contraseña)) {
			mensajeContraseña.innerHTML = '<span class="badge bg-success">Contraseña válida</span>';
			mensajeContraseña.style.color = 'green';
			document.getElementById("btnRegistro").disabled = false;
			btnRegistro.style.backgroundColor = "#5993d3";
		} else {
			mensajeContraseña.innerHTML = '<span class="badge bg-danger">La contraseña debe tener al menos 8 caracteres con 1 número</span>';
			mensajeContraseña.style.color = 'red';
			document.getElementById("btnRegistro").disabled = true;
			btnRegistro.style.backgroundColor = "#959595";
		}
	} else {
		mensajeContraseña.innerHTML = '<span class="badge bg-danger">Las contraseñas introducidas no son iguales</span>';
		mensajeContraseña.style.color = 'red';
		document.getElementById("btnRegistro").disabled = true;
		btnRegistro.style.backgroundColor = "#959595";
	}
}


function mostrarNotificacion(titulo, mensaje, tipo) {
	Swal.fire({
		title: titulo,
		text: mensaje,
		icon: tipo,
		confirmButtonText: 'OK',
		customClass: {
			confirmButton: 'btn btn-primary'
		}
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
function confirmar(mensaje) {
	return Swal.fire({
		title: '¿Estás seguro de que deseas ' + mensaje + '?',
		text: 'Esta acción es irreversible.',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#d33',
		cancelButtonColor: '#3085d6',
		confirmButtonText: 'Sí'
	}).then((result) => {
		return result.isConfirmed;
	});
}

function confirmarEliminar(event) {
	const dataUsuario = event.currentTarget.getAttribute("data-usuario");
    const usuarioProps = dataUsuario.split('|');
    const idUsuario = usuarioProps[0];
    const emailUsuario = usuarioProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar este usuario",
		input: "text",
		inputLabel: `Introduce '${emailUsuario}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== emailUsuario.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === emailUsuario.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar-usuario/' + idUsuario;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
		}
	});
}

function confirmarEliminarMoto(event) {
	const dataMoto = event.currentTarget.getAttribute("data-moto");
    const motoProps = dataMoto.split('|');
    const idMoto = motoProps[0];
    const nombreMoto = motoProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar esta moto",
		input: "text",
		inputLabel: `Introduce '${nombreMoto}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== nombreMoto.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === nombreMoto.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar-moto/' + idMoto;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
		}
	});
}
function confirmarCancelarQuedada(event) {
	const idQuedada = event.currentTarget.getAttribute("data-id");
	confirmar("cancelar").then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/quedadas/detalle-quedada/cancelar-quedada/' + idQuedada;
		}
	});
}
function confirmarCompletarQuedada(event) {
	const idQuedada = event.currentTarget.getAttribute("data-id");
	confirmar("marcar como completada").then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/quedadas/detalle-quedada/completar-quedada/' + idQuedada;
		}
	});
}
function establecerFechaMinima() {
	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);
	var formattedTomorrow = tomorrow.toISOString().slice(0, 10);

	document.getElementById("fechaHora").min = formattedTomorrow + "T00:00";
}
function cambiarPlaceholder() {
	let valorSelect = document.querySelector('select[name="filtro"]').value;
	let input = document.getElementById('busquedaUser');

	if (valorSelect === 'email') {
		input.placeholder = "Buscar por el email del usuario";
	} else if (valorSelect === 'nombre_apellidos') {
		input.placeholder = "Buscar por nombre y apellidos";
	}
}
function generarPDF() {
    const element = document.querySelector('.card-body');
    const options = {
        margin: 10,
        filename: 'Mis_quedada_bikerconnect.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().from(element).set(options).save().then(() => {
        console.log('PDF generado correctamente');
    }).catch((error) => {
        console.error('Error al generar el PDF:', error);
    });
}