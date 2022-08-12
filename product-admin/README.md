# Product Administration

This is a small Django project to build an admin ui which mainly help create products

### Run locally

```bash
docker compose up -d
```

Load dummy data (Refer [**loaddata** docs](https://docs.djangoproject.com/en/4.1/ref/django-admin/#django-admin-loaddata))

```bash
docker compose exec -it web manage.py loaddata db.json
```

Database connection details:
* Host: `localhost`
* Port: `5432`
* Database name: `product`
* Username: `product`
* Password: `product`

Web UI connection details:
* Homepage: [http://localhost:8000](https://docs.djangoproject.com/en/4.1/ref/django-admin/#django-admin-loaddata)
* Admin page: [http://localhost:8000/admin]()
* Username: `product`
* Password: `product`

### Migration models

These are steps to make change models

* Change your models (in **models.py**).
* Run `python manage.py makemigrations` to create migrations for those changes
* Run `python manage.py migrate` to apply those changes to the database.