package com.cono.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.test.web.servlet.client.RestTestClient;

@AutoConfigureRestTestClient
public class EndToEndTest extends IntegrationTest {
  @Autowired protected RestTestClient client;
}
