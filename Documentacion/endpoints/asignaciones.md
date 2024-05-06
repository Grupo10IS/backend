- /asignaciones
    - _GET_: mostrar la regla de asignacion de puntos activa. X monto equivale a 1 punto
     - Response:
        ```json
        {
            "montoPorPunto": 0,
        }
        ```

    - _PUT_: modificar la regla de asignacion vigente.
    filtros (obligatorio):
        - monto{integer} = monto en dolares que equivale a un punto

