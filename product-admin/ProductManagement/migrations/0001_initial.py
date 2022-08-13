# Generated by Django 4.1 on 2022-08-12 17:45

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Book',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=500)),
                ('author', models.CharField(max_length=500)),
                ('genre', models.CharField(choices=[('action', 'Action'), ('adventure', 'Adventure'), ('classics', 'Classics'), ('fantasy', 'Fantasy')], max_length=100)),
                ('height', models.IntegerField()),
                ('publisher', models.CharField(max_length=500)),
            ],
        ),
    ]