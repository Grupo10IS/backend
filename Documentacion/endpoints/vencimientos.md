- /vencimientosPuntos
    - _GET_:
      listar la regla de vencimiento de puntos activas o por activar
         - Response:
            ```json
            {
                "vencimientos": [ 
                    {
                        "vencimiento": {
                            "id": 0,
                            "fechaInicioValidez": "yyyy-mm-dd",
                            "fechaFinValidez": "yyyy-mm-dd",
                            "diasDuracionPuntaje": 0
                        }
                    }
                ]
            }
            ```

    - _POST_:
      anadir una nueva regla de vencimiento de puntos
         - Request:
            ```json
            {
                "fechaInicioValidez": "yyyy-mm-dd",
                "fechaFinValidez": "yyyy-mm-dd",
                "diasDuracionPuntaje": 0,
            }
            ```

