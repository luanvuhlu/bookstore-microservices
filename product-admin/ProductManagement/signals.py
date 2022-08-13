import json
from django.conf import settings
from django.forms.models import model_to_dict
from django.db.models.signals import post_save
from django.dispatch import receiver
from kafka import KafkaProducer
from .models import Book


producer = KafkaProducer(
  bootstrap_servers=settings.KAFKA_SERVERS,
  retries=5,
)


@receiver(post_save, sender=Book)
def publish_save_event(sender, instance, **kwargs):
  print(type(instance))
  producer.send(
    topic=settings.BOOK_EVENT_TOPIC,
    key=str.encode('{}-{}'.format(instance.id, instance.updated_at)),
    value=json.dumps(model_to_dict(instance)).encode('utf-8')
  )
