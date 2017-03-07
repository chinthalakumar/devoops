package com.example.newt;

import java.io.IOException;

public class PipelineMain {
	

	
	
	public static void main(String[] args) throws IOException
	{
		PipelineConsumer con=null;
				
				con=new PipelineConsumer();
		
		con.createProperties(args[0],args[1],args[2]);
		
	}

}
