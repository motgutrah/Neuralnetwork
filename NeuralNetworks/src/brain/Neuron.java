/**
 * The Neuron class
 * 
 * this class contains the neuron used in the neural network backwards
 * propagation
 * @author Jonathan Valentin
 */
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
	
	/**
	 * the setThreshold method
	 * this method is used to set the threshold
	 * after it is changed
	 * @param t the new threshold
	 */
	public void setThreshold(double t)
	{
		threshold = t;
	}

	/**
	 * the getThreshold method
	 * this method is used to get the threshold number
	 * that is used to calculate y values
	 * @return a double containg the threshold
	 */
	public double getThreshold()
	{
		return threshold;
	}
	
	/**
	 * the setConnected method
	 * this method is used to set the connections of the neurons
	 * it takes in neurons and the weights of that connection
	 * @param n the neuron that needs to be connected with this neuron
	 * @param pos the position of where the weight of the connection goes
	 * @param weight the value of the weight to be given to the connection
	 */
	public void setConnected(Neuron n,int pos,double weight)
	{
		connected.add(n);
		connectedWeight[pos] = weight;
	}
	
	/**
	 * the getConnected method
	 * this method gets the connected neurons and returns them
	 * @return an arrayList of neurons that are connected to this neuron
	 */
	public ArrayList<Neuron> getConnected()
	{
		return connected;
	}

	/**
	 * the getConnectedWeight method
	 * this method returns the value of the weight of a certain
	 * connection given
	 * @param pos the position of the connection 
	 * @return the weight of the connection
	 */
	public double getConnectedWeight(int pos)
	{
		return connectedWeight[pos];
	}
	
	/**
	 * the setConnectedWeight method
	 * this method is to set the weight of the connection if it has changed
	 * @param pos the position that connection is in the array
	 * @param val the new value of the weight
	 */
	public void setConnectedWeight(int pos, double val)
	{
		connectedWeight[pos] = val; 
	}
	
	/**
	 * the setYVal method
	 * this method sets what the yvalue of the neuron is
	 * this method also doubles as the way to set the value of the input neurons
	 * @param y the new y value or input we want
	 */
	public void setYVal(double y)
	{
		yVal = y;
	}
	
	/**
	 * the getYVal method
	 * this method returns the yval or the input
	 * depending if the neuron is an input neuron or otherwhise 
	 * @return the y value/input value
	 */
	public double getYVal()
	{
		return yVal;
	}
	
	/**
	 * the calculateYVal method
	 * this method calculates the Y value of the neuron
	 * has a check just in case it was used on a input neuron
	 */
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
