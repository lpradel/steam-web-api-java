package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiServiceRequestBuilder;

/**
 * Skeleton for Steam Web API service parameters. Web API endpoints whose names
 * end with "Service" (e.g. "IPlayerService") require a specific service
 * parameter in the form of a single JSON blob for certain parameters. If the
 * service parameter is used, ALL request parameters must be included in the
 * JSON blob. Exceptions are "key" parameter and the "format" parameter.
 * <p>
 * Heirs of this class must be Jackson-annotated POJOs that are converted to
 * JSON by {@link AbstractSteamWebApiServiceRequestBuilder}.
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#Calling_Service_interfaces"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#Calling_Service_interfaces</a>
 * @author lpradel
 *
 */
public abstract class SteamWebApiServiceParameter {
}
