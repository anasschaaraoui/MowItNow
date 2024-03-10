package mowitnow.common;

import mowitnow.models.Tondeuse;

//Functional interface for executing instructions
@FunctionalInterface
public interface InstructionExecutor {
	void execute(Tondeuse tondeuse);
}