function validarFormulario() {
  let valido = true;

  const nombre = document.getElementById("nombres").value;
  const apellido = document.getElementById("apellidos").value;
  const email = document.getElementById("email").value;
  const numero = document.getElementById("numero").value;
  const mensaje = document.getElementById("mensaje").value;

  if (!/^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/.test(nombre)) {
    document.getElementById("error-nombres").textContent = "Solo letras";
    valido = false;
  }

  if (!/^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/.test(apellido)) {
    document.getElementById("error-apellidos").textContent = "Solo letras";
    valido = false;
  }

  if (!email.includes("@")) {
    document.getElementById("error-email").textContent = "Correo inválido";
    valido = false;
  }

  if (!/^\d{1,10}$/.test(numero)) {
    document.getElementById("error-telefono").textContent = "Solo números";
    valido = false;
  }

  if (mensaje.trim() === "") {
    document.getElementById("error-mensaje").textContent = "El mensaje no puede estar vacío";
    valido = false;
  }

  return valido;
}
