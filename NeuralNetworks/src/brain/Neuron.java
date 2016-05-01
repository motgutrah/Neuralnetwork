package brain;

import java.util.ArrayList;
public class Neuron {

	double threshold;
	ArrayList<Neuron> connected;
	double[] connectedWeight;
	boolean isInput;
	double yVal;
	public Neuron(boolean i)
	{

		threshold = -1.2 + (1.2 - -1.2)* Math.random();
		connected = new ArrayList<Neuron>();
		connectedWeight = new double[10];
		isInput = i;//this determines if its in the input layer or not(true for input layer false for everything else
		yVal = 0;//this will either be the value of the input or the value of the y
		
	}

	public void setThreshold(double t)
	{
		threshold = t;
	}

	public double getThreshold()
	{
		return threshold;
	}

	public void setConnected(Neuron n,int pos,double weight)
	{
		connected.add(n);
		connectedWeight[pos] = weight;
	}

	public ArrayList<Neuron> getConnected()
	{
		return connected;
	}

	public double getConnectedWeight(int pos)
	{
		return connectedWeight[pos];
	}
	
	public void setConnectedWeight(int pos, double val)
	{
		connectedWeight[pos] = val; 
	}
	public void setYVal(double y)
	{
		yVal = y;
	}
	public double getYVal()
	{
		return yVal;
	}

	public void calculateYVal()
	{
		Double d = 0.0;
		if(!isInput)
		{
			d = (connected.get(0).getYVal() * connectedWeight[0]) + (connected.get(1).getYVal() * connectedWeight[1] - threshold);
			d = (1 /(1 + Math.pow(Math.E, -d)));
		}
		yVal = d;
	}



}
