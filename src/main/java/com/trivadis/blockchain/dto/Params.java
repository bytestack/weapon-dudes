
package com.trivadis.blockchain.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "chaincodeID",
        "CtorMsg"
})
public class Params {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("chaincodeID")
    private ChaincodeID chaincodeID;
    @JsonProperty("CtorMsg")
    private CtorMsg ctorMsg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("chaincodeID")
    public ChaincodeID getChaincodeID() {
        return chaincodeID;
    }

    @JsonProperty("chaincodeID")
    public void setChaincodeID(ChaincodeID chaincodeID) {
        this.chaincodeID = chaincodeID;
    }

    @JsonProperty("CtorMsg")
    public CtorMsg getCtorMsg() {
        return ctorMsg;
    }

    @JsonProperty("CtorMsg")
    public void setCtorMsg(CtorMsg ctorMsg) {
        this.ctorMsg = ctorMsg;
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
