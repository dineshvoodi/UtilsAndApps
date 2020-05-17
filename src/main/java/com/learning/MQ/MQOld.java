package com.learning.MQ;


import com.ibm.jms.JMSTextMessage;
import com.ibm.mq.jms.*;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.DatatypeConverter;

public class MQOld {

    public static void main(String[] args) throws JMSException {

    }

    public static void listen() throws JMSException {
        MQQueueConnectionFactory mqQueueConnectionFactory =  new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName("");
        mqQueueConnectionFactory.setPort(Integer.valueOf(""));
        mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        mqQueueConnectionFactory.setQueueManager("");
        mqQueueConnectionFactory.setChannel("");
        //mqQueueConnectionFactory.setSSLCipherSuite(""); // Set only for SSL connections

        MQQueueConnection mqQueueConnection = (MQQueueConnection) mqQueueConnectionFactory.createConnection("", null);
        MQQueueSession mqQueueSession = (MQQueueSession) mqQueueConnection.createQueueSession(true, Session.SESSION_TRANSACTED);
        MQQueue mqQueue = (MQQueue) mqQueueSession.createQueue("");
        mqQueue.setBooleanProperty(WMQConstants.WMQ_MQMD_WRITE_ENABLED, true); // To enable property overwrite

        MQQueueReceiver mqQueueReceiver = (MQQueueReceiver) mqQueueSession.createConsumer(mqQueue);
        JMSTextMessage jmsTextMessage = (JMSTextMessage) mqQueueReceiver.receive();
        String text = jmsTextMessage.getText();
    }

    public static void push() throws JMSException {
        MQQueueConnectionFactory mqQueueConnectionFactory =  new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName("");
        mqQueueConnectionFactory.setPort(Integer.valueOf(""));
        mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        mqQueueConnectionFactory.setQueueManager("");
        mqQueueConnectionFactory.setChannel("");
        //mqQueueConnectionFactory.setSSLCipherSuite(""); // Set only for SSL connections

        MQQueueConnection mqQueueConnection = (MQQueueConnection) mqQueueConnectionFactory.createConnection("", null);
        MQQueueSession mqQueueSession = (MQQueueSession) mqQueueConnection.createQueueSession(true, Session.SESSION_TRANSACTED);
        MQQueue mqQueue = (MQQueue) mqQueueSession.createQueue("");
        MQQueueSender mqQueueSender = (MQQueueSender) mqQueueSession.createSender(mqQueue);

        TextMessage textMessage = mqQueueSession.createTextMessage("ABC");
        byte[] messageId = DatatypeConverter.parseHexBinary(""); // Hexa decimal Message ID
        textMessage.setObjectProperty(WMQConstants.JMS_IBM_MQMD_MSGID, messageId); // To set message ID

        mqQueueSender.send(mqQueue, textMessage);
    }

    public void setSSL() {
        System.setProperty("javax.net.ssl.trustStore", "trustStorePath");
        System.setProperty("javax.net.ssl.keyStore", "keyStorePath");
        System.setProperty("javax.net.ssl.trustStorePassword", "trustStorePassword");
        System.setProperty("javax.net.ssl.keyStorePassword", "keyStorePassword");
        System.setProperty("javax.net.ssl.keyStoreType", "jks");
    }


}
