package org.aesh.readline.terminal;

import org.aesh.terminal.Device;
import org.aesh.terminal.tty.Capability;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ståle W. Pedersen <stale.pedersen@jboss.org>
 */
public class DeviceTest {

    @Test
    public void testAnsiCapabilities() throws Exception {
        Device device = DeviceBuilder.builder().name("ansi").build();

        assertTrue( device.getBooleanCapability(Capability.auto_right_margin));
        assertFalse( device.getBooleanCapability(Capability.auto_left_margin));

        assertEquals(8, device.getNumericCapability(Capability.max_colors).intValue());
        assertEquals(24, device.getNumericCapability(Capability.lines).intValue());

        assertEquals("^M", device.getStringCapability(Capability.carriage_return));

        String cuf = device.getStringCapability(Capability.parm_right_cursor);
        System.out.println("CUF: "+cuf);

        ArrayList<int[]> out = new ArrayList<>();

        Consumer<int[]> capabilityConsumer = out::add;
        device.puts(capabilityConsumer, Capability.carriage_return);
        assertArrayEquals(new int[]{13}, out.get(0));

        //home
        assertArrayEquals(new int[]{27,91,72}, device.getStringCapabilityAsInts(Capability.key_home));
    }

    @Test
    public void testWindowsCapabilities() throws Exception {
        Device device = DeviceBuilder.builder().name("windows").build();
        assertTrue( device.getBooleanCapability(Capability.move_standout_mode));
        assertEquals(8, device.getNumericCapability(Capability.max_colors).intValue());
        assertEquals(64, device.getNumericCapability(Capability.max_pairs).intValue());

        assertArrayEquals(new int[]{10}, device.getStringCapabilityAsInts(Capability.scroll_forward));
    }

}
