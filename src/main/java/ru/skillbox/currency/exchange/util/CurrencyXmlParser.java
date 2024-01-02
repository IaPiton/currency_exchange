package ru.skillbox.currency.exchange.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.dto.CurrencyDetailsXmlDto;
import ru.skillbox.currency.exchange.dto.CurrencyXmlDto;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import java.util.List;

@Component

public final class CurrencyXmlParser {
    @Value("${update.urlUpdate}")
    public String url;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private DataBaseUtil dataBaseUtil;
@PostConstruct
@Scheduled(fixedRateString = "${update.timeUpdate}")
    public void xmlClient() {
        XmlHttpClient client = new XmlHttpClient();
        String xml = client.getCurrencys(url);
        CurrencyXmlDto currencyXml = toObject(xml);
        List<CurrencyDetailsXmlDto> currencyDetailsXmls = currencyXml.getValutes();
        for (CurrencyDetailsXmlDto currencyDetails : currencyDetailsXmls) {
            Double value = Double.valueOf(currencyDetails.getValue().replace(",", "."));
            if (!currencyRepository.existsByName(currencyDetails.getName())) {
                dataBaseUtil.createCurrency(currencyDetails, value);
            } else {
                dataBaseUtil.updateCurrency(currencyDetails, value);
            }
        }
    }

    public static CurrencyXmlDto toObject(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyXmlDto.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (CurrencyXmlDto) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
