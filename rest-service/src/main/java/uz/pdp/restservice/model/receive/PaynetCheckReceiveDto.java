package uz.pdp.restservice.model.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JacksonXmlRootElement(localName = "paynet")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = PaynetCheckReceiveDto.class)
public class PaynetCheckReceiveDto extends DefaultCheckReceiveDto{

    @JacksonXmlProperty(localName = "check")
    private Content content;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    private class Content{

        @JacksonXmlProperty(localName = "amount")
        private BigDecimal amount;

        @JacksonXmlProperty(localName = "account")
        private String account;

        @JacksonXmlProperty(localName = "merchant_id")
        private long merchantId;

    }
}

/*
* <paynet>
*   <check>
        <amount>1000</amount>
        <account>949418545</account>
    </check>
* </paynet>
* */