
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

public class JavaFlagsLogger implements RequestHandler<Map<String,String>, String> {
  
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    void showParameters(Context context) {
    
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List arguments = runtimeMxBean.getInputArguments();
        context.getLogger().log("### JVM PARAMETERS ###");
        for (Object arg : arguments) {
            context.getLogger().log(gson.toJson(arg));
        }
        context.getLogger().log("### ------ ###");
        return;
    };
    
    public String handleRequest(Map<String,String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());
        showParameters(context);
        String response = new String("200 OK");
        return response;
    }
};