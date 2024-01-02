package ru.skillbox.currency.exchange.dto;

import javax.xml.bind.annotation.XmlElement;

public final class CurrencyDetailsXmlDto {
    private  String name;
    private  Long nominal;
    private  String value;
    private  Long isoNumCode;
    private  String charCode;
   @XmlElement(name = "Name")
    public String getName() {
        return name;
    }
    @XmlElement(name = "Nominal")
    public Long getNominal() {
        return nominal;
    }
    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }
    @XmlElement(name = "NumCode")
    public Long getIsoNumCode() {
        return isoNumCode;
    }
    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return charCode;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setIsoNumCode(Long isoNumCode) {
        this.isoNumCode = isoNumCode;
    }
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

}
