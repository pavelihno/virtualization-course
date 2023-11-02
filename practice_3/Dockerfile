FROM python:3.8-slim

LABEL author="savinov.p.a@edu.mirea.ru"

ENV text="Hello World"

# создание рабочей директории
WORKDIR /app

COPY app.py /app

EXPOSE 8080

VOLUME /data

RUN useradd -ms /bin/bash pasavinov
USER pasavinov

ONBUILD ADD . /onbuild

# Команда при запуске контейнера, если не определена другая
CMD ["python3", "app.py"]

# Команда при любом запуске контейнера
ENTRYPOINT ["echo", "123"]