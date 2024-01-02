package ru.skillbox.currency.exchange.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ValCurs")
public final class CurrencyXmlDto {
        private List<CurrencyDetailsXmlDto> valutes;
        public CurrencyXmlDto() {
        }
        @XmlElement(name = "Valute")
        public List<CurrencyDetailsXmlDto> getValutes() {
                return valutes;
        }
        public void setValutes(List<CurrencyDetailsXmlDto> valutes) {
                this.valutes = valutes;
        }
}

