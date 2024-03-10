package mowitnow.models;

import mowitnow.common.InstructionExecutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command pattern: Encapsulates movement instructions.
 */
public class Command {
	private static final Logger logger = LogManager.getLogger(Command.class);

	private InstructionExecutor executor;

	/**
	 * Constructs a command with the specified executor.
	 *
	 * @param executor The executor to perform the command.
	 */
	public Command(InstructionExecutor executor) {
		this.executor = executor;
	}

	/**
	 * Executes the command using the specified tondeuse.
	 *
	 * @param tondeuse The tondeuse to execute the command on.
	 */
	public void execute(Tondeuse tondeuse) {
		logger.debug("Executing command");
		executor.execute(tondeuse);
		logger.debug("Command executed successfully");
	}
}
