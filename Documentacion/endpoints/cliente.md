- /cliente
    - _GET_: listar y buscar los datos de un cliente
    - Filters: 
        - nacionalidad={_string_}: filtrar por pais
        - puntosMinimos={_integer_}: puntos minimos de los clientes a listar
        - puntosMaximos={_integer_}: puntos maximos de loa clientes a listar
        - cumpleanos{_date (yyyy-mm-dd)_}: filtrar por fecha de nacimeinto
        - vencimientoPuntos{_integer_}: listar los clientes que tengan puntos a vencer en x
        dias
    - Response:
        ```json
        {
            "clientes": [
                "cliente": {
                "tipoDocumento": [1 o 2] // 1: cedula 2: ruc,
                "id": 0000,
                "nombre": "string",
                "apellido": "string",
                "numeroDocumento": 000,
                "nacionalidad": "string",
                "email": "string",
                "telefono": "string",
                "fechaNacimiento": "date (yyyy-mm-dd)",
                "puntosDisponibles": 000,
            }
            ]
        }
        ```

    - _POST_: anadir un nuevo cliente a la base de datos
    - Body:
        ```json
        {
            "tipoDocumento": [1 o 2] // 1: cedula 2: ruc,
            "nombre": "string",
            "apellido": "string",
            "numeroDocumento": 9999,
            "nacionalidad": "string",
            "nacimiento": "yyyy-mm-dd",
            "email": "string",
            "telefono": "string [opcional]"
        }
        ```

