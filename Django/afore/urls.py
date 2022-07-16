from django.urls import path
from . import views

app_name = 'afore'

urlpatterns = [
    path('', views.index, name='index'),
    path('<int:trabajador_id>/detalles/', views.detalles,name="detalles"),
    path('agrega_trabajador', views.agrega_trabajador, name = "agrega_trabajador"),
    path('<str:criterio>/listado/', views.listado,name="listado"),
]
