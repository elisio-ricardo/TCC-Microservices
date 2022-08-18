package com.example.kafkaconsumer.consumer;


import com.example.kafkaconsumer.dto.BookDTO;
import com.example.kafkaconsumer.dto.MessageConsumerDTO;
import com.example.kafkaconsumer.dto.enums.MethodRequested;
import com.example.kafkaconsumer.dto.enums.ServiceRequested;
import com.example.kafkaconsumer.model.Book;
import com.example.kafkaconsumer.model.Cambio;
import com.example.kafkaconsumer.model.Transaction;
import com.example.kafkaconsumer.proxy.BookProxy;
import com.example.kafkaconsumer.proxy.CambioProxy;
import com.example.kafkaconsumer.services.TransactionService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class KafkaMessageConsumer {

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;

    @Value(value = "${topic.name}")
    private String topic;

    @Autowired
    private BookProxy bookProxy;

    @Autowired
    private CambioProxy cambioProxy;

    @Autowired
    private TransactionService transactionService;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTopicLogs(ConsumerRecord<String, MessageConsumerDTO> record){
        log.info("Received message: "+record.value());
        processMessage(record.value());
    }

    private void processMessage(MessageConsumerDTO message){

        if(message.getService() == ServiceRequested.BOOK){
            processBookRequest(message);
        }else if(message.getService() == ServiceRequested.CAMBIO){
            processCambioRequest(message);
        }

    }

    private void processBookRequest(MessageConsumerDTO message){
        switch (message.getMethod()) {

            case GET_BOOK:
                //TODO
                break;

            //Book CRUD
            case FIND_BOOK_BY_ID:{
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    BookDTO byId = bookProxy.findById(1l);
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end - start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(), message.getMethod().toString());
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end - start));
            }
                break;

            case FIND_ALL_BOOK: {
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    List<BookDTO> all = bookProxy.findAll();
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;

            case CREATE_BOOK: {
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    BookDTO bookDTO = bookProxy.create(getMoockBook());
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                deleteBookMocks();

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;

            case UPDATE_BOOK: {

                for (int i = 0; i < message.getRepetitions(); i++) {
                    BookDTO bookDTO = bookProxy.create(getMoockBook());
                }
                List<Book> listMock = getListMock();


                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < listMock.size(); i++) {
                    listMock.get(i).setTitle("Mock update");
                    bookProxy.update(listMock.get(i).getId(), listMock.get(i));
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                deleteBookMocks();

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;

            case DELETE_BOOK:
            {
                for (int i = 0; i < message.getRepetitions(); i++) {
                    BookDTO bookDTO = bookProxy.create(getMoockBook());
                }
                List<Book> listMock = getListMock();
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < listMock.size(); i++) {
                    bookProxy.delete(listMock.get(i).getId());
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

    private void processCambioRequest(MessageConsumerDTO message){
        switch (message.getMethod()){
            case GET_CAMBIO:
                //TODO
                break;
            case FIND_CAMBIO_BY_ID:
            {
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    Cambio byId = cambioProxy.findById(1l);
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end - start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(), message.getMethod().toString());
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end - start));
            }
                break;
            case FIND_ALL_CAMBIO: {
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    List<Cambio> all = cambioProxy.findAll();
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end - start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(), message.getMethod().toString());

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISEGUNDOS", message, (end - start));
            }
                break;
            case CREATE_CAMBIO:
            {
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < message.getRepetitions(); i++) {
                    Cambio cambio = cambioProxy.create(getMoockCambio());
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                deleteCambioMoocks();

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;
            case UPDATE_CAMBIO: {

                for (int i = 0; i < message.getRepetitions(); i++) {
                    Cambio cambio = cambioProxy.create(getMoockCambio());
                }
                List<Cambio> listMock = getListCambioMock();


                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < listMock.size(); i++) {
                    listMock.get(i).setFrom("BRL");
                    cambioProxy.update(listMock.get(i).getId(), listMock.get(i));
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end - start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(), message.getMethod().toString());

                deleteCambioMoocks();

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end - start));
            }
                break;
            case DELETE_CAMBIO:
            {
                for (int i = 0; i < message.getRepetitions(); i++) {
                    Cambio cambio = cambioProxy.create(getMoockCambio());
                }
                List<Cambio> listMock = getListCambioMock();
                String initialTime = LocalDateTime.now().toString();
                Long start = System.currentTimeMillis();
                for (int i = 0; i < listMock.size(); i++) {
                    cambioProxy.delete(listMock.get(i).getId());
                }
                Long end = System.currentTimeMillis();
                String endTime = LocalDateTime.now().toString();
                Long timeLapsed = end-start;

                registerTransaction(initialTime,
                        endTime, timeLapsed.toString(), 0,
                        message.getRepetitions(),message.getMethod().toString());

                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} MILISSEGUNDOS", message.getMethod(), (end-start));
            }
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

    private void deleteCambioMoocks() {
        try{
            cambioProxy.deletemocks();
        }catch (Exception ex){
            log.info("Error "+ex.getMessage());
        }
    }

    private Cambio getMoockCambio() {
        BigDecimal conversionFactor = new BigDecimal(135.78);
        Cambio cambio = new Cambio("USD", "MCK", conversionFactor);
        return cambio;
    }

    private Book getMoockBook() {

        Date date = new Date();

        Book book = new Book("Autor mock","titulo mock", date, 100d, "USD", "environment");

        return book;

    }

    private void deleteBookMocks() {
        try{
            bookProxy.deletemocks();
        }catch (Exception ex){
            log.info("Error "+ex.getMessage());
        }
    }

    private List<Book> getListMock(){
        try{
            List<Book> mocks = bookProxy.getMocks();
            return mocks;
        }catch (Exception ex){
            log.info("Error "+ex.getMessage());
        }
        return null;
    }

    private List<Cambio> getListCambioMock() {
        try{
            List<Cambio> mocks = cambioProxy.getMocks();
            return mocks;
        }catch (Exception ex){
            log.info("Error "+ex.getMessage());
        }
        return null;
    }


    private void registerTransaction(String initialTime, String endTime, String timeLapsed, Integer level, Integer repetitions, String method) {
        timeLapsed = timeLapsed+"ms";

        Transaction transactionBuilder = Transaction.builder()
                .initialTime(initialTime)
                .endTime(endTime)
                .timeLapsed(timeLapsed.toString())
                .repetitions(repetitions)
                .method(method).build();

        transactionService.addTransaction(transactionBuilder);
    }



}
