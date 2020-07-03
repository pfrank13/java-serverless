package com.github.pfrank13;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Random;

import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.test.annotation.MicronautTest;

/**
 * @author pfrank
 */
@MicronautTest(environments = {"function","lambda","test"}) //Need to configure this explicitly cause it doesn't seem to suss out to add "function","lambda"
@Testcontainers
public class MySqlDialectFailureTest {

  private static MicronautLambdaHandler handler;
  private static Context lambdaContext = new MockLambdaContext();
  private static ObjectMapper objectMapper;
  private static Random random = new Random(System.currentTimeMillis());

  private String isbn; //This is stupid, I'm well aware but this is also a POC and a Saturday :)

  @BeforeEach
  public void setUp(){
    isbn = null;
  }

  @BeforeAll
  public static void setupSpec() {
    try {
      handler = new MicronautLambdaHandler();
      objectMapper = handler.getApplicationContext().getBean(ObjectMapper.class);

    } catch (ContainerInitializationException e) {
      e.printStackTrace();
    }
  }

  @AfterAll
  public static void cleanupSpec() {
    handler.getApplicationContext().close();
  }

  @Test
  void testSaveBook() throws JsonProcessingException {
    BookDtoRequest bookDtoRequest = new BookDtoRequest();
    bookDtoRequest.setName("Building Microservices" + random.nextLong());
    String json = objectMapper.writeValueAsString(bookDtoRequest);
    AwsProxyRequest request = new AwsProxyRequestBuilder("/books", HttpMethod.POST.toString())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .body(json)
        .build();
    AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
    Assertions.assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    BookDtoResponse bookDtoResponse = objectMapper.readValue(response.getBody(), BookDtoResponse.class);
    Assertions.assertEquals(bookDtoResponse.getName(), bookDtoRequest.getName());
    Assertions.assertNotNull(bookDtoResponse.getIsbn());
    isbn = bookDtoResponse.getIsbn();
  }

  @Test
  public void findBookByIsbn() throws JsonProcessingException{
    testSaveBook();
    AwsProxyRequest request = new AwsProxyRequestBuilder("/books/" + isbn, HttpMethod.GET.toString()).build();

    AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
    Assertions.assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    BookDtoResponse bookDtoResponse = objectMapper.readValue(response.getBody(), BookDtoResponse.class);
    Assertions.assertNotNull(bookDtoResponse.getName());
    Assertions.assertEquals(bookDtoResponse.getIsbn(), isbn);
  }
}
