- /vencimientosPuntos
    - _GET_: listar la regla de vencimiento de puntos activas o por activar
         - Response:
            ```json
            {
                "reglas": [ 
                    {
                        "regla": {
                            "idRegla": 0,
                            "fechaInicioValidez": "yyyy-mm-dd",
                            "fechaFinValidez": "yyyy-mm-dd",
                            "diasDuracion": 0
                        }
                    }
                ]
            }
            ```

    - _POST_: anadir una nueva regla de vencimiento de puntos
         - Response:
            ```json
            {
                "fechaInicioValidez": "yyyy-mm-dd",
                "fechaFinValidez": "yyyy-mm-dd",
                "diasDuracion": 0,
            }
            ```

