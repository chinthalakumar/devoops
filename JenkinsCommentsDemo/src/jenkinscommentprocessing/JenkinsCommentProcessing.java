/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenkinscommentprocessing;

import java.io.IOException;

/**
 *
 * @author shyams
 */
public class JenkinsCommentProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        PipelineConsumer con=null;
				
				con=new PipelineConsumer();
		
		con.createProperties(args[0],args[1],args[2]);
    }
    
}
