FROM liquibase/liquibase:4.21

COPY migrations/config /liquibase/migrations/
COPY migrations/docker/custom-entrypoint.sh /liquibase/custom-entrypoint.sh

ENTRYPOINT ["sh", "liquibase/custom-entrypoint.sh"]