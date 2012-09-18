package com.sample;

import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Activation;

import com.mstar.meds.base.util.XmlHelper;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
    	Date day1=new Date();

        // load up the knowledge base
        KnowledgeBase kbase;
		try {
			kbase = readKnowledgeBase();
	        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	        KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            Message message = new Message();
            
            
	    	for(int i=1;i<1001;i++){
	    		System.out.println("index: "+ i);
	           // go !
	            message.setMessage("Hello World");
	            message.setStatus(Message.HELLO);
	            ksession.insert(message);
	            Customer cus1 = new Customer();
	    		cus1.setName("张三");
	    		Customer cus2 = new Customer();
	    		cus2.setName("李四");
	    		Customer cus3 = new Customer();
	    		cus3.setName("王二");
	    		Customer cus4 = new Customer();
	    		cus4.setName("李小龙");
	    		ksession.insert(cus1);
	    		ksession.insert(cus2);
	    		ksession.insert(cus3);
	    		ksession.insert(cus4);
	            ksession.fireAllRules();
	        }
	    	ksession.dispose();
            logger.close();
    	} catch (Throwable t) {
            t.printStackTrace();
        }
    	long cost = new Date().getTime()-day1.getTime();
    	System.out.println("cost time: "+cost);
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
