from django.shortcuts import render
from django.http import HttpResponse
from myfirstapp.models import Estudiante, Carrera
from django.template import loader
from django.shortcuts import render
from django.http import Http404
from django.shortcuts import get_object_or_404

# Create your views here.


def index(request):
    """
    estudiantes = Estudiante.objects.order_by("nombre")
    lista = ""
    for e in estudiantes:
        lista += str(e) + ", "
    return HttpResponse(lista)
    """
    """
    estudiantes = Estudiante.objects.order_by('nombre')
    template = loader.get_template('myfirstapp/index.html')
    context = {
    'estudiantes': estudiantes,
    }
    return HttpResponse(template.render(context, request))
    """
    
    """
    trabajadores = Trabajador.objects.order_by('nombre')
    context = {
        'trabajadores': trabajadores,
    }
    return render(request, 'myfirstapp/index.html', context)
    """    
    estudiantes = Estudiante.objects.order_by('nombre')
    context = {
        'estudiantes': estudiantes,
    }
    return render(request, 'myfirstapp/index.html', context)    


def detalles(request, estudiante_id):
    """
    return HttpResponse("Detalles del estudiante %s." % estudiante_id)
    """
    """
    estudiante = Estudiante.objects.get(pk=estudiante_id)    
    return render(request, 'myfirstapp/detalles.html', {'estudiante': estudiante})
    """
    """
    try:
        estudiante = Estudiante.objects.get(pk=estudiante_id)
    except Estudiante.DoesNotExist:
        raise Http404()
        return render(request, 'myfirstapp/detalles.html',{'estudiante': estudiante})
    """
    """
        Forma más sencilla de hacer el 404
    """    
    estudiante = get_object_or_404(Estudiante, pk=estudiante_id)
    return render(request, 'myfirstapp/detalles.html', {'estudiante': estudiante})
    


def carreras(request, estudiante_id):
    return HttpResponse("Carreras del estudiante %s." % estudiante_id)


def agrega_carrera(request, estudiante_id, tipo, nombre):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.carrera_set.create(tipo=int(tipo), nombre=nombre)
    return HttpResponse("Carrera agregada al estudiante %s" % estudiante_id)


def agrega_estudiante(request, nombre, apellidos, edad, foraneo, promedio):
    foraneo = "True".lower() == "true"
    estudiante = Estudiante(nombre=nombre,
                            apellidos=apellidos,
                            edad=int(edad),
                            foraneo=foraneo,
                            promedio=float(promedio))
    estudiante.save()
    return HttpResponse("Estudiante %s agregado exitósamente" % estudiante.id)


def borra_estudiante(request, estudiante_id):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.delete()
    return HttpResponse("Estudiante %s borrado exitósamente" % estudiante_id)


def edita_estudiante(request, estudiante_id, promedio):
    estudiante = Estudiante.objects.get(pk=estudiante_id)
    estudiante.promedio = float(promedio)
    estudiante.save()
    return HttpResponse("El promedio del estudiante %s se ha actualizado exitósamente" % estudiante.id)


def agrega_estudiante_forma(request):
    nombre = request.POST.get("nombre")
    apellidos = request.POST.get("apellidos")
    edad = int(request.POST.get("edad"))
    if request.POST.get("foraneo") == None:
        foraneo = False
    else:
        foraneo = request.POST.get("foraneo").lower() == "true"
    promedio = float(request.POST.get("promedio"))
    estudiante = Estudiante(nombre=nombre,
                            apellidos=apellidos,
                            edad=int(edad),
                            foraneo=foraneo,
                            promedio=float(promedio))
    estudiante.save()
    return HttpResponse("Estudiante %s agregado exitósamente desde la forma" % estudiante.id)


def calcula_pension(request, nombre, edad_act, edad_ret, saldo, ahorro, genero):
    return HttpResponse("Done")
