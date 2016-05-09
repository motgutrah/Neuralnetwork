package brain;

public class Main {
	public static void main(String[] args)
	{
		double alpha = .3;//alpha constant
		double yd = 0;//expected value
		double error = 0;
		double etotal = 0;
		boolean done = false;//used to find out when we will stop
		Neuron input1 = new Neuron(true);//input layer 1st node
		Neuron input2 = new Neuron(true);//input layer 2nd node
		Neuron hidden3 = new Neuron(false);//hidden layer 1 1st node
		Neuron hidden4 = new Neuron(false);//hidden layer 1 2nd node
		Neuron hidden5 = new Neuron(false);//hidden layer 2 1st node
		Neuron hidden6 = new Neuron(false);//hidden layer 2 2nd node
		Neuron output7 = new Neuron(false);//output layer node
		//Connecting all the neurons (setting the weights of the connection
		hidden3.setConnected(input1, 0, getNum());//w13
		hidden3.setConnected(input2, 1, getNum());//w23
		hidden4.setConnected(input1, 0, getNum());//w14
		hidden4.setConnected(input2, 1, getNum());//w24
		hidden5.setConnected(hidden3, 0, getNum());//w35
		hidden5.setConnected(hidden4, 1, getNum());//w45
		hidden6.setConnected(hidden3, 0, getNum());//w36
		hidden6.setConnected(hidden4, 1, getNum());//w46
		output7.setConnected(hidden5, 0, getNum());//w57
		output7.setConnected(hidden6, 1, getNum());//w67
		System.out.println("Starting Weights");
		System.out.println("this is weight w13 " + hidden3.getConnectedWeight(0));
		System.out.println("this is weight w23 " + hidden3.getConnectedWeight(1));
		System.out.println("this is weight w14 " + hidden4.getConnectedWeight(0));
		System.out.println("this is weight w24 " + hidden4.getConnectedWeight(1));
		System.out.println("this is weight w35 " + hidden5.getConnectedWeight(0));
		System.out.println("this is weight w45 " + hidden5.getConnectedWeight(1));
		System.out.println("this is weight w36 " + hidden6.getConnectedWeight(0));
		System.out.println("this is weight w46 " + hidden6.getConnectedWeight(1));
		System.out.println("this is weight w57 " + output7.getConnectedWeight(0));
		System.out.println("this is weight w67 " + output7.getConnectedWeight(1) + "\n");

		System.out.println("Starting thresholds");
		System.out.println("this is threshold at 3 " + hidden3.getThreshold());
		System.out.println("this is threshold at 4 " + hidden4.getThreshold());
		System.out.println("this is threshold at 5 " + hidden5.getThreshold());
		System.out.println("this is threshold at 6 " + hidden6.getThreshold());
		System.out.println("this is threshold at 7 " + output7.getThreshold() + "\n");
		
		
		int i = 0;
		while (done == false)//while loop to go until desired error is met
		{
			//setting the input values and desired y 
			for(int j = 0; j<4;j++ )
			{
			if(j == 0)
			{
				input1.setYVal(1);
				input2.setYVal(1);
				yd = 0;
				if(i % 300 == 0)
				{
					System.out.println("For Inputs x1 = 1 x2 = 1");
				}
			}
			else if(j == 1)
			{
				input1.setYVal(1);
				input2.setYVal(0);
				yd = 1;
				if(i % 300 == 0)
				{
					System.out.println("For Inputs x1 = 1 x2 = 0");
				}
			}

			else if(j == 2)
			{
				input1.setYVal(0);
				input2.setYVal(1);
				yd = 1;
				if(i % 300 == 0)
				{
					System.out.println("For Inputs x1 = 0 x2 = 1");
				}
			}
			else if( j == 3)
			{
				input1.setYVal(0);
				input2.setYVal(0);
				yd = 0;
				if(i % 300 == 0)
				{
					System.out.println("For Inputs x1 = 0 x2 = 0");
				}
			}
	
			
			//calculating the y value
			hidden3.calculateYVal();
			hidden4.calculateYVal();
			hidden5.calculateYVal();
			hidden6.calculateYVal();
			output7.calculateYVal();
			//storing the y values
			double x1 = input1.getYVal();
			double x2 = input2.getYVal();
			double y3 = hidden3.getYVal();
			double y4 = hidden4.getYVal();
			double y5 = hidden5.getYVal();
			double y6 = hidden6.getYVal();
			double y7 = output7.getYVal();

			//calculating error
			 error = yd - y7;

			//Calculating (delta),change inweights and change in theta(threshold)
			double d7 = y7 * (1-y7) *error;
			double w57 = alpha * y5 * d7;
			double w67 = alpha * y6 * d7;
			double dt7 = alpha * -1 * d7;

			double d6 = y6 * (1-y6)*(d7*output7.getConnectedWeight(1));
			double w36 = alpha * y3 * d6;
			double w46 = alpha * y4 * d6;
			double dt6 = alpha * -1 * d6;

			double d5 = y5 * (1-y5)*(d7*output7.getConnectedWeight(0));
			double w35 = alpha * y3 * d5;
			double w45 = alpha * y4 * d5;
			double dt5 = alpha * -1 * d5;

			double d4 = y4 * (1-y4)*(d5*hidden5.getConnectedWeight(1) + d6 * hidden6.getConnectedWeight(1));
			double d3 = y3 * (1-y3)*(d5*hidden5.getConnectedWeight(0) + d6 * hidden6.getConnectedWeight(0));
			double w13 = alpha * x1 * d3;
			double w23 = alpha * x2 * d3;
			double dt3 = alpha * -1 * d3;

			double w14 = alpha * x1 * d4;
			double w24 = alpha * x2 * d4;
			double dt4 = alpha * -1 * d4;

			//time to update weights and threshold values
			double temp = 0.0;
			temp = w13 + hidden3.getConnectedWeight(0);
			hidden3.setConnectedWeight(0, temp);

			temp = w14 + hidden4.getConnectedWeight(0);
			hidden4.setConnectedWeight(0, temp);

			temp = w23 + hidden3.getConnectedWeight(1);
			hidden3.setConnectedWeight(1, temp);

			temp = w24 + hidden4.getConnectedWeight(1);
			hidden4.setConnectedWeight(1, temp);

			temp = w35 + hidden5.getConnectedWeight(0);
			hidden5.setConnectedWeight(0, temp);

			temp = w36 + hidden6.getConnectedWeight(0);
			hidden6.setConnectedWeight(0, temp);

			temp = w45 + hidden5.getConnectedWeight(1);
			hidden5.setConnectedWeight(1, temp);

			temp = w46 + hidden6.getConnectedWeight(1);
			hidden6.setConnectedWeight(1, temp);

			temp = w57 + output7.getConnectedWeight(0);
			output7.setConnectedWeight(0, temp);

			temp = w67 + output7.getConnectedWeight(1);
			output7.setConnectedWeight(1, temp);

			temp = dt3 +  hidden3.getThreshold();
			hidden3.setThreshold(temp);

			temp = dt4 +  hidden4.getThreshold();
			hidden4.setThreshold(temp);

			temp = dt5 +  hidden5.getThreshold();
			hidden5.setThreshold(temp);

			temp = dt6 +  hidden6.getThreshold();
			hidden6.setThreshold(temp);

			temp = dt7 +  output7.getThreshold();
			output7.setThreshold(temp);
			
			if(i % 300 == 0)
			{
				System.out.println("Sample output divide for epoch: " +i);
				
			
				System.out.println(" error squared  at this epoch: " + error * error);
				System.out.println("this is weight w13 at epoch " + i + " : " + hidden3.getConnectedWeight(0));
				System.out.println("this is weight w23 at epoch " + i + " : " + hidden3.getConnectedWeight(1));
				System.out.println("this is weight w14 at epoch " + i + " : " + hidden4.getConnectedWeight(0));
				System.out.println("this is weight w24 at epoch " + i + " : " + hidden4.getConnectedWeight(1));
				System.out.println("this is weight w35 at epoch " + i + " : " + hidden5.getConnectedWeight(0));
				System.out.println("this is weight w45 at epoch " + i + " : " + hidden5.getConnectedWeight(1));
				System.out.println("this is weight w36 at epoch " + i + " : " + hidden6.getConnectedWeight(0));
				System.out.println("this is weight w46 at epoch " + i + " : " + hidden6.getConnectedWeight(1));
				System.out.println("this is weight w57 at epoch " + i + " : " + output7.getConnectedWeight(0));
				System.out.println("this is weight w67 at epoch " + i + " : " + output7.getConnectedWeight(1) + "\n");
				
				System.out.println("this is threshold at 3 " + hidden3.getThreshold());
				System.out.println("this is threshold at 4 " + hidden4.getThreshold());
				System.out.println("this is threshold at 5 " + hidden5.getThreshold());
				System.out.println("this is threshold at 6 " + hidden6.getThreshold());
				System.out.println("this is threshold at 7 " + output7.getThreshold() + "\n");
				
			}
		
			
		}
			i++;
			etotal += (error * error);
			
			if((error * error) < .01)//check to see if the error has reached the point we want it to be
			{
				done = true;
			}
			//just used for counting and changing what the inputs are and expected value
		

		}
		System.out.println("divide this number by 4 to get total amount of epochs " + i);
		System.out.println("error squared value " + error * error);
		System.out.println("this is weight w13 at iteration " + i + " : " + hidden3.getConnectedWeight(0));
		System.out.println("this is weight w23 at iteration " + i + " : " + hidden3.getConnectedWeight(1));
		System.out.println("this is weight w14 at iteration " + i + " : " + hidden4.getConnectedWeight(0));
		System.out.println("this is weight w24 at iteration " + i + " : " + hidden4.getConnectedWeight(1));
		System.out.println("this is weight w35 at iteration " + i + " : " + hidden5.getConnectedWeight(0));
		System.out.println("this is weight w45 at iteration " + i + " : " + hidden5.getConnectedWeight(1));
		System.out.println("this is weight w36 at iteration " + i + " : " + hidden6.getConnectedWeight(0));
		System.out.println("this is weight w46 at iteration " + i + " : " + hidden6.getConnectedWeight(1));
		System.out.println("this is weight w57 at iteration " + i + " : " + output7.getConnectedWeight(0));
		System.out.println("this is weight w67 at iteration " + i + " : " + output7.getConnectedWeight(1) + "\n");
	}

	/**
	 * the getNum static method
	 * this method is used to generate a random number between 
	 * -1.2 to 1.2
	 * @return a double
	 */
	public static double getNum()
	{
		double d = 0.0;
		d = -1.2 + (1.2 - -1.2)* Math.random();

		return d;

	}
}
