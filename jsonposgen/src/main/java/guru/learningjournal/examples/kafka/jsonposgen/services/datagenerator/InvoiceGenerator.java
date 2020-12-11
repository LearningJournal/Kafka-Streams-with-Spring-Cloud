/*
 * Copyright (c) 2018. Prashant Kumar Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package guru.learningjournal.examples.kafka.jsonposgen.services.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.learningjournal.examples.kafka.jsonposgen.model.LineItem;
import guru.learningjournal.examples.kafka.jsonposgen.model.PosInvoice;
import guru.learningjournal.examples.kafka.jsonposgen.model.DeliveryAddress;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class InvoiceGenerator {
    private final Random invoiceIndex;
    private final Random invoiceNumber;
    private final Random numberOfItems;
    private final PosInvoice[] invoices;
    @Autowired
    AddressGenerator addressGenerator;
    @Autowired
    ProductGenerator productGenerator;

    public InvoiceGenerator() {
        String DATAFILE = "src/main/resources/data/Invoice.json";
        invoiceIndex = new Random();
        invoiceNumber = new Random();
        numberOfItems = new Random();
        ObjectMapper mapper;
        mapper = new ObjectMapper();
        try {
            invoices = mapper.readValue(new File(DATAFILE), PosInvoice[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndex() {
        return invoiceIndex.nextInt(100);
    }

    private int getNewInvoiceNumber() {
        return invoiceNumber.nextInt(99999999) + 99999;
    }

    private int getNoOfItems() {
        return numberOfItems.nextInt(4) + 1;
    }

    public PosInvoice getNextInvoice() {
        PosInvoice invoice = invoices[getIndex()];
        invoice.setInvoiceNumber(Integer.toString(getNewInvoiceNumber()));
        invoice.setCreatedTime(System.currentTimeMillis());
        if ("HOME-DELIVERY".equalsIgnoreCase(invoice.getDeliveryType())) {
            DeliveryAddress deliveryAddress = addressGenerator.getNextAddress();
            invoice.setDeliveryAddress(deliveryAddress);
        }
        int itemCount = getNoOfItems();
        double totalAmount = 0.0;
        List<LineItem> items = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            LineItem item = productGenerator.getNextProduct();
            totalAmount = totalAmount + item.getTotalValue();
            items.add(item);
        }
        invoice.setNumberOfItems(itemCount);
        invoice.setInvoiceLineItems(items);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxableAmount(totalAmount);
        invoice.setCGST(totalAmount * 0.025);
        invoice.setSGST(totalAmount * 0.025);
        invoice.setCESS(totalAmount * 0.00125);
        log.debug(invoice);
        return invoice;
    }
}
