from email.mime import application
from pickle import UNICODE
from grpc import server
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
from spyne import Application, ServiceBase, rpc, Integer, Unicode, String, Iterable, Float, Boolean
import random

class Service(ServiceBase):

    @rpc(Unicode, Integer, _returns=[Integer, Float])
    def simulated_annealing(ctx, cad, n):
        return len(cad), n+.5
    
    @rpc(Unicode, Integer, Boolean, _returns=Float)
    def genetic_algorithm(ctx, cad, n, flag):
        if flag:
            return len(cad)+n*0.33
        return n*0.33
    
    @rpc(_returns=Iterable(Unicode))
    def ant_colony_optimization(ctx):
        n = int(random.random()*10)
        ans = [str(i) for i in range(n)]
        return ans
    
    @rpc(Unicode, Integer, _returns=(Integer, Float))
    def particle_swarm_optimization(ctx, cad, n):
        return (len(cad), n*.5)
    

# Despliegue del servicio web
application = Application([Service], 
                        "localhost",
                        in_protocol=Soap11(),
                        out_protocol=Soap11())

wsgi_app = WsgiApplication(application, 8000)

server = make_server("127.0.0.1", 8000, wsgi_app)

server.serve_forever()