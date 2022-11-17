#!/bin/bash
set -eu

# Create the DB and connect to it
psql -v ON_ERROR_STOP=1 -U postgres -c "DROP DATABASE IF EXISTS dvdrental;"
psql -v ON_ERROR_STOP=1 -U postgres -c "CREATE DATABASE dvdrental;"

# Load data
pg_restore -v -e -U postgres -d dvdrental /home/data/dvdrental.tar