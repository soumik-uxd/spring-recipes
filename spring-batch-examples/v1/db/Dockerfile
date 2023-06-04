ARG TAG=12-alpine

FROM postgres:${TAG}

LABEL version="1.0"
LABEL description="A postgres image with a sample database preloaded"
LABEL maintainer="Soumik Das -- soumik.uxd@gmail.com"
LABEL source_url=""

# Create dirs and log files
RUN touch /var/log/db-load.log

# Load init sql
COPY initDB.sql /home/data/

# Load init script
COPY initDB.sh /docker-entrypoint-initdb.d/

# Provide necessary permissions
RUN chmod a+x /docker-entrypoint-initdb.d/initDB.sh && \
    chmod a+rwx /home/data && \
    chmod a+w /home/data/initDB.sql && \
    chmod a+w /var/log/db-load.log

# Superuser password for the postgresql. Uncomment only for local use
#ENV POSTGRES_PASSWORD=adm1n