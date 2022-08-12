from django.conf import settings
from django.db import models


# Create your models here.
class Book(models.Model):
  GENRES = (
    ('action', 'Action'),
    ('adventure', 'Adventure'),
    ('classics', 'Classics'),
    ('fantasy', 'Fantasy'),
  )
  STATUS = (
    ('active', 'Active'),
    ('inactive', 'Inactive'),
    ('hide', 'Hide'),
  )
  title = models.CharField(max_length=500)
  author = models.CharField(max_length=500)
  genre = models.CharField(max_length=100, choices=GENRES)
  height = models.IntegerField()
  publisher = models.CharField(max_length=500)
  status = models.CharField(max_length=25, choices=STATUS, default='active')
  created_at = models.DateTimeField(auto_now_add=True)
  updated_at = models.DateTimeField(auto_now=True)
  created_by = models.ForeignKey(
    settings.AUTH_USER_MODEL,
    on_delete=models.CASCADE,
    null=True
  )

  def __str__(self):
    return self.title + ' by ' + self.author
