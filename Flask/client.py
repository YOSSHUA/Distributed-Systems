import requests

result = requests.get("http://127.0.0.1:81/",
                    params={"usuario" : "Juana", 
                    "contrasena"  : "123"})

print(result.text)

result = requests.get("http://127.0.0.1:81/",
                    params={"usuario" : "Pedro", 
                    "contrasena"  : "1234"})

print(result.text)