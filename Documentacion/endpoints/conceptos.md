- /conceptos
    - _GET_: listar y buscar los conceptos de uso de puntos
     - Response:
        ```json
        {
            "conceptos": [
                "concepto": {
                    "idConcepto": 0,
                    "descripcion": 0,
                    "puntosRequeridos": 0
                }
            ]
        }
        ```

    - _POST_: anadir un nuevo concepto de uso
        - Body:
            ```json
            {
                "puntosRequeridos": 0,
                "descripcion": "vale de comida",
            }
            ```

