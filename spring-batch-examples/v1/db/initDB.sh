#!/bin/bash
set -eu

# Create the DB and connect to it
psql -v ON_ERROR_STOP=1 -U postgres -c "DROP DATABASE IF EXISTS springbatchdb;"
psql -v ON_ERROR_STOP=1 -U postgres -c "CREATE DATABASE springbatchdb;"
psql -v ON_ERROR_STOP=1 -U postgres -c "\c springbatchdb;"
psql -v ON_ERROR_STOP=1 -U postgres -d springbatchdb -f /home/data/initDB.sql
