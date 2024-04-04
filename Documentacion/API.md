# Estructura de la API

Send: application/json
Recieve: application/json

## Paths

- /cliente
    - _GET_: listar y buscar los datos de un cliente
    - Filters: 
        - nacionalidad={_string_}: filtrar por pais
        - puntosMinimos={_integer_}: puntos minimos de los clientes a listar
        - puntosMaximos={_integer_}: puntos maximos de loa clientes a listar
        - cumpleanos{_date (dd-mm-yyyy)_}: filtrar por fecha de nacimeinto
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
                    "fechaNacimiento": "date (dd-mm-yyyy)",
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
            "fechaNacimiento": "string",
            "email": "string",
            "telefono": "string [opcional]"
        }
        ```

- /puntosUsados
    - _GET_: listar los puntos utilizados por los clientes
    - Filtros:
        - cliente={_integer_}: id del cliente
        - concepto={_integer_}: id concepto de uso
        - fechaMinima={_date (dd-mm-yyyy)_}: fecha minima de busqueda 
        - fechaMaxima={_date (dd-mm-yyyy)_}: fecha maxima de busqueda
     - Response:
        ```json
        {
            "usos": [
                "uso": {
                    "idCliente": 0,
                    "idConcepto": 0,
                    "puntosUtilizados": 0,
                    "fechaUso": "date (dd-mm-yyyy)"
                }
            ]
        }
        ```
     
- /bolsaPuntos
    - _GET_: listar y buscar las bolsas de puntos activas de los clientes
    - Filters: 
        - cliente={_id_}: id del cliente
        - puntosMinimos={_integer_}: puntos minimos de los clientes a listar
        - puntosMaximos={_integer_}: puntos maximos de los clientes a listar
        - vencimiento{_integer_}: mostrar solo las bolsas que venceran en x dias o menos
     - Response:
        ```json
        {
            "bolsas": [
                "bolsa": {
                    "idBolsa": 0,
                    "idCliente": 0,
                    "puntos": 0,
                    "fechaAsignacion": "date (dd-mm-yyyy)",
                    "fechaCaducidad": "date (dd-mm-yyyy)",
                    "puntajeAsignado": 0,
                    "puntajeUtilizado": 0, 
                    "saldo": 0, 
                    "montoOperacion": 0, // monto de dinero que asigno los x puntos
                }
            ]
        }
        ```

    - _POST_: anadir nuevos puntos a un usuario
        - Body:
            ```json
            {
                "monto": 0,
                "idCliente": 0,
            }
            ```

    - _PUT_: utilizar una cantidad x de puntos
        - Body:
            ```json
            {
                "idCliente": 0,
                "idConcepto": 0,
            }
            ```
    
- /cotizacion?monto={}
    - _GET_: realizar la cotizacion de un monto x a puntos.
    - filtros: (obligatorio)
        - monto={_integer_}: monto de dinero a ser cotizado en puntos
     - Response:
        ```json
        {
            "cotizacion": 0,
        }
        ```
