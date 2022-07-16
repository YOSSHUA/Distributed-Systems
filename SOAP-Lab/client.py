from suds.client import Client

# suds es para SOAP, requests es para rest

client = Client("http://localhost:8000/?wsdl")

result = client.service.simulated_annealing("Hi", 3)
print("Result simulated_annealing: ", result)

result = client.service.genetic_algorithm("Hi", 3, True)
print("Result genetic_algorithm: ", result)

result = client.service.ant_colony_optimization()
print("Result ant_colony_optimization: ", result)

result = client.service.particle_swarm_optimization("Hi", 3)
print("Result particle_swarm_optimization: ", result)


