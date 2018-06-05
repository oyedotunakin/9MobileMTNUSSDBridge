/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jodao.etisalattextng.ussd;

import com.jodao.etisalattextng.wcs.soap.SendUssd;
import javax.xml.ws.Endpoint;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author akin.oyedotun
 */
class AppServer {

    public void start() throws Exception {
//        server = new Server();
//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(8090);
//        server.setConnectors(new Connector[]{connector});

//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//
//        Server jettyServer = new Server(9000);//please read this from config
//        jettyServer.setHandler(context);
//
//        ServletHolder jerseyServlet = context.addServlet(
//                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
//        jerseyServlet.setInitParameter(
//                "jersey.config.server.provider.classnames",
//                SendUssd.class.getCanonicalName());
        Endpoint.publish("http://localhost:9000/9Mobile/SendUssdService/services/SendUssd", new SendUssd());

//        try {
//            jettyServer.start();
//            jettyServer.join();
//        } catch (Exception e) {
//            jettyServer.stop();
//            jettyServer.destroy();
//        }

    }

}
