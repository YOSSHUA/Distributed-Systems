from suds.client import Client

# suds es para SOAP, requests es para rest

client = Client("http://localhost:8000/?wsdl")

result = client.service.suma(3, 4)
print(f"La suma de 3 y 4 es {result}")

result = client.service.n_strings(3)
print(f"{3} strings: ")
print(result)
