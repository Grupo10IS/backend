- /puntosUsados
    - _GET_: listar los puntos utilizados por los clientes
    - Filtros:
        - cliente={_integer_}: id del cliente
        - concepto={_integer_}: id concepto de uso
        - fechaMinima={_date (yyyy-mm-dd)_}: fecha minima de busqueda 
        - fechaMaxima={_date (yyyy-mm-dd)_}: fecha maxima de busqueda
     - Response:
        ```json
        {
            "usos": [
                "uso": {
                    "idCliente": 0,
                    "idConcepto": 0,
                    "puntosUtilizados": 0,
                    "fechaUso": "date (yyyy-mm-dd)"
                }
            ]
        }
        ```
