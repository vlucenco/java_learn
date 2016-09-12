package ru.vitali.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("109.185.136.163");
    assertEquals(geoIP.getCountryCode(), "MDA");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("109.185.136.xxx");
    assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
  }
}
