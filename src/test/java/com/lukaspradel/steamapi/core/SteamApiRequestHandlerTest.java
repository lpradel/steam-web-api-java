package com.lukaspradel.steamapi.core;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SteamApiRequestHandlerTest {

    @Test
    public void testGetProtocolIsHttps() {
        SteamApiRequestHandler requestHandler = new SteamApiRequestHandler(true, "https://api.steampowered.com", "key") {};
        String protocol = requestHandler.getProtocol();
        assertEquals(protocol, "https");
    }

    @Test
    public void testGetProtocolIsHttp() {
        SteamApiRequestHandler requestHandler = new SteamApiRequestHandler(false, "https://api.steampowered.com", "key") {};
        String protocol = requestHandler.getProtocol();
        assertEquals(protocol, "http");
    }
}