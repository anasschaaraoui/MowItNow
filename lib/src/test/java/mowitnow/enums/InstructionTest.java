package mowitnow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class InstructionTest {

    @Test
    void testInstruction() {
        // Test enum values
        assertEquals('G', Instruction.LEFT.getCode());
        assertEquals('D', Instruction.RIGHT.getCode());
        assertEquals('A', Instruction.FORWARD.getCode());
    }

    @Test
    void testParse() {
        // Test valid input
        assertEquals(Instruction.LEFT, Instruction.parse('G'));
        assertEquals(Instruction.RIGHT, Instruction.parse('D'));
        assertEquals(Instruction.FORWARD, Instruction.parse('A'));

        // Test invalid input
        assertNull(Instruction.parse('X')); // Non-existing instruction
    }
}

