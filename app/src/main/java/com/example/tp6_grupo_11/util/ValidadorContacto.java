package com.example.tp6_grupo_11.util;

import android.util.Patterns;
import android.widget.EditText;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class ValidadorContacto {

    // Letras (incluye tildes y ñ). Entre palabras admite espacio, guion o apóstrofe
    // (ej: "María José", "Pérez-Gómez", "D'Angelo"). Prohíbe números y otros símbolos.
    private static final String REGEX_SOLO_LETRAS =
            "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(( +|['-])[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+)*$";
    // Solo numeros y guiones opcionales
    private static final String REGEX_TELEFONO = "^[0-9]+(-[0-9]+)*$";
    // Cantidad de dígitos (sin contar guiones) aceptada para el teléfono
    private static final int TELEFONO_MIN_DIGITOS = 6;
    private static final int TELEFONO_MAX_DIGITOS = 15; // máximo del estándar internacional E.164
    // Email: usuario@dominio.terminacion. La terminacion debe ser de 2 letras (pais: ar, es, uy...)
    // o una generica comun. Patterns.EMAIL_ADDRESS por si solo acepta cosas como ".comasdasdasda".
    private static final Pattern PATRON_EMAIL = Pattern.compile(
            "^[A-Z0-9._%+-]+@([A-Z0-9-]+\\.)+([A-Z]{2}|COM|NET|ORG|EDU|GOV|MIL|INT|INFO|BIZ|APP|DEV|ONLINE|STORE|TECH)$",
            Pattern.CASE_INSENSITIVE);

    public static boolean validarFormulario(EditText etNombre, EditText etApellido,
                                            EditText etTelefono, EditText etEmail,
                                            EditText etDireccion, EditText etFechaNac) {
        boolean esValido = true;
        EditText primerCampoConError = null;

        // 1. Validar Nombre
        String nombre = etNombre.getText().toString().trim();
        if (nombre.isEmpty()) {
            etNombre.setError("Campo obligatorio: ingresá el nombre del contacto.");
            esValido = false;
            primerCampoConError = etNombre;
        } else if (!nombre.matches(REGEX_SOLO_LETRAS)) {
            etNombre.setError("Nombre inválido: debe contener solo letras (se admiten espacio, guion y apóstrofe entre palabras), sin números ni otros símbolos.");
            esValido = false;
            primerCampoConError = etNombre;
        }

        // 2. Validar Apellido
        String apellido = etApellido.getText().toString().trim();
        if (apellido.isEmpty()) {
            etApellido.setError("Campo obligatorio: ingresá el apellido del contacto.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etApellido;
        } else if (!apellido.matches(REGEX_SOLO_LETRAS)) {
            etApellido.setError("Apellido inválido: debe contener solo letras (se admiten espacio, guion y apóstrofe entre palabras), sin números ni otros símbolos.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etApellido;
        }

        // 3. Validar Telefono
        String telefono = etTelefono.getText().toString().trim();
        if (telefono.isEmpty()) {
            etTelefono.setError("Campo obligatorio: ingresá un número de teléfono.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etTelefono;
        } else if (!telefono.matches(REGEX_TELEFONO)) {
            etTelefono.setError("Teléfono inválido: ingresá solo números y guiones opcionales (ej: 11-4567-8901), sin letras ni espacios.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etTelefono;
        } else if (telefono.replace("-", "").length() < TELEFONO_MIN_DIGITOS
                || telefono.replace("-", "").length() > TELEFONO_MAX_DIGITOS) {
            etTelefono.setError("Teléfono inválido: debe tener entre " + TELEFONO_MIN_DIGITOS
                    + " y " + TELEFONO_MAX_DIGITOS + " dígitos (sin contar los guiones).");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etTelefono;
        }

        // 4. Validar Email
        String email = etEmail.getText().toString().trim();
        if (email.isEmpty()) {
            etEmail.setError("Campo obligatorio: ingresá un correo electrónico.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etEmail;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()
                || !PATRON_EMAIL.matcher(email).matches()) {
            etEmail.setError("Email inválido: debe respetar el formato usuario@dominio.com.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etEmail;
        }

        // 5. Validar Direccion
        String direccion = etDireccion.getText().toString().trim();
        if (direccion.isEmpty()) {
            etDireccion.setError("Campo obligatorio: ingresá la dirección (calle y altura).");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etDireccion;
        }

        // 6. Validar Fecha de Nacimiento
        String fecha = etFechaNac.getText().toString().trim();
        if (fecha.isEmpty()) {
            etFechaNac.setError("Campo obligatorio: ingresá la fecha de nacimiento (dd/mm/aaaa).");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etFechaNac;
        } else if (!esFechaValida(fecha)) {
            etFechaNac.setError("Fecha inválida: usá el formato dd/mm/aaaa con una fecha real que no sea futura.");
            esValido = false;
            if (primerCampoConError == null) primerCampoConError = etFechaNac;
        }

        // Enfoca automaticamente el primer campo que fallo
        if (primerCampoConError != null) {
            primerCampoConError.requestFocus();
        }

        return esValido;
    }

    private static boolean esFechaValida(String fechaStr) {
        if (!fechaStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) return false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false); // Rechaza fechas inexistentes como 31/02/2000

        try {
            Date fechaIngresada = sdf.parse(fechaStr); //  lanza la excepcion con la fecha inexistente
            if (fechaIngresada == null) return false;

            Calendar calIngresada = Calendar.getInstance();
            calIngresada.setTime(fechaIngresada);
            int anio = calIngresada.get(Calendar.YEAR);

            Date hoy = new Date();

            return anio >= 1900 && !fechaIngresada.after(hoy);
        } catch (ParseException e) {
            return false;
        }
    }
}