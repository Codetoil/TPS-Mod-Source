package io.github.littoil.litlaunch.launchcommon;

import java.lang.reflect.Method;

public class Command {
	public Command() {}
	public Command(String name, String usage, Method method, Side side)
	{
		this.name = name;
		this.usage = usage;
		this.method = method;
		this.side = side;
	}
	/**
	 * Name of the command.
	 */
	public String name;
	/**
	 * Usage of the command.
	 */
	public String usage;
	/**
	 * the method this command calls when executed.
	 */
	public Method method;
	/**
	 * Side the command should be registered to. E.g. CLIENT gives a client side command.
	 */
	public Side side;
	
	public enum Side
	{
		CLIENT,
		SERVER,
		BOTH;
	}
}