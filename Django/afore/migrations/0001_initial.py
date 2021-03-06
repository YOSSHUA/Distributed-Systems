# Generated by Django 4.0.4 on 2022-05-03 02:41

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Trabajador',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nombre', models.CharField(max_length=200)),
                ('edad_act', models.IntegerField(default=18)),
                ('edad_ret', models.IntegerField(default=56)),
                ('saldo', models.FloatField(default=0.0)),
                ('ahorro', models.FloatField(default=0.0)),
                ('genero', models.IntegerField(choices=[(0, 'Masculino'), (1, 'Femenino'), (2, 'Otro')], default=2)),
            ],
        ),
    ]
