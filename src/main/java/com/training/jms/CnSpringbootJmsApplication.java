package com.training.jms;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyAcceptorFactory;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication//(exclude = ArtemisAutoConfiguration.class)
@EnableConfigurationProperties(ArtemisProperties.class)
public class CnSpringbootJmsApplication {

    public static void main(String[] args) throws Exception {

        // Setting up an embedded ActiveMQ server

        /*Configuration config = new ConfigurationImpl();
        HashSet<TransportConfiguration> transports = new HashSet<TransportConfiguration>();

        transports.add(new TransportConfiguration(NettyAcceptorFactory.class.getName()));
        transports.add(new TransportConfiguration(InVMAcceptorFactory.class.getName()));

        config.setAcceptorConfigurations(transports);*/

        Configuration config = new ConfigurationImpl();
        config.setPersistenceEnabled(false);
        config.setSecurityEnabled(false);
        config.setJournalDirectory("target/data/journal");
        config.getAcceptorConfigurations().add(new TransportConfiguration(NettyAcceptorFactory.class.getName()));

        ActiveMQServers.newActiveMQServer(config);


        /*ActiveMQServer server = new ActiveMQServerImpl(config);
        server.start();
*/
        SpringApplication.run(CnSpringbootJmsApplication.class, args);
    }

}
