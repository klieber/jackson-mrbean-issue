package com.klieber.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ObjectMapperTest {

  private static final long TIME_IN_MILLIS = 1463620778163L;

  private static final String JSON_STRING = "{ \"name\" : \"Joe\", \"expiration\" : " + TIME_IN_MILLIS + ", \"amount\" : 15 }";
  private static final Map<String, Object> MOCK_MAP;
  static {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", "Joe");
    map.put("expiration", TIME_IN_MILLIS);
    map.put("amount", 15);
    MOCK_MAP = Collections.unmodifiableMap(map);
  }

  @Test
  public void testConvertValueWithoutMrBean() {
    ObjectMapper mapper = new ObjectMapper();
    checkResult(mapper.convertValue(MOCK_MAP, MockObject.class));
  }

  @Test
  public void testConvertValueWithMrBean() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModules(new MrBeanModule());
    checkResult(mapper.convertValue(MOCK_MAP, MockObject.class));
  }

  @Test
  public void testReadValueWithoutMrBean() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    checkResult(mapper.readValue(JSON_STRING, MockObject.class));
  }

  @Test
  public void testReadValueWithMrBean() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModules(new MrBeanModule());
    checkResult(mapper.readValue(JSON_STRING, MockObject.class));
  }

  private void checkResult(MockObject mockObject) {
    assertEquals("Joe", mockObject.getName());
    assertEquals(TIME_IN_MILLIS, mockObject.getExpiration().getTimeInMillis());
    assertEquals(15, mockObject.getAmount());
  }

  static class MockObject {

    private String name;
    private Calendar expiration;
    private int amount;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Calendar getExpiration() {
      return expiration;
    }

    public void setExpiration(Calendar expiration) {
      this.expiration = expiration;
    }

    public int getAmount() {
      return amount;
    }

    public void setAmount(int amount) {
      this.amount = amount;
    }
  }
}

