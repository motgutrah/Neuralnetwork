package brain;

public class Main {
	public static void main(String[] args)
	{
		double alpha = .1;//alpha constant
		double yd = 0;//expected value
		boolean done = false;//used to find out when we will stop
		Neuron input1 = new Neuron(true);//input layer 1st node
		Neuron input2 = new Neuron(true);//input layer 2nd node
		Neuron hidden3 = new Neuron(false);//hidden layer 1 1st node
		Neuron hidden4 = new Neuron(false);//hidden layer 1 2nd node
		Neuron hidden5 = new Neuron(false);//hidden layer 2 1st node
		Neuron hidden6 = new Neuron(false);//hidden layer 2 2nd node
		Neuron output7 = new Neuron(false);//output layer node
		//Connecting all the neurons
		hidden3.setConnected(input1, 0, getNum());
		hidden3.setConnected(input2, 0, getNum());
		hidden4.setConnected(input1, 0, getNum());
		hidden4.setConnected(input1, 0, getNum());
		hidden5.setConnected(hidden3, 0, getNum());
		hidden5.setConnected(hidden4, 0, getNum());
		hidden6.setConnected(hidden3, 0, getNum());
		hidden6.setConnected(hidden4, 0, getNum());
		output7.setConnected(hidden5, 0, getNum());
		output7.setConnected(hidden6, 0, getNum());

		int i = 0;

		while (done == false)//while loop to go until desired error is met
		{
			//setting the input values and desired y 
			if(i % 4 == 0)
			{
				input1.setYVal(1);
				input2.setYVal(1);
				yd = 0;
			}
			if( i % 3 == 1)
			{
				input1.setYVal(1);
				input2.setYVal(0);
				yd = 1;
			}

			if(i % 3 == 2)
			{
				input1.setYVal(0);
				input2.setYVal(1);
				yd = 1;
			}
			if( i % 3 == 3)
			{
				input1.setYVal(0);
				input2.setYVal(0);
				yd = 0;
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
			double error = yd - y7;

			//Calculating (delta),change inweights and change in theta(threshold)
			double d7 = y7 * (1-y7)*error;
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

			if(Math.pow(error, 2) < .01)//check to see if the error has reached the point we want it to be
			{
				done = true;
			}
			i++;//just used for counting
			System.out.println(Math.pow(error, 2));
			System.out.println(i);
		}
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
