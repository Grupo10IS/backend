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
                    "fechaAsignacion": "date (yyyy-mm-dd)",
                    "fechaCaducidad": "date (yyyy-mm-dd)",
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
