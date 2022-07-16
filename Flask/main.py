from flask import Flask, request
import json

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
@app.route("/index", methods=["GET", "POST"])
def index():
    usuario = request.args.get("usuario")
    contrasena = request.args.get("contrasena")
    return "Ingresaste %s con contraseña %s" % (usuario, contrasena)

@app.route("/registro/<usuario>/<contrasena>", 
            methods=["PUT"])
def registro(usuario, contrasena):    
    return "Registraste usuario %s con contraseña %s" % (usuario, contrasena)

app.run(host="0.0.0.0", port=81)