FROM alpine:latest

LABEL maintainer="Aditya"

RUN apk update && apk add --no-cache curl

WORKDIR /app

RUN echo "Hello Aditya! This is my custom Alpine Docker image." > index.html

CMD ["cat", "index.html"]
