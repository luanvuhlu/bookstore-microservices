from django.contrib import admin
from .models import Book


# Register your models here.
@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
  exclude = ('created_by', 'created_at', 'updated_at')
  date_hierarchy = 'updated_at'
  list_display = ('title', 'author', 'publisher', 'status', 'created_at', 'updated_at')
  list_filter = ('status', 'genre', )