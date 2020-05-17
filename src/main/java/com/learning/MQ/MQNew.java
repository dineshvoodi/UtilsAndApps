package com.learning.MQ;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryCustomizer;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.net.ssl.SSLContext;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "ibm.mq")
public class MQNew {

    MQConfigurationProperties mqConfigurationProperties;

    @Autowired
    JmsTemplate jmsTemplate;

    public void consume() {
        TextMessage textMessage = (TextMessage) jmsTemplate.receive("");
    }

    public void send() {
        jmsTemplate.send(messageCreater -> {
            TextMessage textMessage = messageCreater.createTextMessage();
            byte[] messageId = DatatypeConverter.parseHexBinary(""); // Hexa decimal Message ID
            textMessage.setObjectProperty(WMQConstants.JMS_IBM_MQMD_MSGID, messageId); // To set message ID

            return textMessage;
        });
    }

    @Autowired
    public JmsTemplate getJmsTemplate(@Qualifier("getConnectionFactory") ConnectionFactory connectionFactory) throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        MQQueue mqQueue = new MQQueue();
        mqQueue.setBooleanProperty(WMQConstants.WMQ_MQMD_WRITE_ENABLED, true); // To enable property overwrite

        jmsTemplate.setDefaultDestination(mqQueue);
        return jmsTemplate;
    }

    @Autowired
    public ConnectionFactory getConnectionFactory() {

        boolean isEncrypted = false;
        MQConnectionFactoryFactory mqConnectionFactoryFactory = new MQConnectionFactoryFactory(mqConfigurationProperties, null);

        if (isEncrypted) {
            List<MQConnectionFactoryCustomizer> mqConnectionFactoryCustomizers = new ArrayList<>();

            MQConnectionFactoryCustomizer mqConnectionFactoryCustomizer = mqConnectionFactory ->
            {
                try {
                    mqConnectionFactory.setSSLSocketFactory(sslContext());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            };

            mqConnectionFactoryCustomizers.add(mqConnectionFactoryCustomizer);
            mqConnectionFactoryFactory = new MQConnectionFactoryFactory(mqConfigurationProperties, mqConnectionFactoryCustomizers);

        }

        return mqConnectionFactoryFactory.createConnectionFactory(MQConnectionFactory.class);
    }

    public SSLContext sslContext() throws NoSuchAlgorithmException {

        // Statements to load key store files to SSL context

        return SSLContext.getInstance("TLSv1.2");

    }

}
