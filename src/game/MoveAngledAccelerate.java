/*
 * MoveAngledAccelerate.java
 *
 * Created on 19 November 2007, 00:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author Mark
 */
public class MoveAngledAccelerate extends Move
{
    private double newAngle;
    private double angularAcceleration;
    private double friction;
    private double brake;
    
    private double angularFriction;
    
    public Location goUp(Location location)
    {
	this.setVelocity(getVelocity()+getAcceleration());
	location = changedAccelerate(location);
	return location;
    }
    
    private Location changedAccelerate(Location location)
    {
	
	setXVelocity(Math.cos(getAngle())*getVelocity());
	setYVelocity(Math.sin(getAngle())*getVelocity());
	
	location = super.go(location);
	return location;
    }
    
    public Location goDown(Location location)
    {
	
	this.setVelocity(getVelocity()-getAcceleration());
	location = changedAccelerate(location);
	return location;
	
    }
    
    public Location turn(Location location)
    {
	
	double angle = getAngle();
	double angularVelocity= getAngularVelocity();
	double velocity = getVelocity();
	double xVelocity = getXVelocity();
	double yVelocity = getYVelocity();
	
	angle+=angularVelocity;
	angle = clampAngle(angle);
	xVelocity= Math.cos(angle)*velocity;
	yVelocity = Math.sin(angle)*velocity;
	
	setAngle(angle);
	setAngularVelocity(angularVelocity);
	setVelocity(velocity );
	setXVelocity(xVelocity );
	setYVelocity(yVelocity );
	
//	location = super.go(location);
	return location;
    }
    
    /**decreases angular velocity
     (accelerates in an anti clockwise direction)
     */
    public Location goLeft(Location location)
    {
	
	
	setAngularVelocity(getAngularVelocity()- getAngularAcceleration());
	if (getAngularVelocity() <= -getAngularMaxVelocity())
	{
	    setAngularVelocity(-getAngularMaxVelocity());;
	}
	
	location =turn(location);
	return location;
	
    }
    
    /**increases angular velocity
     (accelerates in a clockwise direction)
     */
    public Location goRight(Location location)
    {
	
	
	setAngularVelocity(getAngularVelocity()+ getAngularAcceleration());
	if (getAngularVelocity() >= getAngularMaxVelocity())
	{
	    setAngularVelocity(getAngularMaxVelocity());;
	}
	location =turn(location);
	return location;
	
    }
    
    public Location go(Location location)
    {
	
	double velocity = getVelocity();
	double friction = getFriction();
	double angularVelocity = getAngularVelocity();
	double angularFriction= getAngularFriction();
	
	
	if (velocity>0.0)
	{
	    velocity -= this.friction;
	    if (velocity<0.0)
		velocity =0.0;
	}
	else
	{
	    velocity += this.friction;
	    if (velocity>0.0)
		velocity =0.0;
	}
	
	if (angularVelocity>0.0)
	{
	    angularVelocity -= this.angularFriction;
	    if (angularVelocity<0.0)
		angularVelocity =0.0;
	}
	else
	{
	    angularVelocity += this.angularFriction;
	    if (angularVelocity>0.0)
		angularVelocity =0.0;
	}
	setAngularVelocity(angularVelocity);
	
	double angle = getAngle();
	double xVelocity = getXVelocity();
	double yVelocity = getYVelocity();
	angle+=angularVelocity;
	angle = clampAngle(angle);
	setAngle(angle);
	
	xVelocity= Math.cos(angle)*velocity;
	yVelocity = Math.sin(angle)*velocity;
	
	setVelocity(velocity );
	setXVelocity(xVelocity );
	setYVelocity(yVelocity );
	
	location = super.go(location);
	return location;
	
    }
    
    private double clampAngle(double angle)
    {
	if (angle>0)
	{
	    while (angle>Math.PI)
	    {
		angle = angle - (2*Math.PI);
	    }
	}
	if(angle<0)
	{
	    while(angle<(-Math.PI))
	    {
		angle = angle + (2*Math.PI);
	    }
	}
	return angle;
    }
    /** Creates a new instance of MoveAngledAccelerate */
    public MoveAngledAccelerate()
    {
	
    }
    
    public void setAngularAcceleration(double angularAcceleration)
    {
	this.angularAcceleration = angularAcceleration;
    }
    
    public double getAngularAcceleration()
    {
	return angularAcceleration;
    }
    
    public void setFriction(double friction)
    {
	this.friction = friction;
    }
    
    public double getFriction()
    {
	return friction;
    }
    
    public double getBrake()
    {
	return brake;
    }
    
    public void setBrake(double brake)
    {
	this.brake = brake;
    }
    
    public double getAngularFriction()
    {
	return angularFriction;
    }
    
    public void setAngularFriction(double angularFriction)
    {
	this.angularFriction = angularFriction;
    }
    
}