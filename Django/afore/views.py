from django.shortcuts import render
from django.http import HttpResponse
from afore.models import Trabajador
from django.shortcuts import render
from django.http import Http404


def index(request):
    """
    trabajadores = Trabajador.objects.all()
    result = "Trabajadores registrados<br/>"
    for t in trabajadores:
        result += str(t) + "<br/>"
    return HttpResponse(result)
    """    
    context = {}
    return render(request, 'afore/index.html', context) 


def agrega_trabajador(request):
    nombre = request.POST.get("nombre")
    edad_act = request.POST.get("edad_act")
    edad_ret = request.POST.get("edad_ret") 
    saldo = request.POST.get("saldo")
    ahorro = request.POST.get("ahorro") 
    genero = request.POST.get("gen")
    if genero == "Masculino":
        genero = 0
    elif genero == "Femenino":
        genero = 1
    else:
        genero = 2
    trabajador = Trabajador(nombre=nombre, edad_act=int(edad_act), edad_ret=int(edad_ret), saldo=float(saldo), ahorro=float(ahorro), genero=genero)
    try:
        trabajador.save()
        print("Trabajador con id %s agregado con éxito" % trabajador.id)
    except Exception as ex:
        print(str(ex))
        trabajador = None

    return render(request, 'afore/agrega_trabajador.html',{'trabajador': trabajador})


def detalles(request, trabajador_id):
    try:
        trabajador = Trabajador.objects.get(pk=trabajador_id)

    except Trabajador.DoesNotExist:
        raise Http404()
    return render(request, 'afore/detalles.html',{'trabajador': trabajador})


def listado(request, criterio):
    if criterio in ["nombre", "edad", "saldo", "ahorro"]:
        trabajadores = Trabajador.objects.order_by(criterio)
        context = {
            'trabajadores': trabajadores,
        }
        return render(request, 'afore/listado.html', context)    
    else:
        raise Http404()





