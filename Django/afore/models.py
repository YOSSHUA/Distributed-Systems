from django.db import models

class Trabajador(models.Model):
    nombre = models.CharField(max_length=200)
    edad_act = models.IntegerField(default=18)
    edad_ret = models.IntegerField(default=56)
    saldo = models.FloatField(default=0.00)
    ahorro = models.FloatField(default=0.00)
    
    MASCULINO = 0
    FEMENINO = 1
    OTRO = 2
    OPCIONES_GENERO = (
        (MASCULINO, 'Masculino'),
        (FEMENINO, 'Femenino'),
        (OTRO, 'Otro'),
    )

    genero = models.IntegerField(choices=OPCIONES_GENERO, default=2)

    def __str__(self) -> str:
        return self.nombre
    
    def calcular_pension(self):
        return self.nombre + " tendrá una pensión mensual de " + str(self.ahorro*(self.edad_ret-self.edad_act)*12 + self.saldo)

