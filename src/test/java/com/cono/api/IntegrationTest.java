package com.cono.api;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.postgresql.PostgreSQLContainer;

public abstract class IntegrationTest {
  @ServiceConnection static PostgreSQLContainer db = new PostgreSQLContainer("postgres:18");

  static {
    db.start();
  }

  @Autowired Flyway flyway;

  @BeforeEach
  void cleanUp() {
    flyway.clean();
    flyway.migrate();
  }
}
