
package com.trivadis.blockchain.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jsonrpc",
        "method",
        "params",
        "id"
})
public class ChainDto {

    @JsonProperty("jsonrpc")
    private String jsonrpc;
    @JsonProperty("method")
    private String method;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("id")
    private Integer id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("jsonrpc")
    public String getJsonrpc() {
        return jsonrpc;
    }

    @JsonProperty("jsonrpc")
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("params")
    public Params getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(Params params) {
        this.params = params;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
