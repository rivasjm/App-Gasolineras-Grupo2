# Manual de uso

## Interacciones de Usuario

### 1. Añadir un Nuevo Punto de Interés

1. El usuario debe rellenar los siguientes campos:
   - **Nombre**: El nombre del nuevo punto de interés.
   - **Latitud**: La latitud en formato numérico (por ejemplo, `43.4623`).
   - **Longitud**: La longitud en formato numérico (por ejemplo, `-3.8099`).

2. Después de introducir la información, el usuario debe pulsar el botón **Guardar** para almacenar el punto de interés en la base de datos.

3. Si algún campo está vacío o los valores numéricos son incorrectos, el sistema mostrará un mensaje de error:
   - "Por favor, llene todos los campos" si algún campo está vacío.
   - "Por favor, ingresa valores numéricos válidos para latitud y longitud" si los valores no son numéricos.

4. Si ya existe un punto de interés con el mismo nombre, se mostrará un mensaje:
   - "Ya existe un punto de interés con ese nombre".

5. Si todo es correcto, el sistema mostrará un mensaje de éxito:
   - "Punto de interés guardado".
   - A continuación, la vista se cerrará automáticamente.

### 2. Cancelar la Operación

El usuario puede cancelar el proceso en cualquier momento pulsando el botón **Cancelar**, lo cual cerrará la vista sin guardar ningún dato.

## Posibles Errores y Mensajes

- **Por favor, llene todos los campos**: Aparece cuando uno o más campos están vacíos.
- **Por favor, ingresa valores numéricos válidos para latitud y longitud**: Aparece cuando los valores introducidos para latitud o longitud no son numéricos.
- **Ya existe un punto de interés con ese nombre**: Aparece cuando el nombre del punto de interés ya está registrado en la base de datos.
- **Ha ocurrido un error en la base de datos**: Aparece cuando ocurre un error inesperado al acceder a la base de datos.

## Flujo del Caso de Uso

1. El usuario abre la vista de añadir punto de interés.
2. El usuario introduce los datos requeridos (nombre, latitud y longitud).
3. El usuario pulsa **Guardar** para almacenar el nuevo punto de interés.
4. El sistema valida los datos:
   - Si son válidos, el punto de interés se guarda y se cierra la vista.
   - Si hay errores, se muestran los mensajes correspondientes.
5. El usuario puede cancelar la operación en cualquier momento pulsando **Cancelar**, lo que cierra la vista sin realizar ningún cambio.
