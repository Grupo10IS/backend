# Este archivo esta hecho para actuar como test de integracion y end-to-end
# para la API.
#
# A falta de documentacion apropiada acerca de como testear las aplicaciones
# con el servidor WILDFLY, nos vemos en la necesidad de realizar estos tests
#
# Los tests se detendran automaticamente si no se puede establecer conexion
# con el servidor WILDFLY

# Utilidades
pass=true

# Usage: curl_req (method) (status) "(endopoint)" '[data]'
curl_req() {
	method=$1
    s=$2
	endpoint=$3
    data=$4

	resp=$(curl -X $method "http://127.0.0.1:8080/parcial1/$endpoint" -H 'Content-Type: application/json' -d "$data" --silent -i 2>/dev/null)

	status=$(echo "$resp" | grep "HTTP" | grep $s)

	if [[ -z "$status" ]]; then
		pass=false
		echo "Esperado: $s"
		echo "Respuesta recibido: $status"
        echo "Body: $resp"
    else
        echo "- Test passed"
	fi

}

# -------------------
# | "/asignaciones" |
# -------------------
echo -e "\nTEST /asignaciones"

#### Metodo GET

echo -e " \n- GET"

curl_req get 200 "asignaciones"

# Revisamos que la conexion con el servidor sea exitosa. Este bloque
# solo es necesario correrlo una sola vez
if [ ! $? -eq 0 ]; then
	echo $resp
	exit 1
fi

if [[ -z "$resp" ]]; then
	pass=false
	echo "La respuesta del servidor esta vacia"
    exit 1
fi

#### Metodo PUT

echo -e "\n- PUT"

# Peticion valida
curl_req put 200 "asignaciones?monto=200"

# Peticion invalida. Faltan los campos necesarios
curl_req put 400 "asignaciones"

# Peticion invalida. Valor de la variable invalido
curl_req put 404 "asignaciones?monto=hola"

# ------------------
# | "/bolsaPuntos" |
# ------------------
echo -e "\n\nTEST /bolsaPuntos"

#### Metodo GET

echo -e " \n- GET"

# Peticion valida.
curl_req get 200 "bolsaPuntos"

#### Metodo POST

echo -e " \n- POST"

# Peticion valida.
curl_req post 200 "bolsaPuntos" '{"monto": 0, "idCliente": 1}'

# Peticion invalida. Cliente no existe
curl_req post 400 "bolsaPuntos" '{"monto": 0, "idCliente": 2000}'

# Peticion invalida. Datos incompletos
curl_req post 400 "bolsaPuntos" '{"monto": 0}'

#### Metodo PUT

echo -e "\n- PUT"

# Peticion valida.
curl_req put 200 "bolsaPuntos" '{"idCliente": 1, "idConcepto": 1}'

# Peticion invalida. Cliente no existe
curl_req put 400 "bolsaPuntos" '{"idConcepto": 1, "idCliente": 2000}'

# Peticion invalida. Concepto no existe
curl_req put 400 "bolsaPuntos" '{"idConcepto": 2000, "idCliente": 1}'

# Peticion invalida. Campos incompletos
curl_req put 400 "bolsaPuntos" '{"idCliente": 1}'

# --------------
# | "/cliente" |
# --------------
echo -e "\n\nTEST /cliente"

#### Metodo GET
echo -e " \n- GET"

curl_req get 200 "cliente"

#### Metodo POST
echo -e " \n- POST"

# Peticion valida
curl_req post 200 "cliente" '{
    "tipoDocumento": 1,
    "nombre": "elias",
    "apellido": "elias",
    "numeroDocumento": 9999,
    "nacionalidad": "paraguay",
    "nacimiento": "2001-01-01",
    "email": "elias@elias.com",
    "telefono": "091823"
}'

# Peticion valida. El telefono es opcional
curl_req post 200 "cliente" '{
    "tipoDocumento": 1,
    "nombre": "elias",
    "apellido": "elias",
    "numeroDocumento": 9999,
    "nacionalidad": "paraguay",
    "nacimiento": "2001-01-01",
    "email": "elias@elias.com"
}'

# Peticion invalida. Campos incompletos
curl_req post 400 "cliente" '{
    "tipoDocumento": 1,
    "numeroDocumento": 9999,
    "nacionalidad": "paraguay",
    "nacimiento": "2001-01-01",
    "email": "elias@elias.com",
    "telefono": "091823"
}'

# Peticion invalida. Formato de fecha invalido
curl_req post 400 "cliente" '{
    "tipoDocumento": 1,
    "nombre": "elias",
    "apellido": "elias",
    "numeroDocumento": 9999,
    "nacionalidad": "paraguay",
    "nacimiento": "01-01-2001",
    "email": "elias@elias.com",
    "telefono": "091823"
}'

# ----------------
# | "/conceptos" |
# ----------------
echo -e "\n\nTEST /conceptos"

#### Metodo GET
echo -e " \n- GET"

curl_req get 200 "conceptos"

#### Metodo POST
echo -e " \n- POST"

# Peticion valida
curl_req post 200 "conceptos" '{"puntosRequeridos": 3, "descripcion": "vale de comida"}'

# Peticion invalida. Incompletos
curl_req post 400 "conceptos" '{"puntosRequeridos": 3}'

# Peticion invalida. Monto negativo
curl_req post 400 "conceptos" '{"puntosRequeridos": -3, "descripcion": "vale de comida"}'

# -------------------
# | "/cotizaciones" |
# -------------------
echo -e "\n\nTEST /cotizacion"

#### Metodo GET
echo -e " \n- GET"

# Peticion valida
curl_req get 200 "cotizacion?monto=2000"

# Peticion invalida. El filtro de "monto" es obligatorio
curl_req get 400 "cotizacion"

# -------------------
# | "/puntosUsados" |
# -------------------
echo -e "\n\nTEST /puntosUsados"

#### Metodo GET
echo -e " \n- GET"

# Peticion valida
curl_req get 200 "puntosUsados"

# -------------------
# | "/vencimientos" |
# -------------------
echo -e "\n\nTEST /vencimientosPuntos"

#### Metodo GET
echo -e " \n- GET"

# Peticion valida
curl_req get 200 "vencimientosPuntos"

#### Metodo POST
echo -e " \n- POST"

# Peticion valida
curl_req post 200 "vencimientosPuntos" '{
    "fechaInicioValidez": "2020-01-01",
    "fechaFinValidez": "2020-05-01",
    "diasDuracionPuntaje": 15
}'

# Peticion invalida. Incompleto
curl_req post 400 "vencimientosPuntos" '{
    "fechaInicioValidez": "2020-01-01"
}'

# Peticion invalida. Formato de fecha incorrecto
curl_req post 400 "vencimientosPuntos" '{
    "fechaInicioValidez": "01-01-2020",
    "fechaFinValidez": "2020-05-01",
    "diasDuracionPuntaje": 15
}'

# Peticion invalida. Formato de fecha incorrecto
curl_req post 400 "vencimientosPuntos" '{
    "fechaFinValidez": "01-01-2020",
    "fechaInicioValidez": "2020-05-01",
    "diasDuracionPuntaje": 15
}'
